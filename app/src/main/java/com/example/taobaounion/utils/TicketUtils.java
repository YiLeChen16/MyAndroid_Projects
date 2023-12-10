package com.example.taobaounion.utils;

import static com.vondear.rxtool.RxTool.getContext;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

import com.example.taobaounion.base.BaseApplication;
import com.example.taobaounion.base.IBaseInfo;
import com.example.taobaounion.presenter.impl.TicketPresenterImpl;
import com.example.taobaounion.ui.activity.TicketActivity;

//跳转到TicketActivity界面的工具类
public class TicketUtils {
    public static void toTicketActivity(Context context, IBaseInfo item){
        //获取被点击的条目数据
        String title = item.getTitle();
        String url = item.getUrl();
        String pict_url = item.getPicUrl();
        LogUtils.d(TicketUtils.class,"toTicketActivity-->picurl:" + pict_url);
        //获取请求淘口令界面数据的presenter单例
        TicketPresenterImpl ticketPresenter = PresenterManager.getInstance().getTicketPresenter();
        ticketPresenter.getTicket(pict_url,url,title);//请求数据
        //跳转到TicketActivity
        Intent intent = new Intent(BaseApplication.getBaseApplicationContext(), TicketActivity.class);
        LogUtils.d(TicketUtils.class,"跳转到淘口令界面");
        context.startActivity(intent);
    }
}
