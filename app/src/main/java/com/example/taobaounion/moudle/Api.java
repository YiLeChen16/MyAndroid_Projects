package com.example.taobaounion.moudle;

import com.example.taobaounion.moudle.bean.Categories;
import com.example.taobaounion.moudle.bean.HomeViewPagerContent;
import com.example.taobaounion.moudle.bean.RedPacketContentResult;
import com.example.taobaounion.moudle.bean.SearchRecommendWords;
import com.example.taobaounion.moudle.bean.SearchResult;
import com.example.taobaounion.moudle.bean.SelectedContentResult;
import com.example.taobaounion.moudle.bean.TicketParams;
import com.example.taobaounion.moudle.bean.TicketResult;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.Url;

//Retrofit请求网络的接口
public interface Api {
    //获取首页分类的数据
    @GET("discovery/categories")
    Call<Categories> getDiscoveryCategories();

    //根据分类ID获取ViewPager数据:因为每个ViewPager的URL都不同，所以需要传入来获取
    @GET
    Call<HomeViewPagerContent> getHomeViewPagerContent(@Url String url);

    //获取淘口令
    @POST("tpwd")
    Call<TicketResult> getTicket(@Body TicketParams params);


    //根据分类id获取精选界面数据
    @GET
    Call<SelectedContentResult> getSelectedContent(@Url String url);


    //获取特惠界面数据
    //由于此接口必须有参数：页码，故需要传入来获取
    @GET
    Call<RedPacketContentResult> getRedPacketContent(@Url String url);

    //搜索界面的搜索推荐词加载接口
    @GET("search/recommend")
    Call<SearchRecommendWords> getSearchRecommendWords();

    //获取搜索结果
    @GET("search")
    Call<SearchResult> getSearchResult(@Query("page")int page,@Query("keyword")String keyword);


}
