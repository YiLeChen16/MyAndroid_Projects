package com.example.onlineorderapp.bean;

import java.io.Serializable;
import java.math.BigDecimal;

//菜品实体类
//用于封装菜品信息
/*实现Serializable接口以实现MainActivity跳转到StoreDetailsActivity时传递此类的对象数据
 * Serializable接口提供了一种将对象转换为字节序列（serialization）的机制。
 * 通过实现Serializable接口，对象的状态可以被保存到字节流中，并在需要时重新构造。
 * 这样，在Activity之间传递对象时，可以将对象序列化为字节流，然后再进行传输。*/
public class Food implements Serializable {
    private int foodID;//菜品ID
    private String foodName;//菜品名
    private String taste;//菜品配料
    private int saleCount;//菜品销量
    private BigDecimal price;//菜品价格
    private int count;//添加到购物车中的数量
    private String foodImg;//菜品图片

    public Food() {
    }

    public Food(int foodID, String foodName, String taste, int saleCount, BigDecimal price, int count, String foodImg) {
        this.foodID = foodID;
        this.foodName = foodName;
        this.taste = taste;
        this.saleCount = saleCount;
        this.price = price;
        this.count = count;
        this.foodImg = foodImg;
    }

    /**
     * 获取
     * @return foodID
     */
    public int getFoodID() {
        return foodID;
    }

    /**
     * 设置
     * @param foodID
     */
    public void setFoodID(int foodID) {
        this.foodID = foodID;
    }

    /**
     * 获取
     * @return foodName
     */
    public String getFoodName() {
        return foodName;
    }

    /**
     * 设置
     * @param foodName
     */
    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    /**
     * 获取
     * @return taste
     */
    public String getTaste() {
        return taste;
    }

    /**
     * 设置
     * @param taste
     */
    public void setTaste(String taste) {
        this.taste = taste;
    }

    /**
     * 获取
     * @return saleCount
     */
    public int getSaleCount() {
        return saleCount;
    }

    /**
     * 设置
     * @param saleCount
     */
    public void setSaleCount(int saleCount) {
        this.saleCount = saleCount;
    }

    /**
     * 获取
     * @return price
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * 设置
     * @param price
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * 获取
     * @return count
     */
    public int getCount() {
        return count;
    }

    /**
     * 设置
     * @param count
     */
    public void setCount(int count) {
        this.count = count;
    }

    /**
     * 获取
     * @return foodImg
     */
    public String getFoodImg() {
        return foodImg;
    }

    /**
     * 设置
     * @param foodImg
     */
    public void setFoodImg(String foodImg) {
        this.foodImg = foodImg;
    }

    public String toString() {
        return "Food{foodID = " + foodID + ", foodName = " + foodName + ", taste = " + taste + ", saleCount = " + saleCount + ", price = " + price + ", count = " + count + ", foodImg = " + foodImg + "}";
    }
}
