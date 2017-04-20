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
 * ��Demo���к󣬻���ʾ������sd���ϵ������ļ��б� �����Ե���б�ѡ��ĳһ�׸������в��š�
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
		 * ����һ�����п��ó�Ƭ�����б�
		 */
		cursor = managedQuery(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
				null, null, null, null);
		String[] displayFields = new String[] { MediaStore.Audio.Media.TITLE };
		int[] displayViews = new int[] { android.R.id.text1 };
		/**
		 * ����ʹ��setListAdapter������Cursor����󶨵�ListView����
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
		 * �жϵ�����Ǹ����ļ��л��ǵ�������
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
			 * ���������Ǹ����������ϵͳ�Ĳ��������и�������
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


