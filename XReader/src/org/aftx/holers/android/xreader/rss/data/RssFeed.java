package org.aftx.holers.android.xreader.rss.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RssFeed {
    private List<RssItem> listview;
    private int           itemCount = 0;
    private String        title, pubDate;

    public RssFeed() {
        listview = new ArrayList<RssItem>(0);
    }

    public int addItem(RssItem item) {
        listview.add(item);
        itemCount++;
        return itemCount;
    }

    public List<RssItem> getList() {
        return listview;
    }

    int getItemsCount() {
        return itemCount;
    }

    public RssItem getItem(int location) {
        return listview.get(location);
    }

    public List<Map<String, String>> getItemsForList() {
        List<Map<String, String>> data = new ArrayList<Map<String, String>>();
        int SIZE = listview.size();
        for (int i = 0; i < SIZE; i++) {
            Map<String, String> mdMap = new HashMap<String, String>();
            mdMap.put(RssItem.TITLE, listview.get(i).getTitle());
            mdMap.put(RssItem.PUBDATE, listview.get(i).getPubDate());
            data.add(mdMap);
        }
        return data;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }
}