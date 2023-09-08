package com.example.onlineorderapp.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.onlineorderapp.R;
import com.example.onlineorderapp.activity.MainActivity;
import com.example.onlineorderapp.activity.StoreDetailsActivity;
import com.example.onlineorderapp.bean.Store;

import java.util.ArrayList;

//RecyclerView的适配器
public class MyStoreListAdapter extends RecyclerView.Adapter<MyStoreListAdapter.MyViewHolder>{
    private Context mContext;//上下文对象
    public static ArrayList<Store> mStoreList;//店铺列表数据集合,包含各店铺的菜单数据集合
    private OnItemClickListener mListener;  // 点击事件监听器

    //事件监听器接口及其回调方法
    public interface OnItemClickListener {
        void onItemClick(Store store);  // 点击事件回调方法
    }

    //此方法用于设置条目的监听事件,其实就是设置一个回调接口
    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }


    public MyStoreListAdapter(Context context) {
        this.mContext = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //设置条目布局
        View view = LayoutInflater.from(mContext).inflate(R.layout.store_item, parent, false);
        //返回条目
        return new MyViewHolder(view);
    }

    //绑定数据到ViewHolder中
    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Store store = mStoreList.get(position);
        if (store != null) {
            //使用Glide类来根据服务器中请求下来的网络图片URL加载网络图片
            Glide.with(mContext)
                    .load(store.getStoreImg())//指定要加载的图片URL
                    .error(R.mipmap.ic_launcher)//设置错误图，用于在加载失败时显示
                    .into(holder.iv_image);//将图片加载到指定的ImageView控件上

            holder.tv_name.setText(store.getStoreName());
            holder.tv_count.setText("月售 " + store.getSaleCount());
            holder.tv_start_price.setText("起送￥" + store.getStartPrice());
            holder.tv_time.setText(store.getTime());
            holder.tv_welfare.setText(store.getWelfare());
            holder.tv_delivery_fees.setText("配送费￥" + store.getDeliveryFees());
        }


    }

    @Override
    public int getItemCount() {
        return mStoreList == null ? 0 : mStoreList.size();
    }

    //设置数据，更新列表
    @SuppressLint("NotifyDataSetChanged")
    public void setData(ArrayList<Store> storeArrayList) {
        this.mStoreList = storeArrayList;
        notifyDataSetChanged();
    }



    //创建ViewHolder类
    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        //声明控件
        private ImageView iv_image;
        private TextView tv_count;
        private TextView tv_name;
        private TextView tv_start_price;
        private TextView tv_welfare;
        private TextView tv_time;
        private TextView tv_delivery_fees;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            //初始化控件
            iv_image = itemView.findViewById(R.id.iv_image);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_count = itemView.findViewById(R.id.tv_count);
            tv_start_price = itemView.findViewById(R.id.tv_start_price);
            tv_welfare = itemView.findViewById(R.id.tv_welfare);
            tv_time = itemView.findViewById(R.id.tv_time);
            tv_delivery_fees = itemView.findViewById(R.id.tv_delivery_fee);
            //为条目设置监听
            itemView.setOnClickListener(this);
        }

        //点击条目后回调监听监听接口,将条目数据传递到接口中
        @Override
        public void onClick(View v) {
            if(mListener != null){
                mListener.onItemClick(MainActivity.storeDataList.get(getAdapterPosition()));
            }
        }
    }
}
