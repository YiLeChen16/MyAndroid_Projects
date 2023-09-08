package com.example.onlineorderapp.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.onlineorderapp.R;
import com.example.onlineorderapp.adapter.MyFoodListAdapter;
import com.example.onlineorderapp.adapter.MyShopCarListAdapter;
import com.example.onlineorderapp.bean.Food;
import com.example.onlineorderapp.bean.Store;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;

//店铺详情界面
public class StoreDetailsActivity extends AppCompatActivity implements View.OnClickListener {

    //本界面店铺的所有数据
    private Store storeData;

    //声明控件
    private TextView tv_back;//标题返回按钮
    private TextView tv_title;//界面标题
    private TextView tv_storeName;//店铺名
    private TextView tv_time;//配送时间
    private TextView tv_storeNotice;//店铺公告
    private ImageView iv_storeImage;//店铺头像
    private ListView foodList;//菜单列表
    private ListView carList;//购物车列表
    private TextView tv_totalPrice;//购物车中菜品的总价格
    private TextView tv_settleAccounts;//结算按钮
    private TextView tv_notEnough;
    private TextView tv_foodName;//购物车菜品名
    private TextView tv_foodPrice;//菜品价格
    private ImageView iv_reduce;//减少菜品按钮
    private ImageView iv_add;//增加菜品按钮
    private TextView tv_totalCount;//总菜品数量
    private TextView tv_clear;//清空购物车按钮
    private ImageView iv_car;//购物车图片
    private RelativeLayout rl_carBar;//购物车列表标题
    private TextView tv_deliveryFees;//配送费
    private RelativeLayout rl_carList;//购物车列表布局


    private ArrayList<Food> foodArrayList;//店铺菜单集合
    private MyFoodListAdapter foodListAdapter;//店铺菜单列表适配器
    private MyShopCarListAdapter shopCarListAdapter;//购物车列表适配器
    private int totalCount;//购物车商品总数
    private ArrayList<Food> shopCarFoodList;//购物车列表菜品集合
    private BigDecimal totalPrice;//购物车菜品总价(使用BigDecimal方便直接调用方法计算数值)
    private final int MSG_COUNT_OK = 0;//表示获取购物车中的商品数量成功

    private final Handler handler = new Handler(new Handler.Callback() {
        //处理接口方法中发送的数据，更新UI界面
        @SuppressLint("SetTextI18n")
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            if (msg.what == MSG_COUNT_OK) {
                Bundle data = msg.getData();
                String count = data.getString("totalCount");
                String price = data.getString("totalPrice");
                if (Integer.parseInt(count) > 0) {
                    //购物车中有菜品，更新购物车列表数据
                    tv_deliveryFees.setVisibility(View.VISIBLE);//设置配送费可见
                    //更新购物车菜品总数及总价格
                    tv_totalCount.setText(count);
                    tv_totalPrice.setTextColor(Color.RED);
                    tv_totalPrice.setText("￥" + price);
                    //显示配送费
                    tv_deliveryFees.setText("另需配送费￥" + storeData.getDeliveryFees());
                    //将price变量转换为BigDecimal方便进行数值运算与比较
                    BigDecimal bdm_price = new BigDecimal(price);
                    //将总价与起送价进行比较，若大于等于则隐藏起送按钮，显示去结算按钮
                    int res = bdm_price.compareTo(storeData.getStartPrice());
                    if (res == -1) {
                        //小于
                        //隐藏去结算按钮
                        tv_settleAccounts.setVisibility(View.GONE);
                        //显示起送按钮
                        tv_notEnough.setVisibility(View.VISIBLE);
                        //修改起送按钮文本信息
                        tv_notEnough.setTextSize(16);
                        tv_notEnough.setText("还差￥" + storeData.getStartPrice().subtract(bdm_price) + "元起送");
                    } else {
                        //大于等于
                        //隐藏起送按钮，显示去结算按钮
                        tv_notEnough.setVisibility(View.GONE);
                        tv_totalPrice.setTextColor(Color.RED);
                        tv_settleAccounts.setVisibility(View.VISIBLE);
                    }
                } else {
                    //购物车中没有菜品
                    //隐藏购物车列表
                    carList.setVisibility(View.GONE);
                    rl_carBar.setVisibility(View.GONE);
                    //隐藏去结算按钮
                    tv_settleAccounts.setVisibility(View.GONE);
                    tv_notEnough.setVisibility(View.VISIBLE);
                    //修改差价文本
                    tv_notEnough.setText("￥" + storeData.getStartPrice() + "元起送");
                    //隐藏配送费用
                    tv_deliveryFees.setVisibility(View.GONE);
                    //修改总价文本及颜色
                    tv_totalPrice.setTextColor(Color.BLACK);
                    tv_totalPrice.setText("未选购菜品");
                    //修改购物车菜品总数
                    tv_totalCount.setText(totalCount + "");
                }
            }
            return true;
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 设置全屏标志位
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_store_details_layout);
        shopCarFoodList = new ArrayList<>();//初始化购物车菜品集合
        totalPrice = new BigDecimal("0.0");//初始化购物车总价
        init();
        initAdapter();
        initData();
    }

