package osf;

import java.sql.Connection;

import osf.db.DBConnector;

public class Execute {
	public static void main(String[] args) {
		Connection con = DBConnector.getCon();
	}
}
