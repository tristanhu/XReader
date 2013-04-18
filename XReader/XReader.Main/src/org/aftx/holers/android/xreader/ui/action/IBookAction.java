package org.aftx.holers.android.xreader.ui.action;

import org.aftx.holers.android.xreader.ui.action.implement.BookAction;

import com.google.inject.ImplementedBy;

@ImplementedBy(BookAction.class)
public interface IBookAction {
    public void AddBook(String name, String path, int colleciton);

    public void DelBook(int id);

    public void UpdateBookList();
}
