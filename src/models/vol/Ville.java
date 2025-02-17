package models.vol;

public class Ville {
	private int idVille;
	private String nom;

	public Ville(int id, String nom) {
		this.idVille = id;
		this.nom = nom;
	}

	public Ville() {
	}

	public int getIdVille() {
		return idVille;
	}

	public void setIdVille(int id) {
		this.idVille = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public static java.util.List<Ville> getAll() {
		java.util.List<Ville> villes = new java.util.ArrayList<>();
		java.sql.Connection connection = database.Connect.getConnection();
		try {
			java.sql.Statement statement = connection.createStatement();
			java.sql.ResultSet resultSet = statement.executeQuery("SELECT * FROM Ville");
			while (resultSet.next()) {
				Ville ville = new Ville(resultSet.getInt("Id_Ville"), resultSet.getString("nom"));
				villes.add(ville);
			}
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return villes;
	}

	public Ville getById(int id) {
		Ville ville = null;
		java.sql.Connection connection = database.Connect.getConnection();
		try {
			java.sql.PreparedStatement preparedStatement = connection
					.prepareStatement("SELECT * FROM Ville WHERE Id_Ville = ?");
			preparedStatement.setInt(1, id);
			java.sql.ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				this.setIdVille(resultSet.getInt("Id_Ville"));
				setNom(resultSet.getString("nom"));
			}
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ville;
	}

	// Added getById and getAll methods using Siege.java as template for Ville

	public Ville getById(java.sql.Connection connection, int id) {
		boolean nullConn = connection == null;
		if (nullConn)
			connection = database.Connect.getConnection();
		try {
			java.sql.PreparedStatement statement = connection.prepareStatement("SELECT * FROM Ville WHERE Id_Ville = ?");
			statement.setInt(1, id);
			java.sql.ResultSet result = statement.executeQuery();
			if (result.next()) {
				this.idVille = result.getInt("Id_Ville");
				this.nom = result.getString("nom");
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
		return this;
	}

	public static java.util.List<Ville> getAll(java.sql.Connection connection) {
		java.util.List<Ville> list = new java.util.ArrayList<>();
		boolean nullConn = connection == null;
		if (nullConn)
			connection = database.Connect.getConnection();
		try {
			java.sql.PreparedStatement statement = connection.prepareStatement("SELECT * FROM Ville");
			java.sql.ResultSet result = statement.executeQuery();
			while (result.next()) {
				Ville ville = new Ville();
				ville.idVille = result.getInt("Id_Ville");
				ville.nom = result.getString("nom");
				list.add(ville);
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
		return list;
	}
}
