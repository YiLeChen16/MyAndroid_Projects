package com.example.taobaounion.view;

import com.example.taobaounion.base.IBaseCallBack;
import com.example.taobaounion.moudle.bean.TicketResult;

//淘口令界面数据请求结果回调接口
public interface ITicketResultCallBack extends IBaseCallBack {
    /**
     * 请求的淘口令数据从这里回来
     * @param picUrl
     * @param result
     */
    void onLoadTicketResult(String picUrl, TicketResult result);
}
