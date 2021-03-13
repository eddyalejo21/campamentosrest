package ec.gob.epmmop.utils.bdd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
	private static final String IP_BIO = "10.52.66.202";
	private static final String DATABASE_BIO = "EQ_RRHH_C2";
	private static final String USER_BIO = "sa";
	
	public  static Connection getConnection() throws ClassNotFoundException, SQLException {  
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String connectionUrl = "jdbc:sqlserver://" + IP_BIO + ";"
                + "databaseName=" + DATABASE_BIO + ";"
                + "user=" + USER_BIO + ";"
                + "password=" + USER_BIO; 
        return DriverManager.getConnection(connectionUrl);
		
	}
	
	
	public  static Connection getDistConnection() throws ClassNotFoundException, SQLException {  
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String connectionUrl = "jdbc:sqlserver://" + IP_BIO + ";"
                + "databaseName=" + DATABASE_BIO + ";"
                + "user=" + USER_BIO + ";"
                + "password=" + USER_BIO; 
        return DriverManager.getConnection(connectionUrl);
		
	}

	

	
}
