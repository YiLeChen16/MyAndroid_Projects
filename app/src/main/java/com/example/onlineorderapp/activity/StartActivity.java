package com.example.onlineorderapp.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.os.Handler;
import android.os.Message;
import android.view.WindowManager;
import android.widget.ImageView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.onlineorderapp.R;

//启动页
public class StartActivity extends AppCompatActivity {


    //声明控件
    private ImageView iv_img;
    private ImageView iv_logo;
    private LottieAnimationView lottieAnimationView;


    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            if(msg.what == 1){
                //跳转到登录注册页面
                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
            }
            return true;
        }
    });
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // 设置全屏标志位
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_start_page_layout);
        iv_img = findViewById(R.id.iv_img);
        iv_logo = findViewById(R.id.iv_logo);
        lottieAnimationView = findViewById(R.id.lottie);

        //设置动画
        iv_img.animate().translationY(-2000).setDuration(1500).setStartDelay(5000);
        iv_logo.animate().translationY(1800).setDuration(1000).setStartDelay(5000);
        lottieAnimationView.animate().translationY(1600).setDuration(1000).setStartDelay(5000);

        //开启线程等待5秒，等待动画播放完毕跳转到登录注册页面
        startThread();
    }

    private void startThread(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                //发送信息到主线程跳转Activity
                Message msg = Message.obtain();
                msg.what = 1;
                handler.sendMessage(msg);
            }
        }).start();

    }


}