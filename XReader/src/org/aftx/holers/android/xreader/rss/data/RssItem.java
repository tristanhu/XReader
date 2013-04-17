package org.aftx.holers.android.xreader.rss.data;

//这是一个实体类
public class RssItem {
	//声明两个静态常量，有来表示标题与时间
	public static String TITLE = "title";
	public static String PUBDATE = "pubDate";
	
	//定义一个我们需要从RSS中获取的数据字段
	private String title = null;
	private String pubDate = null;
	private String link = null;
	private String description = null;
	
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

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		if(title.length()>18){
			return title.substring(0,18)+"...";
		}
		return super.toString();
	}
}
