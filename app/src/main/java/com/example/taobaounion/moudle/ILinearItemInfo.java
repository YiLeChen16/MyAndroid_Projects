package com.example.taobaounion.moudle;

import com.example.taobaounion.base.IBaseInfo;

//用于规范适配器数据的接口
//由于首页的列表与搜索页的搜索结果列表是一样的，故可考虑；
// 两个列表采用同一个适配器，但两个列表请求回来的数据不一样，
// 一个为HomeViewPagerContent，另一个为SearchResult，但实际使用到的数据却是一样的
// 故可定义一个接口来统一数据格式，此接口就是这个作用
public interface ILinearItemInfo extends IBaseInfo {

    /**
     * 获取原价
     * @return
     */
    String getNormalPrice();

    /**
     * 获取优惠金额
     * @return
     */
    long getDiscount();


    /**
     * 获取销售量
     * @return
     */
    long getSellCount();


}
