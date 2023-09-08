package com.example.onlineorderapp.adapter;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.bumptech.glide.Glide;
import com.example.onlineorderapp.R;
import com.example.onlineorderapp.bean.Food;

import java.util.ArrayList;

//菜品列表适配器
public class MyFoodListAdapter extends BaseAdapter{
    private final Context mContext;
    private ArrayList<Food> foodArrayList;//菜单数据集合
    private AlertDialog alertDialog;//菜品详情对话框
    private OnSelectListener onSelectListener;//条目监听器

    //创建适配器时，为列表条目设置监听事件
    public MyFoodListAdapter(Context context,OnSelectListener onSelectListener) {
        mContext = context;
        this.onSelectListener = onSelectListener;
    }

    @Override
    public int getCount() {
        return foodArrayList == null ? 0 : foodArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return foodArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return foodArrayList.get(position).getFoodID();
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyHolder holder;
        //复用convertView
        if (convertView == null) {
            holder = new MyHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.food_item, null);
            //初始化控件
            holder.imageView = convertView.findViewById(R.id.iv_food_img);
            holder.tv_foodName = convertView.findViewById(R.id.tv_food_name);
            holder.tv_taste = convertView.findViewById(R.id.tv_taste);
            holder.tv_saleCount = convertView.findViewById(R.id.tv_saleCount);
            holder.tv_price = convertView.findViewById(R.id.tv_food_price);
            holder.btn_add = convertView.findViewById(R.id.btn_add_to_shop_car);
            convertView.setTag(holder);
        } else {
            holder = (MyHolder) convertView.getTag();
        }
        //设置数据
        //使用Glide类来根据服务器中请求下来的网络图片URL加载网络图片
        Glide.with(mContext)
                .load(foodArrayList.get(position).getFoodImg())//指定要加载的图片URL
                .error(R.mipmap.ic_launcher)//设置错误图，用于在加载失败时显示
                .into(holder.imageView);//将图片加载到指定的ImageView控件上
        holder.tv_foodName.setText(foodArrayList.get(position).getFoodName());
        holder.tv_taste.setText("配料：" + foodArrayList.get(position).getTaste());
        holder.tv_saleCount.setText("月售" + Integer.toString(foodArrayList.get(position).getSaleCount()));
        holder.tv_price.setText("￥" + foodArrayList.get(position).getPrice().toString());

        //为条目设置监听事件
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("tag", "点击了第" + position + "条目");
                //获取点击条目的信息
                Food food = foodArrayList.get(position);
                //创建对话框
                Dialog dialog = new Dialog(mContext,R.style.Theme_ActivityDialogStyle);
                dialog.setContentView(R.layout.food_detail_dialog_layout);//设置自定义布局
                //获取对话框中的自定义组件
                Button btn_close = dialog.findViewById(R.id.btn_close);
                ImageView iv_foodImg = dialog.findViewById(R.id.iv_food_img);
                TextView tv_foodName = dialog.findViewById(R.id.tv_food_name);
                TextView tv_taste = dialog.findViewById(R.id.tv_food_taste);
                TextView tv_saleCount = dialog.findViewById(R.id.tv_saleCount);
                TextView tv_price = dialog.findViewById(R.id.tv_food_price);
                //设置数据
                Glide.with(mContext)
                        .load(food.getFoodImg())
                        .error(R.mipmap.ic_launcher)
                        .into(iv_foodImg);
                tv_foodName.setText(food.getFoodName());
                tv_taste.setText("配料：" + food.getTaste());
                tv_saleCount.setText("月售" + food.getSaleCount());
                tv_price.setText("￥" + food.getPrice());
                //为按钮设置监听事件
                btn_close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //关闭对话框
                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        });

        //为条目的加入购物车按钮设置点击事件
        holder.btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSelectListener.onSelectAddCar(position);
            }
        });
        return convertView;
    }

    //为适配器设置数据
    public void setData(ArrayList<Food> foodArrayList) {
        this.foodArrayList = foodArrayList;
        notifyDataSetChanged();//更新数据
    }

    class MyHolder {
        private ImageView imageView;
        private TextView tv_foodName;
        private TextView tv_taste;
        private TextView tv_saleCount;
        private TextView tv_price;
        private Button btn_add;
    }

    //创建自定义监听接口和回调方法
    public interface OnSelectListener{
        void onSelectAddCar(int position);//此方法用于编写加入购物车按钮的逻辑代码
    }
}
