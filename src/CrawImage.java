import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//引入线程池的包
//引入jsoup的包


public class CrawImage {

    public static void getUrl(Document document, ExecutorService pool) {
        Element id = document.getElementById("listBox");
        Elements els = id.getElementsByTag("img");

        for (Element el : els) {
            //url.add(el.attr("src"));
            String imageUrl = el.attr("src");
            System.out.println("imageUrl==== " + imageUrl);
            pool.execute(new DownloadImage(imageUrl));
//            System.out.println(el.attr("src"));
        }

    }

    public static void main(String[] args) throws Exception {
        //Set<String> url = new TreeSet<>();
        try {
            //创建一个缓冲池
            ExecutorService pool = Executors.newCachedThreadPool();
            //设置其容量为9
            pool = Executors.newFixedThreadPool(9);

            //获取指定网页源码
            Document document = Jsoup.connect("http://item.kongfz.com/Cjisuanji/w2/").userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.64 Safari/537.31").get();
            getUrl(document, pool);
            int a = 4;
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

            //遍历set中图片的url
//            for(String imageUrl:url){
//                pool.execute(new DownloadImage(imageUrl));
//            }
            pool.shutdown();

        } catch (Exception e) {
            System.out.print("嘻嘻" + e);
        }
    }
}