package com.yichen.mapreduce.combineTextInputFormat;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * KEYIN,reduce阶段输入的key的类型：Text
 * VALUEIN,reduce阶段输入value类型：IntWritable
 * KEYOUT,reduce阶段输出的Key类型：Text
 * VALUEOUT,reduce阶段输出的value类型：IntWritable
 */

public class WordCountReducer extends Reducer<Text, IntWritable, Text, IntWritable> {
    private IntWritable outV = new IntWritable(1);

    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Reducer<Text, IntWritable, Text, IntWritable>.Context context) throws IOException, InterruptedException {

        int sum = 0;
        //yichen,(1,1)
        //进行累加
        for (IntWritable value : values) {
            sum += value.get();
        }
        outV.set(sum);

        //写出
        context.write(key, outV);
    }
}
