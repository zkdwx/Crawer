import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Crawler {

    public static void main(String[] args) {
        //获取所有图书的分类列表
        List<String> bookKindList = getBookKindList();
        //遍历每个分类的列表，获取每个分类列表的URL
        bookKindList.forEach(bookListUrl -> {
            try {
                //创建一个缓冲池
                ExecutorService pool = Executors.newCachedThreadPool();
                //获取指定网页源码
                Document document = Jsoup.connect(bookListUrl).userAgent(CommonConstant.USER_AGENT).get();
                pool.execute(new DocumentRunner(document));
                int a = 100;
                while (a-- != 0) {
                    Element el = document.getElementById(CommonConstant.PAGEER_BOX);
                    Elements el2 = el.getElementsByClass(CommonConstant.NEXT_BTN);
                    if (el2 == null) {
                        System.out.println("最后一页。。。");
                        break;
                    }
                    String urlIndex = el2.attr(CommonConstant.HREF);
                    Document document2 = Jsoup.connect(urlIndex).userAgent(CommonConstant.USER_AGENT).get();
                    pool.execute(new DocumentRunner(document2));
                }
                pool.shutdown();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * 获取所有图书的分类列表
     *
     * @return
     */
    public static List<String> getBookKindList() {
        List<String> list = new ArrayList<>();
        try {
            //获取指定网页源码
            Document document = Jsoup.connect(CommonConstant.KONGFZ_URL).userAgent(CommonConstant.USER_AGENT).get();
            Elements elements = document.getElementsByClass(CommonConstant.DETAIL_LINK);
            for (Element e : elements) {
                String uri = e.getElementsByTag(CommonConstant.A).get(0).attr(CommonConstant.HREF);
                list.add(uri);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}