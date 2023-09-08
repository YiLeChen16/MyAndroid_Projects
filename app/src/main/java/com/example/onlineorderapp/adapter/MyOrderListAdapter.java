package com.example.onlineorderapp.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.onlineorderapp.R;
import com.example.onlineorderapp.bean.Food;

import java.util.ArrayList;

//订单列表适配器
public class MyOrderListAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<Food> orderList;//订单列表集合，从上一界面获取

    public MyOrderListAdapter(Context mContext) {
        this.mContext = mContext;
    }

    //设置数据更新列表
    public void setData(ArrayList<Food> orderList) {
        this.orderList = orderList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return orderList == null ? 0 : orderList.size();
    }

    @Override
    public Object getItem(int position) {
        return orderList == null ? null : orderList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyViewHolder holder;
        if (convertView == null) {
            holder = new MyViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.order_list_item, null);
            holder.iv_foodImg = convertView.findViewById(R.id.iv_food_img);
            holder.tv_foodName = convertView.findViewById(R.id.tv_food_name);
            holder.tv_foodCount = convertView.findViewById(R.id.tv_count);
            holder.tv_foodPrice = convertView.findViewById(R.id.tv_food_price);
            convertView.setTag(holder);
        } else {
            holder = (MyViewHolder) convertView.getTag();
        }
        //设置数据
        Glide.with(mContext)
                .load(orderList.get(position).getFoodImg())
                .error(R.mipmap.ic_launcher)
                .into(holder.iv_foodImg);
        holder.tv_foodName.setText(orderList.get(position).getFoodName());
        holder.tv_foodCount.setText("×" + orderList.get(position).getCount());
        holder.tv_foodPrice.setText("￥" + orderList.get(position).getPrice());
        return convertView;
    }

    class MyViewHolder {
        private ImageView iv_foodImg;
        private TextView tv_foodName;
        private TextView tv_foodCount;
        private TextView tv_foodPrice;
    }
}
