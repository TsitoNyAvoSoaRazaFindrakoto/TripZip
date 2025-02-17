package models.reservation;

import java.time.LocalTime;

public class ConfigReservation {
	private String libelle;
	private LocalTime duree;

	public ConfigReservation() {
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public LocalTime getDuree() {
		return duree;
	}

	public void setDuree(LocalTime duree) {
		this.duree = duree;
	}

	public ConfigReservation getById(java.sql.Connection connection, String libelle) {
		boolean nullConn = connection == null;
		if (nullConn)
			connection = database.Connect.getConnection();
		try {
			java.sql.PreparedStatement statement = connection
					.prepareStatement("SELECT * FROM Config_reservation WHERE libelle = ?");
			statement.setString(1, libelle);
			java.sql.ResultSet result = statement.executeQuery();
			if (result.next()) {
				this.libelle = result.getString("libelle");
				this.duree = LocalTime.parse(result.getString("duree"));
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

	public static java.util.List<ConfigReservation> getAll(java.sql.Connection connection) {
		java.util.List<ConfigReservation> list = new java.util.ArrayList<>();
		boolean nullConn = connection == null;
		if (nullConn)
			connection = database.Connect.getConnection();
		try {
			java.sql.PreparedStatement statement = connection.prepareStatement("SELECT * FROM Config_reservation");
			java.sql.ResultSet result = statement.executeQuery();
			while (result.next()) {
				ConfigReservation cr = new ConfigReservation();
				cr.libelle = result.getString("libelle");
				cr.duree = LocalTime.parse(result.getString("duree"));
				list.add(cr);
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
