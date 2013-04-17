package org.aftx.holers.android.xreader.ui.activity.base;

import java.util.List;

import org.aftx.holers.android.xreader.R;
import org.aftx.holers.android.xreader.db.model.Book;
import org.aftx.holers.android.xreader.db.model.Collection;
import org.aftx.holers.android.xreader.service.binder.LogBinder;
import org.aftx.holers.android.xreader.service.fake.LogLayer;
import org.aftx.holers.android.xreader.ui.fragment.BaseListFragment;
import org.aftx.holers.android.xreader.ui.fragment.BookListFragment;
import org.aftx.holers.android.xreader.ui.fragment.HistoryListFragment;
import org.aftx.holers.android.xreader.ui.handler.MsgDefine;
import org.aftx.holers.android.xreader.ui.interfaces.IBookAction;
import org.aftx.holers.android.xreader.ui.interfaces.ICollectionAction;
import org.aftx.holers.android.xreader.ui.utils.BaseList;
import org.aftx.holers.android.xreader.ui.utils.BookList;
import org.aftx.holers.android.xreader.ui.utils.CollectionList;
import org.aftx.holers.android.xreader.ui.utils.HistoryList;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ArrayAdapter;

import com.google.inject.Inject;

public class BaseMainActivity extends BaseActivity implements
        ActionBar.OnNavigationListener, IBookAction, ICollectionAction,
        MsgDefine {

    /**
     * The serialization (saved instance state) Bundle key representing the
     * current dropdown position.
     */
    private static final String STATE_SELECTED_NAVIGATION_ITEM = "selected_navigation_item";

    protected ActionBar         actionBar;

    @Inject
    protected LogLayer  logic;
    
    @Inject
    protected LogBinder binder;

    @Override
    public void AddBook(String name, String path) {
        Book book = new Book(name, path);
        book.setCollection(((CollectionList) collectionList).GetEntity(
                actionBar.getSelectedNavigationIndex()).getId());
        binder.AddBook(book);
        UpdateBookList();
    }

    @Override
    public void DelBook(int id) {
        binder.DeleteBook(id);
        UpdateBookList();
    }

    @Override
    public void UpdateBookList() {
        AlertDialog dialog = new AlertDialog.Builder(this).setTitle("加载中")
                .show();

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

        dialog.dismiss();
    }

    @Override
    public void AddCollection(String name) {
        binder.AddCollection(new Collection(name));
        Collection old = ((CollectionList) collectionList).GetEntity(actionBar
                .getSelectedNavigationIndex());
        UpdateCollectionList();
        actionBar.setSelectedNavigationItem(((CollectionList) collectionList)
                .GetItemIndex(old));
    }

    @Override
    public void DelCollection() {
        binder.DeleteCollection(((CollectionList) collectionList).GetEntity(
                actionBar.getSelectedNavigationIndex()).getId());
        UpdateCollectionList();
        actionBar.setSelectedNavigationItem(0);
    }

    @Override
    public void UpdateCollectionList() {
        AlertDialog dialog = new AlertDialog.Builder(this).setTitle("加载中")
                .show();

        collectionList.Update();
        List<String> data = collectionList.GetData();
        data.add(getString(R.string.title_history));

        // Set up the dropdown list navigation in the action bar.
        actionBar.setListNavigationCallbacks(
        // Specify a SpinnerAdapter to populate the dropdown list.
                new ArrayAdapter<String>(actionBar.getThemedContext(),
                        android.R.layout.simple_list_item_1,
                        android.R.id.text1, data), this);

        dialog.dismiss();
    }

    @Inject
    @GetHandler
    public Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Intent service = new Intent();
        // service.setAction(LOGSERVICE_ACTION);
        // conn = new SvrConn(this);
        // bindService(service, conn, BIND_AUTO_CREATE);
        // while (binder == null) {
        // }
        // logic = new LogLayer(this);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        // Restore the previously serialized current dropdown position.
        if (savedInstanceState.containsKey(STATE_SELECTED_NAVIGATION_ITEM)) {
            getActionBar().setSelectedNavigationItem(
                    savedInstanceState.getInt(STATE_SELECTED_NAVIGATION_ITEM));
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        // Serialize the current dropdown position.
        outState.putInt(STATE_SELECTED_NAVIGATION_ITEM, getActionBar()
                .getSelectedNavigationIndex());
    }

    @Override
    public boolean onNavigationItemSelected(int position, long id) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                handler.sendEmptyMessage(UPDATEBOOKLIST);
            }
        }).start();
        return true;
    }
}
