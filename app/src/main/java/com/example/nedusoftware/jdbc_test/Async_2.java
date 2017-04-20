package com.example.nedusoftware.jdbc_test;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by NEDUsoftware on 2017/3/31.
 */

public class Async_2 extends Activity implements View.OnClickListener {
    private Button button;
    private EditText editText;
    private TextView textView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thread);
        init();
        initlisten();
    }
    private void init(){
        editText=(EditText) findViewById(R.id.e_1);
        button=(Button) findViewById(R.id.b_1);
        textView=(TextView)findViewById(R.id.t_1);
    }
    private void initlisten(){
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        CalTask task=new CalTask();
        task.execute(Integer.parseInt(editText.getText().toString()));//开启异步任务
    }

    /**
     * 自定义异步任务类
     * */
    class CalTask extends AsyncTask<Integer,Integer,ArrayList>{
        //执行异步任务操作的方法
        @Override
        protected ArrayList doInBackground(Integer... params) {
            ArrayList<Integer> list=new ArrayList<Integer>();
            outer:
            for(int i=2;i<=params[0];i++){
                for (int j=2;j<=Math.sqrt(i);j++){
                    if (i!=2&&i%j==0){
                        continue outer;
                    }
                }
                list.add(i);
            }
            return list;
        }
        //任务完成后调用的方法
        @Override
        protected void onPostExecute(ArrayList arrayList) {
            textView.setText(arrayList.toString());
        }
    }

}
