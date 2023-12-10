package com.example.taobaounion;

import java.util.List;

public class SearchResult {

    /**
     * category_id : 50007071
     * category_name : 马克杯
     * commission_rate : 135
     * commission_type : COMMON
     * coupon_amount : a
     * coupon_end_time : a
     * coupon_id : a
     * coupon_info : b
     * coupon_remain_count : c
     * coupon_share_url : a
     * coupon_start_fee : a
     * coupon_start_time : a
     * coupon_total_count : c
     * include_dxjh : d
     * include_mkt : d
     * info_dxjh : {}
     * item_description : b
     * item_id : e
     * item_url : https://uland.taobao.com/item/edetail?id=amnxjbIotM44nJx4OsDazfjty-wKgkm2tQZ6kpryRf9
     * level_one_category_id : 50025004
     * level_one_category_name : 个性定制/设计服务/DIY
     * nick : f
     * num_iid : e
     * pict_url : https://img.alicdn.com/bao/uploaded/i3/2106375504/O1CN010UegbO1qWspl1pmPq_!!2106375504.jpg
     * presale_deposit : b
     * presale_end_time : c
     * presale_start_time : c
     * presale_tail_end_time : c
     * presale_tail_start_time : c
     * provcity : 浙江 杭州
     * real_post_fee : 0.00
     * reserve_price : 19.90
     * seller_id : 45248893776176300
     * shop_dsr : 46578
     * shop_title : f
     * short_title : b
     * small_images : {"string":["https://img.alicdn.com/i4/2106375504/O1CN01yAf76s1qWspmAq3WO_!!2106375504.jpg","https://img.alicdn.com/i2/2106375504/O1CN01RR8dBn1qWsi6eNI3i_!!2106375504.jpg","https://img.alicdn.com/i2/2106375504/O1CN01C53HaO1qWspn7aVMe_!!2106375504.jpg","https://img.alicdn.com/i3/2106375504/O1CN011jUovi1qWspi1P2EQ_!!2106375504.jpg"]}
     * superior_brand : 0
     * title : 新品计算机程序员编程语言钟表早餐带盖水杯咖啡陶瓷马克杯定制
     * tk_total_commi : b
     * tk_total_sales : b
     * url : //s.click.taobao.com/t?e=m%3D2%26s%3D2y6mJbfoePUcQipKwQzePOeEDrYVVa64lwnaF1WLQxlyINtkUhsv0BW9j1zKgDJ9M2CqctPx8sdm5nS0VR3oT0KAZCke%2BMGJxC%2FP4%2FZfPFbcQmwDRwHnn1oN8CPq4PKMSBaygToy7XkMOutr8mj4PMXl6pX2OnjJgfrdXQjMGaujxPyXbYYd7YlrPvoGZimRYH67npP5EEqcYLV7CJlHutlwWM6oliEazIm%2BjQ79DQ7l6zBAOdZt3Wm2t8PuBBJ7xgxdTc00KD8%3D&scm=1007.30148.309617.0&pvid=938417a5-aaf0-4fba-af6d-88195f48d2c3&app_pvid=59590_33.60.200.0_853_1700404826750&ptl=floorId:2836;originalFloorId:2836;pvid:938417a5-aaf0-4fba-af6d-88195f48d2c3;app_pvid:59590_33.60.200.0_853_1700404826750&xId=3CNW2H7ZENUBuoAVsGAmpwhWdb5tssQi0f3b5RCjYckoJyiKyd52Di7cUaAUBzKDmuqIE5vR1NeUhYyEOBDItlaBHfr1wE8NKrH4sCdRqWTh&union_lens=lensId%3AMAPI%401700404826%40213cc800_0fa6_18be80692c9_6094%4001%40eyJmbG9vcklkIjoyODM2fQieie
     * user_type : c
     * volume : c
     * white_image : b
     * x_id : 3CNW2H7ZENUBuoAVsGAmpwhWdb5tssQi0f3b5RCjYckoJyiKyd52Di7cUaAUBzKDmuqIE5vR1NeUhYyEOBDItlaBHfr1wE8NKrH4sCdRqWTh
     * zk_final_price : 19.9
     */

