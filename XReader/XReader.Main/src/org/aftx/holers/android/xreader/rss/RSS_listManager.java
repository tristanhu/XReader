package org.aftx.holers.android.xreader.rss;

import java.util.Calendar;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

//RSS列表管理类
public class RSS_listManager {
	// 数据库相关数据
	private static final String DATABASE_NAME = "RSSDB";
	private static final int DATABASE_VERSION = 1;
	private static final String TABLE_NAME = "RSSLIST";

	private Context _context;
	private RSSHelper rHelper;
	private SQLiteDatabase sDatabase;

	public RSS_listManager(Context ct) {
		this._context = ct;
	}

	public class RSSHelper extends SQLiteOpenHelper {

		public RSSHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL("create table " + TABLE_NAME + " (" + RSS_mlist._ID
					+ " integer primary key autoincrement," + RSS_mlist.title
					+ " text not null," + RSS_mlist.link + " text not null,"
					+ RSS_mlist.date + " text not null);");

			ContentValues cValues = new ContentValues();
			cValues.put(RSS_mlist.title, RSS_mlist.TITLE);
			cValues.put(RSS_mlist.link, RSS_mlist.LINK);
			Calendar calendar = Calendar.getInstance();
			String date = calendar.get(Calendar.YEAR) + "年"
					+ calendar.get(Calendar.MONTH) + "月"
					+ calendar.get(Calendar.MONDAY)
					+ calendar.get(Calendar.HOUR_OF_DAY)
					+ calendar.get(Calendar.MINUTE);
			cValues.put(RSS_mlist.date, date);
			// 添加一条数据到数据库
			db.insert(TABLE_NAME, null, cValues);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub

		}

	}

	// 打开数据库连接
	public boolean Open() {
		rHelper = new RSSHelper(this._context);

		return true;
	}

	// 关闭数据库
	public void Close() {
		if (rHelper != null) {
			rHelper.close();
		}
	}

	// 添加数据到数据库
	public boolean insertDate(String title, String link) {
		ContentValues cValues = new ContentValues();
		cValues.put(RSS_mlist.title, title);
		cValues.put(RSS_mlist.link, link);

		Calendar calendar = Calendar.getInstance();
		String date = calendar.get(Calendar.YEAR) + "年"
				+ calendar.get(Calendar.MONTH) + "月"
				+ calendar.get(Calendar.MONDAY)
				+ calendar.get(Calendar.HOUR_OF_DAY)
				+ calendar.get(Calendar.MINUTE);
		cValues.put(RSS_mlist.date, date);

		sDatabase = rHelper.getWritableDatabase();
		return sDatabase.insert(TABLE_NAME, null, cValues) > 0;

	}

	// 删除数据
	public boolean deleteDate(long id) {
		sDatabase = rHelper.getWritableDatabase();
		return sDatabase.delete(TABLE_NAME, RSS_mlist._ID + "=" + id, null) > 0;
	}

	// 修改数据
	public boolean updateDate(long id, String title, String link) {
		System.out.println("YES");
		sDatabase = rHelper.getWritableDatabase();
		ContentValues cValues = new ContentValues();
		cValues.put(RSS_mlist.title, title);
		cValues.put(RSS_mlist.link, link);
		System.out.println("NO");
		return sDatabase.update(TABLE_NAME, cValues, RSS_mlist._ID + "=" + id,
				null) > 0;
	}

	// 查询数据
	// 查询全部数据
	public Cursor selectDate() {
		sDatabase = rHelper.getReadableDatabase();
		return sDatabase.query(TABLE_NAME, new String[] { RSS_mlist._ID,
				RSS_mlist.title, RSS_mlist.link, RSS_mlist.date }, null, null,
				null, null, null);
	}

	// 查询单条数据
	public Cursor selectDate(long id) {
		sDatabase = rHelper.getReadableDatabase();
		Cursor cursor = sDatabase.query(TABLE_NAME,
				new String[] { RSS_mlist._ID, RSS_mlist.title, RSS_mlist.link,
						RSS_mlist.date }, RSS_mlist._ID + "=" + id, null, null,
				null, null);
		if (cursor != null) {
			cursor.moveToFirst();
		}
		return cursor;
	}

	// 查询单条数据,采取倒序的方式
	public Cursor selectDateDesc() {
		sDatabase = rHelper.getReadableDatabase();
		Cursor cursor = sDatabase.query(TABLE_NAME,
				new String[] { RSS_mlist._ID, RSS_mlist.title, RSS_mlist.link,
						RSS_mlist.date }, null, null, null,
				null, RSS_mlist._ID+" desc");
		if (cursor != null) {
			cursor.moveToFirst();
		}
		return cursor;
	}

}
