package org.aftx.holers.android.xreader.ui.action.implement;

import org.aftx.holers.android.xreader.ui.action.IAction;
import org.aftx.holers.android.xreader.ui.action.IBookAction;
import org.aftx.holers.android.xreader.ui.action.ICollectionAction;

import com.google.inject.Inject;
import com.google.inject.Singleton;


@Singleton
public class Action implements IAction {
    @Inject
    private IBookAction       book;
    @Inject
    private ICollectionAction collection;

    @Override
    public void AddBook(String name, String path) {
        book.AddBook(name, path);
    }

    @Override
    public void DelBook(int id) {
        book.DelBook(id);
    }

    @Override
    public void UpdateBookList() {
        book.UpdateBookList();
    }

    @Override
    public void AddCollection(String name) {
        collection.AddCollection(name);
    }

    @Override
    public void DelCollection() {
        collection.DelCollection();
    }

    @Override
    public void UpdateCollectionList() {
        collection.UpdateCollectionList();
    }

}
