package com.api_test;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.File;
import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.DownloadFileListener;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UploadFileListener;

/**
 * Created by NEDUsoftware on 2017/4/25.
 */

public class FileUp extends Activity implements View.OnClickListener {
    private ProgressBar progressBar;
    private Button btn_up;
    private Button btn_down;
    private TextView textView;
    private BmobFile bmobfile;
    private String filename;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fileup);
        initViews();
        initListeners();
        Bmob.initialize(this, "d08d1c3148f3335bfabeb938162f7d8f");
    }

    private void initViews() {
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        btn_up = (Button) findViewById(R.id.btn_up);
        btn_down = (Button) findViewById(R.id.btn_down);
        textView = (TextView) findViewById(R.id.t_file);
    }

    private void initListeners() {
        btn_up.setOnClickListener(this);
        btn_down.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_up:
                up();
                break;
            case R.id.btn_down:
                down();
                break;

        }
    }
    private void up(){
        String picPath = "sdcard/shenshi.mp3";
        final BmobFile bmobFile = new BmobFile(new File(picPath));
        bmobFile.uploadblock(new UploadFileListener() {
            @Override
            public void done(BmobException e) {
                if (e == null) {
                    Log.i("1", "电影文件上传成功，返回的名称--" + bmobFile.getFileUrl());
                    FileBean fileBean = new FileBean();
                    fileBean.setId("绅士1");
                    fileBean.setFile(bmobFile);
                    fileBean.save(new SaveListener<String>() {
                        @Override
                        public void done(String s, BmobException e) {
                            Toast.makeText(FileUp.this, "chenggong", Toast.LENGTH_SHORT);
                        }
                    });
                } else {
                    Log.i("tag", "done: " + e);
                }
            }

            @Override
            public void onProgress(Integer value) {
                // TODO Auto-generated method stub
                // 返回的上传进度（百分比）
                progressBar.setProgress(value);
            }


        });
    }
    private void down(){
        String path=getApplicationContext().getFilesDir().getAbsolutePath();
        Log.i("2", "path: "+path);
        File destdir=new File("/data/data/com.api_test/icon");
        if(!destdir.exists()){
            destdir.mkdirs();
        }

        BmobQuery<FileBean> bmobquery=new BmobQuery<FileBean>();
        bmobquery.addWhereEqualTo("id","绅士");
        bmobquery.findObjects(new FindListener<FileBean>() {
            @Override
            public void done(List<FileBean> list, BmobException e) {
                if (e==null)
                {
                    if (list.size()!=0){
                        for (FileBean file:list){
                            filename=file.getId();
                            bmobfile=file.getFile();
                        }
                        File savepath=new File("/data/data/com.api_test/icon",filename);
                        bmobfile.download(savepath, new DownloadFileListener() {
                            @Override
                            public void done(String s, BmobException e) {
                                textView.setText("成功"+s);
                            }

                            @Override
                            public void onProgress(Integer integer, long l) {
                                progressBar.setProgress(integer);
                            }
                        });
                    }
                }
            }
        });
    }

}
