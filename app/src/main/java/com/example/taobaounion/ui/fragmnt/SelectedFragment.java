package com.example.taobaounion.ui.fragmnt;

import android.content.Intent;
import android.graphics.Rect;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.taobaounion.R;
import com.example.taobaounion.base.BaseFragment;
import com.example.taobaounion.base.IBaseInfo;
import com.example.taobaounion.moudle.bean.SelectedContentResult;
import com.example.taobaounion.presenter.impl.SelectedPresenterImpl;
import com.example.taobaounion.presenter.impl.TicketPresenterImpl;
import com.example.taobaounion.ui.activity.TicketActivity;
import com.example.taobaounion.ui.adapter.SelectedCategoriesAdapter;
import com.example.taobaounion.ui.adapter.SelectedContentListAdapter;
import com.example.taobaounion.utils.LogUtils;
import com.example.taobaounion.utils.PresenterManager;
import com.example.taobaounion.utils.TicketUtils;
import com.example.taobaounion.view.ISelectedCallBack;

import butterknife.BindView;

//首页Fragment
public class SelectedFragment extends BaseFragment implements ISelectedCallBack, SelectedCategoriesAdapter.OnSelectedCategoryClickListener, SelectedContentListAdapter.OnSelectedContentItemClickListener {

    private SelectedPresenterImpl mSelectedPresenter;

    @BindView(R.id.category_list)
    public RecyclerView categoryList;

    @BindView(R.id.content_list)
    public RecyclerView contentList;
    private SelectedCategoriesAdapter mCategoryListAdapter;
    private SelectedContentListAdapter mContentListAdapter;
    private LinearLayoutManager mLayoutManager;

    @BindView(R.id.tv_fragment_title_bar)
    public TextView tv_fragmentTitleBar;//根布局的标题

    private int mCategoryId = 1;


    //对此方法进行重写，加载带标题的根布局，使加载状态时能够显示标题
    @Override
    public View loadRootView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.base_fragment_with_titlebar_layout,container,false);
    }

    @Override
    public int getViewID() {
        return R.layout.fragment_selected;
    }

    @Override
    public void initView(View rootView) {
        tv_fragmentTitleBar.setText(R.string.text_selected_title);
        setupState(State.SUCCESS);
        //为分类列表设置适配器
        categoryList.setLayoutManager(new LinearLayoutManager(getContext()));
        mCategoryListAdapter = new SelectedCategoriesAdapter();
        categoryList.setAdapter(mCategoryListAdapter);
        //为内容列表设置适配器
        mLayoutManager = new LinearLayoutManager(getContext());
        contentList.setLayoutManager(mLayoutManager);
        mContentListAdapter = new SelectedContentListAdapter();
        contentList.setAdapter(mContentListAdapter);
        //设置条目间距
        contentList.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.top = 5;
                outRect.bottom = 5;
            }
        });
    }

    @Override
    public void initPresenter() {
        mSelectedPresenter = PresenterManager.getInstance().getSelectedPresenter();
        mSelectedPresenter.registerViewCallBack(this);
        //开始默认加载第一个分类的数据
        mSelectedPresenter.getSelectedContent(mCategoryId);

    }

    @Override
    public void initListener() {
        mCategoryListAdapter.setOnSelectedCategoryClickListener(this);
        mContentListAdapter.setOnSelectedContentItemClickListener(this);
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
     * 请求的数据从这里回来
     *
     * @param content
     */
    @Override
    public void onLoadContent(SelectedContentResult.DataBean content) {
        setupState(State.SUCCESS);
        //使RecyclerView重新跳转到顶部条目
        mLayoutManager.scrollToPositionWithOffset(0, 0);
        //为内容列表适配器设置数据
        mContentListAdapter.setDataList(content);
    }

    //左侧分类条目被点击时，回从此方法传回其对应的索引
    @Override
    public void onSelectedCategoryClick(int categoryID) {
        this.mCategoryId = categoryID;
        LogUtils.d(this, "onSelectedCategoryClick-->categoryID:" + categoryID);
        //根据分类id加载右边列表的内容
        if (mSelectedPresenter != null) {
            mSelectedPresenter.getSelectedContent(categoryID);
        }
    }

    /**
     * 内容列表被点击的条目的数据从这里传回来
     * @param
     */
    @Override
    public void onSelectedContentItemClick(IBaseInfo item ) {
        TicketUtils.toTicketActivity(getContext(),item);
    }

    //网络加载错误时，重新加载
    @Override
    public void onRetry() {
        if(mSelectedPresenter != null){
            mSelectedPresenter.getSelectedContent(mCategoryId);
        }
    }

    //释放资源
    @Override
    public void release() {
        //取消注册
        if(mSelectedPresenter != null){
            mSelectedPresenter.unregisterViewCallBack(this);
        }
    }

}
