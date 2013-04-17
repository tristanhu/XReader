package org.aftx.holers.android.xreader.ui.fragment;

import org.aftx.holers.android.xreader.R;
import org.aftx.holers.android.xreader.db.model.Book;
import org.aftx.holers.android.xreader.ui.activity.ReadActivity;
import org.aftx.holers.android.xreader.ui.utils.HistoryList;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class HistoryListFragment extends BaseListFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.book_list_fragment,
                container, false);

        ListView view = (ListView) rootView.findViewById(R.id.BookList);
        view.setAdapter(new ArrayAdapter<String>(view.getContext(),
                android.R.layout.simple_list_item_1, android.R.id.text1, list
                        .GetData()));

        view.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int pos,
                    long id) {
                Intent intent = new Intent(getActivity(), ReadActivity.class);
                Book book = ((HistoryList) list).GetBook(pos);
                intent.putExtra("Name", book.getName());
                intent.putExtra("Path", book.getPath());
                intent.putExtra("Page", ((HistoryList) list).GetEntity(pos)
                        .getPage());
                startActivity(intent);
            }
        });

        return rootView;
    }
}