    //初始化适配器
    private void initAdapter() {
        //创建菜单列表适配器，并为条目按钮设置监听
        foodListAdapter = new MyFoodListAdapter(this, new MyFoodListAdapter.OnSelectListener() {
            @Override
            public void onSelectAddCar(int position) {
                //获取点击的条目菜品信息
                Food food = foodArrayList.get(position);
                //初始化菜品信息
                food.setCount(food.getCount() + 1);//已选菜品数加一
                //遍历购物车列表菜品集合移除集合中原有的此菜品的数据
                Iterator<Food> iterator = shopCarFoodList.iterator();
                while (iterator.hasNext()) {
                    Food food1 = iterator.next();
                    if (food1.getFoodID() == food.getFoodID()) {
                        //将数据移除
                        iterator.remove();
                    }
                }
                //更新集合中此菜品的数据
                if (position < shopCarFoodList.size()) {
                    shopCarFoodList.add(position, food);
                } else {
                    shopCarFoodList.add(food);
                }
                totalCount++;//购物车总菜品数+1
                totalPrice = totalPrice.add(food.getPrice());
                updateCarData();//发送消息到主线程更新购物车UI数据
            }
        });
        foodList.setAdapter(foodListAdapter);//为菜单列表设置适配器

        //创建购物车列表适配器
        shopCarListAdapter = new MyShopCarListAdapter(this, new MyShopCarListAdapter.OnSelectListener() {
            //增加菜品
            //形参中的控件为原有的购物车列表数据的控件
            @SuppressLint("SetTextI18n")
            @Override
            public void onSelectAdd(int position, TextView tv_foodPrice, TextView tv_foodCount) {
                //获取点击的购物车条目菜品对象
                Food food = shopCarFoodList.get(position);
                //更新菜品对象信息
                food.setCount(food.getCount() + 1);
                BigDecimal bdm_count = new BigDecimal(food.getCount() + 1);//将购物车菜品总数转化为BigDecimal类型方便运算
                tv_foodCount.setText((food.getCount() + 1) + "");//更新对应购物车条目菜品数量
                tv_foodPrice.setText("￥" + food.getPrice().multiply(bdm_count));//更新单个菜品总价
                //遍历购物车列表菜品集合移除集合中原有的此菜品的数据
                Iterator<Food> iterator = shopCarFoodList.iterator();
                while (iterator.hasNext()) {
                    Food food1 = iterator.next();
                    if (food1.getFoodID() == food.getFoodID()) {
                        //将数据移除
                        iterator.remove();
                    }
                }
                //更新集合中此菜品的数据
                shopCarFoodList.add(position, food);
                totalCount++;//购物车总菜品数+1
                totalPrice = totalPrice.add(food.getPrice());//更新总价
                updateCarData();//发送消息到主线程更新购物车UI数据
            }

            //减少菜品
            @SuppressLint("SetTextI18n")
            @Override
            public void onSelectReduce(int position, TextView tv_foodPrice, TextView tv_foodCount) {
                //获取点击的购物车条目菜品对象
                Food food = shopCarFoodList.get(position);
                //更新菜品对象信息
                food.setCount(food.getCount() - 1);
                BigDecimal bdm_count = new BigDecimal(food.getCount() - 1);//将购物车菜品总数转化为BigDecimal类型方便运算
                tv_foodCount.setText(food.getCount() - 1 + "");//更新对应购物车条目菜品数量
                tv_foodPrice.setText("￥" + food.getPrice().multiply(bdm_count));//更新单个菜品总价
                //删除购物车中的菜品，若减少为0时，需移除条目，同时调用updateCarData()方法在主线程中更新购物车UI数据
                reduceCarListData(food, position);
            }
        });
        carList.setAdapter(shopCarListAdapter);//为购物车列表设置适配器
    }

