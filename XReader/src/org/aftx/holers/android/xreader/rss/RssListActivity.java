package org.aftx.holers.android.xreader.rss;

import org.aftx.holers.android.xreader.R;

import roboguice.activity.RoboListActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckedTextView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class RssListActivity extends RoboListActivity {
	private static final int Menu_item1 = Menu.FIRST;
	private static final int Menu_item2 = Menu_item1 + 1;
	private static final int Menu_item3 = Menu_item2 + 1;

	private int selected = -1;
	private int curID = 0;
	private String link = null;

	private SharedPreferences sPreferences;
	private RSS_listManager rManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.rss_list);
		init();

		sPreferences = getSharedPreferences("list_info", 0);
		selected = sPreferences.getInt("LIST_ID", -1);
		curID = selected;
		System.out.println("onCreate :" + curID);
		UpdateList();
	}

	// 打开数据库
	public void init() {
		rManager = new RSS_listManager(this);
		rManager.Open();
	}

	// 更新列表
	public void UpdateList() {
		Cursor cursor = rManager.selectDate();
		SimpleCursorAdapter sAdapter = new SimpleCursorAdapter(this,
				android.R.layout.simple_list_item_single_choice, cursor,
				new String[] { RSS_mlist.title },
				new int[] { android.R.id.text1 });
		setListAdapter(sAdapter);
		this.getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		this.getListView().setItemChecked(selected, true);
	}

	// 当前窗体不可见时，将当前选择项的ID、与Link保存到SharePreferences中
	// 保存当前项ID的目的是为了从数据库中查找到当前ID对应的数据
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		// sPreferences = getSharedPreferences("list_info", 0);
		// sPreferences.edit().putInt("LIST_ID",
		// selected).putLong("LIST_ITEMID",
		// curItemID).putString("LIST_LINK", link).commit();
		rManager.Close();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(0, Menu_item1, 0, R.string.rss_item3);
		menu.add(0, Menu_item2, 0, R.string.rss_item4);
		menu.add(0, Menu_item3, 0, R.string.rss_item5);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Intent intent = null;
		Cursor cursor = null;
		sPreferences = getSharedPreferences("list_info", 0);
		Long id = sPreferences.getLong("LIST_ITEMID", 1);
		switch (item.getItemId()) {
		case Menu_item1:
			// 新添加一个标题
			intent = new Intent(RssListActivity.this, RSS_listItem.class);
			intent.setAction("com.lq.addlistitem");
			startActivity(intent);
			break;
		case Menu_item2:
			// 编辑当前所选标题
			cursor = rManager.selectDate(id);
			intent = new Intent(RssListActivity.this, RSS_listItem.class);
			Bundle bundle = new Bundle();
			bundle.putInt(RSS_mlist._ID, cursor.getInt(0));
			bundle.putString(RSS_mlist.title, cursor.getString(1));
			bundle.putString(RSS_mlist.link, cursor.getString(2));
			intent.putExtra("com.lq.editlistitem", bundle);
			startActivity(intent);
			break;
		case Menu_item3:
			// 删除当前所选标题
			rManager.deleteDate(id);
			UpdateList();
			// 将当前选择项定位到前一项
			selected = selected-1;
			this.getListView().setItemChecked(selected, true);
			// 修改getSharedPreferences中 所有值，这样在下次单击删除的时候，才能有效
			cursor = rManager.selectDateDesc();
			sPreferences.edit().putInt("LIST_ID", selected).putLong(
					"LIST_ITEMID", cursor.getLong(0)).putString("LIST_LINK",
					cursor.getString(2)).commit();
			break;

		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	// 保存所选择的标题与地址
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		if (selected != -1) {
			View view = this.getListView().getChildAt(position);
			((CheckedTextView) view).setChecked(false);
		}
		((CheckedTextView) v).setChecked(true);
		selected = position;
		Cursor cursor = rManager.selectDate(id);
		link = cursor.getString(2);

		sPreferences = getSharedPreferences("list_info", 0);
		sPreferences.edit().putInt("LIST_ID", position).putLong("LIST_ITEMID",
				id).putString("LIST_LINK", link).commit();
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		sPreferences = getSharedPreferences("list_info", 0);
		selected = sPreferences.getInt("LIST_ID", -1);
		curID = selected;
		System.out.println(selected);
		UpdateList();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			if (curID != selected) {
				Intent intent = new Intent();
				setResult(RESULT_OK, intent);
			}
			this.finish();
			return true;
		} else {
			return super.onKeyDown(keyCode, event);
		}
	}

}
