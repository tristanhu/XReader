package org.aftx.holers.android.xreader.ui.activity;

import org.aftx.holers.android.xreader.R;
import org.aftx.holers.android.xreader.db.model.Book;
import org.aftx.holers.android.xreader.db.model.History;
import org.aftx.holers.android.xreader.service.fake.LogLayer;
import org.aftx.holers.android.xreader.ui.activity.base.BaseReadActivity;
import org.aftx.holers.android.xreader.ui.adapter.SectionsPagerAdapter;
import org.aftx.holers.android.xreader.ui.handler.UiHandler;
import org.aftx.holers.android.xreader.ui.utils.BookList;
import org.aftx.holers.android.xreader.ui.utils.CollectionList;
import org.aftx.holers.android.xreader.ui.utils.HistoryList;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.Menu;

public class ReadActivity extends BaseReadActivity {

    protected Book               book;

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link android.support.v4.app.FragmentPagerAdapter} derivative, which
     * will keep every loaded fragment in memory. If this becomes too memory
     * intensive, it may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);

        // Set up the action bar.
        actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the app.
        mSectionsPagerAdapter = new SectionsPagerAdapter(
                getSupportFragmentManager());

        mSectionsPagerAdapter.setContext(this);

        Bundle bundle = getIntent().getExtras();
        book = new Book(bundle.getString("Name"), bundle.getString("Path"));
        book.setId(bundle.getInt("Id"));
        mSectionsPagerAdapter.setBook(book);
        setTitle(book.getName());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        // When swiping between different sections, select the corresponding
        // tab. We can also use ActionBar.Tab#select() to do this if we have
        // a reference to the Tab.
        mViewPager
                .setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
                    @Override
                    public void onPageSelected(int position) {
                        History history;
                        history = new History(book.getId(), position + 1);
                        ((HistoryList) ReadActivity.this.historyList)
                                .AddHistory(history);
                    }
                });

        actionBar.addTab(actionBar.newTab().setText(R.string.title_read)
                .setTabListener(this));

        actionBar.addTab(actionBar.newTab().setText(R.string.title_mark)
                .setTabListener(this));

        mViewPager.setCurrentItem(bundle.getInt("Page") - 1);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.read, menu);
        return true;
    }
}
