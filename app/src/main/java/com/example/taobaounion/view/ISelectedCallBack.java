package com.example.taobaounion.view;

import com.example.taobaounion.base.IBaseCallBack;
import com.example.taobaounion.moudle.bean.SelectedContentResult;

//精选界面的UI回调接口
public interface ISelectedCallBack extends IBaseCallBack {

    /**
     * presenter层请求成功的精选内容数据从这里回来
     */
    void onLoadContent(SelectedContentResult.DataBean content);
}
