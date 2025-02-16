package models;

public class Siege {
	private int id;
	private String nom;

	public Siege(int id, String nom) {
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

	// getById
	public Siege getById(int id) {
		java.sql.Connection connection = database.Connect.getConnection();
		try {
			java.sql.PreparedStatement statement = connection.prepareStatement("SELECT * FROM Siege WHERE Id_Siege = ?");
			statement.setInt(1, id);
			java.sql.ResultSet result = statement.executeQuery();
			if (result.next()) {
				this.id = result.getInt("Id_Siege");
				this.nom = result.getString("nom");
			}
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}

	// getAll
	public static java.util.List<Siege> getAll() {
		java.util.List<Siege> sieges = new java.util.ArrayList<Siege>();
		java.sql.Connection connection = database.Connect.getConnection();
		try {
			java.sql.PreparedStatement statement = connection.prepareStatement("SELECT * FROM Siege");
			java.sql.ResultSet result = statement.executeQuery();
			while (result.next()) {
				Siege siege = new Siege(result.getInt("Id_Siege"), result.getString("nom"));
				sieges.add(siege);
			}
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sieges;
	}
}