    //删除购物车中的菜品，若减少为0时，需移除条目，同时调用updateCarData()方法在主线程中更新购物车UI数据
    private void reduceCarListData(Food food, int position) {
        //遍历购物车列表集合删除菜品数据
        Iterator<Food> iterator = shopCarFoodList.iterator();
        while (iterator.hasNext()) {
            Food food1 = iterator.next();
            if (food1.getFoodID() == food.getFoodID()) {
                //将数据移除
                iterator.remove();
            }
        }
        if (food.getCount() <= 0) {
            //从购物车列表中移除条目
            shopCarListAdapter.notifyDataSetChanged();
        } else {
            //不移除条目，只更新列表数据
            shopCarFoodList.add(position, food);
            shopCarListAdapter.notifyDataSetChanged();
        }
        totalCount--;//购物车总菜品数-1
        totalPrice = totalPrice.subtract(food.getPrice());//更新总价
        //调用方法更新购物车UI数据
        updateCarData();
    }

    //发送购物车总价和总数信息到主线程中更新UI数据
    private void updateCarData() {
        Message msg = new Message();
        Bundle bundle = new Bundle();
        bundle.putString("totalPrice", totalPrice + "");
        bundle.putString("totalCount", totalCount + "");
        msg.setData(bundle);
        msg.what = MSG_COUNT_OK;
        handler.sendMessage(msg);
        shopCarListAdapter.setData(shopCarFoodList);
    }

    @SuppressLint("ClickableViewAccessibility")
    private void init() {
        //初始化控件
        tv_back = findViewById(R.id.tv_back);
        tv_title = findViewById(R.id.tv_title);
        tv_storeName = findViewById(R.id.tv_store_name);
        tv_time = findViewById(R.id.tv_time);
        tv_storeNotice = findViewById(R.id.tv_store_notice);
        iv_storeImage = findViewById(R.id.iv_store_image);
        foodList = findViewById(R.id.food_list);
        carList = findViewById(R.id.car_list);
        tv_totalPrice = findViewById(R.id.tv_total_price);
        tv_settleAccounts = findViewById(R.id.tv_settle_accounts);
        tv_notEnough = findViewById(R.id.tv_not_enough);
        tv_foodName = findViewById(R.id.tv_food_name);
        tv_foodPrice = findViewById(R.id.tv_food_price);
        iv_reduce = findViewById(R.id.iv_reduce);
        iv_add = findViewById(R.id.iv_add);
        tv_totalCount = findViewById(R.id.tv_count);
        iv_car = findViewById(R.id.iv_shop_car);
        tv_clear = findViewById(R.id.tv_clear);
        rl_carBar = findViewById(R.id.rl_bar);
        tv_deliveryFees = findViewById(R.id.tv_delivery_fees);
        rl_carList = findViewById(R.id.rl_car_list);

        //修改标题
        tv_title.setText("店铺详情");
        tv_back.setVisibility(View.VISIBLE);//设置返回按钮可见

        //设置监听事件
        tv_back.setOnClickListener(this);//返回按钮
        iv_car.setOnClickListener(this);//购物车图标，点击弹出购物车列表
        tv_clear.setOnClickListener(this);//清空购物车按钮
        tv_settleAccounts.setOnClickListener(this);//去结算按钮

        //设置购物车列表不可见
        rl_carBar.setVisibility(View.GONE);
        carList.setVisibility(View.GONE);
    }

