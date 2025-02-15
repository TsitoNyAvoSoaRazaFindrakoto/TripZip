package database;

public class Connect {
	// connection credentials (user, password, database) postgresql
	private static final String URL = "jdbc:postgresql://localhost:5432/";
	private static final String USER = "postgres";
	private static final String PASSWORD = "postgre";
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

}
