package com.example.taobaounion.ui.fragmnt;

import static androidx.fragment.app.FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.taobaounion.R;
import com.example.taobaounion.base.BaseFragment;
import com.example.taobaounion.moudle.bean.Categories;
import com.example.taobaounion.presenter.impl.HomePresenterImpl;
import com.example.taobaounion.ui.activity.MainActivity;
import com.example.taobaounion.ui.activity.ScanQrCodeActivity;
import com.example.taobaounion.ui.adapter.ViewPagerAdapter;
import com.example.taobaounion.utils.LogUtils;
import com.example.taobaounion.utils.PresenterManager;
import com.example.taobaounion.view.IHomeCallBack;
import com.google.android.material.tabs.TabLayout;
import com.vondear.rxfeature.activity.ActivityScanerCode;

import java.util.List;

import butterknife.BindView;

//首页Fragment
public class HomeFragment extends BaseFragment implements IHomeCallBack {
    private HomePresenterImpl homePresenter;
    @BindView(R.id.ed_search_box)
    public EditText editText;
    @BindView(R.id.iv_scan)
    public ImageView iv_scan;
    @BindView(R.id.tablayout)
    public TabLayout tabLayout;

    @BindView(R.id.home_view_page)
    public ViewPager viewPager;

    ViewPagerAdapter adapter;


    @Override
    public View loadRootView(LayoutInflater inflater, ViewGroup container) {
        return LayoutInflater.from(container.getContext()).inflate(R.layout.base_home_fragment, container, false);
    }

    @Override
    public void initView(View rootView) {
        //为TabLayout设置ViewPager
        tabLayout.setupWithViewPager(viewPager);
        //创建ViewPager的适配器
        adapter = new ViewPagerAdapter(getChildFragmentManager(),BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        LogUtils.d(this,"initView");
        //为ViewPager绑定适配器，用于管理ViewPager
        viewPager.setAdapter(adapter);
    }

    @Override
    public int getViewID() {
        return R.layout.fragment_home;
    }

    //创建Presenter用于加载数据
    @Override
    public void initPresenter() {
        homePresenter = PresenterManager.getInstance().getHomePresenter();
        homePresenter.registerViewCallBack(this);
        //注册View更新接口,这个this的意思，不是指IHomeCallBack，而是指实现了IHomeCallBack接口的当前这个实现类HomeFragment的对象！！！！
    }

    //加载数据
    @Override
    public void loadData() {
        //调用HomePresenter类的getCategories()方法进行网络请求数据，请求成功后会通过接口返回到onCategoriesLoad()方法中
        homePresenter.getCategories();
    }


    @Override
    public void initListener() {
        //为搜索框设置监听事件，点击跳转至搜索Fragment: Fragment之间的通信
        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取到当前Fragment所属的Activity
                FragmentActivity activity = getActivity();
                if(activity instanceof MainActivity){
                    //调用Activity中自定义的方法来切换Fragment
                    ((MainActivity) activity).switchToSearchFragment();
                }
            }
        });

        //为iv_scan控件设置点击事件，点击跳转到扫描二维码界面
        iv_scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity().getBaseContext(), ScanQrCodeActivity.class));
            }
        });
    }

    @Override
    public void release() {
        //取消注册
        if(homePresenter != null){
            homePresenter.unregisterViewCallBack(this);
        }
    }

    //请求的数据回从这里回来
    @Override
    public void onCategoriesLoad(Categories categories) {
        setupState(State.SUCCESS);
        LogUtils.d(this,"onCategoriesLoad->" + categories.getData().toString());
        if(adapter != null){
            //给ViewPager的适配器设置数据
            List<Categories.DataBean> data = categories.getData();
            LogUtils.d(this,"onCategoriesLoad->" + data.toString());
            viewPager.setOffscreenPageLimit(2);//设置ViewPager的预加载页面，设置为2个效果最好（默认不设置也是同时加载2个）
            adapter.setCategories(data);
        }

    }

    //网络错误，请求失败
    @Override
    public void onError() {
        setupState(State.ERROR);
    }

    //正在加载中
    @Override
    public void onLoading() {
        setupState(State.LOADING);
    }

    //数据为空
    @Override
    public void onEmpty() {
        setupState(State.EMPTY);
    }

    //网络加载错误时，点击重新加载数据
    @Override
    public void onRetry() {
        if(homePresenter != null){
            homePresenter.retry();
        }
    }
}
