package com.example.taobaounion.ui.custom;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatImageView;

import com.example.taobaounion.R;
import com.example.taobaounion.utils.LogUtils;

//自定义的正在加载View
public class MyLoadingView extends AppCompatImageView {

    private int mDegrees = 10;

    public MyLoadingView(Context context) {
        //保证创建此类的对象都调用到同一个构造方法
        this(context,null);
    }

    public MyLoadingView(Context context, AttributeSet attrs) {
        //保证创建此类的对象都调用到同一个构造方法
        this(context, attrs,0);
    }

    public MyLoadingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //设置加载的界面
        setImageResource(R.mipmap.loading);
    }

    //当视图加载到界面使，开始旋转
    @Override
    protected void onAttachedToWindow() {
        startRotate();
        super.onAttachedToWindow();
        LogUtils.d(this,"onAttachedToWindow");
    }

    public void startRotate() {
        //让加载圈旋转
        post(new Runnable() {
            @Override
            public void run() {
                mDegrees = mDegrees + 10;
                if(mDegrees >= 360){
                    mDegrees = 0;
                }
                //刷新界面
                invalidate();
                LogUtils.d(MyLoadingView.this,"Loading...");
                //如果视图在界面上可见且在界面上显示，旋转
                if(getVisibility() != VISIBLE){
                    //停止旋转
                    removeCallbacks(this);
                    LogUtils.d(MyLoadingView.this,"removeCallbacks...");
                } else {
                    postDelayed(this,20);
                }
            }
        });
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //让加载视图画布旋转一定角度
        canvas.rotate(mDegrees,getWidth() / 2, getHeight() / 2);
        super.onDraw(canvas);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        LogUtils.d(this,"onDetachedFromWindow...");
    }
}
