package com.example.vincent.lab9;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.URL;

import java.net.HttpURLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    public static int SHOW_RESPONSE = 0;
    private Button search;
    private EditText cityName;
    private TextView city;
    private TextView time;
    private TextView temperature;
    private TextView interval;
    private TextView humidity;
    private TextView wind;
    private TextView air;
    private ListView listView;
    private LinearLayout back;
    private RecyclerView weather_horizontal;
    private static final String url = "http://ws.webxml.com.cn/WebServices/WeatherWS.asmx/getWeather";

    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            try {
                switch (msg.what) {
                    case 0:
                        back.setVisibility(View.VISIBLE);
                        ArrayList<String> list = (ArrayList<String>)msg.obj;
                        String city_ =  list.get(1) + "";
                        city.setText(city_);
                        String temp = list.get(3) + "";
                        String [] time_ = temp.split(" ");
                        time.setText(time_[1] + " 更新");
                        temp = list.get(4);
                        String [] air_ = temp.split("：|；");
                        temperature.setText(air_[2]);
                        humidity.setText("湿度：" + air_[6]);
                        wind.setText(air_[4]);
                        interval.setText(list.get(8));
                        temp = list.get(5);
                        String [] quality = temp.split("。");
                        air.setText(quality[1]);
                        temp = list.get(6);
                        String [] list_ = temp.split("：|。");
                        List<Map<String, Object>> data = new ArrayList<>();
                        ArrayList<Weather> weather_list = new ArrayList<>();
                        Map<String, Object> _temp = new LinkedHashMap<>();
                        for (int i = 0; i < list_.length; i++) {
                            if (i % 2 == 0) {
                                _temp = new LinkedHashMap<>();
                                _temp.put("title", list_[i]);
                            } else {
                                _temp.put("detail", list_[i]);
                            }
                            if (i % 2 == 1) {
                                data.add(_temp);
                            }
                        }
                        SimpleAdapter simpleAdapter = new SimpleAdapter(MainActivity.this, data, R.layout.list_item, new String[] {"title", "detail"}, new int[] { R.id.title, R.id.detail});
                        listView.setAdapter(simpleAdapter);
                        Weather temp_ = new Weather();
                        for(int i = 7; i < list.size(); i++) {
                            if ((i - 7 + 5)%5 == 0) {
                                temp_ = new Weather();
                            }
                            if (!list.get(i).contains("gif")) {
                                temp = list.get(i);
                                String [] _list = temp.split(" ");
                                if (_list.length != 1) {
                                    temp_.setDate(_list[0]);
                                    temp_.setWeather_description(_list[1]);
                                } else {
                                    temp_.setTemperature(_list[0]);
                                    i++;
                                }
                            }
                            if ((i - 7 + 5)%5 == 4) {
                                weather_list.add(temp_);
                            }

                        }
                        WeatherAdapter adapter = new WeatherAdapter(MainActivity.this, weather_list);
                        weather_horizontal.setAdapter(adapter);
                        break;
                    case 1:
                        back.setVisibility(View.GONE);
                        Toast.makeText(MainActivity.this, "该城市今日天气实况：暂无实况", Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        back.setVisibility(View.GONE);
                        Toast.makeText(MainActivity.this, "城市名输入错误，请重新输入", Toast.LENGTH_SHORT).show();
                        break;
                    case 3:
                        back.setVisibility(View.GONE);
                        Toast.makeText(MainActivity.this, "您的点击速度过快，二次查询间隔<600ms", Toast.LENGTH_SHORT).show();
                        break;
                    case 4:
                        back.setVisibility(View.GONE);
                        Toast.makeText(MainActivity.this, "免费用户24小时内访问超过规定数量50次", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        search = (Button) findViewById(R.id.search);
        cityName = (EditText) findViewById(R.id.cityName);
        city = (TextView) findViewById(R.id.city);
        time = (TextView) findViewById(R.id.time);
        temperature = (TextView) findViewById(R.id.temperature);
        interval = (TextView) findViewById(R.id.interal);
        humidity = (TextView) findViewById(R.id.humidity);
        wind = (TextView) findViewById(R.id.wind);
        air = (TextView) findViewById(R.id.air);
        listView = (ListView) findViewById(R.id.list_view);
        back = (LinearLayout) findViewById(R.id.back);
        weather_horizontal = (RecyclerView) findViewById(R.id.weather_horizontal);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        weather_horizontal.setLayoutManager(layoutManager);
        search.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.search) {
            if (isNetworkAvailable(MainActivity.this)) {
                sendRequestWithHttpUrlConnection();
            } else {
                Toast.makeText(getApplicationContext(), "当前没有可用网络！", Toast.LENGTH_LONG).show();
            }
        }
    }

    private ArrayList<String> parseXMLWithPull(String xml) throws IOException{
        ArrayList<String> list = new ArrayList<>();
        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = factory.newPullParser();
            parser.setInput(new StringReader(xml));
            int eventType = parser.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        if ("string".equals(parser.getName())) {
                            String str = parser.nextText();
                            list.add(str);
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        break;
                    default:
                        break;
                }
                eventType = parser.next();
            }
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
        return list;
    }

    private void sendRequestWithHttpUrlConnection() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                try {
                    Log.i("Key", "Begin the connection");
                    connection = (HttpURLConnection) ((new URL(url.toString()))).openConnection();
                    connection.setRequestMethod("POST");
                    connection.setReadTimeout(8000);
                    connection.setConnectTimeout(8000);
                    DataOutputStream out = new DataOutputStream(connection.getOutputStream());
                    String request = cityName.getText().toString();
                    request = URLEncoder.encode(request, "utf-8");
                    out.writeBytes("theCityCode=" + request + "&theUserID=63fb551c35014bd88bc642b9357bebc7");
                    connection.connect();
                    InputStream in = connection.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                    StringBuilder response = new StringBuilder();
                    String line;

                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                    XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                    XmlPullParser parser = factory.newPullParser();
                    parser.setInput(new StringReader(response.toString()));
                    int eventType = parser.getEventType();
                    while (eventType != XmlPullParser.END_DOCUMENT) {
                        switch (eventType) {
                            case XmlPullParser.START_TAG:
                                if ("string".equals(parser.getName())) {
                                    String str = parser.nextText();
                                    if (str.equals("今日天气实况：暂无实况")) {
                                        SHOW_RESPONSE = 1;
                                    } else if (str.equals("查询结果为空")) {
                                        Log.i("key", str);
                                        SHOW_RESPONSE = 2;
                                    } else if (str.equals("发现错误：免费用户不能使用高速访问。http://www.webxml.com.cn/")) {
                                        Log.i("key", str);
                                        SHOW_RESPONSE = 3;
                                    } else if (str.equals("发现错误：免费用户24小时内访问超过规定数量。http://www.webxml.com.cn/")) {
                                        Log.i("key", str);
                                        SHOW_RESPONSE = 4;
                                    } else {
                                        SHOW_RESPONSE = 0;
                                    }
                                }
                                break;
                            case XmlPullParser.END_TAG:
                                break;
                            default:
                                break;
                        }
                        eventType = parser.next();
                    }
                    Message msg = new Message();
                    msg.what = SHOW_RESPONSE;
                    if (SHOW_RESPONSE == 0) {
                        msg.obj = parseXMLWithPull(response.toString());
                        handler.sendMessage(msg);
                    } else {
                        handler.sendMessage(msg);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (connection != null) {
                        connection.disconnect();
                    }
                }
            }
        }).start();
    }
    public boolean isNetworkAvailable(Activity activity)
    {
        Context context = activity.getApplicationContext();
        // 获取手机所有连接管理对象（包括对wi-fi,net等连接的管理）
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivityManager == null)
        {
            return false;
        }
        else
        {
            // 获取NetworkInfo对象
            NetworkInfo[] networkInfo = connectivityManager.getAllNetworkInfo();

            if (networkInfo != null && networkInfo.length > 0)
            {
                for (int i = 0; i < networkInfo.length; i++)
                {
                    System.out.println(i + "===状态===" + networkInfo[i].getState());
                    System.out.println(i + "===类型===" + networkInfo[i].getTypeName());
                    // 判断当前网络状态是否为连接状态
                    if (networkInfo[i].getState() == NetworkInfo.State.CONNECTED)
                    {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
