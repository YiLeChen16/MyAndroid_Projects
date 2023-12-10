package com.example.taobaounion.base;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;


//Activity的基类
public abstract class BaseActivity extends AppCompatActivity {
    private Unbinder bind;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getViewId());
        //绑定控件
        //使用ButterKnife加载控件(通过注解反射自动加载控件)
        bind = ButterKnife.bind(this);
        initView();
        initListener();
        initPresenter();
        /*//实现全界面黑白化
        //拿到最顶部的View
        View decorView = this.getWindow().getDecorView();
        //创建一个颜色矩阵
        ColorMatrix colorMatrix = new ColorMatrix();
        //将颜色矩阵的饱和度设置为0
        colorMatrix.setSaturation(0);
        //创建画笔
        Paint paint = new Paint();
        //给画笔设置颜色
        paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
        //修改最顶部View的画笔颜色
        decorView.setLayerType(View.LAYER_TYPE_SOFTWARE,paint);*/
    }

    //初始化Preshenter
    public abstract void initPresenter();

    //设置监听事件
    public void initListener() {

    }

    //初始化视图,强制子类重写
    public abstract void initView();

    //用于给子类返回视图id，以设置视图,设为抽象，强制子类重写
    public abstract int getViewId();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(bind != null){
            bind.unbind();
        }
        //释放资源
        release();
    }

    /**
     * 子类有需要释放资源可复写此方法
     */
    public void release() {
    }
}
