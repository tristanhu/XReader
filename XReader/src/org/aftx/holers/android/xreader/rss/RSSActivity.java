package org.aftx.holers.android.xreader.rss;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.aftx.holers.android.xreader.R;
import org.aftx.holers.android.xreader.rss.data.RssFeed;
import org.aftx.holers.android.xreader.rss.data.RssItem;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import roboguice.activity.RoboListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class RSSActivity extends RoboListActivity {
	private static String strUrl = "http://rss.sina.com.cn/ent/hot_roll.xml";

	private static final int Rss_Item1 = Menu.FIRST;
	
	private String shareinfo = "list_info";
	private SharedPreferences sPreferences;

	protected static final int GUIUPDATEIDENTIFIER = 0x101;
	URL url = null;
	ReadHelper handler;
	RssFeed rsFeed;
	ProgressDialog pDialog;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.rss_main);

		// 程序开始的时候，显示一个进度条，表示数据正在加载
		pDialog = ProgressDialog.show(this, "", "数据正在加载中...", true, false);
		new LoadDataThread().start();

	}

	// 从指定的URL来加载数据
	public RssFeed loadData(String rssUrl) {
		try {
			try {
				url = new URL(rssUrl);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			SAXParserFactory xFactory = SAXParserFactory.newInstance();
			SAXParser parser = xFactory.newSAXParser();
			XMLReader xReader = parser.getXMLReader();

			handler = new ReadHelper();
			xReader.setContentHandler(handler);
			InputSource iSource = new InputSource(url.openStream());

			xReader.parse(iSource);
			return handler.getFeed();

		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	Handler myHandler = new Handler() {
		// 接受到相应消息时候，进行相应的处理
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case GUIUPDATEIDENTIFIER:
				pDialog.dismiss();
				updata();
				break;
			default:
				break;
			}
		}
	};

	public class LoadDataThread extends Thread {

		@Override
		public void run() {
			rsFeed = loadData(strUrl);
			System.out.println(strUrl);
			Message msg = new Message();
			msg.what = GUIUPDATEIDENTIFIER;
			RSSActivity.this.myHandler.sendMessage(msg);
		}
	}

	public void updata() {
		if (rsFeed != null) {
			SimpleAdapter sAdapter = new SimpleAdapter(this, rsFeed
					.getItemsForList(), R.layout.rss_list_row, new String[] {
					RssItem.TITLE, RssItem.PUBDATE }, new int[] {
					R.id.txt_title, R.id.txt_pubDate });
			setListAdapter(sAdapter);
			this.getListView().setSelection(0);
		} else {
			return;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(0, Rss_Item1, 1, R.string.rss_item1);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Intent intent = null;
		switch (item.getItemId()) {
		case Rss_Item1:
			intent = new Intent(RSSActivity.this, RssListActivity.class);
			startActivityForResult(intent, 0);
			break;
		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		RssItem rItem = rsFeed.getItem(position);
		Intent intent = new Intent(RSSActivity.this,RSSShowItem.class);
		
		Bundle bundle = new Bundle();
		bundle.putString("title", rItem.getTitle());
		bundle.putString("pubDate", rItem.getPubDate());
		bundle.putString("description", rItem.getDescription());
		bundle.putString("link", rItem.getLink());
		intent.putExtra("com.lq.showitem", bundle);
		
		startActivity(intent);
		super.onListItemClick(l, v, position, id);
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		// 第一次运行时，没有link值，需初始化一个
		sPreferences = getSharedPreferences(shareinfo, 0);
		String link = sPreferences.getString("LIST_LINK", "");
		if ("".equals(link)) {
			sPreferences.edit().putInt("LIST_ID", 0).putLong("LIST_ITEMID", 1)
					.putString("LIST_LINK", RSS_mlist.LINK).commit();
		}
	}

	
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == 0) {
			if (resultCode == RESULT_OK) {
				strUrl = sPreferences.getString("LIST_LINK", strUrl);
				pDialog = ProgressDialog.show(this, "", "数据正在加载中...", true,
						false);
				new LoadDataThread().start();
			}
		}
	}
}