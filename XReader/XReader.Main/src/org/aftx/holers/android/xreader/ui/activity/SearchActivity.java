package org.aftx.holers.android.xreader.ui.activity;

import java.util.List;

import org.aftx.holers.android.xreader.R;
import org.aftx.holers.android.xreader.ui.utils.FileSearch;

import roboguice.activity.RoboActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;

@ContentView(R.layout.search)
public class SearchActivity extends RoboActivity {

    @InjectView(R.id.search_result)
    ListView    listView;
    @InjectView(R.id.search_btn)
    Button      searchBtn;
    @InjectView(R.id.filename)
    EditText    EditText;
    @InjectView(R.id.search_phone)
    RadioButton RadioButton1;
    @InjectView(R.id.search_sdcard)
    RadioButton RadioButton2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setTitle(R.string.title_search);

        searchBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = EditText.getText().toString().trim(); // 搜索的文件名

                List<String> list = null;

                if (RadioButton1.isChecked()) {
                    list = FileSearch.searchFile("/", str);
                } else if (RadioButton2.isChecked()) {
                    list = FileSearch.searchFile("/mnt/sdcard", str);
                }

                listView.setAdapter(new ArrayAdapter<String>(
                        SearchActivity.this,
                        android.R.layout.simple_list_item_1,
                        android.R.id.text1, list));
            }
        });
    }
}