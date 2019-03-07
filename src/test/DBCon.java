package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBCon {
	public static final String URL = "jdbc:oracle:thin@:localhost:1521:xe";
	public static final String ID = "osfu";
	public static final String PASSWORD = "12345678";
	public static final String CLASS_NAME = "oracle.jdbc.OracleDriver";
	public static Connection con;

	public static boolean open() {
		try {
			Class.forName(CLASS_NAME);
			con = DriverManager.getConnection(URL,ID,PASSWORD);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	public static Connection getCon() {
		if(con==null) {
			if(open()) {
				return con;
			}
		}
		return null;
	}
	public static void close() {
		if(con!= null) {
			try {
				if(con.isClosed()) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		con = null;
	}
}
