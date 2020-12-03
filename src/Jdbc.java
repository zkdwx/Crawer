import entity.BookDetail;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.SQLException;

public class Jdbc {
	
	public static void saveBook(BookDetail detail) {

		QueryRunner qr = new QueryRunner(ConnectionPool.dataSource);
		
		try {
			qr.update("insert into book(title,url) value(?,?)", detail.getTitle(), detail.getUrl());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
