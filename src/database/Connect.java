package database;

public class Connect {
	// connection credentials (user, password, database) postgresql
	private static final String URL = "jdbc:postgresql://localhost:5432/";
	private static final String USER = "postgres";
	private static final String PASSWORD = "postgres";
	private static final String DATABASE = "tripzip";

	// getConnection static method
	public static java.sql.Connection getConnection() {
		try {
			Class.forName("org.postgresql.Driver");
			return java.sql.DriverManager.getConnection(URL + DATABASE, USER, PASSWORD);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static int getCount(java.sql.Connection connection, String table) {
		int count = 0;
		boolean nullConn = connection == null;
		if (nullConn)
			connection = database.Connect.getConnection();
		try {
			java.sql.PreparedStatement statement = connection.prepareStatement("SELECT COUNT(*) AS total FROM "+ table);
			java.sql.ResultSet result = statement.executeQuery();
			if (result.next()) {
				count = result.getInt("total");
			}
			statement.close();
			if (nullConn)
				connection.close();
		} catch (Exception e) {
			try {
				connection.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			e.printStackTrace();
		}
		return count;
	}

}
