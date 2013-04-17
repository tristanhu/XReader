package org.aftx.holers.android.xreader.ui.action.implement;

import org.aftx.holers.android.xreader.R;
import org.aftx.holers.android.xreader.db.model.Book;
import org.aftx.holers.android.xreader.db.model.Collection;
import org.aftx.holers.android.xreader.ui.action.IBookAction;
import org.aftx.holers.android.xreader.ui.fragment.BaseListFragment;
import org.aftx.holers.android.xreader.ui.fragment.BookListFragment;
import org.aftx.holers.android.xreader.ui.fragment.HistoryListFragment;
import org.aftx.holers.android.xreader.ui.utils.BaseList;
import org.aftx.holers.android.xreader.ui.utils.BookList;
import org.aftx.holers.android.xreader.ui.utils.CollectionList;
import org.aftx.holers.android.xreader.ui.utils.HistoryList;

import android.app.Activity;
import android.content.Context;

import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class BookAction extends BaseAction implements IBookAction {
    
    @Inject
    public BookAction(Activity context) {
        super(context);
    }

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
        BaseListFragment ft;

        Collection coll = ((CollectionList) collectionList).GetEntity(actionBar
                .getSelectedNavigationIndex());

        if (coll != null) {
            ((BookList) bookList).Update(coll.getId());
            ft = new BookListFragment();
            ft.setList((BaseList<?>) bookList);
            ((Activity) context).getFragmentManager().beginTransaction().replace(R.id.container, ft)
                    .commit();
        } else {
            ((HistoryList) historyList).Update();
            ft = new HistoryListFragment();
            ft.setList((BaseList<?>) historyList);
            ((Activity) context).getFragmentManager().beginTransaction().replace(R.id.container, ft)
                    .commit();
        }
    }
}
