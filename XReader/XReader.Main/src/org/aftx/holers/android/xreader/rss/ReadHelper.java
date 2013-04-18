package org.aftx.holers.android.xreader.rss;

import org.aftx.holers.android.xreader.rss.data.RssFeed;
import org.aftx.holers.android.xreader.rss.data.RssItem;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class ReadHelper extends DefaultHandler {
	private RssFeed r_Feed;
	private RssItem r_item;
	
	private static final int RSS_TITLE = 1;
	private static final int RSS_LINK = 2;
	private static final int RSS_PUBDATE = 3;
	private static final int RSS_DESCRIPTION = 4;
	int currentStatus = 0;

	public ReadHelper() {
	}

	public RssFeed getFeed(){
		return r_Feed;
	}
	
	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
			if(length<5){
				return;
			}
			else{
				String conString = new String(ch,start,length);
				switch (currentStatus) {
				case RSS_TITLE:
					r_item.setTitle(conString);
					currentStatus = 0;
					break;
				case RSS_LINK:
					r_item.setLink(conString);
					currentStatus = 0;
					break;
				case RSS_PUBDATE:
					r_item.setPubDate(conString);
					currentStatus = 0;
					break;
				case RSS_DESCRIPTION:
					r_item.setDescription(conString);
					currentStatus = 0;
					break;
				default:
					break;
				}
			}
	}

	@Override
	public void endDocument() throws SAXException {

	}

	@Override
	public void endElement(String uri, String localName, String name)
			throws SAXException {
		if (localName.equals("item")) {
			r_Feed.addItem(r_item);
			return;
		}
	}

	@Override
	public void startDocument() throws SAXException {
		// TODO Auto-generated method stub
		r_Feed = new RssFeed();
		r_item = new RssItem();
	}

	@Override
	public void startElement(String uri, String localName, String name,
			Attributes attributes) throws SAXException {
		if (localName.equals("channel")) {
			currentStatus = 0;
			return;
		}
		if (localName.equals("item")) {
			r_item = new RssItem();
			return;
		}
		if (localName.equals("title")) {
			currentStatus = RSS_TITLE;
			return;
		}
		if (localName.equals("link")) {
			currentStatus = RSS_LINK;
			return;
		}
		if (localName.equals("pubDate")) {
			currentStatus = RSS_PUBDATE;
			return;
		}
		if (localName.equals("description")) {
			currentStatus = RSS_DESCRIPTION;;
			return;
		}
		currentStatus = 0;
	}

}
