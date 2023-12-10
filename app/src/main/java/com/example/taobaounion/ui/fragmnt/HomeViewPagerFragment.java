package com.example.taobaounion.ui.fragmnt;

import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.taobaounion.R;
import com.example.taobaounion.base.BaseFragment;
import com.example.taobaounion.base.IBaseInfo;
import com.example.taobaounion.moudle.ILinearItemInfo;
import com.example.taobaounion.moudle.bean.Categories;
import com.example.taobaounion.moudle.bean.Constants;
import com.example.taobaounion.moudle.bean.HomeViewPagerContent;
import com.example.taobaounion.presenter.impl.CategoryPagerPresenterImpl;
import com.example.taobaounion.presenter.impl.TicketPresenterImpl;
import com.example.taobaounion.ui.adapter.LinearItemContentAdapter;
import com.example.taobaounion.ui.custom.AutoLoopViewPager;
import com.example.taobaounion.ui.custom.TbNestedScrollView;
import com.example.taobaounion.utils.PresenterManager;
import com.example.taobaounion.utils.SizeUtils;
import com.example.taobaounion.ui.adapter.LooperViewPagerAdapter;
import com.example.taobaounion.utils.LogUtils;
import com.example.taobaounion.utils.TicketUtils;
import com.example.taobaounion.utils.ToastUtils;
import com.example.taobaounion.view.ICategoryPagerCallBack;
import com.scwang.smart.refresh.footer.BallPulseFooter;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.constant.SpinnerStyle;
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener;

import java.util.List;

import butterknife.BindView;

public class HomeViewPagerFragment extends BaseFragment implements ICategoryPagerCallBack, LinearItemContentAdapter.OnListItemClickListener, LooperViewPagerAdapter.OnLooperListItemClickListener {

    private CategoryPagerPresenterImpl categoryPagerPresenter;
    private int categoryId;

    @BindView(R.id.looper_view_pager)
    protected AutoLoopViewPager looperViewPager;

    @BindView(R.id.ly_looper_point_container)
    protected LinearLayout lv_looperPointsContainer;

    @BindView(R.id.tv_home_pager_title)
    protected TextView tv_homePageTitle;

    @BindView(R.id.home_pager_content_list)
    protected RecyclerView homePagerContentList;


    @BindView(R.id.refreshLayout)
    protected SmartRefreshLayout refreshLayout;

    @BindView(R.id.home_pager_header)
    protected ConstraintLayout homePagerHeader;

    @BindView(R.id.tb_nested_scrollView)
    protected TbNestedScrollView TbNestedScrollView;
    private LinearItemContentAdapter contentAdapter;
    private LooperViewPagerAdapter looperViewPagerAdapter;


    @BindView(R.id.home_pager_parent)
    protected ConstraintLayout homePagerParent;
    private TicketPresenterImpl ticketPresenter;

    public HomeViewPagerFragment() {
    }

    private HomeViewPagerFragment homeViewPagerFragment;

    public HomeViewPagerFragment(Categories.DataBean categories) {
        homeViewPagerFragment = new HomeViewPagerFragment();
        String title = categories.getTitle();
        int id = categories.getId();
        Bundle bundle = new Bundle();
        bundle.putString(Constants.KEY_HOME_PAGER_TITLE, title);
        bundle.putInt(Constants.KEY_HOME_PAGER_MATERIAL_ID, id);
        homeViewPagerFragment.setArguments(bundle);
    }

    @Override
    public int getViewID() {
        return R.layout.fragment_home_view_pager;
    }

