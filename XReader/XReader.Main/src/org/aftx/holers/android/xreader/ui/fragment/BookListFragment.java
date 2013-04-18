package org.aftx.holers.android.xreader.ui.fragment;

import org.aftx.holers.android.xreader.R;
import org.aftx.holers.android.xreader.db.model.Book;
import org.aftx.holers.android.xreader.ui.activity.ReadActivity;
import org.aftx.holers.android.xreader.ui.utils.BookList;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class BookListFragment extends BaseListFragment {

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
            ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(0, 0, 0, "É¾³ýÊé¼®");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        final AdapterContextMenuInfo info = (AdapterContextMenuInfo) item
                .getMenuInfo();
        switch (item.getItemId()) {
        case 0:
            sendDelBook(info.position);
            return false;
        default:
            return super.onContextItemSelected(item);
        }
    }

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
                Book book = ((BookList) list).GetEntity(pos);
                intent.putExtra("Id", book.getId());
                intent.putExtra("Name", book.getName());
                intent.putExtra("Path", book.getPath());
                intent.putExtra("Page", 0);
                startActivity(intent);
            }
        });

        registerForContextMenu(view);

        return rootView;
    }
}
