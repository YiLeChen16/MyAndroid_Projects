package com.example.onlineorderapp.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.onlineorderapp.R;
import com.example.onlineorderapp.bean.MyMessage;
import com.example.onlineorderapp.bean.User;
import com.example.onlineorderapp.utils.DBUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//登录注册界面
public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    EditText ed_phoneNumber;
    EditText ed_password;
    Button btn_login;
    Button btn_registered;

    ArrayList<User> userArrayList;

    //用于处理子线程的Toast信息
    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            MyMessage myMessage = (MyMessage) msg.obj;
            Toast.makeText(LoginActivity.this,myMessage.getMsg(), Toast.LENGTH_SHORT).show();
            return false;
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 设置全屏标志位
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login_layout);
        init();
    }

    //初始化控件
    private void init() {
        //找到控件
        ed_phoneNumber = findViewById(R.id.ed_phone_number);
        ed_password = findViewById(R.id.ed_password);
        btn_login = findViewById(R.id.btn_login);
        btn_registered = findViewById(R.id.btn_registered);

        //设置监听事件
        btn_login.setOnClickListener(this);
        btn_registered.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        //获取输入的账号和密码
        String phoneNumber = ed_phoneNumber.getText().toString();
        String password = ed_password.getText().toString();
        switch (v.getId()) {
            case R.id.btn_login: {
                login(phoneNumber, password);
                break;
            }
            case R.id.btn_registered: {
                registered(phoneNumber,password);
                break;
            }
        }

    }

    //点击登录按钮，判断数据库中是否已存在输入的账号，若存在匹配密码
    private void login(String phoneNumber, String password) {
        //创建新线程用于进行数据库的相关操作
        new Thread(new Runnable() {
            @Override
            public void run() {
                Message msg = new Message();
                //查询数据库中的所有数据，并获取返回的集合
                try {
                    userArrayList = DBUtils.query();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                if (userArrayList == null) {
                    Log.d("tag", "数据库中没有数据");
                    msg.obj = new MyMessage("登录失败！账号不存在");
                    handler.sendMessage(msg);
                    return;
                }
                //遍历集合
                for (User user : userArrayList) {
                    String phoneNumber1 = user.getPhoneNumber();
                    String password1 = user.getPassword();
                    if (phoneNumber1.equals(phoneNumber)) {
                        //账号在数据库中存在
                        //匹配密码
                        if (password1.equals(password)) {
                            //密码正确
                            Log.d("tag", "登陆成功！");
                            msg.obj = new MyMessage("登陆成功");
                            handler.sendMessage(msg);
                            //跳转到主界面
                            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                            startActivity(intent);
                            //销毁当前Activity
                            finish();
                        } else {
                            //密码错误
                            Log.d("tag", "密码错误");
                            msg.obj = new MyMessage("登陆失败！密码错误");
                            handler.sendMessage(msg);
                        }
                        return;
                    }
                }
                Log.d("tag", "账号不存在");
                msg.obj = new MyMessage("登陆失败！账号不存在");
                handler.sendMessage(msg);
            }
        }).start();


    }

    //点击注册按钮，判断数据库中是否已存在输入的账号
    private void registered(String phoneNumber,String password){

        //点击注册按钮，判断密码录入是否规范
        boolean judge = judge(password);
        if(!judge){
            return;
        }
        //开启新线程进行数据库相关操作
       new Thread(new Runnable() {
           @Override
           public void run() {
               Message msg = new Message();
               try {
                   //查询数据库中的所有数据
                   userArrayList = DBUtils.query();
               } catch (SQLException e) {
                   e.printStackTrace();
               }
               //遍历查询到的集合
               if(userArrayList != null){
                   for (User user : userArrayList) {
                       String phoneNumber1 = user.getPhoneNumber();
                       if(phoneNumber1.equals(phoneNumber)){
                           Log.d("tag","账号已存在");
                           msg.obj = new MyMessage("注册失败！账号已存在");
                           handler.sendMessage(msg);
                           return;
                       }
                   }
               }
               //将数据插入数据库中
               try {
                   boolean insert = DBUtils.insert(phoneNumber, password);
                   if(insert){
                       Log.d("tag","注册成功！");
                       msg.obj = new MyMessage("注册成功！请重新登录！");
                       handler.sendMessage(msg);
                   }
                   else{
                       Log.d("tag","数据插入失败，注册失败！");
                       msg.obj = new MyMessage("数据插入失败，注册失败！");
                       handler.sendMessage(msg);
                       return;
                   }
               } catch (SQLException e) {
                   e.printStackTrace();
               }
           }
       }).start();

    }

    //密码规范判断：
    /*
    点击注册按钮后，判断注册密码是否符合规范
    密码规范如下：包含大小写字母和数字，至少为6位
    * */
    private boolean judge(String password){
        //判断密码长度
        if(password.length() < 6){
            Toast.makeText(this, "密码长度小于6，请重新输入！", Toast.LENGTH_SHORT).show();
            return false;
        }
        //书写符合要求的正则表达式
        String regex = "(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]+";
        //获取正则表达式的对象
        Pattern compile = Pattern.compile(regex);
        //获取文本匹配器的对象
        Matcher matcher = compile.matcher(password);
        //开始匹配
        if(matcher.matches()){
            return  true;
        }else{
            Toast.makeText(this, "密码录入不规范，请重新录入！密码必须包含大小写字母和数字", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

}