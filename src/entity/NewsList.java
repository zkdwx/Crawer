package entity;

import java.util.List;

public class NewsList {
    private String url;
    private List<NewsDetail> newsDetaileList;
    private String nextPageUrl;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<NewsDetail> getNewsDetaileList() {
        return newsDetaileList;
    }

    public void setNewsDetaileList(List<NewsDetail> newsDetaileList) {
        this.newsDetaileList = newsDetaileList;
    }

    public String getNextPageUrl() {
        return nextPageUrl;
    }

    public void setNextPageUrl(String nextPageUrl) {
        this.nextPageUrl = nextPageUrl;
    }

}
