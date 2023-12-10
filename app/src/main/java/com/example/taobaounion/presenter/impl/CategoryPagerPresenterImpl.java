package com.example.taobaounion.presenter.impl;

import com.example.taobaounion.moudle.bean.HomeViewPagerContent;
import com.example.taobaounion.moudle.Api;
import com.example.taobaounion.presenter.ICategoryPagerPresenter;
import com.example.taobaounion.utils.LogUtils;
import com.example.taobaounion.utils.RetrofitCreator;
import com.example.taobaounion.utils.UrlUtils;
import com.example.taobaounion.view.ICategoryPagerCallBack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CategoryPagerPresenterImpl implements ICategoryPagerPresenter {
    private static CategoryPagerPresenterImpl categoryPagerPresenter = null;

    //创建集合用于管理分类Id和页码
    private Map<Integer,Integer> homePagerInfo = new HashMap<>();

    //默认页码
    private final int DEFAULT_PAGE = 1;
    private Integer mCurrentPage;


    //提供获取单例的方法，避免切换ViewPager时，重复创建此类的对象
    public CategoryPagerPresenterImpl() {
    }

    public static CategoryPagerPresenterImpl getInstance(){
        if(categoryPagerPresenter == null){
            categoryPagerPresenter = new CategoryPagerPresenterImpl();
        }
        return categoryPagerPresenter;
    }

    //加载ViewPager数据
    @Override
    public void getContentByCategoryId(int categoryId) {
        //通知UI正在加载中
        homePagerLoadingResulTotUI(categoryId);

        //获取对应页码
        Integer targetPage = homePagerInfo.get(categoryId);
        if(targetPage == null){
            targetPage = DEFAULT_PAGE;
            homePagerInfo.put(categoryId,targetPage);
        }
        Call<HomeViewPagerContent> task = getTask(categoryId, targetPage);
        task.enqueue(new Callback<HomeViewPagerContent>() {
            @Override
            public void onResponse(Call<HomeViewPagerContent> call, Response<HomeViewPagerContent> response) {
                if(response.code() == HttpsURLConnection.HTTP_OK){
                    LogUtils.d(CategoryPagerPresenterImpl.this,"onResponse-->");
                    //获取成功
                    HomeViewPagerContent homeViewPagerContent = response.body();
                    //通知UI更新界面
                    handleGetContentSuccess(homeViewPagerContent.getData(),categoryId);
                    if(homeViewPagerContent != null){
                        LogUtils.d(CategoryPagerPresenterImpl.this,"homeViewPagerContent-->" + homeViewPagerContent);
                    }else{
                        LogUtils.d(CategoryPagerPresenterImpl.this,"homeViewPagerContent-->null");
                    }
                }
                else{
                    //加载错误
                    handleGetContentError(categoryId);
                }
            }

            @Override
            public void onFailure(Call<HomeViewPagerContent> call, Throwable t) {
                LogUtils.d(CategoryPagerPresenterImpl.this,"onFailure-->" + t);
                //网络错误，加载失败
                handleGetContentError(categoryId);
            }
        });

    }

    //获取网络请求任务
    private Call<HomeViewPagerContent> getTask(int categoryId, Integer targetPage) {
        //获取Retrofit
        Retrofit retrofit = RetrofitCreator.getInstance().getRetrofit();
        Api api = retrofit.create(Api.class);
        //创建URL
        String homeViewPagerUrl = UrlUtils.createHomeViewPagerUrl(categoryId, targetPage);
        LogUtils.d(this,"homeViewPagerUrl-->" + homeViewPagerUrl);
        Call<HomeViewPagerContent> task = api.getHomeViewPagerContent(homeViewPagerUrl);
        return task;
    }


    //通知UI数据正在加载中
    private void homePagerLoadingResulTotUI(int categoryId) {
        for (ICategoryPagerCallBack callBack : callBackList) {
            //调用UI类中的getCategoryId()方法，获取此时处理的UI界面的ID，确保数据更新到对应的页面
            if(callBack.getCategoryId() == categoryId){
                callBack.onLoading();
            }
        }
    }

    //数据加载失败通知UI
    private void handleGetContentError(int categoryId) {
        for (ICategoryPagerCallBack callBack : callBackList) {
            if(callBack.getCategoryId() == categoryId) {
                callBack.onError();
            }
        }
    }

    //数据加载成功通知UI更新
    private void handleGetContentSuccess(List<HomeViewPagerContent.DataBean> dataBeanList, int categoryId) {
        //遍历UI类的对象,逐个通知UI类更新
        for (ICategoryPagerCallBack callBack : callBackList) {
            if(callBack.getCategoryId() == categoryId) {
                if (dataBeanList == null || dataBeanList.size() == 0) {
                    //数据为空
                    callBack.onEmpty();
                } else {
                    //数据不为空
                    //轮播图数据
                    //由于轮播图没有特定的接口来请求数据，故而我们将列表数据的最后五个作为轮播图的数据
                    List<HomeViewPagerContent.DataBean> loopList = dataBeanList.subList(dataBeanList.size() - 5, dataBeanList.size());
                    callBack.onLooperListLoaded(loopList);
                    //列表数据
                    callBack.onContentLoaded(dataBeanList);
                }
            }
        }
    }

    //加载更多数据
    @Override
    public void loadMore(int categoryId) {
        //拿到当前分类页面的数据页码
        mCurrentPage = homePagerInfo.get(categoryId);
        if(mCurrentPage == null){
            mCurrentPage = 1;
        }
        //页码加一
        mCurrentPage++;
        //网络请求
        Call<HomeViewPagerContent> task = getTask(categoryId, mCurrentPage);
        task.enqueue(new Callback<HomeViewPagerContent>() {
            @Override
            public void onResponse(Call<HomeViewPagerContent> call, Response<HomeViewPagerContent> response) {
                int code = response.code();
                if(code == HttpsURLConnection.HTTP_OK){
                    HomeViewPagerContent body = response.body();
                    handleLoadMoreSuccess(body.getData(),categoryId);
                    LogUtils.d(CategoryPagerPresenterImpl.this,"body-->" + body);
                } else{
                    //网络请求错误
                    handleLoadMoreError(categoryId, mCurrentPage);
                }
            }

            @Override
            public void onFailure(Call<HomeViewPagerContent> call, Throwable t) {
                //网络请求错误
                handleLoadMoreError(categoryId, mCurrentPage);
            }
        });
    }

    //处理加载更多数据加载成功
    private void handleLoadMoreSuccess(List<HomeViewPagerContent.DataBean> dataBean, int categoryId) {
        for (ICategoryPagerCallBack callBack : callBackList) {
            if(callBack.getCategoryId() == categoryId){
                if(dataBean == null || dataBean.size() == 0){
                    //数据为空
                    callBack.onLoadMoreEmpty();
                } else{
                    //数据加载成功
                    callBack.onLoadMoreSuccess(dataBean,categoryId);
                }
            }
        }
    }

    //处理加载更多数据错误
    private void handleLoadMoreError(int categoryId,int currentPage) {
        //页码返回上一页
        currentPage--;
        //更新集合中的数据
        homePagerInfo.put(categoryId,currentPage);
        for (ICategoryPagerCallBack callBack : callBackList) {
            if(callBack.getCategoryId() == categoryId){
                callBack.onLoadMoreError(categoryId);
            }
        }
    }

    //重新加载页面
    @Override
    public void reload(int categoryId) {
        getContentByCategoryId(categoryId);
    }


    //由于首页有多个ViewPager，故会注册多个UI通知更新接口，故而此处会传来不同UI类的对象，我们应当创建一个集合来保存它们
    private List<ICategoryPagerCallBack> callBackList = new ArrayList<>();
    @Override
    public void registerViewCallBack(ICategoryPagerCallBack callback) {
        if(callBackList.contains(callback)){
            //如果传来的UI类已经存在，则不再添加进去
            return;
        }
        callBackList.add(callback);//添加到集合中
    }

    @Override
    public void unregisterViewCallBack(ICategoryPagerCallBack callback) {
        callBackList.remove(callback);
    }
}
