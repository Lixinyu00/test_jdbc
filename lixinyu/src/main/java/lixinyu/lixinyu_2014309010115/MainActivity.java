package lixinyu.lixinyu_2014309010115;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;

public class MainActivity extends Activity implements AdapterView.OnItemClickListener
{
    private Button mNext, mStart, mStop,mprevious;
    private TextView mTextView1;
    private ListView listView;
    private Cursor cursor;
    private int position;
    private String name;
    /*设定bIsReleased一开始为false */
    private boolean bIsReleased = false;
    /*设定bIsPaused一开始为false */
    private boolean bIsPaused = false;
    public MediaPlayer myPlayer1 = new MediaPlayer();

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mStart = (Button) findViewById(R.id.btn_2);
        mStop = (Button) findViewById(R.id.btn_4);
        mNext = (Button) findViewById(R.id.btn_3);
        mprevious=(Button) findViewById(R.id.btn_1);
        mTextView1 = (TextView) findViewById(R.id.text_1);
        listView=(ListView)findViewById(R.id.list_1);
        listView.setOnItemClickListener(this);
        cursor = managedQuery(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                null, null, null, null);
            String[] displayFields = new String[] { MediaStore.Audio.Media.TITLE };
        int[] displayViews = new int[] { android.R.id.text1 };
        /**
         * 可以使用setListAdapter方法将Cursor对象绑定到ListView对象
         */
        listView.setAdapter(new SimpleCursorAdapter(this,
                android.R.layout.simple_list_item_1, cursor, displayFields,
                displayViews));
        /**
         * 播放完自动下一首
         */
       myPlayer1.setOnCompletionListener(new MediaPlayer.OnCompletionListener(){
           @Override
           public void onCompletion(MediaPlayer mediaPlayer) {
               position++;
               music(position);
           }
       });
        /**
         * 播放/暂停
         */
        mStart.setOnClickListener(new Button.OnClickListener()
        {
            public void onClick(View view)
            {
                if (myPlayer1 != null)
                {
                    if(bIsReleased == false)
                    {
                        if(bIsPaused==false)
                        {
              /*设定 MediaPlayer暂停播放*/
                            myPlayer1.pause();
                            bIsPaused = true;
                            mStart.setText("播放");
                        }
                        else if(bIsPaused==true)
                        {
              /*设定 MediaPlayer播放*/
                            myPlayer1.start();
                            bIsPaused = false;
                            mStart.setText("暂停");
                        }
                    }
                }
            }
        });

        /**
         * 下一首
         */
        mNext.setOnClickListener(new Button.OnClickListener()
        {
            @Override
            public void onClick(View arg0)
            {
                position++;
                music(position);
            }
        });
        /**
         * 上一首
         */
        mprevious.setOnClickListener(new Button.OnClickListener()
        {
            @Override
            public void onClick(View arg0)
            {
                position--;
                music(position);
            }
        });
        /**
         * 停止
         */
        mStop.setOnClickListener(new Button.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                    myPlayer1.reset();
                    mTextView1.setText(R.string.str_stopped);
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        position=i;
        music(i);
    }

    /**
     *
     * 查找并播放音乐 by LXY
     */
    public void music(int pos){
        if (cursor.moveToPosition(pos)) {
            int fileColumn = cursor
                    .getColumnIndex(MediaStore.Audio.Media.DATA);
            String audioFilePath = cursor.getString(fileColumn);
            int nameColumn = cursor
                    .getColumnIndex(MediaStore.Audio.Media.TITLE);
            name = cursor.getString(nameColumn);
            try
            {
                if(myPlayer1.isPlaying()==true)
                {
                    myPlayer1.reset();
                }
                myPlayer1.setDataSource(audioFilePath);
                myPlayer1.prepare();
                myPlayer1.start();
                mTextView1.setText("正在播放："+name);
                mStart.setText("暂停");
            }
            catch (IllegalStateException e)
            {
                // TODO Auto-generated catch block
                mTextView1.setText(e.toString());
                e.printStackTrace();
            }
            catch (IOException e)
            {
                // TODO Auto-generated catch block
                mTextView1.setText(e.toString());
                e.printStackTrace();
            }
        }
    }
}
