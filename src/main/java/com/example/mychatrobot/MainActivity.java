package com.example.mychatrobot;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mychatrobot.Adater.MyAdapter;
import com.example.mychatrobot.Bean.ChatBean;
import com.example.mychatrobot.Bean.JsonResult;
import com.example.mychatrobot.Bean.Result;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, View.OnKeyListener {

    //创建Handler对象以处理子线程发送过来的信息，并进行UI更新
    protected static Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            //接收数据并更新UI
            if (msg.what == 200) {
                JsonResult jsonResult = (JsonResult) msg.obj;
                Result result = jsonResult.getResult();
                list.add(new ChatBean(result.getDisplayText(), ChatBean.RECEIVE));
                //更新列表数据
                adapter.notifyDataSetChanged();
                // 滚动到最新的位置
                listView.setSelection(list.size() - 2);
                return true;
            } else if (msg.what == 400 || msg.what == 500) {
                return false;
            }
            return false;
        }
    });

    //声明控件
    @SuppressLint("StaticFieldLeak")
    protected static TextView textView;
    @SuppressLint("StaticFieldLeak")
    private static ListView listView;
    private EditText editText;
    private Button button;

    //声明变量
    private static List<ChatBean> list = new ArrayList<>();
    private String tag = "tag";
    private static String[] welcomeText;
    private static String sendMessage;
    private static MyAdapter adapter;

    //接口地址
    protected static final String WEB_SITE = "https://api.oioweb.cn/api/ai/chat";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 隐藏标题栏
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        //初始化主界面控件
        init();

    }

    //初始化控件
    private void init() {
        textView = findViewById(R.id.tv_title);
        listView = findViewById(R.id.list);
        editText = findViewById(R.id.ed_input);
        button = findViewById(R.id.btn_send);

        //加载欢迎语句
        welcomeText = getResources().getStringArray(R.array.welcome_text);

        //为按钮设置点击监听事件
        button.setOnClickListener(this);
        editText.setOnKeyListener(this);

        //随机生成一条欢迎语句
        Random r = new Random();
        int index = r.nextInt(welcomeText.length);
        list.add(new ChatBean(welcomeText[index], ChatBean.RECEIVE));

        //创建适配器对象,并将适配数据传递进去
        adapter = new MyAdapter(list, this);
        //为ListView设置适配器
        listView.setAdapter(adapter);
    }

    //点击按钮发送信息
    @Override
    public void onClick(View v) {
        // 隐藏键盘
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(editText.getWindowToken(), 0);
        sendData();
    }

    //按下enter键发送信息
    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {
            // 隐藏键盘
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(editText.getWindowToken(), 0);
            //发送信息给服务器
            sendData();
            return true;
        }
        return false;
    }

    //处理消息，将信息发送到服务器并获取服务器返回的JSON数据
    private void sendData() {
        //获取输入框的内容
        sendMessage = editText.getText().toString();
        //清空输入框
        editText.setText("");
        if (sendMessage.equals("")) {
            Log.d(tag, "发送信息为空");
            Toast.makeText(this, "发送的消息为空！", Toast.LENGTH_SHORT).show();
            return;
        }
        //将信息中的空格和回车符进行替换,trim()用于去除字符串两端的空白字符
        sendMessage = sendMessage.replace(" ", "").replace("\n", "").trim();
        //将信息封装为一个条目对象
        ChatBean chatBean = new ChatBean(sendMessage, ChatBean.SEND);
        //将条目对象添加到集合中
        list.add(chatBean);
        //更新列表数据
        adapter.notifyDataSetChanged();
        //将信息发送到服务器中,并获取服务器返回的数据
        sendAndGetDataFromServer(sendMessage);
    }

    //将信息发送到服务器中,并获取服务器返回的数据,为耗时操作，在子线程中进行
    private void sendAndGetDataFromServer(String sendMessage) {
        //创建子线程
        MyThread myThread = new MyThread(sendMessage);
        //开启子线程
        myThread.start();

    }
}