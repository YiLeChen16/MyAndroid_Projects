package com.example.taobaounion.view;

import com.example.taobaounion.base.IBaseCallBack;
import com.example.taobaounion.moudle.bean.Categories;

//主页UI分类数据的更新回调接口
public interface IHomeCallBack extends IBaseCallBack {
    //加载获取到的主页数据
    void onCategoriesLoad(Categories categories);
}
