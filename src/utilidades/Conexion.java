package utilidades;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

//import org.apache.commons.dbcp2.BasicDataSource;
import org.h2.jdbcx.JdbcConnectionPool;


public class Conexion {
	
	private static final String JDBC_URL = "jdbc:h2:../Taro_h2/sistema";
    private static final String JDBC_USER = "sa";
    private static final String JDBC_PASS = "h2Taro";
    
    private static JdbcConnectionPool cp;
    
    public static JdbcConnectionPool getConnectionPool(){
    	if (cp == null) {
    		cp = JdbcConnectionPool.
    				create(JDBC_URL,JDBC_USER, JDBC_PASS);
    	}
        return cp;
    }
    
    public static Connection getConnection() throws SQLException {
    	return getConnectionPool().getConnection();
        
    }
    
    public static void close(ResultSet rs){
        try {
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }
    
    public static void close(PreparedStatement stmt){
        try {
            stmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }
    
    public static void close(Connection conn){
    	
        try {
            conn.close();
//            getConnectionPool().dispose();
        } catch (SQLException ex) {
           ex.printStackTrace(System.out);
        }
    }

}
