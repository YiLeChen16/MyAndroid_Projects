package com.example.taobaounion.moudle.bean;

import com.example.taobaounion.base.IBaseInfo;

import java.util.List;

public class RedPacketContentResult {
    @Override
    public String toString() {
        return "RedPacketContentResult{" +
                "success=" + success +
                ", code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

    /**
     * success : true
     * code : 10000
     * message : 获取特惠成功.
     * data : {"tbk_dg_optimus_material_response":{"is_default":"false","result_list":{"map_data":[{"category_id":202063804,"click_url":"//s.click.taobao.com/t?e=m%3D2%26s%3DXUyZIiyGBf1w4vFB6t2Z2ueEDrYVVa64Dne87AjQPk9yINtkUhsv0O8e6e6MPM4T2FYyuHGhGghm5nS0VR3oT0KAZCke%2BMGJxC%2FP4%2FZfPFbcQmwDRwHnn1oN8CPq4PKMZiqtwk9j5QPwdDmZ4my9rNT49yORfWrUzZ2hHxb1zzBpuSEp0zXtNWtAAJPyaB8jtnV67b%2FTE2iUXBnbMaNLiX4glYdZqNkju258KePWL%2Bl%2F3GjI3ncPn1MKwonKDIk3VHe4ArYiMv4Xo3nn71y9faJn5AyUbPoV&scm=1007.15348.115058.0_3756_29924bad-a434-4df1-b90e-824525f97b4f&pvid=29924bad-a434-4df1-b90e-824525f97b4f&app_pvid=59590_33.5.176.239_866_1700135410909&ptl=floorId:3756;originalFloorId:3756;pvid:29924bad-a434-4df1-b90e-824525f97b4f;app_pvid:59590_33.5.176.239_866_1700135410909&union_lens=lensId%3AMAPI%401700135410%400_3756_29924bad-a434-4df1-b90e-824525f97b4f%401%40eyJmbG9vcklkIjozNzU2fQieie%3Bscm%3A1007.15348.115058.0_3756_29924bad-a434-4df1-b90e-824525f97b4f","commission_rate":"3.0","coupon_amount":10,"coupon_click_url":"//uland.taobao.com/coupon/edetail?e=1BdjnZMW438NfLV8niU3R5TgU2jJNKOfc4SfXBF8mZrvetYjk1b843pdQkCL7XkeillicrF1Z9sc3xBQbPUWKU2bUkf4wJa0caSVuxyYXdy4bVlo0QJT9MC60cWA%2B9iekqF%2BtS7iMektGdp2ClZRgY9THTNkOjNE%2BuahTGPkBVaQLyYDPTlhfzY0Yzs62ET%2B3hCfIVUibMEVPsSMrKa5bTdz9ckE5iaTffpMYqp3OccVjrM4%2B%2Bw0EqPyzGVR%2BfC2B3X%2BcZBhVxmjGwG5%2FUm0S6J7%2BkHL3AEW&app_pvid=59590_33.5.176.239_866_1700135410909&ptl=floorId:3756;app_pvid:59590_33.5.176.239_866_1700135410909;tpp_pvid:29924bad-a434-4df1-b90e-824525f97b4f&union_lens=lensId%3AMAPI%401700135410%400_3756_29924bad-a434-4df1-b90e-824525f97b4f%401%40eyJmbG9vcklkIjozNzU2fQieie%3Bscm%3A1007.15348.115058.0_3756_29924bad-a434-4df1-b90e-824525f97b4f","coupon_end_time":"1704988799000","coupon_remain_count":9918,"coupon_share_url":"//uland.taobao.com/coupon/edetail?e=1BdjnZMW438NfLV8niU3R5TgU2jJNKOfc4SfXBF8mZrvetYjk1b843pdQkCL7XkeillicrF1Z9sc3xBQbPUWKU2bUkf4wJa0caSVuxyYXdy4bVlo0QJT9MC60cWA%2B9iekqF%2BtS7iMektGdp2ClZRgY9THTNkOjNE%2BuahTGPkBVaQLyYDPTlhfzY0Yzs62ET%2B3hCfIVUibMEVPsSMrKa5bTdz9ckE5iaTffpMYqp3OccVjrM4%2B%2Bw0EqPyzGVR%2BfC2B3X%2BcZBhVxmjGwG5%2FUm0S6J7%2BkHL3AEW&app_pvid=59590_33.5.176.239_866_1700135410909&ptl=floorId:3756;app_pvid:59590_33.5.176.239_866_1700135410909;tpp_pvid:29924bad-a434-4df1-b90e-824525f97b4f&union_lens=lensId%3AMAPI%401700135410%400_3756_29924bad-a434-4df1-b90e-824525f97b4f%401%40eyJmbG9vcklkIjozNzU2fQieie%3Bscm%3A1007.15348.115058.0_3756_29924bad-a434-4df1-b90e-824525f97b4f","coupon_start_fee":"11.0","coupon_start_time":"1699804800000","coupon_total_count":10000,"item_description":"保护视神经 抵御蓝光 澳洲进口 学习不伤眼","item_id":"zDXv0XyhetaXZqNJ5KcYPnHdt4-6oP7bjivPnP5keWcKW","level_one_category_id":35,"level_one_category_name":"奶粉/辅食/营养品/零食","pict_url":"//img.alicdn.com/bao/uploaded/i3/2206761952961/O1CN019X9Es11XkBeDYkRJi-2206761952961.jpg","reserve_price":"169.00","seller_id":109247621504324660,"short_title":"","small_images":{"string":["//img.alicdn.com/i1/2206761952961/O1CN01XCXhuZ1XkBVXRz8eQ_!!2206761952961.png","//img.alicdn.com/i4/2206761952961/O1CN01tOZ2bY1XkBQA1BWKW_!!2206761952961.jpg","//img.alicdn.com/i1/2206761952961/O1CN01d2UHmv1XkBVY8TYvx_!!2206761952961.jpg","//img.alicdn.com/i2/2206761952961/O1CN01WkkIxy1XkBVSi0bpG_!!2206761952961.png"]},"sub_title":"保护视神经 抵御蓝光 澳洲进口 学习不伤眼","title":"【新日期】佳思敏儿童叶黄素护眼维生素软糖青少年视力澳洲保健品","tmall_play_activity_end_time":0,"tmall_play_activity_start_time":0,"user_type":1,"volume":100,"zk_final_price":"85.9"}]},"request_id":"16mk9fcekef4g"}}
     */

    private boolean success;
    private int code;
    private String message;
    private DataBean data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        @Override
        public String toString() {
            return "DataBean{" +
                    "tbk_dg_optimus_material_response=" + tbk_dg_optimus_material_response +
                    '}';
        }

        /**
         * tbk_dg_optimus_material_response : {"is_default":"false","result_list":{"map_data":[{"category_id":202063804,"click_url":"//s.click.taobao.com/t?e=m%3D2%26s%3DXUyZIiyGBf1w4vFB6t2Z2ueEDrYVVa64Dne87AjQPk9yINtkUhsv0O8e6e6MPM4T2FYyuHGhGghm5nS0VR3oT0KAZCke%2BMGJxC%2FP4%2FZfPFbcQmwDRwHnn1oN8CPq4PKMZiqtwk9j5QPwdDmZ4my9rNT49yORfWrUzZ2hHxb1zzBpuSEp0zXtNWtAAJPyaB8jtnV67b%2FTE2iUXBnbMaNLiX4glYdZqNkju258KePWL%2Bl%2F3GjI3ncPn1MKwonKDIk3VHe4ArYiMv4Xo3nn71y9faJn5AyUbPoV&scm=1007.15348.115058.0_3756_29924bad-a434-4df1-b90e-824525f97b4f&pvid=29924bad-a434-4df1-b90e-824525f97b4f&app_pvid=59590_33.5.176.239_866_1700135410909&ptl=floorId:3756;originalFloorId:3756;pvid:29924bad-a434-4df1-b90e-824525f97b4f;app_pvid:59590_33.5.176.239_866_1700135410909&union_lens=lensId%3AMAPI%401700135410%400_3756_29924bad-a434-4df1-b90e-824525f97b4f%401%40eyJmbG9vcklkIjozNzU2fQieie%3Bscm%3A1007.15348.115058.0_3756_29924bad-a434-4df1-b90e-824525f97b4f","commission_rate":"3.0","coupon_amount":10,"coupon_click_url":"//uland.taobao.com/coupon/edetail?e=1BdjnZMW438NfLV8niU3R5TgU2jJNKOfc4SfXBF8mZrvetYjk1b843pdQkCL7XkeillicrF1Z9sc3xBQbPUWKU2bUkf4wJa0caSVuxyYXdy4bVlo0QJT9MC60cWA%2B9iekqF%2BtS7iMektGdp2ClZRgY9THTNkOjNE%2BuahTGPkBVaQLyYDPTlhfzY0Yzs62ET%2B3hCfIVUibMEVPsSMrKa5bTdz9ckE5iaTffpMYqp3OccVjrM4%2B%2Bw0EqPyzGVR%2BfC2B3X%2BcZBhVxmjGwG5%2FUm0S6J7%2BkHL3AEW&app_pvid=59590_33.5.176.239_866_1700135410909&ptl=floorId:3756;app_pvid:59590_33.5.176.239_866_1700135410909;tpp_pvid:29924bad-a434-4df1-b90e-824525f97b4f&union_lens=lensId%3AMAPI%401700135410%400_3756_29924bad-a434-4df1-b90e-824525f97b4f%401%40eyJmbG9vcklkIjozNzU2fQieie%3Bscm%3A1007.15348.115058.0_3756_29924bad-a434-4df1-b90e-824525f97b4f","coupon_end_time":"1704988799000","coupon_remain_count":9918,"coupon_share_url":"//uland.taobao.com/coupon/edetail?e=1BdjnZMW438NfLV8niU3R5TgU2jJNKOfc4SfXBF8mZrvetYjk1b843pdQkCL7XkeillicrF1Z9sc3xBQbPUWKU2bUkf4wJa0caSVuxyYXdy4bVlo0QJT9MC60cWA%2B9iekqF%2BtS7iMektGdp2ClZRgY9THTNkOjNE%2BuahTGPkBVaQLyYDPTlhfzY0Yzs62ET%2B3hCfIVUibMEVPsSMrKa5bTdz9ckE5iaTffpMYqp3OccVjrM4%2B%2Bw0EqPyzGVR%2BfC2B3X%2BcZBhVxmjGwG5%2FUm0S6J7%2BkHL3AEW&app_pvid=59590_33.5.176.239_866_1700135410909&ptl=floorId:3756;app_pvid:59590_33.5.176.239_866_1700135410909;tpp_pvid:29924bad-a434-4df1-b90e-824525f97b4f&union_lens=lensId%3AMAPI%401700135410%400_3756_29924bad-a434-4df1-b90e-824525f97b4f%401%40eyJmbG9vcklkIjozNzU2fQieie%3Bscm%3A1007.15348.115058.0_3756_29924bad-a434-4df1-b90e-824525f97b4f","coupon_start_fee":"11.0","coupon_start_time":"1699804800000","coupon_total_count":10000,"item_description":"保护视神经 抵御蓝光 澳洲进口 学习不伤眼","item_id":"zDXv0XyhetaXZqNJ5KcYPnHdt4-6oP7bjivPnP5keWcKW","level_one_category_id":35,"level_one_category_name":"奶粉/辅食/营养品/零食","pict_url":"//img.alicdn.com/bao/uploaded/i3/2206761952961/O1CN019X9Es11XkBeDYkRJi-2206761952961.jpg","reserve_price":"169.00","seller_id":109247621504324660,"short_title":"","small_images":{"string":["//img.alicdn.com/i1/2206761952961/O1CN01XCXhuZ1XkBVXRz8eQ_!!2206761952961.png","//img.alicdn.com/i4/2206761952961/O1CN01tOZ2bY1XkBQA1BWKW_!!2206761952961.jpg","//img.alicdn.com/i1/2206761952961/O1CN01d2UHmv1XkBVY8TYvx_!!2206761952961.jpg","//img.alicdn.com/i2/2206761952961/O1CN01WkkIxy1XkBVSi0bpG_!!2206761952961.png"]},"sub_title":"保护视神经 抵御蓝光 澳洲进口 学习不伤眼","title":"【新日期】佳思敏儿童叶黄素护眼维生素软糖青少年视力澳洲保健品","tmall_play_activity_end_time":0,"tmall_play_activity_start_time":0,"user_type":1,"volume":100,"zk_final_price":"85.9"}]},"request_id":"16mk9fcekef4g"}
         */

        private TbkDgOptimusMaterialResponseBean tbk_dg_optimus_material_response;

        public TbkDgOptimusMaterialResponseBean getTbk_dg_optimus_material_response() {
            return tbk_dg_optimus_material_response;
        }

        public void setTbk_dg_optimus_material_response(TbkDgOptimusMaterialResponseBean tbk_dg_optimus_material_response) {
            this.tbk_dg_optimus_material_response = tbk_dg_optimus_material_response;
        }

        public static class TbkDgOptimusMaterialResponseBean {
            @Override
            public String toString() {
                return "TbkDgOptimusMaterialResponseBean{" +
                        "is_default='" + is_default + '\'' +
                        ", result_list=" + result_list +
                        ", request_id='" + request_id + '\'' +
                        '}';
            }

            /**
             * is_default : false
             * result_list : {"map_data":[{"category_id":202063804,"click_url":"//s.click.taobao.com/t?e=m%3D2%26s%3DXUyZIiyGBf1w4vFB6t2Z2ueEDrYVVa64Dne87AjQPk9yINtkUhsv0O8e6e6MPM4T2FYyuHGhGghm5nS0VR3oT0KAZCke%2BMGJxC%2FP4%2FZfPFbcQmwDRwHnn1oN8CPq4PKMZiqtwk9j5QPwdDmZ4my9rNT49yORfWrUzZ2hHxb1zzBpuSEp0zXtNWtAAJPyaB8jtnV67b%2FTE2iUXBnbMaNLiX4glYdZqNkju258KePWL%2Bl%2F3GjI3ncPn1MKwonKDIk3VHe4ArYiMv4Xo3nn71y9faJn5AyUbPoV&scm=1007.15348.115058.0_3756_29924bad-a434-4df1-b90e-824525f97b4f&pvid=29924bad-a434-4df1-b90e-824525f97b4f&app_pvid=59590_33.5.176.239_866_1700135410909&ptl=floorId:3756;originalFloorId:3756;pvid:29924bad-a434-4df1-b90e-824525f97b4f;app_pvid:59590_33.5.176.239_866_1700135410909&union_lens=lensId%3AMAPI%401700135410%400_3756_29924bad-a434-4df1-b90e-824525f97b4f%401%40eyJmbG9vcklkIjozNzU2fQieie%3Bscm%3A1007.15348.115058.0_3756_29924bad-a434-4df1-b90e-824525f97b4f","commission_rate":"3.0","coupon_amount":10,"coupon_click_url":"//uland.taobao.com/coupon/edetail?e=1BdjnZMW438NfLV8niU3R5TgU2jJNKOfc4SfXBF8mZrvetYjk1b843pdQkCL7XkeillicrF1Z9sc3xBQbPUWKU2bUkf4wJa0caSVuxyYXdy4bVlo0QJT9MC60cWA%2B9iekqF%2BtS7iMektGdp2ClZRgY9THTNkOjNE%2BuahTGPkBVaQLyYDPTlhfzY0Yzs62ET%2B3hCfIVUibMEVPsSMrKa5bTdz9ckE5iaTffpMYqp3OccVjrM4%2B%2Bw0EqPyzGVR%2BfC2B3X%2BcZBhVxmjGwG5%2FUm0S6J7%2BkHL3AEW&app_pvid=59590_33.5.176.239_866_1700135410909&ptl=floorId:3756;app_pvid:59590_33.5.176.239_866_1700135410909;tpp_pvid:29924bad-a434-4df1-b90e-824525f97b4f&union_lens=lensId%3AMAPI%401700135410%400_3756_29924bad-a434-4df1-b90e-824525f97b4f%401%40eyJmbG9vcklkIjozNzU2fQieie%3Bscm%3A1007.15348.115058.0_3756_29924bad-a434-4df1-b90e-824525f97b4f","coupon_end_time":"1704988799000","coupon_remain_count":9918,"coupon_share_url":"//uland.taobao.com/coupon/edetail?e=1BdjnZMW438NfLV8niU3R5TgU2jJNKOfc4SfXBF8mZrvetYjk1b843pdQkCL7XkeillicrF1Z9sc3xBQbPUWKU2bUkf4wJa0caSVuxyYXdy4bVlo0QJT9MC60cWA%2B9iekqF%2BtS7iMektGdp2ClZRgY9THTNkOjNE%2BuahTGPkBVaQLyYDPTlhfzY0Yzs62ET%2B3hCfIVUibMEVPsSMrKa5bTdz9ckE5iaTffpMYqp3OccVjrM4%2B%2Bw0EqPyzGVR%2BfC2B3X%2BcZBhVxmjGwG5%2FUm0S6J7%2BkHL3AEW&app_pvid=59590_33.5.176.239_866_1700135410909&ptl=floorId:3756;app_pvid:59590_33.5.176.239_866_1700135410909;tpp_pvid:29924bad-a434-4df1-b90e-824525f97b4f&union_lens=lensId%3AMAPI%401700135410%400_3756_29924bad-a434-4df1-b90e-824525f97b4f%401%40eyJmbG9vcklkIjozNzU2fQieie%3Bscm%3A1007.15348.115058.0_3756_29924bad-a434-4df1-b90e-824525f97b4f","coupon_start_fee":"11.0","coupon_start_time":"1699804800000","coupon_total_count":10000,"item_description":"保护视神经 抵御蓝光 澳洲进口 学习不伤眼","item_id":"zDXv0XyhetaXZqNJ5KcYPnHdt4-6oP7bjivPnP5keWcKW","level_one_category_id":35,"level_one_category_name":"奶粉/辅食/营养品/零食","pict_url":"//img.alicdn.com/bao/uploaded/i3/2206761952961/O1CN019X9Es11XkBeDYkRJi-2206761952961.jpg","reserve_price":"169.00","seller_id":109247621504324660,"short_title":"","small_images":{"string":["//img.alicdn.com/i1/2206761952961/O1CN01XCXhuZ1XkBVXRz8eQ_!!2206761952961.png","//img.alicdn.com/i4/2206761952961/O1CN01tOZ2bY1XkBQA1BWKW_!!2206761952961.jpg","//img.alicdn.com/i1/2206761952961/O1CN01d2UHmv1XkBVY8TYvx_!!2206761952961.jpg","//img.alicdn.com/i2/2206761952961/O1CN01WkkIxy1XkBVSi0bpG_!!2206761952961.png"]},"sub_title":"保护视神经 抵御蓝光 澳洲进口 学习不伤眼","title":"【新日期】佳思敏儿童叶黄素护眼维生素软糖青少年视力澳洲保健品","tmall_play_activity_end_time":0,"tmall_play_activity_start_time":0,"user_type":1,"volume":100,"zk_final_price":"85.9"}]}
             * request_id : 16mk9fcekef4g
             */

            private String is_default;
            private ResultListBean result_list;
            private String request_id;

            public String getIs_default() {
                return is_default;
            }

            public void setIs_default(String is_default) {
                this.is_default = is_default;
            }

            public ResultListBean getResult_list() {
                return result_list;
            }

            public void setResult_list(ResultListBean result_list) {
                this.result_list = result_list;
            }

            public String getRequest_id() {
                return request_id;
            }

            public void setRequest_id(String request_id) {
                this.request_id = request_id;
            }

            public static class ResultListBean {
                @Override
                public String toString() {
                    return "ResultListBean{" +
                            "map_data=" + map_data +
                            '}';
                }

                private List<MapDataBean> map_data;

                public List<MapDataBean> getMap_data() {
                    return map_data;
                }

                public void setMap_data(List<MapDataBean> map_data) {
                    this.map_data = map_data;
                }

                public static class MapDataBean implements IBaseInfo {
                    @Override
                    public String toString() {
                        return "MapDataBean{" +
                                "category_id=" + category_id +
                                ", click_url='" + click_url + '\'' +
                                ", commission_rate='" + commission_rate + '\'' +
                                ", coupon_amount=" + coupon_amount +
                                ", coupon_click_url='" + coupon_click_url + '\'' +
                                ", coupon_end_time='" + coupon_end_time + '\'' +
                                ", coupon_remain_count=" + coupon_remain_count +
                                ", coupon_share_url='" + coupon_share_url + '\'' +
                                ", coupon_start_fee='" + coupon_start_fee + '\'' +
                                ", coupon_start_time='" + coupon_start_time + '\'' +
                                ", coupon_total_count=" + coupon_total_count +
                                ", item_description='" + item_description + '\'' +
                                ", item_id='" + item_id + '\'' +
                                ", level_one_category_id=" + level_one_category_id +
                                ", level_one_category_name='" + level_one_category_name + '\'' +
                                ", pict_url='" + pict_url + '\'' +
                                ", reserve_price='" + reserve_price + '\'' +
                                ", seller_id=" + seller_id +
                                ", short_title='" + short_title + '\'' +
                                ", small_images=" + small_images +
                                ", sub_title='" + sub_title + '\'' +
                                ", title='" + title + '\'' +
                                ", tmall_play_activity_end_time=" + tmall_play_activity_end_time +
                                ", tmall_play_activity_start_time=" + tmall_play_activity_start_time +
                                ", user_type=" + user_type +
                                ", volume=" + volume +
                                ", zk_final_price='" + zk_final_price + '\'' +
                                '}';
                    }

                    /**
                     * category_id : 202063804
                     * click_url : //s.click.taobao.com/t?e=m%3D2%26s%3DXUyZIiyGBf1w4vFB6t2Z2ueEDrYVVa64Dne87AjQPk9yINtkUhsv0O8e6e6MPM4T2FYyuHGhGghm5nS0VR3oT0KAZCke%2BMGJxC%2FP4%2FZfPFbcQmwDRwHnn1oN8CPq4PKMZiqtwk9j5QPwdDmZ4my9rNT49yORfWrUzZ2hHxb1zzBpuSEp0zXtNWtAAJPyaB8jtnV67b%2FTE2iUXBnbMaNLiX4glYdZqNkju258KePWL%2Bl%2F3GjI3ncPn1MKwonKDIk3VHe4ArYiMv4Xo3nn71y9faJn5AyUbPoV&scm=1007.15348.115058.0_3756_29924bad-a434-4df1-b90e-824525f97b4f&pvid=29924bad-a434-4df1-b90e-824525f97b4f&app_pvid=59590_33.5.176.239_866_1700135410909&ptl=floorId:3756;originalFloorId:3756;pvid:29924bad-a434-4df1-b90e-824525f97b4f;app_pvid:59590_33.5.176.239_866_1700135410909&union_lens=lensId%3AMAPI%401700135410%400_3756_29924bad-a434-4df1-b90e-824525f97b4f%401%40eyJmbG9vcklkIjozNzU2fQieie%3Bscm%3A1007.15348.115058.0_3756_29924bad-a434-4df1-b90e-824525f97b4f
                     * commission_rate : 3.0
                     * coupon_amount : 10
                     * coupon_click_url : //uland.taobao.com/coupon/edetail?e=1BdjnZMW438NfLV8niU3R5TgU2jJNKOfc4SfXBF8mZrvetYjk1b843pdQkCL7XkeillicrF1Z9sc3xBQbPUWKU2bUkf4wJa0caSVuxyYXdy4bVlo0QJT9MC60cWA%2B9iekqF%2BtS7iMektGdp2ClZRgY9THTNkOjNE%2BuahTGPkBVaQLyYDPTlhfzY0Yzs62ET%2B3hCfIVUibMEVPsSMrKa5bTdz9ckE5iaTffpMYqp3OccVjrM4%2B%2Bw0EqPyzGVR%2BfC2B3X%2BcZBhVxmjGwG5%2FUm0S6J7%2BkHL3AEW&app_pvid=59590_33.5.176.239_866_1700135410909&ptl=floorId:3756;app_pvid:59590_33.5.176.239_866_1700135410909;tpp_pvid:29924bad-a434-4df1-b90e-824525f97b4f&union_lens=lensId%3AMAPI%401700135410%400_3756_29924bad-a434-4df1-b90e-824525f97b4f%401%40eyJmbG9vcklkIjozNzU2fQieie%3Bscm%3A1007.15348.115058.0_3756_29924bad-a434-4df1-b90e-824525f97b4f
                     * coupon_end_time : 1704988799000
                     * coupon_remain_count : 9918
                     * coupon_share_url : //uland.taobao.com/coupon/edetail?e=1BdjnZMW438NfLV8niU3R5TgU2jJNKOfc4SfXBF8mZrvetYjk1b843pdQkCL7XkeillicrF1Z9sc3xBQbPUWKU2bUkf4wJa0caSVuxyYXdy4bVlo0QJT9MC60cWA%2B9iekqF%2BtS7iMektGdp2ClZRgY9THTNkOjNE%2BuahTGPkBVaQLyYDPTlhfzY0Yzs62ET%2B3hCfIVUibMEVPsSMrKa5bTdz9ckE5iaTffpMYqp3OccVjrM4%2B%2Bw0EqPyzGVR%2BfC2B3X%2BcZBhVxmjGwG5%2FUm0S6J7%2BkHL3AEW&app_pvid=59590_33.5.176.239_866_1700135410909&ptl=floorId:3756;app_pvid:59590_33.5.176.239_866_1700135410909;tpp_pvid:29924bad-a434-4df1-b90e-824525f97b4f&union_lens=lensId%3AMAPI%401700135410%400_3756_29924bad-a434-4df1-b90e-824525f97b4f%401%40eyJmbG9vcklkIjozNzU2fQieie%3Bscm%3A1007.15348.115058.0_3756_29924bad-a434-4df1-b90e-824525f97b4f
                     * coupon_start_fee : 11.0
                     * coupon_start_time : 1699804800000
                     * coupon_total_count : 10000
                     * item_description : 保护视神经 抵御蓝光 澳洲进口 学习不伤眼
                     * item_id : zDXv0XyhetaXZqNJ5KcYPnHdt4-6oP7bjivPnP5keWcKW
                     * level_one_category_id : 35
                     * level_one_category_name : 奶粉/辅食/营养品/零食
                     * pict_url : //img.alicdn.com/bao/uploaded/i3/2206761952961/O1CN019X9Es11XkBeDYkRJi-2206761952961.jpg
                     * reserve_price : 169.00
                     * seller_id : 109247621504324660
                     * short_title :
                     * small_images : {"string":["//img.alicdn.com/i1/2206761952961/O1CN01XCXhuZ1XkBVXRz8eQ_!!2206761952961.png","//img.alicdn.com/i4/2206761952961/O1CN01tOZ2bY1XkBQA1BWKW_!!2206761952961.jpg","//img.alicdn.com/i1/2206761952961/O1CN01d2UHmv1XkBVY8TYvx_!!2206761952961.jpg","//img.alicdn.com/i2/2206761952961/O1CN01WkkIxy1XkBVSi0bpG_!!2206761952961.png"]}
                     * sub_title : 保护视神经 抵御蓝光 澳洲进口 学习不伤眼
                     * title : 【新日期】佳思敏儿童叶黄素护眼维生素软糖青少年视力澳洲保健品
                     * tmall_play_activity_end_time : 0
                     * tmall_play_activity_start_time : 0
                     * user_type : 1
                     * volume : 100
                     * zk_final_price : 85.9
                     */

                    private long category_id;
                    private String click_url;
                    private String commission_rate;
                    private long coupon_amount;
                    private String coupon_click_url;
                    private String coupon_end_time;
                    private long coupon_remain_count;
                    private String coupon_share_url;
                    private String coupon_start_fee;
                    private String coupon_start_time;
                    private long coupon_total_count;
                    private String item_description;
                    private String item_id;
                    private long level_one_category_id;
                    private String level_one_category_name;
                    private String pict_url;
                    private String reserve_price;
                    private long seller_id;
                    private String short_title;
                    private SmallImagesBean small_images;
                    private String sub_title;
                    private String title;
                    private long tmall_play_activity_end_time;
                    private long tmall_play_activity_start_time;
                    private long user_type;
                    private long volume;
                    private String zk_final_price;

                    public long getCategory_id() {
                        return category_id;
                    }

                    public void setCategory_id(int category_id) {
                        this.category_id = category_id;
                    }

                    public String getClick_url() {
                        return click_url;
                    }

                    public void setClick_url(String click_url) {
                        this.click_url = click_url;
                    }

                    public String getCommission_rate() {
                        return commission_rate;
                    }

                    public void setCommission_rate(String commission_rate) {
                        this.commission_rate = commission_rate;
                    }

                    public long getCoupon_amount() {
                        return coupon_amount;
                    }

                    public void setCoupon_amount(long coupon_amount) {
                        this.coupon_amount = coupon_amount;
                    }

                    public String getCoupon_click_url() {
                        return coupon_click_url;
                    }

                    public void setCoupon_click_url(String coupon_click_url) {
                        this.coupon_click_url = coupon_click_url;
                    }

                    public String getCoupon_end_time() {
                        return coupon_end_time;
                    }

                    public void setCoupon_end_time(String coupon_end_time) {
                        this.coupon_end_time = coupon_end_time;
                    }

                    public long getCoupon_remain_count() {
                        return coupon_remain_count;
                    }

                    public void setCoupon_remain_count(long coupon_remain_count) {
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

                    public long getCoupon_total_count() {
                        return coupon_total_count;
                    }

                    public void setCoupon_total_count(long coupon_total_count) {
                        this.coupon_total_count = coupon_total_count;
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

                    public long getLevel_one_category_id() {
                        return level_one_category_id;
                    }

                    public void setLevel_one_category_id(long level_one_category_id) {
                        this.level_one_category_id = level_one_category_id;
                    }

                    public String getLevel_one_category_name() {
                        return level_one_category_name;
                    }

                    public void setLevel_one_category_name(String level_one_category_name) {
                        this.level_one_category_name = level_one_category_name;
                    }

                    public String getPict_url() {
                        return pict_url;
                    }

                    public void setPict_url(String pict_url) {
                        this.pict_url = pict_url;
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

                    public String getSub_title() {
                        return sub_title;
                    }

                    public void setSub_title(String sub_title) {
                        this.sub_title = sub_title;
                    }

                    @Override
                    public String getPicUrl() {
                        return pict_url;
                    }

                    public String getTitle() {
                        return title;
                    }

                    @Override
                    public String getUrl() {
                        return coupon_click_url != null ? coupon_click_url : click_url;
                    }

                    public void setTitle(String title) {
                        this.title = title;
                    }

                    public long getTmall_play_activity_end_time() {
                        return tmall_play_activity_end_time;
                    }

                    public void setTmall_play_activity_end_time(int tmall_play_activity_end_time) {
                        this.tmall_play_activity_end_time = tmall_play_activity_end_time;
                    }

                    public long getTmall_play_activity_start_time() {
                        return tmall_play_activity_start_time;
                    }

                    public void setTmall_play_activity_start_time(int tmall_play_activity_start_time) {
                        this.tmall_play_activity_start_time = tmall_play_activity_start_time;
                    }

                    public long getUser_type() {
                        return user_type;
                    }

                    public void setUser_type(int user_type) {
                        this.user_type = user_type;
                    }

                    public long getVolume() {
                        return volume;
                    }

                    public void setVolume(long volume) {
                        this.volume = volume;
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
            }
        }
    }
}
