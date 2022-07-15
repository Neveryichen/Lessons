package com.yichen.mapreduce.mapJoin;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class MapDriver {
    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException, URISyntaxException {
    //1.获取job
    Configuration conf = new Configuration();
    Job job = Job.getInstance(conf);

    //2.设置jar包路径
    job.setJarByClass(MapDriver.class);

    //3.关联mapper和reducer
    job.setMapperClass(MapMapper.class);

    //4.设置map输出的kv类型
    job.setMapOutputKeyClass(Text.class);
    job.setMapOutputValueClass(NullWritable.class);

    //5.设置最终输出的kv类型
    job.setOutputKeyClass(Text.class);
    job.setOutputValueClass(NullWritable.class);

    //加载缓存数据
        job.addCacheFile(new URI("file:///D:/Input/input/tablecache/pd.txt"));
    //Map端Join的逻辑不需要Reduce阶段
        job.setNumReduceTasks(0);
    //6.设置输入路径和输出路径
    FileInputFormat.setInputPaths(job, new Path("D:\\Input\\input\\inputtable2"));
    FileOutputFormat.setOutputPath(job, new Path("D:\\Input\\output\\output6"));
    //7.提交job
    boolean result = job.waitForCompletion(true);
    System.exit(result ? 0 : 1);
    }
}
