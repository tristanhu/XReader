package org.aftx.holers.android.xreader.ui.action.implement;

import org.aftx.holers.android.xreader.db.model.Collection;
import org.aftx.holers.android.xreader.ui.action.ICollectionAction;

import com.google.inject.Singleton;

@Singleton
public class CollectionAction extends BaseAction implements ICollectionAction{ 

    @Override
    public void AddCollection(String name) {
        binder.AddCollection(new Collection(name));
    }

    @Override
    public void DelCollection(int id) {
        binder.DeleteCollection(id);
    }

    @Override
    public void UpdateCollectionList() {

    }
}
