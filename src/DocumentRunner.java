import entity.BookDetail;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class DocumentRunner implements Runnable {
    private Document document;

    public DocumentRunner(Document document) {
        this.document = document;
    }

    @Override
    public void run() {
        getAndSaveBookDetail(document);
    }

    /**
     * 解析网页获取BookDetail并保存到数据苦
     *
     * @param document
     */
    private void getAndSaveBookDetail(Document document) {
        Elements elements = document.getElementsByClass("item clearfix");
        for (Element e : elements) {
            Elements els = e.getElementsByTag("img");
            String imageUrl = els.get(0).attr("src");
            String title = e.getElementsByTag("a").get(1).text();
            BookDetail BookDetail = new BookDetail();
            BookDetail.setTitle(title);
            BookDetail.setUrl(imageUrl);
            Jdbc.saveBook(BookDetail);
        }
    }
}
