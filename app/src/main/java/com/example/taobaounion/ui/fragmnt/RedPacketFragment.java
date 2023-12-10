package com.example.taobaounion.ui.fragmnt;

import android.content.Intent;
import android.graphics.Rect;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.taobaounion.R;
import com.example.taobaounion.base.BaseFragment;
import com.example.taobaounion.base.IBaseInfo;
import com.example.taobaounion.moudle.bean.RedPacketContentResult;
import com.example.taobaounion.presenter.impl.RedPacketPresenterImpl;
import com.example.taobaounion.presenter.impl.TicketPresenterImpl;
import com.example.taobaounion.ui.activity.TicketActivity;
import com.example.taobaounion.ui.adapter.RedPacketContentAdapter;
import com.example.taobaounion.utils.PresenterManager;
import com.example.taobaounion.utils.TicketUtils;
import com.example.taobaounion.utils.ToastUtils;
import com.example.taobaounion.view.IRedPacketCallBack;
import com.scwang.smart.refresh.footer.BallPulseFooter;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.constant.SpinnerStyle;
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener;

import butterknife.BindView;

//特惠界面
public class RedPacketFragment extends BaseFragment implements IRedPacketCallBack, RedPacketContentAdapter.OnRedPacketItemClickListener {

    private RedPacketPresenterImpl mOnSellPresenter;

    @BindView(R.id.tv_title)
    public TextView tv_title;

    @BindView(R.id.content_list)
    public RecyclerView contentList;

    @BindView(R.id.refreshLayout)
    protected SmartRefreshLayout refreshLayout;
    private int mSpanCount = 2;
    private RedPacketContentAdapter mAdapter;

    @BindView(R.id.tv_fragment_title_bar)
    public TextView tv_fragmentTitleBar;//根布局的标题


    //对此方法进行重写，加载带标题的根布局，使加载状态时能够显示标题
    @Override
    public View loadRootView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.base_fragment_with_titlebar_layout,container,false);
    }
    @Override
    public int getViewID() {
        return R.layout.fragment_red_packet;
    }

    @Override
    public void initView(View rootView) {
        tv_fragmentTitleBar.setText(R.string.text_red_packet_title);
        setupState(State.SUCCESS);
        //为列表设置布局管理器
        //设置为瀑布流
        contentList.setLayoutManager(new StaggeredGridLayoutManager(mSpanCount,StaggeredGridLayoutManager.VERTICAL));
        mAdapter = new RedPacketContentAdapter();
        //为列表设置适配器
        contentList.setAdapter(mAdapter);
        //设置条目间距
        contentList.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.top = 5;
                outRect.bottom = 5;
                outRect.left = 6;
                outRect.right = 6;
            }
        });

        //初始化刷新框架
        refreshLayout.setEnableRefresh(false);//暂时禁用下拉刷新功能
        //修改样式
        refreshLayout.setRefreshFooter(new BallPulseFooter(getContext()).setSpinnerStyle(SpinnerStyle.Scale));

    }

    @Override
    public void initPresenter() {
        mOnSellPresenter = PresenterManager.getInstance().getOnSellPresenter();
        mOnSellPresenter.registerViewCallBack(this);

    }

    @Override
    public void initListener() {
        //为刷新框架设置监听事件
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                //加载更多数据
                if(mOnSellPresenter != null){
                    mOnSellPresenter.onLoadMore();
                }
            }
        });
        //为列表条目注册点击监听接口
        mAdapter.setOnRedPacketItemClickListener(this);
    }

    @Override
    public void loadData() {
        if(mOnSellPresenter != null){
            mOnSellPresenter.getOnSellContent();
        }
    }

    @Override
    public void onError() {
        setupState(State.ERROR);
    }

    @Override
    public void onLoading() {
        setupState(State.LOADING);
    }

    @Override
    public void onEmpty() {
        setupState(State.EMPTY);
    }

    /**
     * 请求成功的数据从这里回来
     * @param result
     */
    @Override
    public void onLoadContentSuccess(RedPacketContentResult result) {
        //给适配器设置数据
        mAdapter.setData(result.getData().getTbk_dg_optimus_material_response().getResult_list().getMap_data());
        setupState(State.SUCCESS);
    }

    @Override
    public void onLoadMoreSuccess(RedPacketContentResult result) {
        //给适配器添加数据
        mAdapter.addData(result.getData().getTbk_dg_optimus_material_response().getResult_list().getMap_data());
        refreshLayout.finishLoadMore();
        ToastUtils.showToast("加载完成！");
    }

    @Override
    public void onLoadMoreError() {
        refreshLayout.finishLoadMore();
        ToastUtils.showToast("网络加载错误，请稍后再试~");
    }

    @Override
    public void onLoadMoreEmpty() {
        refreshLayout.finishLoadMore();
        ToastUtils.showToast("已经到达宇宙的尽头~");
    }

    @Override
    public void release() {
        if(mOnSellPresenter != null){
            mOnSellPresenter.unregisterViewCallBack(this);
        }
    }

    /**
     * 当列表条目数据被点击时，回将条目的数据回传到此方法中
     * @param item
     */
    @Override
    public void onRedPacketItemClick(IBaseInfo item) {
        TicketUtils.toTicketActivity(getContext(),item);
    }

    @Override
    public void onRetry() {
        if(mOnSellPresenter != null){
            mOnSellPresenter.retry();
        }
    }
}
