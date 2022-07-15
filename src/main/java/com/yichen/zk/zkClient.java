package com.yichen.zk;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class zkClient {

    private String connectString = "hadoop102:2181,hadoop103:2181,hadoop104:2181";//此处不能有任何空格
    private int sessionTimeout = 2000;
    private ZooKeeper zkClient;

    @Before
    public void init() throws IOException {


        zkClient = new ZooKeeper(connectString, sessionTimeout, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {

//                System.out.println("-------------------");
//                List<String> children = null;
//                try {
//                    children = zkClient.getChildren("/", true);
//                    for (String child : children) {
//                        System.out.println(child);
//                    }
//                    System.out.println("-------------------");
//                } catch (KeeperException e) {
//                    e.printStackTrace();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//

            }
        });
    }

    @Test
    public void create() throws InterruptedException, KeeperException {
        String nodeCreated = zkClient.create("/yichen", "loser".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
    }

    @Test
    public void getChildren() throws InterruptedException, KeeperException {
        List<String> children = zkClient.getChildren("/", true);

        for (String child : children) {
            System.out.println(child);
        }

        //加入一个延时
        Thread.sleep(Long.MAX_VALUE);
    }
    @Test
    public void exist() throws InterruptedException, KeeperException {

        Stat exists = zkClient.exists("/yichen", false);

        System.out.println(exists==null?"not exist":"exist");
    }
}
