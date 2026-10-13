package modal;

import java.sql.Connection;
import java.sql.DriverManager;

public class KetNoi {
	public Connection cn;

	public KetNoi() {
	}

	public void ketnoi() throws Exception {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

		String host = getConfig("actionmovie.db.host", "ACTIONMOVIE_DB_HOST", "localhost");
		String port = getConfig("actionmovie.db.port", "ACTIONMOVIE_DB_PORT", "1433");
		String database = getConfig("actionmovie.db.name", "ACTIONMOVIE_DB_NAME", "ActionMovie");
		String user = getConfig("actionmovie.db.user", "ACTIONMOVIE_DB_USER", "sa");
		String password = getConfig("actionmovie.db.password", "ACTIONMOVIE_DB_PASSWORD", "281105");

		String url = "jdbc:sqlserver://" + host + ":" + port
				+ ";databaseName=" + database + ";encrypt=true;trustServerCertificate=true";
		this.cn = DriverManager.getConnection(url, user, password);
		System.out.println("Da ket noi");
	}

	private String getConfig(String property, String environmentVariable, String defaultValue) {
		String value = System.getProperty(property);
		if (value == null || value.trim().isEmpty()) {
			value = System.getenv(environmentVariable);
		}
		return value == null || value.trim().isEmpty() ? defaultValue : value;
	}
}
