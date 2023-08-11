package com.example.mychatrobot;
import android.os.Build;
import android.os.Message;
import android.util.Log;
import androidx.annotation.RequiresApi;

import com.example.mychatrobot.Bean.JsonResult;
import com.google.gson.Gson;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.net.ssl.HttpsURLConnection;

//此线程用于向服务器请求数据
public class MyThread extends Thread {
    private String sendMessage;
    private Message msg;
    //创建StingBuilder对象用于拼接读取出来的JSON数据
    private StringBuilder sb = new StringBuilder();
    //将发送的消息传递过来
    public MyThread(String sendMessage) {
        this.sendMessage = sendMessage;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void run() {
        super.run();
        try {
            //将URL资源传入URL类的构造方法中将字符串转为URL对象
            URL url = new URL(MainActivity.WEB_SITE + "?text=" + sendMessage);
            //打开https连接
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            //设置请求方式
            conn.setRequestMethod("GET");
            //设置超时时间
            conn.setConnectTimeout(5000);
            //获取状态码
            int responseCode = conn.getResponseCode();
            //判断请求结果
            if (responseCode == 200) {
                Log.d("tag", "数据请求成功！");
                //获取服务器返回的输入流对象
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                //循环读取输入流中的数据
                String line;
                while ((line = br.readLine()) != null){
                    sb.append(line);
                    sb.append("\n");
                }
                //释放资源
                br.close();
                Log.d("tag",sb.toString());
                //用Gson解析数据，并打包为一个对象，利用handler机制发送回主线程
                Gson gson = new Gson();
                JsonResult jsonResult = gson.fromJson(sb.toString(), JsonResult.class);
                //将返回的JSON数据利用handler机制返回给主线程
                msg = new Message();
                msg.obj = jsonResult;
                msg.what = responseCode;
                MainActivity.handler.sendMessage(msg);
            } else if(responseCode == 400){
                Log.d("tag","输入参数错误导致失败");
                msg = new Message();
                msg.what = responseCode;
                MainActivity.handler.sendMessage(msg);
            } else if(responseCode == 500){
                Log.d("tag","服务器端出错导致失败");
                msg = new Message();
                msg.what = responseCode;
                MainActivity.handler.sendMessage(msg);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