    private int category_id;
    private String category_name;
    private String commission_rate;
    private String commission_type;
    private String coupon_amount;
    private String coupon_end_time;
    private String coupon_id;
    private String coupon_info;
    private String coupon_remain_count;
    private String coupon_share_url;
    private String coupon_start_fee;
    private String coupon_start_time;
    private String coupon_total_count;
    private String include_dxjh;
    private String include_mkt;
    private String info_dxjh;
    private String item_description;
    private String item_id;
    private String item_url;
    private int level_one_category_id;
    private String level_one_category_name;
    private String nick;
    private String num_iid;
    private String pict_url;
    private String presale_deposit;
    private String presale_end_time;
    private String presale_start_time;
    private String presale_tail_end_time;
    private String presale_tail_start_time;
    private String provcity;
    private String real_post_fee;
    private String reserve_price;
    private long seller_id;
    private int shop_dsr;
    private String shop_title;
    private String short_title;
    private SmallImagesBean small_images;
    private String superior_brand;
    private String title;
    private String tk_total_commi;
    private String tk_total_sales;
    private String url;
    private String user_type;
    private String volume;
    private String white_image;
    private String x_id;
    private String zk_final_price;

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public String getCommission_rate() {
        return commission_rate;
    }

    public void setCommission_rate(String commission_rate) {
        this.commission_rate = commission_rate;
    }

    public String getCommission_type() {
        return commission_type;
    }

    public void setCommission_type(String commission_type) {
        this.commission_type = commission_type;
    }

    public String getCoupon_amount() {
        return coupon_amount;
    }

    public void setCoupon_amount(String coupon_amount) {
        this.coupon_amount = coupon_amount;
    }

    public String getCoupon_end_time() {
        return coupon_end_time;
    }

    public void setCoupon_end_time(String coupon_end_time) {
        this.coupon_end_time = coupon_end_time;
    }

    public String getCoupon_id() {
        return coupon_id;
    }

    public void setCoupon_id(String coupon_id) {
        this.coupon_id = coupon_id;
    }

    public String getCoupon_info() {
        return coupon_info;
    }

    public void setCoupon_info(String coupon_info) {
        this.coupon_info = coupon_info;
    }

    public String getCoupon_remain_count() {
        return coupon_remain_count;
    }

    public void setCoupon_remain_count(String coupon_remain_count) {
        this.coupon_remain_count = coupon_remain_count;
    }

    public String getCoupon_share_url() {
        return coupon_share_url;
    }

    public void setCoupon_share_url(String coupon_share_url) {
        this.coupon_share_url = coupon_share_url;
    }

    public String getCoupon_start_fee() {
        return coupon_start_fee;
    }

    public void setCoupon_start_fee(String coupon_start_fee) {
        this.coupon_start_fee = coupon_start_fee;
    }

    public String getCoupon_start_time() {
        return coupon_start_time;
    }

    public void setCoupon_start_time(String coupon_start_time) {
        this.coupon_start_time = coupon_start_time;
    }

    public String getCoupon_total_count() {
        return coupon_total_count;
    }

    public void setCoupon_total_count(String coupon_total_count) {
        this.coupon_total_count = coupon_total_count;
    }

    public String getInclude_dxjh() {
        return include_dxjh;
    }

    public void setInclude_dxjh(String include_dxjh) {
        this.include_dxjh = include_dxjh;
    }

    public String getInclude_mkt() {
        return include_mkt;
    }

    public void setInclude_mkt(String include_mkt) {
        this.include_mkt = include_mkt;
    }

    public String getInfo_dxjh() {
        return info_dxjh;
    }

    public void setInfo_dxjh(String info_dxjh) {
        this.info_dxjh = info_dxjh;
    }

    public String getItem_description() {
        return item_description;
    }

    public void setItem_description(String item_description) {
        this.item_description = item_description;
    }

    public String getItem_id() {
        return item_id;
    }

    public void setItem_id(String item_id) {
        this.item_id = item_id;
    }

    public String getItem_url() {
        return item_url;
    }

    public void setItem_url(String item_url) {
        this.item_url = item_url;
    }

