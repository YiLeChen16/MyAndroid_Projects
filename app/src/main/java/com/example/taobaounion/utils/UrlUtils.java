package com.example.taobaounion.utils;
//用于根据不同的分类页ID返回不同的URL
public class UrlUtils {
    public static String createHomeViewPagerUrl(int materialId,int page){
        return "discovery/" + materialId + "/" + page;
    }

    //返回指定大小的图片字符串url
    public static String getPicUrl(String picUrl,int size){
        if(picUrl.startsWith("https")){
            return picUrl + '_' + size + "x" + size + ".jpg";
        }
        return "https:" + picUrl + '_' + size + "x" + size + ".jpg";
    }
    public static String getPicUrl(String picUrl){
        if(picUrl.startsWith("https:")){
            return picUrl;
        }
        return "https:" + picUrl;
    }

    public static String getTicketUrl(String clickUrl) {
        if(clickUrl.startsWith("https") || clickUrl.startsWith("http")){
            return clickUrl;
        }
        return "https:" + clickUrl;
    }

    public static String getSelectedCategoryUrl(int categoryID) {
        return "recommend/" + categoryID;
    }

    /**
     * 根据特惠界面的页码获取页码数据
     * @param page
     * @return
     */
    public static String createRedPacketUrl(int page){
        return "onSell/" + page;
    }
}
