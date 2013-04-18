package org.aftx.holers.android.xreader.ui.handler;

import org.aftx.holers.android.xreader.ui.action.IAction;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class UiHandler extends Handler implements MsgDefine {
    @Inject
    private IAction action;

    public void handleMessage(Message msg) {
        Bundle data;
        int id;
        String name;
        String path;
        int collection;

        switch (msg.what) {
        case ADDBOOK:
            data = msg.getData();
            name = data.getString("Name");
            path = data.getString("Path");
            collection = data.getInt("Collection");
            action.AddBook(name, path, collection);
            break;
        case DELBOOK:
            id = msg.getData().getInt("Id");
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
            data = msg.getData();
            id = data.getInt("Id");
            action.DelCollection(id);
            break;
        case UPDATECOLLECTIONLIST:
            action.UpdateCollectionList();
            break;
        }
    }
}