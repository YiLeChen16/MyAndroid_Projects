package com.example.taobaounion.moudle.bean;

import java.util.ArrayList;
import java.util.List;

//保存历史记录的类
public class Histories {
    @Override
    public String toString() {
        return "Histories{" +
                "histories=" + histories +
                '}';
    }

    private List<String> histories = new ArrayList<>();

    public List<String> getHistories() {
        return histories;
    }

    public void setHistories(List<String> histories) {
        this.histories = histories;
    }
}