    //初始化界面数据
    @SuppressLint("SetTextI18n")
    private void initData() {
        //获取从上一界面传递过来的数据
        Intent intent = getIntent();
        storeData = (Store) intent.getSerializableExtra("storeData");
        if (intent.hasExtra("storeData")) {
            tv_storeName.setText(storeData.getStoreName());
            tv_time.setText(storeData.getTime());
            tv_storeNotice.setText(storeData.getStoreNotice());
            Glide.with(this)
                    .load(storeData.getStoreImg())
                    .error(R.mipmap.ic_launcher)
                    .into(iv_storeImage);
            foodListAdapter.setData(storeData.getFoodList());//为菜单列表设置数据
            tv_notEnough.setText("￥" + storeData.getStartPrice() + "起送");//设置起送价
            foodArrayList = storeData.getFoodList();//获取并存储菜单集合信息
        }
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_back: {
                //标题返回按钮
                //点击返回上一界面
                finish();
                break;
            }
            case R.id.iv_shop_car: {
                //购物车图标
                Log.d("tag", "点击了购物车图标");
                //点击图标收回购物车列表或弹出购物车列表
                if (totalCount <= 0) {
                    Log.d("tag", "购物车中没有菜品");
                    return;
                }
                if (carList.getVisibility() == View.VISIBLE) {
                    //收回列表及标题
                    Log.d("'tag", "收回购物车列表及标题");
                    rl_carBar.setVisibility(View.GONE);
                    carList.setVisibility(View.GONE);
                } else {
                    //弹出列表及显示标题
                    Log.d("'tag", "弹出列表及显示标题");
                    //显示购物车列表
                    rl_carBar.setVisibility(View.VISIBLE);
                    carList.setVisibility(View.VISIBLE);
                    //创建弹出动画
                    Animation animation = AnimationUtils.loadAnimation(this, R.anim.slide_bottom_to_top);
                    //为购物车列表加载动画
                    carList.setAnimation(animation);
                    Log.d("'tag", "加载动画");
                }
                shopCarListAdapter.setData(shopCarFoodList);
                carList.setAdapter(shopCarListAdapter);
                break;
            }
            case R.id.tv_clear: {
                Log.d("tag", "清空购物车");
                //清空购物车
                //创建对话框并显示
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("清空购物车")
                        .setMessage("确定清空购物车")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //清空购物车列表并关闭对话框
                                clearCarList();
                                dialog.dismiss();
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //关闭对话框
                                dialog.dismiss();
                            }
                        })
                        .create()
                        .show();
                break;
            }
            case R.id.tv_settle_accounts: {
                //去结算按钮
                //跳转到结算界面
                Intent intent = new Intent(this, SettleAccountsActivity.class);
                //将此界面的订单列表传递到结算界面
                double doubleValue_totalPrice = totalPrice.doubleValue();
                Bundle bundle = new Bundle();
                bundle.putDouble("subtotalPrice", doubleValue_totalPrice);
                bundle.putSerializable("shopCarFoodList", shopCarFoodList);
                bundle.putInt("deliveryFee",storeData.getDeliveryFees());
                intent.putExtra("data", bundle);
                Log.d("tag1",bundle.getDouble("subtotalPrice") + "");
                Log.d("tag1",bundle.getSerializable("shopCarFoodList") + "");
                Log.d("tag1",bundle.getInt("deliveryFee") + "");
                Log.d("tag1",intent.getExtras() + "");
                startActivity(intent);
                break;
            }
        }
    }

    private void clearCarList() {
        if (shopCarFoodList == null) {
            return;
        }
        //清空集合数据
        shopCarFoodList.clear();
        //更新购物车列表适配器数据
        shopCarListAdapter.setData(shopCarFoodList);
        //将购物车菜品总数及总价设为0
        totalCount = 0;
        totalPrice = BigDecimal.valueOf(0.0);
        //传回主线程更新数据
        updateCarData();
    }

}