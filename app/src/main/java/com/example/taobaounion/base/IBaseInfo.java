package com.example.taobaounion.base;
//此接口用于获取三个特定的数据以供TicketActivity界面使用
//由于此处TicketActivity界面只需要三个数据，故而此处可以定义一个统一的接口IBaseInfo来统一规范，简化代码
public interface IBaseInfo {
    /**
     *
     * @return 获取商品图片的URL
     */
    String getPicUrl();

    /**
     *
     * @return 获取图片的标题
     */
    String getTitle();

    /**
     *
     * @return 获取商品的优惠券url或者详情url
     */
    String getUrl();
}
