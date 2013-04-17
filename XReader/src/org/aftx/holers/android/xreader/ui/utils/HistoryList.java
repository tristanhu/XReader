package org.aftx.holers.android.xreader.ui.utils;

import org.aftx.holers.android.xreader.db.model.Book;
import org.aftx.holers.android.xreader.db.model.History;

public class HistoryList extends BaseList<History> {
    
    public void AddHistory(History history)
    {
        binder.AddHistory(history);
    }

    public Book GetBook(int itemIndex) {
        History history = GetEntity(itemIndex);
        return binder.GetBook(history.getBook());
    }

    public void Update() {
        super.Update();
        entities = binder.GetAllHistories();
        int i = 0;
        for (History history : entities) {
            Book book = binder.GetBook(history.getBook());
            map.put(i, history);
            names.add(book.getName() + " -- " + history.getPage());
            i++;
        }
    }
}