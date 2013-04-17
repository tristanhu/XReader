package org.aftx.holers.android.xreader.ui.handler;

import org.aftx.holers.android.xreader.ui.interfaces.GetIBookAction;
import org.aftx.holers.android.xreader.ui.interfaces.GetICollectionAction;
import org.aftx.holers.android.xreader.ui.interfaces.IBookAction;
import org.aftx.holers.android.xreader.ui.interfaces.ICollectionAction;

import android.os.Handler;
import android.os.Message;

import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class UiHandler extends Handler implements MsgDefine {
    @Inject
    @GetIBookAction
    private IBookAction       books;
    @Inject
    @GetICollectionAction
    private ICollectionAction collections;

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