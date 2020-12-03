import entity.BookDetail;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.net.URL;
import java.net.URLConnection;

public class CrawlerHelp implements Runnable {
    BookDetail BookDetail;
    public CrawlerHelp(BookDetail BookDetail) {
        this.BookDetail = BookDetail;
    }
    public void run() {
        Jdbc.saveBook(BookDetail);
    }
}
