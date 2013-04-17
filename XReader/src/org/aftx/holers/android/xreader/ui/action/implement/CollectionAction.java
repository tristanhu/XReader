package org.aftx.holers.android.xreader.ui.action.implement;

import java.util.List;

import org.aftx.holers.android.xreader.R;
import org.aftx.holers.android.xreader.db.model.Collection;
import org.aftx.holers.android.xreader.ui.action.ICollectionAction;
import org.aftx.holers.android.xreader.ui.utils.CollectionList;

import roboguice.inject.InjectResource;
import android.app.ActionBar.OnNavigationListener;
import android.app.Activity;
import android.content.Context;
import android.widget.ArrayAdapter;

import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class CollectionAction extends BaseAction implements ICollectionAction{ 
    
    @InjectResource(R.string.title_history)
    private String title_history;
    
    @Inject
    public CollectionAction(Activity context) {
        super(context);
    }

    @Override
    public void AddCollection(String name) {
        binder.AddCollection(new Collection(name));
        Collection old = ((CollectionList) collectionList).GetEntity(actionBar
                .getSelectedNavigationIndex());
        UpdateCollectionList();
        actionBar.setSelectedNavigationItem(((CollectionList) collectionList)
                .GetItemIndex(old));
    }

    @Override
    public void DelCollection() {
        binder.DeleteCollection(((CollectionList) collectionList).GetEntity(
                actionBar.getSelectedNavigationIndex()).getId());
        UpdateCollectionList();
        actionBar.setSelectedNavigationItem(0);
    }

    @Override
    public void UpdateCollectionList() {
        collectionList.Update();
        List<String> data = collectionList.GetData();
        data.add(title_history);

        actionBar.setListNavigationCallbacks(
                new ArrayAdapter<String>(actionBar.getThemedContext(),
                        android.R.layout.simple_list_item_1,
                        android.R.id.text1, data), (OnNavigationListener) context);
    }
}
