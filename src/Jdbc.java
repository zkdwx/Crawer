import entity.NewsDetail;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.SQLException;

public class Jdbc {
	
	public static void saveNews(NewsDetail detail) {
		
		QueryRunner qr = new QueryRunner(ConnetionPool.dataSource);
		
		try {
			qr.update("insert into news(title,url) value(?,?)", detail.getTitle(), detail.getUrl());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
