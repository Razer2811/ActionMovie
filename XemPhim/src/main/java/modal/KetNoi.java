package modal;

import java.sql.Connection;
import java.sql.DriverManager;

public class KetNoi {
	public Connection cn;

	public KetNoi() {
	}

	public void ketnoi() throws Exception {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		this.cn = DriverManager.getConnection("jdbc:sqlserver://MINH-KHANH:1433;databaseName=ActionMovie;user=sa;password=123");
		System.out.println("Da ket noi");
	}
}
