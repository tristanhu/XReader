package org.aftx.holers.android.xreader.rss;

import org.aftx.holers.android.xreader.R;

import roboguice.inject.InjectView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class RSSShowItem extends Activity{
    
    @InjectView(R.id.txt_content)
	private TextView txtContent;
    @InjectView(R.id.btn_back)
	private Button button;
	
	private String title,pubDate,description,link;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.rss_list_showitem);
		
		button.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
			
		});
		
		//ȡ����
		Intent intent = getIntent();
		if(intent != null){
			Bundle bundle = intent.getBundleExtra("com.lq.showitem");
			if(bundle != null){
				title = bundle.getString("title");
				pubDate = bundle.getString("pubDate");
				description = bundle.getString("description");
				link = bundle.getString("link");
			}
		}
		txtContent.setText(title+"\n\n"+pubDate+"\n\n"+description+"\n\n"+link);
	}
	
}
