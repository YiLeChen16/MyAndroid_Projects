package com.example.taobaounion.ui.custom;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;

import com.example.taobaounion.utils.LogUtils;
//自定义View
//用于处理NestedScrollView嵌套RecyclerView的滑动问题
public class TbNestedScrollView extends NestedScrollView {
    private int originScrollY = 0;
    private int headerHigh = 0;

    public TbNestedScrollView(@NonNull Context context) {
        super(context);
    }

    public TbNestedScrollView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TbNestedScrollView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    //暴露方法给外部，获取除recyclerView顶部的距离
    public void setHeaderHigh(int headerHigh){
        this.headerHigh = headerHigh;
    }
    //当TbNestedScrollView里的内容滑动时调用此方法
    @Override
    public void onNestedPreScroll(@NonNull View target, int dx, int dy, @NonNull int[] consumed, int type) {
        if(originScrollY < headerHigh){
            //滑动整个NestedView
            scrollBy(dx,dy);
            consumed[0] = dx;
            consumed[1] = dy;
        }
        //直接交给RecyclerView滑动
        LogUtils.d(this,"onNestedPreScroll --> dy:" + dy);
        super.onNestedPreScroll(target, dx, dy, consumed, type);
    }

    //TbNestedScrollView包裹的整个内容滑动时，调用此方法
    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        //滑动的y距离
        this.originScrollY = t;
        LogUtils.d(this,"onScrollChanged --> t:" + t);
        super.onScrollChanged(l, t, oldl, oldt);
    }

}
