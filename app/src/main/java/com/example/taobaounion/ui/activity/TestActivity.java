package com.example.taobaounion.ui.activity;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.taobaounion.R;
import com.example.taobaounion.utils.LogUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestActivity extends AppCompatActivity {

    private BufferedReader bufferedReader;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        String keyword = "程序员早餐";
        String result = "";
        //创建一个URL对象
        try {
            URL searchUrl = new URL("https://shop.sunofbeach.net/search?keyword=" + keyword);
            //连接上这个网址
            URLConnection urlConnection = searchUrl.openConnection();
            //创建一个对象去读取网址中的内容
            bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            //获取正则表达式的对象
            String regex = "\\{searchResult:\\[(.|\\n)*\\]keyword:" + keyword +"\\}";
            // 匹配searchResult中的内容
            Pattern pattern = Pattern.compile(regex);
            //利用循环每次爬取1行
            String line;
            while ((line = bufferedReader.readLine()) != null){
                //获取文本匹配器的对象
                Matcher matcher = pattern.matcher(line);
                while (matcher.find()){
                    String group = matcher.group();
                    result = result + group;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(bufferedReader != null){
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        LogUtils.d(this,"result-->" + result);
    }
}
