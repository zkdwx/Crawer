package utils;

import entity.NewsDetail;
import entity.NewsList;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class NewsListUtil {
    //获取网页列表
    public NewsList getNewsList(String url) {
        NewsList newsList = new NewsList();
        newsList.setUrl(url);
        try {
            Document document = Jsoup.connect(url).get();
            Elements newsListElements = document.getElementsByTag("li");
            List<NewsDetail> newsDetaileList = new ArrayList<>();
            for (Element e : newsListElements) {
                NewsDetail newsDetail = new NewsDetail();
                String title = e.getElementsByTag("a").get(0).text();
                System.out.println("title=" + title);
                String detailUrl = e.getElementsByTag("a").get(0).attr("href");
                newsDetail.setTitle(title);
                newsDetail.setUrl(detailUrl);
                System.out.println("detailUrl=" + detailUrl);
                newsDetaileList.add(newsDetail);
            }
            newsList.setNewsDetaileList(newsDetaileList);

            String nextPageUrl = null;
            if (!document.getElementsContainingOwnText("下一页").isEmpty()) {
                nextPageUrl = document.getElementsContainingOwnText("下一页").get(0).attr("href");
                newsList.setNextPageUrl("http://roll.news.sina.com.cn/news/gnxw/gdxw1" + nextPageUrl.substring(1));
                System.out.println("nextPageUrl=" + newsList.getNextPageUrl());
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return newsList;
    }
}
