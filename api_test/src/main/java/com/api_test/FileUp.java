package com.api_test;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import java.io.File;

import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UploadFileListener;

/**
 * Created by NEDUsoftware on 2017/4/25.
 */

public class FileUp extends Activity implements View.OnClickListener{
    private ProgressBar progressBar;
    private Button btn_up;
    private Integer progress;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fileup);
        initViews();
        initListeners();
    }
    private void initViews() {
        progressBar=(ProgressBar) findViewById(R.id.progressBar);
        btn_up=(Button)findViewById(R.id.btn_up);
    }
    private void initListeners() {
        btn_up.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String picPath = "sdcard/temp.jpg";
        final BmobFile bmobFile = new BmobFile(new File(picPath));
        bmobFile.uploadblock(new UploadFileListener() {
            @Override
            public void done(BmobException e) {
                if (e==null){
                    Log.i("1", "电影文件上传成功，返回的名称--"+bmobFile.getFileUrl());
                    (new FileBean("绅士",bmobFile));
                }
            }

            @Override
            public void onProgress(Integer value) {
                // TODO Auto-generated method stub
                // 返回的上传进度（百分比）
            }


        });
    }

}
