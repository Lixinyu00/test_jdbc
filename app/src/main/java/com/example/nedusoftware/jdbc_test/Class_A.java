package com.example.nedusoftware.jdbc_test;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Class_A extends AppCompatActivity implements View.OnClickListener{
    private Button btn;
    private TextView t_A;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a);
        btn=(Button) findViewById(R.id.btn_A);
        t_A=(TextView) findViewById(R.id.t_A);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent=new Intent();
        intent.setClass(this,Class_B.class);
        startActivityForResult(intent,0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            Bundle bundle=data.getExtras();
            t_A.setText(bundle.getString("result"));
        }
    }
}
