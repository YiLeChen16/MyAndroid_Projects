package com.example.taobaounion.ui.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.example.taobaounion.moudle.bean.HomeViewPagerContent;
import com.example.taobaounion.utils.LogUtils;
import com.example.taobaounion.utils.UrlUtils;

import java.util.ArrayList;
import java.util.List;
//自定义轮播图适配器
public class LooperViewPagerAdapter extends PagerAdapter {
    private List<HomeViewPagerContent.DataBean> looperDataList = new ArrayList<>();
    private OnLooperListItemClickListener listener = null;

    //因为数据都在adapter中，故而此处暴露方法给外界获取数据的大小
    public  int getDataSize(){
        return looperDataList.size();
    }

    //销毁视图
    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    //加载item视图
    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ImageView iv = new ImageView(container.getContext());
        //获取到父控件的宽高
        int width = container.getMeasuredWidth();
        int height = container.getMeasuredHeight();
        int size = (Math.max(width, height)) / 3;
        LogUtils.d(this,"size-->" + size);
        LogUtils.d(this, "instantiateItem-->looperDataList.size():" + looperDataList.size());
        //实现无限轮播
        int realPosition = position % looperDataList.size();
        HomeViewPagerContent.DataBean dataBean = looperDataList.get(realPosition);
        //设置加载网络图片的大小
        String picUrl = UrlUtils.getPicUrl(dataBean.getPict_url(), size);
        Glide.with(container.getContext()).load(picUrl).into(iv);
        iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
        container.addView(iv);
        //为轮播图片设置监听事件
        iv.setOnClickListener(v -> {
            if(listener != null){
                //将被点击的条目数据传递回HomeViewPagerFragment中
                listener.onLooperListItemClick(looperDataList.get(realPosition));
            }
        });
        return iv;
    }

    @Override
    public int getCount() {
//        LogUtils.d(this,"getCount-->" + looperDataList.size());
        //此类中的instantiateItem()方法的调用时机，是当监测到getCount()方法返回的值不为0时，则调用。
        //正常情况下：getCount()方法返回的值是looperDataList.size()，故而当looperDataList.size()不等于0时，则说明setData()方法已被调用，此时looperDataList中已有数据
        //但若要实现轮播图的无限循环，则需要将getCount()的返回值设置为最大，但若不对looperDataList.size()的值进行判断而直接返回Integer的最大值，
        //则会导致，数据还没从setData()传递过来就直接调用instantiateItem()，导致此时加载不到数据
        return looperDataList.size() == 0? 0:Integer.MAX_VALUE;//由于此处想要实现轮播图的无限循环，故将最大值设置为Integer的最大值
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    //设置数据
    public void setData(List<HomeViewPagerContent.DataBean> contents) {
        this.looperDataList.clear();
        this.looperDataList.addAll(contents);
        LogUtils.d(this, "setData-->looperDataList.size()" + looperDataList.size());
        notifyDataSetChanged();
    }

    //定义为接口变量赋值的方法
    public void setOnLooperListItemClickListener(OnLooperListItemClickListener listener){
        this.listener = listener;
    }

    //创建自定义监听回调接口，用于将被点击到的轮播图的数据传递到HomeViewPagerFragment中
    public interface OnLooperListItemClickListener{
        void onLooperListItemClick(HomeViewPagerContent.DataBean item);
    }


}
