import entity.NewsDetail;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.net.URL;
import java.net.URLConnection;

public class DownloadImage implements Runnable {
    NewsDetail newsDetail;
    public DownloadImage(NewsDetail newsDetail) {
        this.newsDetail = newsDetail;
    }
    public void run() {
        Jdbc.saveNews(newsDetail);
    }
}
