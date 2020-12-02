import entity.NewsDetail;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//引入线程池的包
//引入jsoup的包


public class CrawImage {

    public static void getUrl(Document document, ExecutorService pool) {
        Elements elements = document.getElementsByClass("item clearfix");
        for (Element e : elements) {
            Elements els = e.getElementsByTag("img");
            String imageUrl = els.get(0).attr("src");
            String title = e.getElementsByTag("a").get(1).text();
            NewsDetail newsDetail = new NewsDetail();
            newsDetail.setTitle(title);
            newsDetail.setUrl(imageUrl);
            pool.execute(new DownloadImage(newsDetail));
        }

    }


    public static void main(String[] args) throws Exception {

        //获取列表类目
        List<String> bookKindList = getBookKindList();

        bookKindList.forEach(bookListUrl -> {
            try {
                //创建一个缓冲池
                ExecutorService pool = Executors.newCachedThreadPool();
                //获取指定网页源码
                Document document = Jsoup.connect(bookListUrl).userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.64 Safari/537.31").get();
                getUrl(document, pool);
                int a = 100;
                while (a-- != 0) {
                    Element el = document.getElementById("pagerBox");
                    Elements el2 = el.getElementsByClass("next-btn");
                    if (el2 == null) {
                        System.out.println("到最后了");
                        break;
                    }
                    String urlIndex = el2.attr("href");
                    Document document2 = Jsoup.connect(urlIndex).userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.64 Safari/537.31").get();
                    getUrl(document2, pool);
                }
                pool.shutdown();

            } catch (Exception e) {
                System.out.print("嘻嘻" + e);
            }
        });
    }

    //获取各个类目的列表
    public static List<String> getBookKindList() {
        List<String> list = new ArrayList<>();
        try {
            //获取指定网页源码
            Document document = Jsoup.connect("https://www.kongfz.com/").userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.64 Safari/537.31").get();
            Elements elements = document.getElementsByClass("detail-link");

            for (Element e : elements) {
                String uri = e.getElementsByTag("a").get(0).attr("href");
                list.add(uri);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}