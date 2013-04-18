package org.aftx.holers.android.xreader.ui.action.implement;

import org.aftx.holers.android.xreader.db.model.Book;
import org.aftx.holers.android.xreader.ui.action.IBookAction;

import com.google.inject.Singleton;

@Singleton
public class BookAction extends BaseAction implements IBookAction {

    @Override
    public void AddBook(String name, String path, int colleciton) {
        Book book = new Book(name, path, colleciton);
        binder.AddBook(book);
    }

    @Override
    public void DelBook(int id) {
        binder.DeleteBook(id);
    }

    @Override
    public void UpdateBookList() {

    }
}
