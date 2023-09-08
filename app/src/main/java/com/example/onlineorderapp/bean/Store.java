package com.example.onlineorderapp.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;

//店铺实体类
//用于封装店铺的信息
/*实现Serializable接口以实现MainActivity跳转到StoreDetailsActivity时传递此类的对象数据
* Serializable接口提供了一种将对象转换为字节序列（serialization）的机制。
* 通过实现Serializable接口，对象的状态可以被保存到字节流中，并在需要时重新构造。
* 这样，在Activity之间传递对象时，可以将对象序列化为字节流，然后再进行传输。*/
public class Store implements Serializable {
    private int id;//店铺ID
    private String storeName;//店铺名
    private String saleCount;//店铺销量
    private BigDecimal startPrice;//店铺起送价
    private int deliveryFees;//配送费
    private String welfare;//店铺福利
    private String time;//店铺配送时间
    private String storeImg;//店铺头像
    private String storeNotice;//店铺公告
    private ArrayList<Food> foodList;//店铺菜单列表


    public Store() {
    }

    public Store(int id, String storeName, String saleCount, BigDecimal startPrice, int deliveryFees, String welfare, String time, String storeImg, String storeNotice, ArrayList<Food> foodList) {
        this.id = id;
        this.storeName = storeName;
        this.saleCount = saleCount;
        this.startPrice = startPrice;
        this.deliveryFees = deliveryFees;
        this.welfare = welfare;
        this.time = time;
        this.storeImg = storeImg;
        this.storeNotice = storeNotice;
        this.foodList = foodList;
    }

    /**
     * 获取
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * 设置
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * 获取
     * @return storeName
     */
    public String getStoreName() {
        return storeName;
    }

    /**
     * 设置
     * @param storeName
     */
    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    /**
     * 获取
     * @return saleCount
     */
    public String getSaleCount() {
        return saleCount;
    }

    /**
     * 设置
     * @param saleCount
     */
    public void setSaleCount(String saleCount) {
        this.saleCount = saleCount;
    }

    /**
     * 获取
     * @return startPrice
     */
    public BigDecimal getStartPrice() {
        return startPrice;
    }

    /**
     * 设置
     * @param startPrice
     */
    public void setStartPrice(BigDecimal startPrice) {
        this.startPrice = startPrice;
    }

    /**
     * 获取
     * @return deliveryFees
     */
    public int getDeliveryFees() {
        return deliveryFees;
    }

    /**
     * 设置
     * @param deliveryFees
     */
    public void setDeliveryFees(int deliveryFees) {
        this.deliveryFees = deliveryFees;
    }

    /**
     * 获取
     * @return welfare
     */
    public String getWelfare() {
        return welfare;
    }

    /**
     * 设置
     * @param welfare
     */
    public void setWelfare(String welfare) {
        this.welfare = welfare;
    }

    /**
     * 获取
     * @return time
     */
    public String getTime() {
        return time;
    }

    /**
     * 设置
     * @param time
     */
    public void setTime(String time) {
        this.time = time;
    }

    /**
     * 获取
     * @return storeImg
     */
    public String getStoreImg() {
        return storeImg;
    }

    /**
     * 设置
     * @param storeImg
     */
    public void setStoreImg(String storeImg) {
        this.storeImg = storeImg;
    }

    /**
     * 获取
     * @return storeNotice
     */
    public String getStoreNotice() {
        return storeNotice;
    }

    /**
     * 设置
     * @param storeNotice
     */
    public void setStoreNotice(String storeNotice) {
        this.storeNotice = storeNotice;
    }

    /**
     * 获取
     * @return foodList
     */
    public ArrayList<Food> getFoodList() {
        return foodList;
    }

    /**
     * 设置
     * @param foodList
     */
    public void setFoodList(ArrayList<Food> foodList) {
        this.foodList = foodList;
    }

    public String toString() {
        return "Store{id = " + id + ", storeName = " + storeName + ", saleCount = " + saleCount + ", startPrice = " + startPrice + ", deliveryFees = " + deliveryFees + ", welfare = " + welfare + ", time = " + time + ", storeImg = " + storeImg + ", storeNotice = " + storeNotice + ", foodList = " + foodList + "}";
    }
}
