package models;

import java.sql.Connection;

public class Siege {
	private int id;
	private String nom;

	public Siege(int id, String nom) {
		this.id = id;
		this.nom = nom;
	}

	public Siege() {
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

	public Siege getById(Connection connection, int id) {
		boolean nullConn = connection == null;
		if (nullConn)
			connection = database.Connect.getConnection();
		try {
			java.sql.PreparedStatement statement = connection.prepareStatement("SELECT * FROM Siege WHERE Id_Siege = ?");
			statement.setInt(1, id);
			java.sql.ResultSet result = statement.executeQuery();
			if (result.next()) {
				this.id = result.getInt("Id_Siege");
				this.nom = result.getString("nom");
			}
			statement.close();
			if (nullConn) {
				connection.close();
			}
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

	public static java.util.List<Siege> getAll(Connection connection) {
		java.util.List<Siege> sieges = new java.util.ArrayList<Siege>();
		boolean nullConn = connection == null;
		if (nullConn)
			connection = database.Connect.getConnection();
		try {
			java.sql.PreparedStatement statement = connection.prepareStatement("SELECT * FROM Siege order by id_siege asc");
			java.sql.ResultSet result = statement.executeQuery();
			while (result.next()) {
				Siege siege = new Siege(result.getInt("Id_Siege"), result.getString("nom"));
				sieges.add(siege);
			}
			statement.close();
			if (nullConn) {
				connection.close();
			}
		} catch (Exception e) {
			try {
				connection.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			e.printStackTrace();
		}
		return sieges;
	}
}
