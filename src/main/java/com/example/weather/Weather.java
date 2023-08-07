package com.example.weather;
//此类用于表示某个城市一天的的所有天气信息
public class Weather {
    private String cityName;
    private String weather;
    private String temper;
    private String nowTemper;
    private String wind;
    private String UV;
    private String AP;


    public Weather() {
    }

    public Weather(String cityName, String weather, String temper, String nowTemper, String wind, String UV, String AP) {
        this.cityName = cityName;
        this.weather = weather;
        this.temper = temper;
        this.nowTemper = nowTemper;
        this.wind = wind;
        this.UV = UV;
        this.AP = AP;
    }

    /**
     * 获取
     * @return cityName
     */
    public String getCityName() {
        return cityName;
    }

    /**
     * 设置
     * @param cityName
     */
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    /**
     * 获取
     * @return weather
     */
    public String getWeather() {
        return weather;
    }

    /**
     * 设置
     * @param weather
     */
    public void setWeather(String weather) {
        this.weather = weather;
    }

    /**
     * 获取
     * @return temper
     */
    public String getTemper() {
        return temper;
    }

    /**
     * 设置
     * @param temper
     */
    public void setTemper(String temper) {
        this.temper = temper;
    }

    /**
     * 获取
     * @return nowTemper
     */
    public String getNowTemper() {
        return nowTemper;
    }

    /**
     * 设置
     * @param nowTemper
     */
    public void setNowTemper(String nowTemper) {
        this.nowTemper = nowTemper;
    }

    /**
     * 获取
     * @return wind
     */
    public String getWind() {
        return wind;
    }

    /**
     * 设置
     * @param wind
     */
    public void setWind(String wind) {
        this.wind = wind;
    }

    /**
     * 获取
     * @return UV
     */
    public String getUV() {
        return UV;
    }

    /**
     * 设置
     * @param UV
     */
    public void setUV(String UV) {
        this.UV = UV;
    }

    /**
     * 获取
     * @return AP
     */
    public String getAP() {
        return AP;
    }

    /**
     * 设置
     * @param AP
     */
    public void setAP(String AP) {
        this.AP = AP;
    }

    public String toString() {
        return "Weather{cityName = " + cityName + ", weather = " + weather + ", temper = " + temper + ", nowTemper = " + nowTemper + ", wind = " + wind + ", UV = " + UV + ", AP = " + AP + "}";
    }
}