    //初始化界面
    @Override
    public void initView(View rootView) {
        //设置布局管理器
        homePagerContentList.setLayoutManager(new LinearLayoutManager(getContext()));
        //设置列表条目间距
        homePagerContentList.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                outRect.top = 5;
                outRect.bottom = 5;
            }
        });
        //创建商品列表适配器
        contentAdapter = new LinearItemContentAdapter();
        //设置商品列表适配器
        homePagerContentList.setAdapter(contentAdapter);

        //创建轮播图适配器
        looperViewPagerAdapter = new LooperViewPagerAdapter();
        //设置适配器
        looperViewPager.setAdapter(looperViewPagerAdapter);

        //初始化刷新框架
        refreshLayout.setEnableRefresh(false);//暂时禁用下拉刷新功能
        refreshLayout.setRefreshFooter(new BallPulseFooter(getContext()).setSpinnerStyle(SpinnerStyle.Scale));
    }

    @Override
    public void initPresenter() {
        //获取CategoryPagerPresenterImpl的单例
        categoryPagerPresenter = PresenterManager.getInstance().getCategoryPagerPresenter();
        //注册UI更新通知接口
        categoryPagerPresenter.registerViewCallBack(this);

    }

    //加载从上一层适配器传递过来的数据
    @Override
    public void loadData() {

        if (homeViewPagerFragment != null) {
            Bundle arguments = homeViewPagerFragment.getArguments();
            String title = arguments.getString(Constants.KEY_HOME_PAGER_TITLE);
            categoryId = arguments.getInt(Constants.KEY_HOME_PAGER_MATERIAL_ID);
            LogUtils.d(this, "pager title-->" + title);
            LogUtils.d(this, "material id-->" + categoryId);
            //设置标题
            if (tv_homePageTitle != null) {
                tv_homePageTitle.setText(title);
            }
            //加载数据
            if (categoryPagerPresenter != null) {
                categoryPagerPresenter.getContentByCategoryId(categoryId);
            }
        }


    }

    @Override
    public void onResume() {
        super.onResume();
        //在轮播图控件显示时，开始自动轮播
        //设置轮播间隔时间
        looperViewPager.setDuration(4000);
        looperViewPager.startLoop();
    }

    @Override
    public void onPause() {
        super.onPause();
        //停止自动轮播
        looperViewPager.stopLoop();
    }

    //设置监听事件
    @Override
    public void initListener() {
        //为列表适配器设置点击item数据回传监听器
        contentAdapter.setListItemClickListener(this);

        //为轮播图适配器设置点击ite数据回传监听器
        looperViewPagerAdapter.setOnLooperListItemClickListener(this);

        //为轮播图设置监听事件
        looperViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            //当图片滑动时调用
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            //当图片被选择时调用
            @Override
            public void onPageSelected(int position) {
                //与轮播图指示器进行联动
                if (looperViewPagerAdapter.getDataSize() == 0) {
                    return;
                }
                int realPosition = position % looperViewPagerAdapter.getDataSize();
                onUpdateLooperIndicator(realPosition);
                LogUtils.d(this, "onPageSelected-->");
            }

            //当图片滑动状态改变时调用
            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });


        //设置上拉加载更多的监听事件
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                //加载更多数据
                if (categoryPagerPresenter != null) {
                    categoryPagerPresenter.loadMore(categoryId);
                }
            }
        });


        //为最外层的布局设置监听事件，用于动态修改recyclerView的高度，当布局发生改变时，动态设置RecyclerView的高度
        homePagerParent.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                int headerHigh = homePagerHeader.getMeasuredHeight();
                TbNestedScrollView.setHeaderHigh(headerHigh);
                int measuredHeight = homePagerParent.getMeasuredHeight();
                ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) homePagerContentList.getLayoutParams();
                LogUtils.d(HomeViewPagerFragment.this, "old layoutParams.height-->" + layoutParams.height);
                layoutParams.height = measuredHeight;
                homePagerContentList.setLayoutParams(layoutParams);
                LogUtils.d(HomeViewPagerFragment.this, "layoutParams.height-->" + layoutParams.height);
                //动态改变完毕后，取消监听事件，避免重复修改浪费时间
                if (measuredHeight != 0) {
                    homePagerParent.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }
            }
        });
    }

    /**
     * 根据轮播图的图片更改指示器状态
     *
     * @param position
     */
    private void onUpdateLooperIndicator(int position) {
        LogUtils.d(this, "onUpdateLooperIndicator-->" + position);
        for (int i = 0; i < lv_looperPointsContainer.getChildCount(); i++) {
            //获取到指示器中每一个子View的对象
            View child = lv_looperPointsContainer.getChildAt(i);
            if (i == position) {
                child.setBackgroundResource(R.drawable.shape_looper_selected_point);

            } else {
                child.setBackgroundResource(R.drawable.shape_looper_normal_point);
            }
        }
    }

    /**
     * 数据从这里回来
     *
     * @param contents
     */
    @Override
    public void onContentLoaded(List<HomeViewPagerContent.DataBean> contents) {
        //更新UI界面
        //为适配器加载数据
        contentAdapter.setData(contents);
        setupState(State.SUCCESS);
        LogUtils.d(this, "onContentLoaded-->");


    }


    //正在加载中
    @Override
    public void onLoading() {
        setupState(State.LOADING);
    }


    @Override
    public void onError() {
        setupState(State.ERROR);
    }

    @Override
    public void onEmpty() {
        setupState(State.EMPTY);
    }


    /**
     * 加载更多的数据从这里回来
     *
     * @param contents
     * @param categoryId
     */
    @Override
    public void onLoadMoreSuccess(List<HomeViewPagerContent.DataBean> contents, int categoryId) {
        //将加载到的数据添加到适配器列表的末尾
        contentAdapter.addData(contents);
        ToastUtils.showToast("已加载" + contents.size() + "条数据");
        //刷新停止
        if (refreshLayout != null) {
            refreshLayout.finishLoadMore();
        }
    }

    @Override
    public void onLoadMoreError(int categoryId) {
        ToastUtils.showToast("网络加载错误，请稍后再试~");
        //结束刷新
        if (refreshLayout != null) {
            refreshLayout.finishLoadMore();
        }

    }

    @Override
    public void onLoadMoreEmpty() {
        ToastUtils.showToast("已经没有更多啦~");
        //结束刷新
        if (refreshLayout != null) {
            refreshLayout.finishLoadMore();
        }
    }

    //请求回来的轮播图数据
    @Override
    public void onLooperListLoaded(List<HomeViewPagerContent.DataBean> contents) {
        LogUtils.d(this, "onLooperListLoaded-->contents.size():" + contents.size());
        //为轮播图适配器设置数据
        looperViewPagerAdapter.setData(contents);
       /* //将当前视图设置到中间点，使轮播图两边都能无限轮播-->加上导致页面加载缓慢卡顿
        int dx = (Integer.MAX_VALUE / 2) % contents.size();
        int currentIndex = (Integer.MAX_VALUE / 2) - dx;
        looperViewPager.setCurrentItem(currentIndex);*/
        //清空所有点视图
        lv_looperPointsContainer.removeAllViews();
        //创建并添加点
        int size = SizeUtils.dip2px(getContext(), 8);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(size, size);
        params.leftMargin = SizeUtils.dip2px(getContext(), 5);
        params.rightMargin = SizeUtils.dip2px(getContext(), 5);
        for (int i = 0; i < contents.size(); i++) {
            View point = new View(getContext());
            point.setLayoutParams(params);
            if (i == 0) {
                point.setBackgroundResource(R.drawable.shape_looper_selected_point);
            } else {
                point.setBackgroundResource(R.drawable.shape_looper_normal_point);
            }
            lv_looperPointsContainer.addView(point);
            LogUtils.d(this, "lv_looperPointsContainer.addView(point)-->");
        }
    }


    //将当前分类页的id返回回去供Presenter类调用，因为此处只有一个Presenter类用于加载网络数据，而有多个页面，故需要通过不同的页面id来区分请求回来的数据
    @Override
    public int getCategoryId() {
        return categoryId;
    }

    @Override
    public void release() {
        super.release();
        //取消UI更新通知接口
        if (categoryPagerPresenter != null) {
            categoryPagerPresenter.unregisterViewCallBack(this);
        }
    }

    //列表中被点击的条目数据从这里传递回来
    @Override
    public void onItemClick(ILinearItemInfo item) {
        LogUtils.d(this,"onItemClick-->" + item.getTitle());
        handleItemClick(item);
    }

    //处理条目的点击事件，点击跳转到TicketActivity
    //由于此处TicketActivity界面只需要三个数据，故而此处可以定义一个统一的接口IBaseInfo来统一规范，简化代码
    //由于此处跳转到TicketActivity界面的代码多个地方都需要使用，为了避免代码重复冗余，可以将其抽为一个工具类的方法
    private void handleItemClick(IBaseInfo item) {
        TicketUtils.toTicketActivity(getContext(),item);
    }

    //轮播图中被点击的条目数据从这里回来
    @Override
    public void onLooperListItemClick(HomeViewPagerContent.DataBean item) {
        LogUtils.d(this,"onLooperListItemClick-->" + item.getTitle());
        handleItemClick(item);
    }

    //重新加载页面
    @Override
    public void onRetry() {
        if(categoryPagerPresenter != null){
            categoryPagerPresenter.reload(getCategoryId());
            LogUtils.d(this,"onRetry-->");
        }
    }
}
