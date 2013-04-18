package org.aftx.holers.android.xreader.ui.activity;

import java.util.List;

import org.aftx.holers.android.xreader.R;
import org.aftx.holers.android.xreader.db.model.Collection;
import org.aftx.holers.android.xreader.rss.RssActivity;
import org.aftx.holers.android.xreader.ui.activity.base.BaseMainActivity;
import org.aftx.holers.android.xreader.ui.dialog.AddBookDialog;
import org.aftx.holers.android.xreader.ui.dialog.AddCollectionDialog;
import org.aftx.holers.android.xreader.ui.dialog.DelCollectionDialog;
import org.aftx.holers.android.xreader.ui.fragment.BaseListFragment;
import org.aftx.holers.android.xreader.ui.fragment.BookListFragment;
import org.aftx.holers.android.xreader.ui.fragment.HistoryListFragment;
import org.aftx.holers.android.xreader.ui.utils.BaseList;
import org.aftx.holers.android.xreader.ui.utils.BookList;
import org.aftx.holers.android.xreader.ui.utils.CollectionList;
import org.aftx.holers.android.xreader.ui.utils.HistoryList;

import roboguice.inject.ContentView;
import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.widget.ArrayAdapter;

import com.google.inject.Singleton;

@Singleton
@ContentView(R.layout.activity_main)
public class MainActivity extends BaseMainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set up the action bar to show a dropdown list.
        actionBar = getActionBar();
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);

        // UpdateCollectionList();
        new Thread(new Runnable() {
            @Override
            public void run() {
                handler.sendEmptyMessage(UPDATECOLLECTIONLIST);
            }
        }).start();
    }

    public void UpdateBookList() {
        BaseListFragment ft;

        Collection coll = ((CollectionList) collectionList).GetEntity(actionBar
                .getSelectedNavigationIndex());

        if (coll != null) {
            ((BookList) bookList).Update(coll.getId());
            ft = new BookListFragment();
            ft.setList((BaseList<?>) bookList);
            getFragmentManager().beginTransaction().replace(R.id.container, ft)
                    .commit();
        } else {
            ((HistoryList) historyList).Update();
            ft = new HistoryListFragment();
            ft.setList((BaseList<?>) historyList);
            getFragmentManager().beginTransaction().replace(R.id.container, ft)
                    .commit();
        }
    }

    public void UpdateCollectionList() {
        collectionList.Update();
        List<String> data = collectionList.GetData();
        data.add(getString(R.string.title_history));

        actionBar.setListNavigationCallbacks(
                new ArrayAdapter<String>(actionBar.getThemedContext(),
                        android.R.layout.simple_list_item_1,
                        android.R.id.text1, data), this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);

        menu.findItem(R.id.AddBook).setOnMenuItemClickListener(
                new OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        new AddBookDialog(MainActivity.this, handler,
                                ((CollectionList) collectionList).GetEntity(
                                        actionBar.getSelectedNavigationIndex())
                                        .getId());
                        UpdateBookList();
                        return false;
                    }
                });

        menu.findItem(R.id.AddCollection).setOnMenuItemClickListener(
                new OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        Collection old = ((CollectionList) collectionList)
                                .GetEntity(actionBar
                                        .getSelectedNavigationIndex());
                        new AddCollectionDialog(MainActivity.this, handler);
                        UpdateCollectionList();
                        actionBar
                                .setSelectedNavigationItem(((CollectionList) collectionList)
                                        .GetItemIndex(old));
                        return false;
                    }
                });

        menu.findItem(R.id.DelCollection).setOnMenuItemClickListener(
                new OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        new DelCollectionDialog(MainActivity.this, handler,
                                ((CollectionList) collectionList).GetEntity(
                                        actionBar.getSelectedNavigationIndex())
                                        .getId());
                        UpdateCollectionList();
                        actionBar.setSelectedNavigationItem(0);
                        return false;
                    }
                });

        menu.findItem(R.id.Search).setOnMenuItemClickListener(
                new OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        Intent intent = new Intent(MainActivity.this,
                                SearchActivity.class);
                        startActivity(intent);
                        return false;
                    }
                });

        menu.findItem(R.id.Rss).setOnMenuItemClickListener(
                new OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        Intent intent = new Intent(MainActivity.this,
                                RssActivity.class);
                        startActivity(intent);
                        return false;
                    }
                });

        return true;
    }
}
