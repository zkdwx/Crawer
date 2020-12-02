import entity.NewsDetail;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.net.URL;
import java.net.URLConnection;

public class DownloadImage implements Runnable {
//    String downUrl;

    NewsDetail newsDetail;

    public DownloadImage(NewsDetail newsDetail) {
        this.newsDetail = newsDetail;
    }
/*
    public void run() {
        BufferedInputStream bis = null;
        FileOutputStream fos = null;
        try {
            //生成url对象
            if (downUrl != null && downUrl.length() != 0 && downUrl.contains("https")) {
                System.out.println("downUrl====  " + downUrl);
                URL url = new URL(downUrl);
                //创建urlconnection对象
                URLConnection uc = url.openConnection();
                //获取uc的输入流
                bis = new BufferedInputStream(uc.getInputStream());
                //创建图片的存储对象
                String[] p = downUrl.split("/");
                String path = "D:\\images\\" + p[p.length - 1];
                fos = new FileOutputStream(path);
                int c;
                while ((c = bis.read()) != -1) {
                    fos.write(c);
                }
            }
        } catch (Exception e) {
            System.out.println("哈哈" + e);
        } finally {
            try {
                if (bis != null) {
                    bis.close();
                }

                if (fos != null) {
                    fos.close();
                }
            } catch (Exception e) {
                System.out.println("没办法了");
            }
        }
    }

*/

    public void run() {
        Jdbc.saveNews(newsDetail);
    }
}
