package models.vol;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;

import mg.itu.prom16.annotations.request.Exclude;
import models.avion.Avion;

public class Vol {
	@Exclude
	private int idVol;
	private int idAvion;
	private LocalDateTime dateVol;
	private LocalDateTime reservation; // Nullable
	private LocalDateTime annulation; // Nullable
	private int idVilleDepart;
	private int idVilleArrivee;

	@Exclude
	private Avion avion;
	@Exclude
	private Ville villeDepart;
	@Exclude
	private Ville villeArrivee;

	public Vol() {
	}

	public int getIdVol() {
		return idVol;
	}

	public void setIdVol(int idVol) {
		this.idVol = idVol;
	}

	public LocalDateTime getDateVol() {
		return dateVol;
	}

	public void setDateVol(LocalDateTime dateVol) {
		this.dateVol = dateVol;
	}

	public LocalDateTime getReservation() {
		return reservation;
	}

	public void setReservation(LocalDateTime reservation) {
		this.reservation = reservation;
	}

	public LocalDateTime getAnnulation() {
		return annulation;
	}

	public void setAnnulation(LocalDateTime annulation) {
		this.annulation = annulation;
	}

	public int getIdVilleDepart() {
		return idVilleDepart;
	}

	public void setIdVilleDepart(int idVille) {
		this.idVilleDepart = idVille;
	}

	public int getIdVilleArrivee() {
		return idVilleArrivee;
	}

	public void setIdVilleArrivee(int idVille1) {
		this.idVilleArrivee = idVille1;
	}

	public Vol getById(java.sql.Connection connection, int id) {
		boolean nullConn = connection == null;
		if (nullConn)
			connection = database.Connect.getConnection();
		try {
			java.sql.PreparedStatement statement = connection.prepareStatement("SELECT * FROM Vol WHERE Id_Vol = ?");
			statement.setInt(1, id);
			java.sql.ResultSet result = statement.executeQuery();
			if (result.next()) {
				this.idVol = result.getInt("Id_Vol");
				this.dateVol = result.getTimestamp("date_vol").toLocalDateTime();
				// reservation and annulation may be nullable
				java.sql.Timestamp resTimestamp = result.getTimestamp("reservation");
				this.reservation = resTimestamp != null ? resTimestamp.toLocalDateTime() : null;
				java.sql.Timestamp annTimestamp = result.getTimestamp("annulation");
				this.annulation = annTimestamp != null ? annTimestamp.toLocalDateTime() : null;
				this.idAvion = result.getInt("Id_Avion");
				this.idVilleDepart = result.getInt("Id_Ville_Depart");
				this.idVilleArrivee = result.getInt("Id_Ville_Arrivee");
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

	public static java.util.List<Vol> getAll(java.sql.Connection connection, int page, int pageSize) {
		java.util.List<Vol> list = new java.util.ArrayList<>();
		boolean nullConn = connection == null;
		if (nullConn)
			connection = database.Connect.getConnection();
		try {
			int offset = (page - 1) * pageSize;
			java.sql.PreparedStatement statement = connection.prepareStatement("SELECT * FROM Vol LIMIT ? OFFSET ?");
			statement.setInt(1, pageSize);
			statement.setInt(2, offset);
			java.sql.ResultSet result = statement.executeQuery();
			while (result.next()) {
				Vol vol = new Vol();
				vol.idVol = result.getInt("Id_Vol");
				vol.dateVol = result.getTimestamp("date_vol").toLocalDateTime();
				java.sql.Timestamp resTimestamp = result.getTimestamp("reservation");
				vol.reservation = resTimestamp != null ? resTimestamp.toLocalDateTime() : null;
				java.sql.Timestamp annTimestamp = result.getTimestamp("annulation");
				vol.annulation = annTimestamp != null ? annTimestamp.toLocalDateTime() : null;
				vol.idAvion = result.getInt("Id_Avion");
				vol.idVilleDepart = result.getInt("Id_Ville_Depart");
				vol.idVilleArrivee = result.getInt("Id_Ville_Arrivee");
				list.add(vol);
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

	public int getIdAvion() {
		return idAvion;
	}

	public void setIdAvion(int idAvion) {
		this.idAvion = idAvion;
	}

	public void save(java.sql.Connection connection) {
		boolean nullConn = connection == null;
		if (nullConn)
			connection = database.Connect.getConnection();
		try {
			String query = "INSERT INTO Vol (Id_Vol, date_vol, reservation, annulation, Id_Avion, Id_Ville_Depart, Id_Ville_Arrivee) VALUES (?, ?, ?, ?, ?, ?, ?)";
			java.sql.PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, this.idVol);
			statement.setTimestamp(2, java.sql.Timestamp.valueOf(this.dateVol));
			statement.setTimestamp(3, this.reservation != null ? java.sql.Timestamp.valueOf(this.reservation) : null);
			statement.setTimestamp(4, this.annulation != null ? java.sql.Timestamp.valueOf(this.annulation) : null);
			statement.setInt(5, this.idAvion);
			statement.setInt(6, this.idVilleDepart);
			statement.setInt(7, this.idVilleArrivee);
			statement.executeUpdate();
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
	}

	public void update(java.sql.Connection connection) {
		boolean nullConn = connection == null;
		if (nullConn)
			connection = database.Connect.getConnection();
		try {
			String query = "UPDATE Vol SET date_vol = ?, reservation = ?, annulation = ?, Id_Avion = ?, Id_Ville_Depart = ?, Id_Ville_Arrivee = ? WHERE Id_Vol = ?";
			java.sql.PreparedStatement statement = connection.prepareStatement(query);
			statement.setTimestamp(1, java.sql.Timestamp.valueOf(this.dateVol));
			statement.setTimestamp(2, this.reservation != null ? java.sql.Timestamp.valueOf(this.reservation) : null);
			statement.setTimestamp(3, this.annulation != null ? java.sql.Timestamp.valueOf(this.annulation) : null);
			statement.setInt(4, this.idAvion);
			statement.setInt(5, this.idVilleDepart);
			statement.setInt(6, this.idVilleArrivee);
			statement.setInt(7, this.idVol);
			statement.executeUpdate();
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
	}

	public Avion getAvion(Connection c) throws SQLException {
		boolean local = false;
		if (c == null) {
			c = database.Connect.getConnection();
			local = true;
		}
		if (this.avion == null) {
			this.avion = new Avion().getById(c, this.idAvion);
		}
		if (local)
			c.close();
		return this.avion;
	}

	public Ville getVilleDepart(Connection c) throws SQLException {
		boolean local = false;
		if (c == null) {
			c = database.Connect.getConnection();
			local = true;
		}
		if (this.villeDepart == null) {
			this.villeDepart = new Ville().getById(c, this.idVilleDepart);
		}
		if (local)
			c.close();
		return this.villeDepart;
	}

	public Ville getVilleArrivee(Connection c) throws SQLException {
		boolean local = false;
		if (c == null) {
			c = database.Connect.getConnection();
			local = true;
		}
		if (this.villeArrivee == null) {
			this.villeArrivee = new Ville().getById(c, this.idVilleArrivee);
		}
		if (local)
			c.close();
		return this.villeArrivee;
	}
}
