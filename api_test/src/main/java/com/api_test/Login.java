package com.api_test;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by NEDUsoftware on 2017/4/20.
 */

public class Login extends Activity implements View.OnClickListener{

    private Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        initView();
        initListener();
        Bmob.initialize(this, "d08d1c3148f3335bfabeb938162f7d8f");
    }

    private void initView() {
        btn=(Button)findViewById(R.id.btn_login);
    }
    private void initListener() {
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

    }
    private void adduser(){
        User user =new User();
        user.setUserId("LXY");
        user.setUserPassword("lxy");
        user.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                if (e==null){
                    Toast.makeText(Login.this,"添加成功",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    private void queryuser(){
        BmobQuery<User> bmobQuery=new BmobQuery<User>();
        bmobQuery.getObject()
    }
}
