package org.aftx.holers.android.xreader.ui.handler;

import org.aftx.holers.android.xreader.ui.action.IAction;

import android.os.Handler;
import android.os.Message;

import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class UiHandler extends Handler implements MsgDefine {
    @Inject
    private IAction       action;

    public void handleMessage(Message msg) {
        String name;
        String path;

        switch (msg.what) {
        case ADDBOOK:
            name = msg.getData().getString("name");
            path = msg.getData().getString("path");
            action.AddBook(name, path);
            break;
        case DELBOOK:
            int id = msg.getData().getInt("Id");
            action.DelBook(id);
            break;
        case UPDATEBOOKLIST:
            action.UpdateBookList();
            break;
        case ADDCOLLECTION:
            name = msg.getData().getString("name");
            action.AddCollection(name);
            break;
        case DELCOLLECTION:
            action.DelCollection();
            break;
        case UPDATECOLLECTIONLIST:
            action.UpdateCollectionList();
            break;
        }
    }
}