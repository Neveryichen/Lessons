package com.yichen.case2;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class DistributedLock {

    private final String connectString = "hadoop102:2181,hadoop103:2181,hadoop104:2181";
    private final int sessionTimeout = 2000;
    private final ZooKeeper zk;

    private CountDownLatch connectLatch = new CountDownLatch(1);
    private CountDownLatch waitLatch = new CountDownLatch(1);

    //监听前一个节点路径
    private String waitPath;
    private String currentMode;

    public DistributedLock() throws IOException, InterruptedException, KeeperException {
        //获取连接
        zk = new ZooKeeper(connectString, sessionTimeout, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                // connectLatch 如果连接上zk，则可以释放
                if (watchedEvent.getState() == Event.KeeperState.SyncConnected) {
                    connectLatch.countDown();
                }

                //waitLatch 也需要释放
                if (watchedEvent.getType() == Event.EventType.NodeDeleted && watchedEvent.getPath().equals(waitPath)) {
                    waitLatch.countDown();
                }
            }
        });

        //等待zookeeper真正连接上后再继续运行，保证程序健壮性
        connectLatch.await();

        //判断根节点/locks是否存在
        Stat stat = zk.exists("/locks", false);

        if (stat == null) {
            //创建一下根节点
            zk.create("/locks", "locks".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        }
    }

    //对zk加锁
    public void zklock() {
        //创建对应的临时带序号结点
        try {
            currentMode = zk.create("/locks/" + "seq-", null, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);

            //判断创建的节点是否是最小的序号节点。如果是则获取到锁，如果不是则监听前一个序号的节点

            List<String> children = zk.getChildren("/locks", false);

            //如果只有一个值，则直接获取锁，如果有多个节点，则需要判断谁最小
            if (children.size() == 1) {
                return;
            } else {
                Collections.sort(children);

                //获取节点名称 seq-00000000
                String thisNode = currentMode.substring("/locks/".length());
                //通过seq-000000000获取该节点在children集合的位置
                int index = children.indexOf(thisNode);

                //判断
                if (index == -1) {
                    System.out.println("数据异常");
                } else if (index == 0) {
                    //表示只有一个节点，可以获取锁
                    return;
                } else {

                    //多个节点，需要监听前一个节点变化
                    waitPath = "/locks/" + children.get(index - 1); //获取前一个节点
                    zk.getData(waitPath, true, null);

                    //等待监听
                    waitLatch.await();
                    return;
                }
            }
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    //解锁
    public void unZklock() {
        //删除节点
        try {
            zk.delete(currentMode, -1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        }
    }
}
