package com.api_test;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView t_1;
    private Button btn_1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initListener();
    }

    private void initView() {
        t_1 = (TextView) findViewById(R.id.t_1);
        btn_1 = (Button) findViewById(R.id.btn_1);
    }

    private void initListener() {
        btn_1.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Async async = new Async();
        async.execute("苏州");
    }

    public String excute(String cityName) {
        String url = "http://v.juhe.cn/weather/index?&cityname=" + cityName + "&key=a3b12671d03faa0f2d047e0980b39dbe";
        return PureNetUtil.get(url);
    }

    public String GetByCity(String city) {
        String result = excute(city);
        Log.i("abc", "GetByCity: " + result);
        if (result != null) {
            Gson gson = new Gson();
            Bean bean = gson.fromJson(result, Bean.class);
            Log.i("2", "bean: " + bean.toString());
            String code = bean.getResultcode();
            if (code != null && code.equals("200")) {
                result =  bean.getResult().getSk().toString()+bean.getResult().getToday().toString();
            } else {
                result = null;
            }
        } else {
            result = "error";
        }
        return result;
    }


    private class Async extends AsyncTask<String, Integer, String> {
        @Override
        protected String doInBackground(String... strings) {
            return GetByCity(strings[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            t_1.setText(s);
        }
    }

}
