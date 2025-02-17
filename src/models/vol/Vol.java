package models.vol;

import java.time.LocalDateTime;

public class Vol {
	private int idVol,idAvion;
	private LocalDateTime dateVol;
	private LocalDateTime reservation; // Nullable
	private LocalDateTime annulation; // Nullable
	private int idVilleDepart;
	private int idVilleArrivee;

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
	        if (nullConn) connection.close();
	    } catch (Exception e) {
	        try { connection.close(); } catch (Exception e2) { e2.printStackTrace(); }
	        e.printStackTrace();
	    }
	    return this;
	}

	public static java.util.List<Vol> getAll(java.sql.Connection connection) {
	    java.util.List<Vol> list = new java.util.ArrayList<>();
	    boolean nullConn = connection == null;
	    if (nullConn)
	        connection = database.Connect.getConnection();
	    try {
	        java.sql.PreparedStatement statement = connection.prepareStatement("SELECT * FROM Vol");
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
	        if (nullConn) connection.close();
	    } catch (Exception e) {
	        try { connection.close(); } catch (Exception e2) { e2.printStackTrace(); }
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
}
