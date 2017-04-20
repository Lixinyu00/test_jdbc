package com.example.nedusoftware.jdbc_test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by NEDUsoftware on 2017/3/22.
 */

public class Class_B extends Activity implements TextView.OnClickListener{
    private Button btn_B;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.b);
        btn_B=(Button) findViewById(R.id.btn_B);
        btn_B.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent=new Intent();
        Bundle bundle=new Bundle();
        bundle.putString("result","B中结果");
        intent.putExtras(bundle);
        setResult(RESULT_OK,intent);
        this.finish();
    }
}
