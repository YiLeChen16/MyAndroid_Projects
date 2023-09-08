package com.example.onlineorderapp.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.onlineorderapp.R;
import com.example.onlineorderapp.adapter.MyStoreListAdapter;
import com.example.onlineorderapp.bean.Store;
import com.example.onlineorderapp.utils.Constant;
import com.example.onlineorderapp.utils.JsonParse;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

//APP主界面
public class MainActivity extends AppCompatActivity{

    private TextView tv_go_back;//返回键
    private TextView tv_title;//标题
    private ImageView iv_adImage;//用于放置广告图片的控件
    private RecyclerView recyclerView;//店铺列表
    private MyHandler myHandler;//异步线程消息处理类的对象
    private static final int MSG_STORE_OK = 1;//表示服务器请求数据成功
    private static final int MSG_STORE_NOT_OK = 0;//表示服务器请求数据不成功
    private MyStoreListAdapter adapter;//店铺列表适配器
    public static ArrayList<Store> storeDataList;//店铺列表数据
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 设置全屏标志位
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        myHandler = new MyHandler();
        //创建适配器的对象
        adapter = new MyStoreListAdapter(this);
        initData();//初始化数据
        init();//初始化界面控件
    }

    //利用OKHTTP库从服务器中获取数据
    private void initData(){
        //创建okhttpClient类的对象
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url(Constant.WEB_SITE + Constant.REQUEST_STORE_URL).build();
        //使用okhttpClient类的对象调用newCall()方法获取到Call类的对象
        Call call = okHttpClient.newCall(request);
        //用Call类的对象调用enqueue()方法开启异步线程访问网络
        call.enqueue(new Callback() {
            //网络请求失败
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Log.d("tag","请求数据失败" + e.getMessage());
                //创建Message对象
                Message msg = new Message();
                //封装消息
                msg.what = MSG_STORE_NOT_OK;
                //发送消息
                myHandler.sendMessage(msg);
            }
            //网络请求成功
            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {

                //获取返回的json数据并将响应体中的数据转为字符串
                String json = response.body().string();
                //创建Message对象
                Message msg = new Message();
                msg.obj = json;//将数据封装在消息对象的obj中
                msg.what = MSG_STORE_OK;//封装请求成功标识码
                //发送消息
                myHandler.sendMessage(msg);
            }
        });
    }

    //创建Handler的子类用于处理异步线程的消息
    class MyHandler extends Handler{
        //处理消息
        @Override
        public void dispatchMessage(@NonNull Message msg) {
            super.dispatchMessage(msg);
            //判断服务器数据是否请求成功
            switch (msg.what){
                case MSG_STORE_OK:{
                    //数据请求成功
                    //解析数据
                    String json = (String) msg.obj;
                    storeDataList = JsonParse.getData(json);
                    //将数据设置到列表适配器中
                    adapter.setData(storeDataList);
                    break;
                }
                case MSG_STORE_NOT_OK:{
                    Log.d("tag","服务器数据请求失败");
                    break;
                }
            }
        }
    }

    //初始化控件
    private void init(){
        tv_go_back = findViewById(R.id.tv_back);
        tv_title = findViewById(R.id.tv_title);
        iv_adImage = findViewById(R.id.iv_image);
        recyclerView = findViewById(R.id.store_list);
        //修改标题
        tv_title.setText("店铺");
        //隐藏主界面的标题栏返回键
        tv_go_back.setVisibility(View.INVISIBLE);
        //为列表设置布局管理器:线性布局
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //为列表设置适配器
        recyclerView.setAdapter(adapter);

        //为列表条目设置监听事件
        adapter.setOnItemClickListener(new MyStoreListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Store store) {
                //处理监听事件
                //点击条目进入店铺详情页
                Intent intent = new Intent(getApplicationContext(),StoreDetailsActivity.class);
                intent.putExtra("storeData",store);
                Log.d("tag",store.toString());
                startActivity(intent);
            }
        });
    }


    long pressTime = 0;//记录上一次点击返回按钮的时间
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //监听导航栏的返回按钮，连续点两下，退出软件
        if(keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN){

            if(System.currentTimeMillis() - pressTime > 2000){
                //若上一次点击返回键的时间与目前时间间隔大于2秒，提示
                Toast.makeText(this, "再按一次返回键，退出程序", Toast.LENGTH_SHORT).show();
                //记录点击时间
                pressTime = System.currentTimeMillis();
            } else{
                //若上一次点击返回键的时间与目前时间间隔小于2秒，直接退出
                finish();
                //关闭虚拟机
                System.exit(0);
            }
        }
        return true;
    }
}