package com.multimediademo5customaudioplayer2;

import java.io.File;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

/**
 * 该Demo运行后，会显示所有你sd卡上的音乐文件列表， 并可以点击列表选择某一首歌曲进行播放。
 * 
 */
public class AudioBrowser extends ListActivity {
	private Cursor cursor;
	public static int STATE_SELECT_ALBUM = 0;
	public static int STATE_SELECT_SONG = 1;
	private int currentState = STATE_SELECT_ALBUM;

	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_audio_browser);

		/**
		 * 返回一个所有可用唱片集的列表
		 */
		cursor = managedQuery(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
				null, null, null, null);
		String[] displayFields = new String[] { MediaStore.Audio.Media.TITLE };
		int[] displayViews = new int[] { android.R.id.text1 };
		/**
		 * 可以使用setListAdapter方法将Cursor对象绑定到ListView对象
		 */
		setListAdapter(new SimpleCursorAdapter(this,
				android.R.layout.simple_list_item_1, cursor, displayFields,
				displayViews));
	}

	@SuppressWarnings("deprecation")
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		/**
		 * 判断点击的是歌曲文件夹还是单个歌曲
		 */
		/*if (currentState == STATE_SELECT_ALBUM) {

			if (cursor.moveToPosition(position)) {
				String[] columns = { MediaStore.Audio.Media.DATA,
						MediaStore.Audio.Media._ID,
						MediaStore.Audio.Media.TITLE,
						MediaStore.Audio.Media.DISPLAY_NAME,
						MediaStore.Audio.Media.MIME_TYPE };
				String where = android.provider.MediaStore.Audio.Media.ALBUM
						+ "=?";
				String whereVal[] = { cursor.getString(cursor
						.getColumnIndex(MediaStore.Audio.Albums.ALBUM)) };
				String orderBy = android.provider.MediaStore.Audio.Media.TITLE;
				cursor = managedQuery(
						MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, columns,
						where, whereVal, orderBy);
				String[] displayFields = new String[] { MediaStore.Audio.Media.DISPLAY_NAME };
				int[] displayViews = new int[] { android.R.id.text1 };
				setListAdapter(new SimpleCursorAdapter(this,
						android.R.layout.simple_list_item_1, cursor,
						displayFields, displayViews));
				currentState = STATE_SELECT_SONG;
			}
		} else if (currentState == STATE_SELECT_SONG) {*/
			/**
			 * 如果点击的是歌曲，则调用系统的播放器进行歌曲播放
			 */
			if (cursor.moveToPosition(position)) {
				int fileColumn = cursor
						.getColumnIndex(MediaStore.Audio.Media.DATA);
				int mimeTypeColumn = cursor
						.getColumnIndex(MediaStore.Audio.Media.MIME_TYPE);
				String audioFilePath = cursor.getString(fileColumn);
				String mimeType = cursor.getString(mimeTypeColumn);
				Intent intent = new Intent(android.content.Intent.ACTION_VIEW);
				File newFile = new File(audioFilePath);
				intent.setDataAndType(Uri.fromFile(newFile), mimeType);
				startActivity(intent);
			}
		}
	}


