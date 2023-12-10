package com.example.taobaounion.presenter;

import com.example.taobaounion.base.IBasePresenter;
import com.example.taobaounion.view.ITicketResultCallBack;

//淘口令页面请求数据
public interface ITicketPresenter extends IBasePresenter<ITicketResultCallBack> {
    //获取淘口令数据
    void getTicket(String picUrl,String clickUrl,String title);
}
