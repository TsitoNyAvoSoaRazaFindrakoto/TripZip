package models.vol;

public class Ville {
	private int id;
	private String nom;

	public Ville(int id, String nom) {
		this.id = id;
		this.nom = nom;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
				this.setId(resultSet.getInt("Id_Ville"));
				setNom(resultSet.getString("nom"));
			}
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ville;
	}
}
