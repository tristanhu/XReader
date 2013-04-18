package org.aftx.holers.android.xreader.ui.utils;

import org.aftx.holers.android.xreader.db.model.Book;

public class BookList extends BaseList<Book>{
    
    public void Update(int id) {
        super.Update();
        entities = binder.GetBooks(id);
        int i = 0;
        for (Book book : entities) {
            map.put(i, book);
            names.add(book.getName());
            i++;
        }
    }
}
