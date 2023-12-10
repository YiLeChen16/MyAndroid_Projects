package com.example.taobaounion.ui.fragmnt;

import android.content.Context;
import android.graphics.Rect;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.taobaounion.R;
import com.example.taobaounion.base.BaseFragment;
import com.example.taobaounion.moudle.ILinearItemInfo;
import com.example.taobaounion.moudle.bean.SearchRecommendWords;
import com.example.taobaounion.moudle.bean.SearchResult;
import com.example.taobaounion.presenter.impl.SearchPresenterImpl;
import com.example.taobaounion.ui.adapter.LinearItemContentAdapter;
import com.example.taobaounion.ui.custom.FlowTextLayout;
import com.example.taobaounion.utils.LogUtils;
import com.example.taobaounion.utils.PresenterManager;
import com.example.taobaounion.utils.SizeUtils;
import com.example.taobaounion.utils.TicketUtils;
import com.example.taobaounion.utils.ToastUtils;
import com.example.taobaounion.view.ISearchCallBack;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

//首页Fragment
public class SearchFragment extends BaseFragment implements ISearchCallBack, FlowTextLayout.OnFlowTextLayoutItemClickListener, LinearItemContentAdapter.OnListItemClickListener {

    private SearchPresenterImpl mSearchPresenter;

    @BindView(R.id.ed_search_box)
    public EditText mSearchEditText;

    @BindView(R.id.recommend_layout)
    public LinearLayout mRecommendLayout;

    @BindView(R.id.search_histories_layout)
    public LinearLayout mSearchHistoriesLayout;

    @BindView(R.id.tv_search_or_cancel_search)
    public TextView mSearchOrCancelSearchButton;

    @BindView(R.id.flow_text_layout_recommend)
    public FlowTextLayout mFlowTextLayoutRecommend;

    @BindView(R.id.flow_text_layout_histories)
    public FlowTextLayout mFlowTextLayoutHistories;

    @BindView(R.id.iv_delete)
    public ImageView mDeleteHistories;

    @BindView(R.id.search_result_ist)
    public RecyclerView mSearchResultList;

    @BindView(R.id.refreshLayout)
    public SmartRefreshLayout mSmartRefreshLayout;

    @BindView(R.id.tv_empty_describe)
    public TextView tv_emptyDescribe;
    private final String SEARCH = "搜索";
    private final String CANCEL = "取消";
    private LinearItemContentAdapter mAdapter;

