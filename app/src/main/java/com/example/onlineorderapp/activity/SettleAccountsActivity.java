package com.example.onlineorderapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.onlineorderapp.R;
import com.example.onlineorderapp.adapter.MyOrderListAdapter;
import com.example.onlineorderapp.bean.Food;

import java.io.Serializable;
import java.util.ArrayList;

public class SettleAccountsActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText ed_address;//收货地址
    private ListView lv_orderList;//订单列表
    private TextView tv_subtotalPrice;//小计
    private TextView tv_deliveryFee;//配送费
    private TextView tv_total_price;//订单总价
    private Button btn_payment;//支付按钮
    private TextView tv_title;//界面标题
    private TextView tv_goBack;//界面返回键
    private MyOrderListAdapter adapter;//订单列表适配器

    private ArrayList<Food> orderListData;//订单列表集合数据
    private double subtotalPrice;//小计数据
    private int deliveryFee;//配送费数据
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // 设置全屏标志位
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settle_accounts);
        initData();
        init();
        setData();
    }

    //初始化界面控件
    private void init(){
        ed_address = findViewById(R.id.ed_receive_address);
        lv_orderList = findViewById(R.id.lv_order);
        tv_subtotalPrice = findViewById(R.id.tv_subtotal_price);
        tv_deliveryFee = findViewById(R.id.tv_delivery_fee);
        tv_total_price = findViewById(R.id.tv_total_price);
        btn_payment = findViewById(R.id.btn_payment);
        tv_title = findViewById(R.id.tv_title);
        tv_goBack = findViewById(R.id.tv_back);


        //为订单列表设置适配器
        adapter = new MyOrderListAdapter(this);
        lv_orderList.setAdapter(adapter);

        //为按钮设置点击事件
        btn_payment.setOnClickListener(this);
        tv_title.setOnClickListener(this);
        tv_goBack.setOnClickListener(this);

        //为界面设置标题
        tv_title.setText("订单");
    }

    //获取上一界面传递过来的数据
    private void initData(){
        Intent intent = getIntent();
        Bundle data = intent.getBundleExtra("data");
        Log.d("tag",data.getSerializable("shopCarFoodList") + "");
        Log.d("tag",data.getDouble("subtotalPrice") +"");
        Log.d("tag",data.getInt("deliveryFee") +"");
        orderListData = (ArrayList<Food>) data.getSerializable("shopCarFoodList");
        subtotalPrice = data.getDouble("subtotalPrice");
        deliveryFee = data.getInt("deliveryFee");
    }

    //为界面设置数据
    @SuppressLint("SetTextI18n")
    private void setData(){
        tv_subtotalPrice.setText("￥" +subtotalPrice);
        tv_deliveryFee.setText("￥" + deliveryFee);
        tv_total_price.setText("￥" + (subtotalPrice + deliveryFee));
        adapter.setData(orderListData);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_payment:{
                //判断收货地址是否为空
                String address = ed_address.getText().toString();
                if(address.isEmpty()){
                    Toast.makeText(this, "收货地址为空，请填写收货地址", Toast.LENGTH_SHORT).show();
                    return;
                }
                //弹出支付界面
                //创建对话框并设置对话框样式
                Dialog dialog = new Dialog(this,R.style.Theme_ActivityDialogStyle);
                dialog.setContentView(R.layout.payment_layout);//为对话框设置自定义布局
                dialog.show();//显示对话框
                break;
            }
            case R.id.tv_back:{
                //返回上一界面，结束此界面
                finish();
                break;
            }
        }
    }
}