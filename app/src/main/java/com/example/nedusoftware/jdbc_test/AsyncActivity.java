package com.example.nedusoftware.jdbc_test;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by NEDUsoftware on 2017/3/31.
 */

public class AsyncActivity extends Activity implements View.OnClickListener{
    private Button button;
    private ProgressBar progressBar;
    private ImageView imageView;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.async);
        init();
        initlisten();
    }
    private void init(){
        button=(Button)findViewById(R.id.btn_im);
        progressBar=(ProgressBar)findViewById(R.id.progressBar);
        imageView=(ImageView)findViewById(R.id.im_1);
    }
    private void initlisten(){
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        DownTask task=new DownTask();
        task.execute("http://android.tgbus.com/Android/UploadFiles_4504/200901/20090113093732437.jpg");
    }
    class DownTask extends AsyncTask<String,Integer,Bitmap>{
        private  URL url;
        @Override
        protected Bitmap doInBackground(String... params) {
            Bitmap bm=null;
            try{
                url=new URL(params[0]);
                publishProgress(30);
            }
            catch (MalformedURLException e){
                e.printStackTrace();
            }
            try {
                HttpURLConnection urlconn=(HttpURLConnection)url.openConnection();
                urlconn.setDoInput(true);
                urlconn.connect();
                publishProgress(60);
                InputStream in=urlconn.getInputStream();
                bm= BitmapFactory.decodeStream(in);
            }
            catch (IOException e){
                e.printStackTrace();
            }
            publishProgress(100);
            return bm;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            imageView.setImageBitmap(bitmap);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            progressBar.setProgress(values[0]);
        }
    }
}
