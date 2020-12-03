import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;

/**
 * 连接池
 */
public class ConnectionPool {
    public static DataSource dataSource;

    static {
        dataSource = new ComboPooledDataSource();
    }
}
