package com.example.taobaounion;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import static org.junit.Assert.*;

import android.os.Message;

import androidx.annotation.NonNull;
import com.example.taobaounion.SearchResult;

import com.example.taobaounion.utils.LogUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    private BufferedReader bufferedReader;

    @Test
    public void addition_isCorrect() throws JSONException, IOException {
        assertEquals(4, 2 + 2);
        //String html = "<html><body><div id=\"json-data\">{\"name\": \"John\", \"age\": 30}</div></body></html>";


        OkHttpClient client = new OkHttpClient();

        // 设置要爬取的网页链接
        String search = "耳机";
        String encode = URLEncoder.encode(search, "UTF-8");
        String url = "https://shop.sunofbeach.net/search"+"?"+"keyword=" + encode;
        String result = "";
        Request request = new Request.Builder()
                .url(url)
                .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.3")
                .addHeader("Referer", "https://blog.csdn.net/?spm=1001.2101.3001.4477")
                .addHeader("Accept-Language", "en-US,en;q=0.9")
                .build();
        try {
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                String html = response.body().string();

                // 在这里可以对html进行解析和处理
                System.out.println(html);
                //利用Jsoup类对爬取到的html进行处理，去掉冗余数据
                Document parse = Jsoup.parse(html);
                System.out.println("=========================");
                Elements elements = parse.getElementsByTag("body");
                int size = elements.size();
                System.out.println(size);
                Elements scripts = elements.select("script");
                int size1 = scripts.size();
                System.out.println(size1);
                String start = "searchResult:";
                String end = ",keyword";
                String json = getSubstringBetween(scripts.html(), start, end);
                System.out.println(json);
//                Type type = new TypeToken<List<SearchResult>>(){}.getType();
//                Gson gson = new Gson();
//                List<SearchResult> list = gson.fromJson(json, type);
//                System.out.println(list.size());
            } else {
                System.out.println("网络请求失败: " + response.message());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static String getSubstringBetween(String input, String start, String end) {
        int startIndex = input.indexOf(start);
        if (startIndex == -1) {
            return null;  // 没有找到起始字符串
        }
        startIndex += start.length();

        int endIndex = input.indexOf(end, startIndex);
        if (endIndex == -1) {
            return null;  // 没有找到结束字符串
        }

        return input.substring(startIndex, endIndex);
    }
}
