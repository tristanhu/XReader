package org.aftx.holers.android.xreader.rss;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.aftx.holers.android.xreader.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class RSS_listItem extends Activity {
	private RSS_listManager rManager;

	private EditText edit_Title = null;
	private EditText edit_Link = null;
	private Button btn_sub = null;
	private boolean IS_EDIT = false;

	private int ID;
	private String title, link;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.rss_listitem);
		init();
		Intent intent = getIntent();
		if (intent != null) {
			Bundle bundle = intent.getBundleExtra("com.lq.editlistitem");
			if (bundle != null) {
				ID = bundle.getInt(RSS_mlist._ID);
				title = bundle.getString(RSS_mlist.title);
				link = bundle.getString(RSS_mlist.link);
				edit_Title.setText(title);
				edit_Link.setText(link);
			} else {
				IS_EDIT = true;
			}
		}

		submit();
	}

	public void init() {
		rManager = new RSS_listManager(this);
		rManager.Open();

		edit_Title = (EditText) findViewById(R.id.edit_title);
		edit_Link = (EditText) findViewById(R.id.edit_link);
		edit_Title.setText("");
		edit_Link.setText("");
		btn_sub = (Button) findViewById(R.id.b_submit);
	}

	public void submit() {
		btn_sub.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (IS_EDIT) {
					Add();
				} else {
					Save();
				}
			}
		});
	}

	// ���һ���µı���
	public void Add() {
		boolean isValid = true;
		String title = edit_Title.getText().toString().trim();
		String link = edit_Link.getText().toString().trim();
		if ("".equals(title) || title == null) {
			isValid = false;
			ShowMsg("���ⲻ��Ϊ��!");
		}
		// �ж����ӵ�ַ��ʹ����������ж�
		else if ("".equals(link) || link == null) {
			isValid = false;
			ShowMsg("��ַ����Ϊ��!");
		} else if (!isValidURL(link)) {
			isValid = false;
			ShowMsg("��ַ��ʽ����ȷ!");
		}
		// �������
		if (isValid) {
			rManager.insertDate(title, link);

			this.finish();
		}
		// this.finish();
	}

	public void Save() {
		boolean isValid = true;
		String title = edit_Title.getText().toString().trim();
		String link = edit_Link.getText().toString().trim();
		System.out.println(title + "   " + link);
		if ("".equals(title) || title == null) {
			isValid = false;
			ShowMsg("���ⲻ��Ϊ��!");
		}
		// �ж����ӵ�ַ��ʹ����������ж�
		// �������
		if ("".equals(link) || link == null) {
			if (!isValidURL(link)) {
				isValid = false;
				ShowMsg("��ַ����Ϊ�ջ��ʽ����ȷ!");
			}
		}
		if (isValid) {
			rManager.updateDate(ID, title, link);
			// // �����б�
			this.finish();
		}
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		if (rManager != null) {
			rManager.Close();
		}
		edit_Title.setText("");
		edit_Link.setText("");
		this.finish();
	}

	public void ShowMsg(String str) {
		AlertDialog.Builder alert = new AlertDialog.Builder(this);
		alert.setMessage(str);
		alert.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {

			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
			}
		});
		alert.show();
	}

	public static boolean isValidURL(String str) {
		 String regex =
		 "http://(([a-zA-Z0-9]){1,}[.]?){1,}(/([a-zA-Z0-9]{1,})){1,}[a-zA-Z0-9]{1,}[_]?[a-zA-Z0-9]*.xml";
		return match(regex, str);
	}

	public static boolean match(String regex, String str) {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(str);
		return matcher.matches();
	}

}
