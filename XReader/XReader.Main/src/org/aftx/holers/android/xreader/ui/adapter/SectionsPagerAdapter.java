package org.aftx.holers.android.xreader.ui.adapter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.aftx.holers.android.xreader.db.model.Book;
import org.aftx.holers.android.xreader.ui.fragment.ReadFragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to one
 * of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {
    private Book    book;
    private String  content;
    private int     count = 40;

    public void setBook(Book book) {
        this.book = book;
        BufferedReader reader;
        try {
            File file = new File(book.getPath());
            reader = new BufferedReader(new FileReader(file));
            int length = (int) file.length();
            char[] buffer = new char[length];
            reader.read(buffer, 0, length);
            content = new String(buffer);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setContext(Context context) {
    }

    public SectionsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a DummySectionFragment (defined as a static inner class
        // below) with the page number as its lone argument.

        Fragment fragment = new ReadFragment();
        Bundle args = new Bundle();
        int start, end;
        start = position * count;
        end = start + count;
        if (end > content.length()) end = content.length();
        args.putString("Content", content.substring(start, end));
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getCount() {
        return content.length() / count;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return book.getName() + "--ตฺ" + String.valueOf(position + 1) + "าณ";
    }
}
