package org.aftx.holers.android.xreader.ui.handler;

import org.aftx.holers.android.xreader.ui.interfaces.IBookList;
import org.aftx.holers.android.xreader.ui.interfaces.ICollectionList;

import android.os.Handler;
import android.os.Message;

public class UiHandler extends Handler implements MsgDefine {
    private IBookList       books;
    private ICollectionList collections;

    public UiHandler(IBookList books, ICollectionList collections) {
        this.books = books;
        this.collections = collections;
    }

    public void handleMessage(Message msg) {
        String name;
        String path;

        switch (msg.what) {
        case ADDBOOK:
            name = msg.getData().getString("name");
            path = msg.getData().getString("path");
            books.AddBook(name, path);
            break;
        case DELBOOK:
            int id = msg.getData().getInt("Id");
            books.DelBook(id);
            break;
        case UPDATEBOOKLIST:
            books.UpdateBookList();
            break;
        case ADDCOLLECTION:
            name = msg.getData().getString("name");
            collections.AddCollection(name);
            break;
        case DELCOLLECTION:
            collections.DelCollection();
            break;
        case UPDATECOLLECTIONLIST:
            collections.UpdateCollectionList();
            break;
        }
    }
}