package com.example.nedusoftware.jdbc_test;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;



import java.util.ArrayList;


/**
 * Created by NEDUsoftware on 2017/3/30.
 */

public class ThreadTest extends Thread {
    public android.os.Handler handler;
    public Handler uihandler;
    public ThreadTest(Handler mhandler){
        uihandler=mhandler;
    }

    public void run(){
        Looper.prepare();
        handler=new Handler() {
            @Override
            public void handleMessage(Message msg) {
                ArrayList<Integer> nums=new ArrayList<Integer>();
                if(msg.what==1){

                    int test=msg.getData().getInt("test");
                    outer:
                    for (int i=2;i<=test;i++){
                        for (int j=2;j<=Math.sqrt(i);j++){
                            if(i!=2&&i%j==0){
                                continue outer;
                            }
                        }
                        nums.add(i);
                    }
                }
                Message message=new Message();
                Bundle bundle=new Bundle();
                bundle.putIntegerArrayList("list",nums);
                message.setData(bundle);
                uihandler.sendMessage(message);
            }
        };
        Looper.loop();
    }
}
