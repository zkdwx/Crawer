import entity.BookDetail;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.SQLException;

public class Jdbc {
	
	public static void saveBook(BookDetail detail) {
		
		QueryRunner qr = new QueryRunner(ConnetionPool.dataSource);
		
		try {
			qr.update("insert into book2(title,url) value(?,?)", detail.getTitle(), detail.getUrl());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
