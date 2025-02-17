package models.avion;

import java.time.LocalDate;
import java.sql.Connection;

public class Avion {
	private int idAvion;
	private String modele;
	private LocalDate fabrication;

	private SiegesAvions[] sieges;

	public Avion() {
	}

	public Avion(int idAvion, String modele, LocalDate fabrication) {
		this.idAvion = idAvion;
		this.modele = modele;
		this.fabrication = fabrication;
	}

	public int getIdAvion() {
		return idAvion;
	}

	public void setIdAvion(int idAvion) {
		this.idAvion = idAvion;
	}

	public String getModele() {
		return modele;
	}

	public void setModele(String modele) {
		this.modele = modele;
	}

	public LocalDate getFabrication() {
		return fabrication;
	}

	public void setFabrication(LocalDate fabrication) {
		this.fabrication = fabrication;
	}

	public SiegesAvions[] getSieges() {
		return sieges;
	}

	public void setSieges(SiegesAvions[] sieges) {
		this.sieges = sieges;
	}

	public Avion getById(Connection connection, int id) {
		boolean nullConn = connection == null;
		if (nullConn)
			connection = database.Connect.getConnection();
		try {
			java.sql.PreparedStatement statement = connection.prepareStatement("SELECT * FROM Avion WHERE Id_Avion = ?");
			statement.setInt(1, id);
			java.sql.ResultSet result = statement.executeQuery();
			if (result.next()) {
				this.idAvion = result.getInt("Id_Avion");
				this.modele = result.getString("modele");
				this.fabrication = result.getDate("fabrication").toLocalDate();
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

	public static java.util.List<Avion> getAll(Connection connection) {
		java.util.List<Avion> avions = new java.util.ArrayList<>();
		boolean nullConn = connection == null;
		if (nullConn)
			connection = database.Connect.getConnection();
		try {
			java.sql.PreparedStatement statement = connection.prepareStatement("SELECT * FROM Avion");
			java.sql.ResultSet result = statement.executeQuery();
			while (result.next()) {
				Avion avion = new Avion(result.getInt("Id_Avion"), result.getString("modele"),
						result.getDate("fabrication").toLocalDate());
				avions.add(avion);
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
		return avions;
	}

	// getSieges(Connection c,int idAvion)
	public SiegesAvions[] getSieges(Connection connection) {
		boolean nullConn = connection == null;
		if (nullConn) {
			connection = database.Connect.getConnection();
		}
		if (this.sieges == null) {
			this.sieges = SiegesAvions.getByIdAvion(connection, this.idAvion).toArray(new SiegesAvions[0]);
		}
		if (nullConn) {
			try {
				connection.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return this.sieges;
	}
}
