package com.example.nedusoftware.jdbc_test;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;

import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by NEDUsoftware on 2017/3/30.
 */

public class MainActivity extends Activity implements View.OnClickListener{
    private EditText editText;
    private Button button;
    private TextView textView;
    private ThreadTest threadTest;
    public Handler uihandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
                textView.setText((msg.getData().getIntegerArrayList("list")).toString());
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thread);
        init();
        initlisten();
    }
    private void init(){
        editText=(EditText) findViewById(R.id.e_1);
        button=(Button) findViewById(R.id.b_1);
        textView=(TextView)findViewById(R.id.t_1);
        threadTest=new ThreadTest(uihandler);
        threadTest.start();
    }
    private void initlisten(){
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Message msg=new Message();
        msg.what=1;
        Bundle bundle=new Bundle();
        bundle.putInt("test",Integer.parseInt(editText.getText().toString()));
        msg.setData(bundle);
        threadTest.handler.sendMessage(msg);
    }
}
