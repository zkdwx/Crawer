import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;

public class ConnetionPool {
	public static DataSource dataSource;
	
	static {
		dataSource = new ComboPooledDataSource();
	}
}
