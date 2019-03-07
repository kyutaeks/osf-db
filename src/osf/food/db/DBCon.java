package osf.food.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBCon {
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String ID = "osfu";
	private static final String PASSWORD = "12345678";
	private static final String CLASS_NAME = "oracle.jdbc.OracleDriver";
	private static Connection con;

	private static boolean open() {
		try {
			Class.forName(CLASS_NAME);
			con = DriverManager.getConnection(URL, ID, PASSWORD);
			return true;
		} catch (ClassNotFoundException e) { 
			e.printStackTrace();
			// ClassNotFoundException 과 SQLException 은 둘다 Exception 으로 볼수있기때문에
			// catch (Exception e) 로 하나만써줘도된다.
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
		//전화를 예로 들었을때 전화를 끊어도 전화기는 그대로 남아있기때문에 널이아니라 공간안에 남아있다.
		//즉, 방안에 주소가 있는 전화기가 있는데 그전화기를 없대기전까진 방안에 주소를가진 전화기가 존재하는것이다.
	}

}
