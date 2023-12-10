package com.example.taobaounion.base;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.taobaounion.R;
import com.example.taobaounion.ui.custom.MyLoadingView;
import com.example.taobaounion.utils.LogUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

//Fragment基类
public abstract class BaseFragment extends Fragment {
    Unbinder bind;
    FrameLayout mBaseContainer;

    @BindView(R.id.my_loading_view)
    public MyLoadingView mLoadingView;


    //使用ButterKnife的@OnClick绑定点击事件，注意此注解只能用于View,当点击ly_network_error时，调用下面的方法
    @OnClick({R.id.ly_network_error})
    protected void retry(){
        onRetry();
    }

    //可以让子类复写此方法，实现在点击时，重新加载数据
    public void onRetry() {
    }

    //定义枚举类表示加载状态
    public enum State{
        NONE,LOADING,ERROR,EMPTY,SUCCESS
    }
    //目前的状态
    State currentState = State.NONE;

    private View successView;
    private View emptyView;
    private View errorView;
    private View loadingView;

    //提供方法给外部获取Fragment的状态
    public State getCurrentState() {
        return currentState;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //加载一个根布局来显示加载失败或成功的状态
        View rootView = loadRootView(inflater,container);
        mBaseContainer = rootView.findViewById(R.id.base_layout);
        loadStateView(inflater,container);
        bind = ButterKnife.bind(this, rootView);
        initView(rootView);
        initPresenter();
        loadData();
        initListener();
        return rootView;
    }

    /**
     * 如果子类需要设置监听事件，可以覆盖这个方法
     */
    public void initListener(){

    }

    public View loadRootView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.base_fragmnet_layout, container,false);
    }

    //将四种状态的View都加载出来
    private void loadStateView(LayoutInflater inflater, ViewGroup container) {
        //加载成功的View
        successView = loadSuccessView(inflater, container);
        mBaseContainer.addView(successView);
        //内容为空的View
        emptyView = loadEmptyView(inflater, container);
        mBaseContainer.addView(emptyView);
        //网络加载错误的View
        errorView = loadErrorView(inflater, container);
        mBaseContainer.addView(errorView);
        //正在加载中的View
        loadingView = loadLoadingView(inflater, container);
        mBaseContainer.addView(loadingView);
        //默认设置所有View都不可见
        setupState(State.NONE);
    }

    //提供方法给子类设置状态View的显示和隐藏
    public void setupState(State state){
        this.currentState = state;
        LogUtils.d(this,"setupState-->currentState:" + currentState.name());
        successView.setVisibility(currentState == State.SUCCESS ? View.VISIBLE:View.INVISIBLE);
        emptyView.setVisibility(currentState == State.EMPTY ? View.VISIBLE:View.INVISIBLE);
        errorView.setVisibility(currentState == State.ERROR ? View.VISIBLE:View.INVISIBLE);
        loadingView.setVisibility(currentState == State.LOADING ? View.VISIBLE:View.INVISIBLE);
        if (currentState != State.LOADING){
            if(mLoadingView != null){
                mLoadingView.setVisibility(View.INVISIBLE);
            }
        }else{
            if(mLoadingView != null){
                mLoadingView.setVisibility(View.VISIBLE);
                mLoadingView.startRotate();
            }
        }
    }

    private View loadLoadingView(LayoutInflater inflater, ViewGroup container) {
        //inflater.inflate(R.layout.view_loading,container);此方法省略了第三个参数attachToRoot，默认attachToRoot为true，表示将其添加到父布局中
        return inflater.inflate(R.layout.view_loading,container,false);
    }

    private View loadErrorView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.view_error,container,false);
    }

    private View loadEmptyView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.view_empty,container,false);
    }


    //初始化界面
    public void initView(View rootView) {

    }

    /**
     * 创建Presenter用于加载数据
     */
    public void initPresenter() {

    }

    /**
     * 加载Fragment的数据
     */
    public void loadData() {

    }

    /**
     *  resID 根据子类的布局资源id来加载布局
     * @return
     */
    private View loadSuccessView(LayoutInflater inflater, ViewGroup container){
        int resID = getViewID();
        return inflater.inflate(resID,container,false);
    }


    //此方法写为抽象，强制子类返回布局资源id
    public abstract int getViewID();


    public void release() {
        //释放资源

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        release();//释放资源
        //解绑视图
        if(bind != null){
            bind.unbind();
        }
    }
}