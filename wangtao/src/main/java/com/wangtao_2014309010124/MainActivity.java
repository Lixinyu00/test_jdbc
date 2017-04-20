package com.wangtao_2014309010124;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements View.OnClickListener,AdapterView.OnItemClickListener{
    private Button button_1;
    private Button button_2;
    private TextView text;
    private File filedir;
    private File cachedir;
    private File file;
    private String path;
    private ListView listview;
    private List<String> items = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button_1=(Button)findViewById(R.id.Button_1);
        button_2=(Button)findViewById(R.id.Button_2);
        listview=(ListView)findViewById(R.id.list_1);
        text=(TextView)findViewById(R.id.t_1);
        button_1.setOnClickListener(this);
        button_2.setOnClickListener(this);
        listview.setOnItemClickListener(this);
        cachedir=this.getCacheDir();
        filedir=this.getFilesDir();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.Button_1:
                path=filedir.getParent() + java.io.File.separator
                        + filedir.getName();
                break;
            case R.id.Button_2:
                path=cachedir.getParent() + java.io.File.separator
                        + cachedir.getName();
                break;
        }
        file=new  File(path);
        file(file.listFiles());
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        file = new File(path + java.io.File.separator + items.get(i));
        if (file.isDirectory())
            file(file.listFiles());
    }
    private void file(File[] files ){
        items=new ArrayList<String>();
        if (files == null){
            return;
        }
        for(int i=0;i<files.length;i++){
            Log.i("2", "file: ");
            items.add(files[i].getName());
        }
        ArrayAdapter<String> filelist=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,items);
        listview.setAdapter(filelist);
        Log.i("1", "file: ");
        try {
            text.setText(file.getCanonicalPath());
        }
        catch (IOException e){

        }
    }
}
