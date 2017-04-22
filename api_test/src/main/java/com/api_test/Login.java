package com.api_test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by NEDUsoftware on 2017/4/20.
 */

public class Login extends Activity implements View.OnClickListener {

    private Button btn_login;
    private EditText et_id;
    private EditText et_psd;
    private Button btn_setup;
    private EditText et_setup_id;
    private EditText et_setup_psd;
    private String psd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        initView();
        initListener();
        Bmob.initialize(this, "d08d1c3148f3335bfabeb938162f7d8f");
    }

    private void initView() {
        btn_login = (Button) findViewById(R.id.btn_login);
        et_id = (EditText) findViewById(R.id.Et_login_Id);
        et_psd = (EditText) findViewById(R.id.Et_login_password);
        btn_setup = (Button) findViewById(R.id.btn_setup);
        et_setup_id = (EditText) findViewById(R.id.Et_setup_Id);
        et_setup_psd = (EditText) findViewById(R.id.Et_setup_password);

    }

    private void initListener() {
        btn_login.setOnClickListener(this);
        btn_setup.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                queryuser();
                break;
            case R.id.btn_setup:
                adduser();
                break;
        }
    }

    private void adduser() {
        if ((et_setup_id.getText().equals("")) || (et_setup_psd.getText().toString().equals(""))) {
            Toast.makeText(Login.this, "请输入账号密码", Toast.LENGTH_SHORT).show();
        } else {
            Md5 md5 = new Md5();
            psd = md5.getmd5(et_setup_psd.getText().toString());
            BmobQuery<User> bmobQuery = new BmobQuery<User>();
            bmobQuery.addWhereEqualTo("userId", et_setup_id.getText());
            bmobQuery.findObjects(new FindListener<User>() {
                @Override
                public void done(List<User> list, BmobException e) {
                    if (list.size() == 0) {
                        User user = new User();
                        user.setUserId(et_setup_id.getText().toString());
                        user.setUserPassword(psd);
                        user.save(new SaveListener<String>() {
                            @Override
                            public void done(String s, BmobException e) {
                                if (e == null) {
                                    Toast.makeText(Login.this, "添加成功", Toast.LENGTH_LONG).show();
                                }
                            }
                        });
                    } else {
                        Toast.makeText(Login.this, "该用户已存在", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void queryuser() {
        if ((et_id.getText().equals("")) || (et_psd.getText().toString().equals(""))) {
            Toast.makeText(Login.this, "请输入账号密码", Toast.LENGTH_SHORT).show();
        } else {
            Md5 md5 = new Md5();
            psd = md5.getmd5(et_psd.getText().toString());
            BmobQuery<User> bmobQuery = new BmobQuery<User>();
            bmobQuery.addWhereEqualTo("userId", et_id.getText().toString());
            bmobQuery.findObjects(new FindListener<User>() {
                @Override
                public void done(List<User> list, BmobException e) {
                    if (e == null) {
                        if (list.size() != 0) {
                            for (User user : list) {
                                if (user.getUserPassword().equals(psd)) {
                                    Toast.makeText(Login.this, "登陆成功", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(Login.this, MainActivity.class);
                                    startActivity(intent);
                                    Login.this.finish();
                                } else {
                                    Toast.makeText(Login.this, "密码错误", Toast.LENGTH_SHORT).show();
                                }
                            }
                        } else {
                            Toast.makeText(Login.this, "账号错误", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(Login.this, "查询失败", Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }
    }
}
