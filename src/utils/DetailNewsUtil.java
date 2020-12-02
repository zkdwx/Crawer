package utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DetailNewsUtil {
    //获取图片的url
    public String getUrl(String detailUrl) {
        List<String> list = new ArrayList<>();
        try {
            Document document = Jsoup.connect(detailUrl).get();
            Element element = document.getElementById("article");
            Elements imgElements = element.select("img[src]");
            for (Element e : imgElements) {
                String src = e.attr("src");//获取到src的值
                list.add("http:" + src);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
        if (list.size() > 0) {
            return list.get(0);
        } else {
            return "";
        }
    }
}
