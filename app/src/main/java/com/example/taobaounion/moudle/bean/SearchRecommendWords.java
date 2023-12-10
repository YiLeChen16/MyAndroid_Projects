package com.example.taobaounion.moudle.bean;

import java.util.List;

public class SearchRecommendWords {

    @Override
    public String toString() {
        return "SearchRecommendWords{" +
                "success=" + success +
                ", code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

    /**
     * success : true
     * code : 10000
     * message : 获取成功
     * data : [{"id":"965250727217725440","keyword":"潮流T恤","createTime":"2022-04-17 14:01"},{"id":"965250756783374336","keyword":"四件套","createTime":"2022-04-17 14:01"},{"id":"965250785011040256","keyword":"新款连衣裙","createTime":"2022-04-17 14:02"},{"id":"965250825133752320","keyword":"双肩包","createTime":"2022-04-17 14:02"}]
     */

    private boolean success;
    private int code;
    private String message;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        @Override
        public String toString() {
            return "DataBean{" +
                    "id='" + id + '\'' +
                    ", keyword='" + keyword + '\'' +
                    ", createTime='" + createTime + '\'' +
                    '}';
        }

        /**
         * id : 965250727217725440
         * keyword : 潮流T恤
         * createTime : 2022-04-17 14:01
         */

        private String id;
        private String keyword;
        private String createTime;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getKeyword() {
            return keyword;
        }

        public void setKeyword(String keyword) {
            this.keyword = keyword;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }
    }
}
