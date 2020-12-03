package entity;

import java.util.List;

public class BookList {
    private String url;
    private List<BookDetail> BookDetaileList;
    private String nextPageUrl;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<BookDetail> getBookDetaileList() {
        return BookDetaileList;
    }

    public void setBookDetaileList(List<BookDetail> BookDetaileList) {
        this.BookDetaileList = BookDetaileList;
    }

    public String getNextPageUrl() {
        return nextPageUrl;
    }

    public void setNextPageUrl(String nextPageUrl) {
        this.nextPageUrl = nextPageUrl;
    }

}
