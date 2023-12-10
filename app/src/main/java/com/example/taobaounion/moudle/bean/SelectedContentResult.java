package com.example.taobaounion.moudle.bean;

import com.example.taobaounion.base.IBaseInfo;

import java.util.List;

public class SelectedContentResult {

    @Override
    public String toString() {
        return "SelectedContentResult{" +
                "success=" + success +
                ", code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

    /**
     * success : true
     * code : 10000
     * message : 获取精选分类成功.
     * data : {"tbk_dg_optimus_material_response":{"is_default":"false","result_list":{"map_data":[{"category_id":127536002,"click_url":"//s.click.taobao.com/t?e=m%3D2%26s%3DJEW3ktTNjEBw4vFB6t2Z2ueEDrYVVa64Dne87AjQPk9yINtkUhsv0NV4%2F3Cr%2Bi1yuTU7pJuK%2BvRm5nS0VR3oT0KAZCke%2BMGJxC%2FP4%2FZfPFbcQmwDRwHnn1oN8CPq4PKMZiqtwk9j5QPy%2FLz5%2B0rv%2FHKrXqK7KDE39Em%2BugrRZr%2FsmjG71gMtVjACeAUG3wwYuREYBaPjITkr1qK3nAM%2BfiNbbz%2BbgXk7xNTRxwBjHl%2BVfcu95C0JSYFJUVcs6trJu12FEsynqfFjhLaTWYn5lQ%3D%3D&scm=1007.19011.125585.0_13366&pvid=51917db4-a824-42e4-aee5-8176c47eee3d&app_pvid=59590_11.248.241.4_865_1700019454756&ptl=floorId:13366;originalFloorId:13366;pvid:51917db4-a824-42e4-aee5-8176c47eee3d;app_pvid:59590_11.248.241.4_865_1700019454756&union_lens=lensId%3AMAPI%401700019455%400bf8f104_0ee1_18bd10e45cb_2bd3%4001%40eyJmbG9vcklkIjoxMzM2Nn0ie%3Bscm%3A1007.19011.125585.0_13366","commission_rate":"6.0","coupon_amount":10,"coupon_click_url":"//uland.taobao.com/coupon/edetail?e=RcslB3TLtPkNfLV8niU3R5TgU2jJNKOfc4SfXBF8mZqAM8A0pFxtp4hvpp7e3AsRAn7owPXhRZFJPslA4oTuFrBCkM8Xkj6BOuAWM3EAAG1a4rBpq3tSHdARQKkTg7tdTNpN5d1uEMcN3kpodBPwA167Shw1QDT2K0f9cvpl9IHvhbyFdjvGVH9NittIWRtFVD2t8R%2FLdg6JtE%2FEGxJZcLcZelJt%2Bzjy1DOTKQqcbfvUhO5ygTWiQfed0Ya7M0jKqnvkrv%2FI6vglM1ZJHcLCJg%3D%3D&app_pvid=59590_11.248.241.4_865_1700019454756&ptl=floorId:13366;app_pvid:59590_11.248.241.4_865_1700019454756;tpp_pvid:51917db4-a824-42e4-aee5-8176c47eee3d&union_lens=lensId%3AMAPI%401700019455%400bf8f104_0ee1_18bd10e45cb_2bd3%4001%40eyJmbG9vcklkIjoxMzM2Nn0ie%3Bscm%3A1007.19011.125585.0_13366","coupon_end_time":"1700063999000","coupon_remain_count":99000,"coupon_share_url":"//uland.taobao.com/coupon/edetail?e=RcslB3TLtPkNfLV8niU3R5TgU2jJNKOfc4SfXBF8mZqAM8A0pFxtp4hvpp7e3AsRAn7owPXhRZFJPslA4oTuFrBCkM8Xkj6BOuAWM3EAAG1a4rBpq3tSHdARQKkTg7tdTNpN5d1uEMcN3kpodBPwA167Shw1QDT2K0f9cvpl9IHvhbyFdjvGVH9NittIWRtFVD2t8R%2FLdg6JtE%2FEGxJZcLcZelJt%2Bzjy1DOTKQqcbfvUhO5ygTWiQfed0Ya7M0jKqnvkrv%2FI6vglM1ZJHcLCJg%3D%3D&app_pvid=59590_11.248.241.4_865_1700019454756&ptl=floorId:13366;app_pvid:59590_11.248.241.4_865_1700019454756;tpp_pvid:51917db4-a824-42e4-aee5-8176c47eee3d&union_lens=lensId%3AMAPI%401700019455%400bf8f104_0ee1_18bd10e45cb_2bd3%4001%40eyJmbG9vcklkIjoxMzM2Nn0ie%3Bscm%3A1007.19011.125585.0_13366","coupon_start_fee":"39.0","coupon_start_time":"1699718400000","coupon_total_count":100000,"item_description":"草本中药 清热提神 养目护目","item_id":"J5k5m02iRtqzx8BX5AtOWpFvta-gxd8mbu2brK52GuGR","level_one_category_id":21,"level_one_category_name":"居家日用","nick":"珍视明官方旗舰店","pict_url":"//gw.alicdn.com/bao/uploaded/i2/822280525/O1CN01wYNanx1FkUpbpuIn6_!!0-item_pic.jpg","reserve_price":"78.00","seller_id":73184043969099800,"shop_title":"珍视明官方旗舰店","small_images":{"string":["//img.alicdn.com/i1/822280525/O1CN01d3rpvc1FkUmzT5j3C_!!822280525.jpg","//img.alicdn.com/i2/822280525/O1CN01q4bkAH1FkUmzT6Ocq_!!822280525.jpg","//img.alicdn.com/i4/822280525/O1CN01KOJe6L1FkUoYKdpto_!!822280525.jpg","//img.alicdn.com/i3/822280525/O1CN01tWeh5f1FkUn29xD3a_!!822280525.jpg"]},"sub_title":"草本中药 清热提神 养目护目","title":"珍视明护眼贴叶黄素儿童少年花青素成人眼贴缓解眼疲劳","user_type":1,"volume":50000,"white_image":"https://img.alicdn.com/imgextra/O1CN017wY6WO1YyDOLpkhjM_!!6000000003127-0-yinhe.jpg","zk_final_price":"39"}]},"request_id":"16lwynk0231m8"}}
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
         * tbk_dg_optimus_material_response : {"is_default":"false","result_list":{"map_data":[{"category_id":127536002,"click_url":"//s.click.taobao.com/t?e=m%3D2%26s%3DJEW3ktTNjEBw4vFB6t2Z2ueEDrYVVa64Dne87AjQPk9yINtkUhsv0NV4%2F3Cr%2Bi1yuTU7pJuK%2BvRm5nS0VR3oT0KAZCke%2BMGJxC%2FP4%2FZfPFbcQmwDRwHnn1oN8CPq4PKMZiqtwk9j5QPy%2FLz5%2B0rv%2FHKrXqK7KDE39Em%2BugrRZr%2FsmjG71gMtVjACeAUG3wwYuREYBaPjITkr1qK3nAM%2BfiNbbz%2BbgXk7xNTRxwBjHl%2BVfcu95C0JSYFJUVcs6trJu12FEsynqfFjhLaTWYn5lQ%3D%3D&scm=1007.19011.125585.0_13366&pvid=51917db4-a824-42e4-aee5-8176c47eee3d&app_pvid=59590_11.248.241.4_865_1700019454756&ptl=floorId:13366;originalFloorId:13366;pvid:51917db4-a824-42e4-aee5-8176c47eee3d;app_pvid:59590_11.248.241.4_865_1700019454756&union_lens=lensId%3AMAPI%401700019455%400bf8f104_0ee1_18bd10e45cb_2bd3%4001%40eyJmbG9vcklkIjoxMzM2Nn0ie%3Bscm%3A1007.19011.125585.0_13366","commission_rate":"6.0","coupon_amount":10,"coupon_click_url":"//uland.taobao.com/coupon/edetail?e=RcslB3TLtPkNfLV8niU3R5TgU2jJNKOfc4SfXBF8mZqAM8A0pFxtp4hvpp7e3AsRAn7owPXhRZFJPslA4oTuFrBCkM8Xkj6BOuAWM3EAAG1a4rBpq3tSHdARQKkTg7tdTNpN5d1uEMcN3kpodBPwA167Shw1QDT2K0f9cvpl9IHvhbyFdjvGVH9NittIWRtFVD2t8R%2FLdg6JtE%2FEGxJZcLcZelJt%2Bzjy1DOTKQqcbfvUhO5ygTWiQfed0Ya7M0jKqnvkrv%2FI6vglM1ZJHcLCJg%3D%3D&app_pvid=59590_11.248.241.4_865_1700019454756&ptl=floorId:13366;app_pvid:59590_11.248.241.4_865_1700019454756;tpp_pvid:51917db4-a824-42e4-aee5-8176c47eee3d&union_lens=lensId%3AMAPI%401700019455%400bf8f104_0ee1_18bd10e45cb_2bd3%4001%40eyJmbG9vcklkIjoxMzM2Nn0ie%3Bscm%3A1007.19011.125585.0_13366","coupon_end_time":"1700063999000","coupon_remain_count":99000,"coupon_share_url":"//uland.taobao.com/coupon/edetail?e=RcslB3TLtPkNfLV8niU3R5TgU2jJNKOfc4SfXBF8mZqAM8A0pFxtp4hvpp7e3AsRAn7owPXhRZFJPslA4oTuFrBCkM8Xkj6BOuAWM3EAAG1a4rBpq3tSHdARQKkTg7tdTNpN5d1uEMcN3kpodBPwA167Shw1QDT2K0f9cvpl9IHvhbyFdjvGVH9NittIWRtFVD2t8R%2FLdg6JtE%2FEGxJZcLcZelJt%2Bzjy1DOTKQqcbfvUhO5ygTWiQfed0Ya7M0jKqnvkrv%2FI6vglM1ZJHcLCJg%3D%3D&app_pvid=59590_11.248.241.4_865_1700019454756&ptl=floorId:13366;app_pvid:59590_11.248.241.4_865_1700019454756;tpp_pvid:51917db4-a824-42e4-aee5-8176c47eee3d&union_lens=lensId%3AMAPI%401700019455%400bf8f104_0ee1_18bd10e45cb_2bd3%4001%40eyJmbG9vcklkIjoxMzM2Nn0ie%3Bscm%3A1007.19011.125585.0_13366","coupon_start_fee":"39.0","coupon_start_time":"1699718400000","coupon_total_count":100000,"item_description":"草本中药 清热提神 养目护目","item_id":"J5k5m02iRtqzx8BX5AtOWpFvta-gxd8mbu2brK52GuGR","level_one_category_id":21,"level_one_category_name":"居家日用","nick":"珍视明官方旗舰店","pict_url":"//gw.alicdn.com/bao/uploaded/i2/822280525/O1CN01wYNanx1FkUpbpuIn6_!!0-item_pic.jpg","reserve_price":"78.00","seller_id":73184043969099800,"shop_title":"珍视明官方旗舰店","small_images":{"string":["//img.alicdn.com/i1/822280525/O1CN01d3rpvc1FkUmzT5j3C_!!822280525.jpg","//img.alicdn.com/i2/822280525/O1CN01q4bkAH1FkUmzT6Ocq_!!822280525.jpg","//img.alicdn.com/i4/822280525/O1CN01KOJe6L1FkUoYKdpto_!!822280525.jpg","//img.alicdn.com/i3/822280525/O1CN01tWeh5f1FkUn29xD3a_!!822280525.jpg"]},"sub_title":"草本中药 清热提神 养目护目","title":"珍视明护眼贴叶黄素儿童少年花青素成人眼贴缓解眼疲劳","user_type":1,"volume":50000,"white_image":"https://img.alicdn.com/imgextra/O1CN017wY6WO1YyDOLpkhjM_!!6000000003127-0-yinhe.jpg","zk_final_price":"39"}]},"request_id":"16lwynk0231m8"}
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
             * result_list : {"map_data":[{"category_id":127536002,"click_url":"//s.click.taobao.com/t?e=m%3D2%26s%3DJEW3ktTNjEBw4vFB6t2Z2ueEDrYVVa64Dne87AjQPk9yINtkUhsv0NV4%2F3Cr%2Bi1yuTU7pJuK%2BvRm5nS0VR3oT0KAZCke%2BMGJxC%2FP4%2FZfPFbcQmwDRwHnn1oN8CPq4PKMZiqtwk9j5QPy%2FLz5%2B0rv%2FHKrXqK7KDE39Em%2BugrRZr%2FsmjG71gMtVjACeAUG3wwYuREYBaPjITkr1qK3nAM%2BfiNbbz%2BbgXk7xNTRxwBjHl%2BVfcu95C0JSYFJUVcs6trJu12FEsynqfFjhLaTWYn5lQ%3D%3D&scm=1007.19011.125585.0_13366&pvid=51917db4-a824-42e4-aee5-8176c47eee3d&app_pvid=59590_11.248.241.4_865_1700019454756&ptl=floorId:13366;originalFloorId:13366;pvid:51917db4-a824-42e4-aee5-8176c47eee3d;app_pvid:59590_11.248.241.4_865_1700019454756&union_lens=lensId%3AMAPI%401700019455%400bf8f104_0ee1_18bd10e45cb_2bd3%4001%40eyJmbG9vcklkIjoxMzM2Nn0ie%3Bscm%3A1007.19011.125585.0_13366","commission_rate":"6.0","coupon_amount":10,"coupon_click_url":"//uland.taobao.com/coupon/edetail?e=RcslB3TLtPkNfLV8niU3R5TgU2jJNKOfc4SfXBF8mZqAM8A0pFxtp4hvpp7e3AsRAn7owPXhRZFJPslA4oTuFrBCkM8Xkj6BOuAWM3EAAG1a4rBpq3tSHdARQKkTg7tdTNpN5d1uEMcN3kpodBPwA167Shw1QDT2K0f9cvpl9IHvhbyFdjvGVH9NittIWRtFVD2t8R%2FLdg6JtE%2FEGxJZcLcZelJt%2Bzjy1DOTKQqcbfvUhO5ygTWiQfed0Ya7M0jKqnvkrv%2FI6vglM1ZJHcLCJg%3D%3D&app_pvid=59590_11.248.241.4_865_1700019454756&ptl=floorId:13366;app_pvid:59590_11.248.241.4_865_1700019454756;tpp_pvid:51917db4-a824-42e4-aee5-8176c47eee3d&union_lens=lensId%3AMAPI%401700019455%400bf8f104_0ee1_18bd10e45cb_2bd3%4001%40eyJmbG9vcklkIjoxMzM2Nn0ie%3Bscm%3A1007.19011.125585.0_13366","coupon_end_time":"1700063999000","coupon_remain_count":99000,"coupon_share_url":"//uland.taobao.com/coupon/edetail?e=RcslB3TLtPkNfLV8niU3R5TgU2jJNKOfc4SfXBF8mZqAM8A0pFxtp4hvpp7e3AsRAn7owPXhRZFJPslA4oTuFrBCkM8Xkj6BOuAWM3EAAG1a4rBpq3tSHdARQKkTg7tdTNpN5d1uEMcN3kpodBPwA167Shw1QDT2K0f9cvpl9IHvhbyFdjvGVH9NittIWRtFVD2t8R%2FLdg6JtE%2FEGxJZcLcZelJt%2Bzjy1DOTKQqcbfvUhO5ygTWiQfed0Ya7M0jKqnvkrv%2FI6vglM1ZJHcLCJg%3D%3D&app_pvid=59590_11.248.241.4_865_1700019454756&ptl=floorId:13366;app_pvid:59590_11.248.241.4_865_1700019454756;tpp_pvid:51917db4-a824-42e4-aee5-8176c47eee3d&union_lens=lensId%3AMAPI%401700019455%400bf8f104_0ee1_18bd10e45cb_2bd3%4001%40eyJmbG9vcklkIjoxMzM2Nn0ie%3Bscm%3A1007.19011.125585.0_13366","coupon_start_fee":"39.0","coupon_start_time":"1699718400000","coupon_total_count":100000,"item_description":"草本中药 清热提神 养目护目","item_id":"J5k5m02iRtqzx8BX5AtOWpFvta-gxd8mbu2brK52GuGR","level_one_category_id":21,"level_one_category_name":"居家日用","nick":"珍视明官方旗舰店","pict_url":"//gw.alicdn.com/bao/uploaded/i2/822280525/O1CN01wYNanx1FkUpbpuIn6_!!0-item_pic.jpg","reserve_price":"78.00","seller_id":73184043969099800,"shop_title":"珍视明官方旗舰店","small_images":{"string":["//img.alicdn.com/i1/822280525/O1CN01d3rpvc1FkUmzT5j3C_!!822280525.jpg","//img.alicdn.com/i2/822280525/O1CN01q4bkAH1FkUmzT6Ocq_!!822280525.jpg","//img.alicdn.com/i4/822280525/O1CN01KOJe6L1FkUoYKdpto_!!822280525.jpg","//img.alicdn.com/i3/822280525/O1CN01tWeh5f1FkUn29xD3a_!!822280525.jpg"]},"sub_title":"草本中药 清热提神 养目护目","title":"珍视明护眼贴叶黄素儿童少年花青素成人眼贴缓解眼疲劳","user_type":1,"volume":50000,"white_image":"https://img.alicdn.com/imgextra/O1CN017wY6WO1YyDOLpkhjM_!!6000000003127-0-yinhe.jpg","zk_final_price":"39"}]}
             * request_id : 16lwynk0231m8
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
                                ", nick='" + nick + '\'' +
                                ", pict_url='" + pict_url + '\'' +
                                ", reserve_price='" + reserve_price + '\'' +
                                ", seller_id=" + seller_id +
                                ", shop_title='" + shop_title + '\'' +
                                ", small_images=" + small_images +
                                ", sub_title='" + sub_title + '\'' +
                                ", title='" + title + '\'' +
                                ", user_type=" + user_type +
                                ", volume=" + volume +
                                ", white_image='" + white_image + '\'' +
                                ", zk_final_price='" + zk_final_price + '\'' +
                                '}';
                    }

                    /**
                     * category_id : 127536002
                     * click_url : //s.click.taobao.com/t?e=m%3D2%26s%3DJEW3ktTNjEBw4vFB6t2Z2ueEDrYVVa64Dne87AjQPk9yINtkUhsv0NV4%2F3Cr%2Bi1yuTU7pJuK%2BvRm5nS0VR3oT0KAZCke%2BMGJxC%2FP4%2FZfPFbcQmwDRwHnn1oN8CPq4PKMZiqtwk9j5QPy%2FLz5%2B0rv%2FHKrXqK7KDE39Em%2BugrRZr%2FsmjG71gMtVjACeAUG3wwYuREYBaPjITkr1qK3nAM%2BfiNbbz%2BbgXk7xNTRxwBjHl%2BVfcu95C0JSYFJUVcs6trJu12FEsynqfFjhLaTWYn5lQ%3D%3D&scm=1007.19011.125585.0_13366&pvid=51917db4-a824-42e4-aee5-8176c47eee3d&app_pvid=59590_11.248.241.4_865_1700019454756&ptl=floorId:13366;originalFloorId:13366;pvid:51917db4-a824-42e4-aee5-8176c47eee3d;app_pvid:59590_11.248.241.4_865_1700019454756&union_lens=lensId%3AMAPI%401700019455%400bf8f104_0ee1_18bd10e45cb_2bd3%4001%40eyJmbG9vcklkIjoxMzM2Nn0ie%3Bscm%3A1007.19011.125585.0_13366
                     * commission_rate : 6.0
                     * coupon_amount : 10
                     * coupon_click_url : //uland.taobao.com/coupon/edetail?e=RcslB3TLtPkNfLV8niU3R5TgU2jJNKOfc4SfXBF8mZqAM8A0pFxtp4hvpp7e3AsRAn7owPXhRZFJPslA4oTuFrBCkM8Xkj6BOuAWM3EAAG1a4rBpq3tSHdARQKkTg7tdTNpN5d1uEMcN3kpodBPwA167Shw1QDT2K0f9cvpl9IHvhbyFdjvGVH9NittIWRtFVD2t8R%2FLdg6JtE%2FEGxJZcLcZelJt%2Bzjy1DOTKQqcbfvUhO5ygTWiQfed0Ya7M0jKqnvkrv%2FI6vglM1ZJHcLCJg%3D%3D&app_pvid=59590_11.248.241.4_865_1700019454756&ptl=floorId:13366;app_pvid:59590_11.248.241.4_865_1700019454756;tpp_pvid:51917db4-a824-42e4-aee5-8176c47eee3d&union_lens=lensId%3AMAPI%401700019455%400bf8f104_0ee1_18bd10e45cb_2bd3%4001%40eyJmbG9vcklkIjoxMzM2Nn0ie%3Bscm%3A1007.19011.125585.0_13366
                     * coupon_end_time : 1700063999000
                     * coupon_remain_count : 99000
                     * coupon_share_url : //uland.taobao.com/coupon/edetail?e=RcslB3TLtPkNfLV8niU3R5TgU2jJNKOfc4SfXBF8mZqAM8A0pFxtp4hvpp7e3AsRAn7owPXhRZFJPslA4oTuFrBCkM8Xkj6BOuAWM3EAAG1a4rBpq3tSHdARQKkTg7tdTNpN5d1uEMcN3kpodBPwA167Shw1QDT2K0f9cvpl9IHvhbyFdjvGVH9NittIWRtFVD2t8R%2FLdg6JtE%2FEGxJZcLcZelJt%2Bzjy1DOTKQqcbfvUhO5ygTWiQfed0Ya7M0jKqnvkrv%2FI6vglM1ZJHcLCJg%3D%3D&app_pvid=59590_11.248.241.4_865_1700019454756&ptl=floorId:13366;app_pvid:59590_11.248.241.4_865_1700019454756;tpp_pvid:51917db4-a824-42e4-aee5-8176c47eee3d&union_lens=lensId%3AMAPI%401700019455%400bf8f104_0ee1_18bd10e45cb_2bd3%4001%40eyJmbG9vcklkIjoxMzM2Nn0ie%3Bscm%3A1007.19011.125585.0_13366
                     * coupon_start_fee : 39.0
                     * coupon_start_time : 1699718400000
                     * coupon_total_count : 100000
                     * item_description : 草本中药 清热提神 养目护目
                     * item_id : J5k5m02iRtqzx8BX5AtOWpFvta-gxd8mbu2brK52GuGR
                     * level_one_category_id : 21
                     * level_one_category_name : 居家日用
                     * nick : 珍视明官方旗舰店
                     * pict_url : //gw.alicdn.com/bao/uploaded/i2/822280525/O1CN01wYNanx1FkUpbpuIn6_!!0-item_pic.jpg
                     * reserve_price : 78.00
                     * seller_id : 73184043969099800
                     * shop_title : 珍视明官方旗舰店
                     * small_images : {"string":["//img.alicdn.com/i1/822280525/O1CN01d3rpvc1FkUmzT5j3C_!!822280525.jpg","//img.alicdn.com/i2/822280525/O1CN01q4bkAH1FkUmzT6Ocq_!!822280525.jpg","//img.alicdn.com/i4/822280525/O1CN01KOJe6L1FkUoYKdpto_!!822280525.jpg","//img.alicdn.com/i3/822280525/O1CN01tWeh5f1FkUn29xD3a_!!822280525.jpg"]}
                     * sub_title : 草本中药 清热提神 养目护目
                     * title : 珍视明护眼贴叶黄素儿童少年花青素成人眼贴缓解眼疲劳
                     * user_type : 1
                     * volume : 50000
                     * white_image : https://img.alicdn.com/imgextra/O1CN017wY6WO1YyDOLpkhjM_!!6000000003127-0-yinhe.jpg
                     * zk_final_price : 39
                     */

                    private int category_id;
                    private String click_url;
                    private String commission_rate;
                    private int coupon_amount;
                    private String coupon_click_url;
                    private String coupon_end_time;
                    private int coupon_remain_count;
                    private String coupon_share_url;
                    private String coupon_start_fee;
                    private String coupon_start_time;
                    private int coupon_total_count;
                    private String item_description;
                    private String item_id;
                    private int level_one_category_id;
                    private String level_one_category_name;
                    private String nick;
                    private String pict_url;
                    private String reserve_price;
                    private long seller_id;
                    private String shop_title;
                    private SmallImagesBean small_images;
                    private String sub_title;
                    private String title;
                    private int user_type;
                    private int volume;
                    private String white_image;
                    private String zk_final_price;

                    public int getCategory_id() {
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

                    public int getCoupon_amount() {
                        return coupon_amount;
                    }

                    public void setCoupon_amount(int coupon_amount) {
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

                    public int getCoupon_remain_count() {
                        return coupon_remain_count;
                    }

                    public void setCoupon_remain_count(int coupon_remain_count) {
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

                    public int getCoupon_total_count() {
                        return coupon_total_count;
                    }

                    public void setCoupon_total_count(int coupon_total_count) {
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

                    public String getShop_title() {
                        return shop_title;
                    }

                    public void setShop_title(String shop_title) {
                        this.shop_title = shop_title;
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

                    public int getUser_type() {
                        return user_type;
                    }

                    public void setUser_type(int user_type) {
                        this.user_type = user_type;
                    }

                    public int getVolume() {
                        return volume;
                    }

                    public void setVolume(int volume) {
                        this.volume = volume;
                    }

                    public String getWhite_image() {
                        return white_image;
                    }

                    public void setWhite_image(String white_image) {
                        this.white_image = white_image;
                    }

                    public String getZk_final_price() {
                        return zk_final_price;
                    }

                    public void setZk_final_price(String zk_final_price) {
                        this.zk_final_price = zk_final_price;
                    }

                    public static class SmallImagesBean {
                        @Override
                        public String toString() {
                            return "SmallImagesBean{" +
                                    "string=" + string +
                                    '}';
                        }

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
