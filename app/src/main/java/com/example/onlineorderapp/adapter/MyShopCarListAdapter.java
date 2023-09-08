package com.example.onlineorderapp.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.onlineorderapp.R;
import com.example.onlineorderapp.bean.Food;

import java.math.BigDecimal;
import java.util.ArrayList;

//购物车列表适配器
public class MyShopCarListAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<Food> foodArrayList;//菜单集合
    private OnSelectListener onSelectListener; //声明自定义监听接口

    //在创建适配器时为条目设置监听事件
    public MyShopCarListAdapter(Context mContext, OnSelectListener onSelectListener) {
        this.mContext = mContext;
        this.onSelectListener = onSelectListener;
    }

    @Override
    public int getCount() {
        return foodArrayList == null ? 0 : foodArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return foodArrayList == null ? null : foodArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint({"MissingInflatedId", "SetTextI18n"})
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyCarViewHolder holder;
        if (convertView == null) {
            holder = new MyCarViewHolder();
            //加载条目布局
            LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            convertView = layoutInflater.inflate(R.layout.shop_car_item, null);
            //初始化控件
            holder.tv_foodName = convertView.findViewById(R.id.tv_food_name);
            holder.tv_foodCount = convertView.findViewById(R.id.tv_food_count);
            holder.tv_foodPrice = convertView.findViewById(R.id.tv_food_price);
            holder.iv_add = convertView.findViewById(R.id.iv_add);
            holder.iv_reduce = convertView.findViewById(R.id.iv_reduce);
            convertView.setTag(holder);
        } else {
            holder = (MyCarViewHolder) convertView.getTag();
        }
        //设置数据
        Food food = (Food) getItem(position);
        if (food != null) {
            holder.tv_foodName.setText(food.getFoodName());
            holder.tv_foodCount.setText(food.getCount() + "");
            BigDecimal bdm_foodCount = BigDecimal.valueOf(food.getCount());
            holder.tv_foodPrice.setText("￥" + food.getPrice().multiply(bdm_foodCount));
        }

        //为购物车列表条目的按钮设置监听事件
        holder.iv_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onSelectListener != null) {
                    onSelectListener.onSelectAdd(position, holder.tv_foodPrice, holder.tv_foodCount);
                }
            }
        });
        holder.iv_reduce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onSelectListener != null) {
                    onSelectListener.onSelectReduce(position, holder.tv_foodPrice, holder.tv_foodCount);
                }
            }
        });

        return convertView;
    }

    //为适配器设置数据,更新数据
    public void setData(ArrayList<Food> foodArrayList) {
        this.foodArrayList = foodArrayList;
        notifyDataSetChanged();
    }

    class MyCarViewHolder {
        private TextView tv_foodName;
        private TextView tv_foodCount;
        private TextView tv_foodPrice;
        private ImageView iv_add;
        private ImageView iv_reduce;
    }

    //创建自定义接口以实现购物车中商品数量的增减
    public interface OnSelectListener {
        //处理监听事件的方法
        void onSelectAdd(int position, TextView tv_foodPrice, TextView tv_foodCount);//加入购物车

        void onSelectReduce(int position, TextView tv_foodPrice, TextView tv_foodCount);//从购物车中删除
    }


}
