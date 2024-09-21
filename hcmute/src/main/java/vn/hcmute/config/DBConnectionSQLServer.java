package vn.hcmute.config;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnectionSQLServer {
	private final String serverName = "LAPTOP-G2CE3P1E";
	private final String dbName = "LT_Web";
	private final String portNumber = "1433";
	private final String instance = "";
	private final String userID = "user";
	private final String password = "12345678";

	public Connection getConnection() throws Exception {
		String url = "jdbc:sqlserver://" + serverName + "\\" + instance + ":" + portNumber + ";databaseName=" + dbName;
		if (instance == null || instance.trim().isEmpty())
			url = "jdbc:sqlserver://" + serverName + ":" + portNumber + ";databaseName=" + dbName;
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		return DriverManager.getConnection(url, userID, password);
	}
	
	public static void main(String[] args) {
		try {
			DBConnectionSQLServer conn = new DBConnectionSQLServer();
			System.out.println(conn.getConnection());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
