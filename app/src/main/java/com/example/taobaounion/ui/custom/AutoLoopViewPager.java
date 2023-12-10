package com.example.taobaounion.ui.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import com.example.taobaounion.R;
import com.example.taobaounion.utils.LogUtils;

//自定义自动轮播ViewPager
public class AutoLoopViewPager extends ViewPager {

    //默认轮播间隔时间，单位毫秒
    private final int DEFAULT_DURATION = 3000;
    private  int mDuration = DEFAULT_DURATION;
    private boolean isLoop = false;

    public AutoLoopViewPager(@NonNull Context context) {
        this(context,null);
    }

    public AutoLoopViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        //获取属性
        init(context,attrs);
    }

    //初始化控件元素设置:设置轮播时间...
    private void init(Context context, AttributeSet attrs) {
        TypedArray t = context.obtainStyledAttributes(attrs, R.styleable.AutoLoopViewPagerStyle);
        mDuration = t.getInteger(R.styleable.AutoLoopViewPagerStyle_duration, DEFAULT_DURATION);
        LogUtils.d(this,"mDuration-->" + mDuration);
        //回收
        t.recycle();
    }

    //提供方法给外界动态设置轮播间隔时间
    public void setDuration(int duration){
        this.mDuration = duration;
        LogUtils.d(this,"mDuration-->" + mDuration);

    }

    //开始自动轮播
    public void startLoop(){
        isLoop = true;
        post(task);
    }

    private Runnable task = new Runnable() {
        @Override
        public void run() {
            //获取当前条目
            int currentItem = getCurrentItem();
            //切换到下一条目
            currentItem++;
            setCurrentItem(currentItem);
            //延时三秒执行
            postDelayed(this,mDuration);
        }
    };

    //停止自动轮播
    public void stopLoop(){
        this.isLoop = false;
        //停止任务
        removeCallbacks(task);
    }
}