    //修改搜索界面的根布局
    @Override
    public View loadRootView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.base_fragment_with_searchbar_layout,container,false);
    }

    //设置搜索界面的子布局
    @Override
    public int getViewID() {
        return R.layout.fragment_search;
    }
    @Override
    public void initView(View rootView) {
        setupState(State.SUCCESS);
        //给搜索结果列表设置布局管理器
        mSearchResultList.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new LinearItemContentAdapter();
        //给搜索结果列表设置适配器
        mSearchResultList.setAdapter(mAdapter);
        //设置条目间距
        mSearchResultList.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.left = SizeUtils.dip2px(getContext(),3);
                outRect.bottom = SizeUtils.dip2px(getContext(),3);
            }
        });
        //为下拉刷新框架设置只允许加载更多
         mSmartRefreshLayout.setEnableRefresh(false);
    }

    @Override
    public void initPresenter() {
        mSearchPresenter = PresenterManager.getInstance().getSearchPresenter();
        mSearchPresenter.registerViewCallBack(this);

    }

    @Override
    public void loadData() {
        if(mSearchPresenter != null){
            mSearchPresenter.loadRecommendWords();
            mSearchPresenter.loadHistory();
        }
    }

    @Override
    public void initListener() {
        //为搜索与取消搜索按钮设置监听事件
        mSearchOrCancelSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mSearchOrCancelSearchButton.getText().toString().equals(SEARCH)){
                    String searchWord = mSearchEditText.getText().toString();
                    //用户当前点击的按钮为搜索,判断搜索内容是否为空
                    if(TextUtils.isEmpty(searchWord)){
                        //空，不用搜索
                        ToastUtils.showToast("搜索了个寂寞~");
                        return;
                    }
                    //非空
                    //将按钮文本修改为取消，并隐藏搜索推荐词和搜索历史记录列表，获取输入框的内容进行搜索
                    search(searchWord);
                }else{
                    //用户当前点击的按钮为取消
                    //将按钮文本修改为搜索，并隐藏搜索结果列表，显示搜索推荐词和搜索历史记录列表
                    cancelSearch();
                }
            }
        });
        //为历史记录及推荐搜索词列表条目设置监听事件
        mFlowTextLayoutRecommend.setOnFlowTextLayoutItemClickListener(this);
        mFlowTextLayoutHistories.setOnFlowTextLayoutItemClickListener(this);
        //清空历史记录
        mDeleteHistories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mSearchPresenter != null){
                    LogUtils.d(this,"清空历史记录");
                    mSearchPresenter.deleteHistories();
                    mSearchPresenter.loadHistory();//更新搜索历史记录
                }
            }
        });

        //为搜索列表条目设置点击事件，点击跳转到淘口令界面
        mAdapter.setListItemClickListener(this);

        //为下拉刷新控件设置上拉监听事件
        mSmartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                //加载更多搜索结果
                if(mSearchPresenter != null){
                    mSearchPresenter.loadMoreSearchResult();
                }
            }
        });

        //给搜索框回车键设置监听，按下回车键时，进行搜索
        mSearchEditText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // 判断按下的是否是回车键
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_UP) {
                    // 在这里处理回车键的事件
                    search(mSearchEditText.getText().toString());
                    return true;  // 返回true表示已经处理了事件
                }
                return false;  // 返回false表示未处理事件，将继续传递给其他监听器或系统处理
            }
        });
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
        tv_emptyDescribe.setText("抱歉，没有找到您要的宝贝");
        setupState(State.EMPTY);
    }

    /**
     * 搜索成功的结果数据从这里回来
     * @param result
     */
    @Override
    public void onLoadSearchResult(SearchResult result) {
        setupState(State.SUCCESS);
        LogUtils.d(this,"SearchResult-->" + result.getData().getTbk_dg_material_optional_response().getResult_list().getMap_data().get(0).getTitle());
        //给搜索结果适配器设置数据
        mAdapter.setData(result.getData().getTbk_dg_material_optional_response().getResult_list().getMap_data());
    }

    /**
     * 搜索推荐词加载成功
     * @param recommendWords
     */
    @Override
    public void onLoadSearchRecommendWordsSuccess(SearchRecommendWords recommendWords) {
        setupState(State.SUCCESS);
        List<SearchRecommendWords.DataBean> data = recommendWords.getData();
        LogUtils.d(this,"onLoadSearchRecommendWordsSuccess-->" + data.get(0));
        List<String> recommendWordList = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            recommendWordList.add(data.get(i).getKeyword());
        }
        mFlowTextLayoutRecommend.setData(recommendWordList);
    }


    //搜索推荐词加载错误
    @Override
    public void onLoadSearchRecommendWordsError() {
        setupState(State.ERROR);
        ToastUtils.showToast("网络异常");
    }

    /**
     * 搜索内容加载更多的数据从这里回来
     * @param result
     */
    @Override
    public void onLoadMoreSuccess(SearchResult result) {
        LogUtils.d(this,"onLoadMoreSuccess-->" + result.getData());
        //为适配器添加更多数据
        mAdapter.addData(result.getData().getTbk_dg_material_optional_response().getResult_list().getMap_data());
        ToastUtils.showToast("加载成功~");
        //停止刷新
        mSmartRefreshLayout.finishLoadMore();
    }

    @Override
    public void onLoadMoreError() {
        //停止刷新
        mSmartRefreshLayout.finishLoadMore();
        ToastUtils.showToast("网络错误~");
        LogUtils.d(this,"onLoadMoreError...");
    }

    @Override
    public void onLoadMoreEmpty() {
        LogUtils.d(this,"onLoadMoreEmpty...");
        //停止刷新
        mSmartRefreshLayout.finishLoadMore();
        ToastUtils.showToast("已经到达宇宙的尽头啦~");
    }


    //加载历史记录成功
    @Override
    public void onLoadHistoriesSuccess(List<String> histories) {
        LogUtils.d(this,"onLoadHistories-->" + histories);
        //将历史记录内容设置到flowTextLayout中
        mFlowTextLayoutHistories.setData(histories);
    }

    //加载历史记录为空,说明用户此时可能清空了历史记录
    @Override
    public void onLoadHistoriesEmpty() {
        //清空FlowTextLayout的所有子View
        mFlowTextLayoutHistories.removeAllViews();
    }

    @Override
    public void release() {
        if(mSearchPresenter != null){
            mSearchPresenter.unregisterViewCallBack(this);
        }
    }

    //重新加载界面
    @Override
    protected void retry() {
        if(mSearchPresenter != null){
            mSearchPresenter.retry();
        }
    }

    //推荐词或搜索历史记录的词被点击后，数据从这里回来
    @Override
    public void onFlowTextLayoutItemClickListener(String title) {
        LogUtils.d(this,"onFlowTextLayoutItemClickListener-->" + title);
        //将被点击的条目数据传递到搜索接口进行搜索
        search(title);
        //将搜索关键词填入搜索框中
        mSearchEditText.setText(title);
        //将搜索框中的光标移动到尾部
        mSearchEditText.setSelection(title.length());
    }

    //搜索
    private void search(String s){
        if(mSearchPresenter != null){
            //将按钮文本修改为取消，并隐藏搜索推荐词和搜索历史记录列表
            mSearchOrCancelSearchButton.setText(CANCEL);
            //搜索
            mSearchPresenter.doSearch(s);
            //显示搜索结果列表
            mSmartRefreshLayout.setVisibility(View.VISIBLE);
            //隐藏
            mRecommendLayout.setVisibility(View.INVISIBLE);
            mSearchHistoriesLayout.setVisibility(View.INVISIBLE);
        }
        //隐藏键盘
        InputMethodManager inputMethodManager = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(mSearchEditText.getWindowToken(), 0);
    }

    //取消搜索
    private void cancelSearch(){
        if(mSearchPresenter != null){
            //清空搜索框
            mSearchEditText.setText("");
            //将按钮文本修改为搜索，并显示搜索推荐词和搜索历史记录列表，隐藏搜索结果列表
            mSearchOrCancelSearchButton.setText(SEARCH);
            //隐藏搜索结果列表
            mSmartRefreshLayout.setVisibility(View.INVISIBLE);
            //更新搜索的历史记录
            mSearchPresenter.loadHistory();
            //判断当前是否是搜索结果为空页面
            if(getCurrentState() == State.EMPTY){
                //切换为成功界面
                setupState(State.SUCCESS);
            }
            //显示搜索推荐词和搜索历史记录列表
            mRecommendLayout.setVisibility(View.VISIBLE);
            mSearchHistoriesLayout.setVisibility(View.VISIBLE);
            //更新搜索的历史记录
            mSearchPresenter.loadHistory();
        }
    }

    //重新加载界面
    @Override
    public void onRetry() {
        LogUtils.d(this,"onRetry-->");
        if(mSearchPresenter != null){
            mSearchPresenter.loadRecommendWords();
            //若搜索框中有搜索内容，则继续搜索
            if(!TextUtils.isEmpty(mSearchEditText.getText().toString())){
                mSearchPresenter.doSearch(mSearchEditText.getText().toString());
            }
            LogUtils.d(this,"onRetry-->doSearch");
        }
    }


    //搜索结果列表条目被点击后的数据从这里传递回来
    @Override
    public void onItemClick(ILinearItemInfo item) {
        //跳转到淘口令界面
        TicketUtils.toTicketActivity(getContext(),item);
    }
}
