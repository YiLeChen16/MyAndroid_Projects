package com.example.mychatrobot;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

//线程池类
public class MyThreadPool {
    //创建线程池
    public static  ThreadPoolExecutor pool = new ThreadPoolExecutor(
            3,//核心线程数
            6,//最大线程数
            20,//空闲线程最大存活时间
            TimeUnit.SECONDS,//时间单位
            new ArrayBlockingQueue<>(3),//任务队列
            Executors.defaultThreadFactory(),//创建线程工厂
            new ThreadPoolExecutor.AbortPolicy()//任务拒绝策略
    );
}
