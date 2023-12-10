package com.example.taobaounion.ui.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.graphics.ColorFilter;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.taobaounion.R;
import com.example.taobaounion.base.BaseActivity;
import com.example.taobaounion.ui.fragmnt.HomeFragment;
import com.example.taobaounion.ui.fragmnt.RedPacketFragment;
import com.example.taobaounion.ui.fragmnt.SearchFragment;
import com.example.taobaounion.ui.fragmnt.SelectedFragment;
import com.example.taobaounion.utils.LogUtils;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MainActivity extends BaseActivity {

    @BindView(R.id.bottom_navigation_view)
    public BottomNavigationView bottomNavigationView;
    private HomeFragment homeFragment;
    private SelectedFragment selectedFragment;
    private RedPacketFragment redPacketFragment;
    private SearchFragment searchFragment;

    //初始化界面
    public void initView() {
        initFragment();
    }

    //初始化碎片
    private void initFragment(){
        homeFragment = new HomeFragment();
        selectedFragment = new SelectedFragment();
        redPacketFragment = new RedPacketFragment();
        searchFragment = new SearchFragment();
        switchFragment(homeFragment);//默认展示首页Fragment
    }


    @Override
    public void initPresenter() {

    }

    //初始化界面的监听事件
    public void initListener() {
        //为底部导航栏的条目设置监听事件,点击切换到不同的Fragment
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                //CharSequence title = item.getTitle();
                //LogUtils.i(this,"title-->" + title);
                if(itemId == R.id.home){
                    switchFragment(homeFragment);
                } else if(itemId == R.id.selected){
                    switchFragment(selectedFragment);
                } else if (itemId == R.id.red_packet) {
                    switchFragment(redPacketFragment);
                } else if (itemId == R.id.search) {
                    switchFragment(searchFragment);
                }
                return true;
            }
        });
    }

    //切换Fragment
    //切换后不替换后销毁原来的Fragment而是隐藏，提升用户体验
    private Fragment lastFragment = null;//记录先前的Fragment
    private void switchFragment(Fragment fragment){
        //判断上一个fragemnt是否与现在要切换的Fragment相同，相同则无需切换，且避免重复点击导航Tab时，被隐藏
        if(lastFragment == fragment){
            //上一个Fragment与当前Fragment相同，无需切换
            return;
        }
        //创建碎片管理器
        FragmentManager fm = getSupportFragmentManager();

        FragmentTransaction ft = fm.beginTransaction();//开启事务

        if(!fragment.isAdded()){
            //添加到容器中
            ft.add(R.id.fragment_container, fragment);
            LogUtils.d(this,"ft.add(R.id.fragment_container, fragment)..." + fragment);
        } else{
            //已经添加，直接显示
            ft.show(fragment);
            LogUtils.d(this,"ft.show(fragment)..." + fragment);
        }
        //判空，避免空指针异常
        if(lastFragment != null){
            ft.hide(lastFragment);
        }
        lastFragment = fragment;
        //ft.replace(R.id.fragment_container, fragment);//添加碎片
        ft.commit();//提交事务
    }


    @Override
    public int getViewId() {
        return R.layout.activity_main;
    }


    //切换到搜索Fragment
    public void switchToSearchFragment(){
        switchFragment(searchFragment);
        //再切换底部Tab的选中状态
        bottomNavigationView.setSelectedItemId(R.id.search);
    }


}