    public int getLevel_one_category_id() {
        return level_one_category_id;
    }

    public void setLevel_one_category_id(int level_one_category_id) {
        this.level_one_category_id = level_one_category_id;
    }

    public String getLevel_one_category_name() {
        return level_one_category_name;
    }

    public void setLevel_one_category_name(String level_one_category_name) {
        this.level_one_category_name = level_one_category_name;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getNum_iid() {
        return num_iid;
    }

    public void setNum_iid(String num_iid) {
        this.num_iid = num_iid;
    }

    public String getPict_url() {
        return pict_url;
    }

    public void setPict_url(String pict_url) {
        this.pict_url = pict_url;
    }

    public String getPresale_deposit() {
        return presale_deposit;
    }

    public void setPresale_deposit(String presale_deposit) {
        this.presale_deposit = presale_deposit;
    }

    public String getPresale_end_time() {
        return presale_end_time;
    }

    public void setPresale_end_time(String presale_end_time) {
        this.presale_end_time = presale_end_time;
    }

    public String getPresale_start_time() {
        return presale_start_time;
    }

    public void setPresale_start_time(String presale_start_time) {
        this.presale_start_time = presale_start_time;
    }

    public String getPresale_tail_end_time() {
        return presale_tail_end_time;
    }

    public void setPresale_tail_end_time(String presale_tail_end_time) {
        this.presale_tail_end_time = presale_tail_end_time;
    }

    public String getPresale_tail_start_time() {
        return presale_tail_start_time;
    }

    public void setPresale_tail_start_time(String presale_tail_start_time) {
        this.presale_tail_start_time = presale_tail_start_time;
    }

    public String getProvcity() {
        return provcity;
    }

    public void setProvcity(String provcity) {
        this.provcity = provcity;
    }

    public String getReal_post_fee() {
        return real_post_fee;
    }

    public void setReal_post_fee(String real_post_fee) {
        this.real_post_fee = real_post_fee;
    }

    public String getReserve_price() {
        return reserve_price;
    }

    public void setReserve_price(String reserve_price) {
        this.reserve_price = reserve_price;
    }

    public long getSeller_id() {
        return seller_id;
    }

    public void setSeller_id(long seller_id) {
        this.seller_id = seller_id;
    }

    public int getShop_dsr() {
        return shop_dsr;
    }

    public void setShop_dsr(int shop_dsr) {
        this.shop_dsr = shop_dsr;
    }

    public String getShop_title() {
        return shop_title;
    }

    public void setShop_title(String shop_title) {
        this.shop_title = shop_title;
    }

    public String getShort_title() {
        return short_title;
    }

    public void setShort_title(String short_title) {
        this.short_title = short_title;
    }

    public SmallImagesBean getSmall_images() {
        return small_images;
    }

    public void setSmall_images(SmallImagesBean small_images) {
        this.small_images = small_images;
    }

    public String getSuperior_brand() {
        return superior_brand;
    }

    public void setSuperior_brand(String superior_brand) {
        this.superior_brand = superior_brand;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTk_total_commi() {
        return tk_total_commi;
    }

    public void setTk_total_commi(String tk_total_commi) {
        this.tk_total_commi = tk_total_commi;
    }

    public String getTk_total_sales() {
        return tk_total_sales;
    }

    public void setTk_total_sales(String tk_total_sales) {
        this.tk_total_sales = tk_total_sales;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUser_type() {
        return user_type;
    }

    public void setUser_type(String user_type) {
        this.user_type = user_type;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getWhite_image() {
        return white_image;
    }

    public void setWhite_image(String white_image) {
        this.white_image = white_image;
    }

    public String getX_id() {
        return x_id;
    }

    public void setX_id(String x_id) {
        this.x_id = x_id;
    }

    public String getZk_final_price() {
        return zk_final_price;
    }

    public void setZk_final_price(String zk_final_price) {
        this.zk_final_price = zk_final_price;
    }

    public static class SmallImagesBean {
        private List<String> string;

        public List<String> getString() {
            return string;
        }

        public void setString(List<String> string) {
            this.string = string;
        }
    }
}
