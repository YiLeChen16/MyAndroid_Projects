package com.example.weather;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.qweather.sdk.bean.base.Lang;
import com.qweather.sdk.bean.base.Unit;
import com.qweather.sdk.bean.geo.GeoBean;
import com.qweather.sdk.bean.weather.WeatherDailyBean;
import com.qweather.sdk.bean.weather.WeatherNowBean;
import com.qweather.sdk.view.HeConfig;
import com.qweather.sdk.view.QWeather;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    //声明控件
    protected TextView tv_city, tv_weather, tv_temper, tv_now_temper, tv_wind, tv_UV, tv_AP;
    protected ImageView iv_weather;
    protected ImageButton ib_search;
    protected EditText ed_input;

    //声明变量
    private String nowTemper;

    //在主线程中创建并绑定Handler机制，用于处理子线程发送过来的信息
    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            //将子线程传过来的Weather类的对象进行处理，以更新UI
            Weather weather1 = (Weather) msg.obj;
            if (weather1 == null) {
                Log.d("tag","UI更新失败，weather为null");
                Toast.makeText(MainActivity.this, "查询失败，请重试！", Toast.LENGTH_SHORT).show();
                return false;
            } else {
                //更新UI
                Log.d("tag","正在更新UI");
                tv_city.setText(weather1.getCityName());
                tv_weather.setText(weather1.getWeather());
                tv_temper.setText(weather1.getTemper());
                tv_now_temper.setText(weather1.getNowTemper());
                tv_wind.setText(weather1.getWind());
                tv_UV.setText(weather1.getUV());
                tv_AP.setText(weather1.getAP());
                matchWeatherImage(weather1.getWeather());
                Toast.makeText(MainActivity.this, "查询成功！", Toast.LENGTH_SHORT).show();
                return true;
            }

        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        //使用和风天气的SDK来查询天气
        //初始化账户
        HeConfig.init("HE2308061018111892", "978dd4d47bd04cdb99b8be6edb779938");
        //切换至免费订阅
        HeConfig.switchToDevService();

    }

    //利用和风天气API查询天气，耗时操作，开启子线程进行申请查询
    private void queryWeather(String cityName) {

        //开启子线程进行查询
        new Thread(new Runnable() {
            @Override
            public void run() {

                //首先查询城市的id
                //设置只返回一个查询结果
                QWeather.getGeoCityLookup(getApplicationContext(), cityName, 1, Lang.EN, new QWeather.OnResultGeoListener() {
                    @Override
                    public void onError(Throwable throwable) {
                        Log.d("tag", "查询不到该城市的信息");
                        //将Toast推送到主线程进行显示
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(MainActivity.this, "查询不到该城市的信息", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }

                    @Override
                    public void onSuccess(GeoBean geoBean) {
                        Log.d("tag", "查询成功！");
                        List<GeoBean.LocationBean> locationBean = geoBean.getLocationBean();
                        //获取查询的城市对应的id
                        String id = locationBean.get(0).getId();
                        Log.d("tag", id);
                        //根据id查询当前气温
                        QWeather.getWeatherNow(getApplicationContext(), id, new QWeather.OnResultWeatherNowListener() {
                            @Override
                            public void onError(Throwable throwable) {
                                Log.d("tag", "当前气温查询失败");
                                nowTemper = null;
                            }

                            @Override
                            public void onSuccess(WeatherNowBean weatherNowBean) {
                                Log.d("tag", "当前气温查询成功！");
                                WeatherNowBean.NowBaseBean now = weatherNowBean.getNow();
                                String temp = now.getTemp();
                                nowTemper = temp + "℃";
                                Log.d("tag",nowTemper);
                            }
                        });
                        //根据id查询天气详情
                        QWeather.getWeather3D(getApplicationContext(), id, new QWeather.OnResultWeatherDailyListener() {
                            @Override
                            public void onError(Throwable throwable) {
                                Log.d("tag",throwable.toString());
                                Log.d("tag", "城市天气查询失败!!!");
                            }

                            @Override
                            public void onSuccess(WeatherDailyBean weatherDailyBean) {
                                Log.d("tag", "城市天气查询成功！");
                                //获取该城市未来三天的天气
                                List<WeatherDailyBean.DailyBean> weathers = weatherDailyBean.getDaily();
                                //此处只查询一天的天气
                                WeatherDailyBean.DailyBean dailyBean = weathers.get(0);
                                //获取当天天气描述
                                String weatherText = dailyBean.getTextDay();
                                //获取当天最高温度
                                String tempMax = dailyBean.getTempMax() + "℃";
                                //获取当天最低温度
                                String tempMin = dailyBean.getTempMin() + "℃";
                                //获取当天风力
                                String wind = dailyBean.getWindScaleDay();
                                //获取当天紫外线强度
                                String uvIndex = dailyBean.getUvIndex();
                                //获取当天大气压强
                                String pressure = dailyBean.getPressure();
                                Weather weather;
                                //判断当前气温是否查询成功
                                if (nowTemper != null) {
                                    Log.d("tag", "当前气温查询成功");
                                    //将所有查询到的数据封装为一个weather对象
                                    weather = new Weather(cityName, weatherText,
                                            tempMin + "/" + tempMax, nowTemper, wind, uvIndex, pressure);
                                } else {
                                    weather = new Weather();
                                    weather = null;
                                }
                                //利用Handler机制将weather对象发送给主线程
                                Log.d("tag","正在发送信息给主线程");
                                Message message = Message.obtain();
                                message.obj = weather;
                                handler.sendMessage(message);
                            }
                        });
                    }
                });

            }
        }).start();
    }


    //根据天气匹配对应的天气图标
    private void matchWeatherImage(String weatherText) {
        if(weatherText.equals("晴")){
            //加载晴天天气图标
            iv_weather.setImageResource(R.drawable._id100);
        }
        else if(weatherText.equals("多云")){
            //加载多云图标
            iv_weather.setImageResource(R.drawable._id101);
        }
        else if(weatherText.equals("少云")){
            iv_weather.setImageResource(R.drawable._id102);
        }
        else if(weatherText.equals("阴")){
            iv_weather.setImageResource(R.drawable._id104);
        }
        else if(weatherText.contains("雨")){
            if(weatherText.equals("雷阵雨")){
                iv_weather.setImageResource(R.drawable._id302);
            }
            else if (weatherText.equals("雨夹雪")){
                iv_weather.setImageResource(R.drawable._id404);
            }
            else{
                iv_weather.setImageResource(R.drawable._id300);
            }
        }
        else if(weatherText.equals("沙尘暴")){
            iv_weather.setImageResource(R.drawable._id507);
        }
        else if (weatherText.contains("雪")) {
            iv_weather.setImageResource(R.drawable._id499);
        }
        else if (weatherText.contains("雾")) {
            iv_weather.setImageResource(R.drawable._id501);
        }
        else if (weatherText.contains("霾")) {
            iv_weather.setImageResource(R.drawable._id502);
        }
        else if(weatherText.contains("冰雹")){
            iv_weather.setImageResource(R.drawable._id313);
        }
        else{
            //未知天气
            iv_weather.setImageResource(R.drawable._id999);
        }
    }

    //初始化控件
    private void init() {
        //找到控件
        tv_city = findViewById(R.id.tv_cityName);
        tv_weather = findViewById(R.id.tv_weather);
        tv_temper = findViewById(R.id.tv_temper);
        tv_now_temper = findViewById(R.id.tv_now_temper);
        tv_wind = findViewById(R.id.tv_wind);
        tv_UV = findViewById(R.id.tv_UV);
        tv_AP = findViewById(R.id.tv_AP);
        iv_weather = findViewById(R.id.iv_weather);
        ib_search = findViewById(R.id.ib_search);
        ed_input = findViewById(R.id.ed_input);

        //为按钮设置监听事件
        ib_search.setOnClickListener(this);

        //初始化界面显示的数据，默认显示潮州的天气
        queryWeather("潮州");

    }

    //点击按钮查询输入框输入的城市的天气
    @Override
    public void onClick(View v) {
        //获取编辑框的内容
        String cityName = ed_input.getText().toString();
        //查询城市天气
        queryWeather(cityName);
    }
}