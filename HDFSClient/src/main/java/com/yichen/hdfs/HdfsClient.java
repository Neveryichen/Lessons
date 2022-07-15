package com.yichen.hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;

public class HdfsClient {

    private FileSystem fs;

    @Before
    public void init() throws URISyntaxException, IOException, InterruptedException {
        //连接的集群nn地址
        URI uri = new URI("hdfs://hadoop102:8020");
        //创建一个配置文件
        Configuration configuration = new Configuration();
        //用户信息
        String user = "yichen";
        //获取到客户端对象
        fs = FileSystem.get(uri, configuration, user);

    }

    @After
    public void close() throws IOException {
        //关闭资源
        fs.close();
    }

    @Test
    public void testmkdir() throws IOException {
        //执行相关命令操作
        fs.mkdirs(new Path("/ideaput"));
    }


    //上传

    /**
     * 参数优先级
     * hdfs-default.xml=>hdfs-site.xml=>在项目资源目录下的配置文件 =>代码里面的配置（最高优先级）
     *
     * @throws IOException
     */
    @Test
    public void testPUT() throws IOException {
        //参数解读：参数1：表示删除最原始的数据；参数2：表示是否允许覆盖；参数3：元数据路径；参数4：目的地路径
        fs.copyFromLocalFile(true, true, new Path("D:\\asd.txt"), new Path("hdfs://hadoop102/ideaput"));
    }

    //文件下载
    @Test
    public void testGet() throws IOException {
        //参数解读：参数1：源文件是否删除；参数2：源文件路径HDFS；参数3：目标地址路径WIN；参数4：是否开启文件校验
        fs.copyToLocalFile(true, new Path("/xiyou/huaguoshan"), new Path("D:\\"), true);
    }

    //删除操作
    @Test
    public void testRm() throws IOException {
        //参数解读：参数1：要删除的路径;参数2：是否递归删除
        //删除文件
        fs.delete(new Path("/jdk-8u212-linux-x64.tar.gz"), false);
        //删除空目录
        fs.delete(new Path("/xiyou"), false);
        //删除非空目录
        fs.delete(new Path("/jinguo"), true);
    }

    //文件的更名和移动
    @Test
    public void testmv() throws IOException {
        //参数解读：参数1：源文件路径；参数2：目标文件路径
        //文件的更名
        fs.rename(new Path("/wcinput/word.txt"), new Path("/wcinput/yichen.txt"));
        //文件的移动与更名
        fs.rename(new Path("/wcinput/yichen.txt"), new Path("/cls.txt"));
        //目录的更名
        fs.rename(new Path("/wcinput"), new Path("/output"));
    }

    //获取文件详情
    @Test
    public void fileDetail() throws IOException {


        //获取所有文件信息
        RemoteIterator<LocatedFileStatus> listFiles = fs.listFiles(new Path("/"), true);

        //遍历文件
        while (listFiles.hasNext()) {
            LocatedFileStatus fileStatus = listFiles.next();

            System.out.println("=============" + fileStatus.getPath() + "==============");
            System.out.println(fileStatus.getPermission());
            System.out.println(fileStatus.getOwner());
            System.out.println(fileStatus.getGroup());
            System.out.println(fileStatus.getLen());
            System.out.println(fileStatus.getModificationTime());
            System.out.println(fileStatus.getReplication());
            System.out.println(fileStatus.getBlockSize());
            System.out.println(fileStatus.getPath().getName());

            //获取块信息
            BlockLocation[] blockLocations = fileStatus.getBlockLocations();

            System.out.println(Arrays.toString(blockLocations));
        }


    }

    //判断是文件夹还是文件
    @Test
    public void testFiles() throws IOException {

        FileStatus[] listStatus = fs.listStatus(new Path("/"));

        for (FileStatus status : listStatus) {
            if (status.isFile()) {
                System.out.println("文件：" + status.getPath().getName());
            } else {
                System.out.println("路径：" + status.getPath().getName());
            }
        }
    }


}
