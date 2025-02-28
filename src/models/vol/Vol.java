package models.vol;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import mg.itu.prom16.annotations.request.Exclude;
import mg.itu.prom16.annotations.request.FieldAlternate;
import mg.itu.prom16.annotations.validation.constraints.Required;
import models.avion.Avion;
import models.avion.SiegesAvions;

public class Vol {
	private int idVol;
	private int idAvion;
	@Required
	private LocalDateTime dateVol;
	private LocalDateTime reservation;
	private LocalDateTime annulation;
	@Exclude
	private boolean etat;

	@Required
	@FieldAlternate(name = "villeDepart")
	private int idVilleDepart;
	@Required
	@FieldAlternate(name = "villeArrivee")
	private int idVilleArrivee;

	@Exclude
	private Avion avion;
	@Exclude
	private Ville villeDepart;
	@Exclude
	private Ville villeArrivee;

	@Exclude
	private List<SiegeVol> sieges;


	public Vol() {
	}

	public Vol(int idVol, int idAvion, LocalDateTime dateVol, LocalDateTime reservation, LocalDateTime annulation,
			int idVilleDepart, int idVilleArrivee) {
		this.idVol = idVol;
		this.idAvion = idAvion;
		this.dateVol = dateVol;
		this.reservation = reservation;
		this.annulation = annulation;
		this.idVilleDepart = idVilleDepart;
		this.idVilleArrivee = idVilleArrivee;
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

	public Vol getById(java.sql.Connection connection, int id) throws Exception{
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
				this.etat = result.getBoolean("etat");
			}
			statement.close();
			if (nullConn)
				connection.close();
		} catch (Exception e) {
				connection.close();
				throw e;
			
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
				vol.etat = result.getBoolean("etat");
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

	public void save(Connection connection) throws SQLException {
		boolean nullConn = connection == null;
		if (nullConn) {
			connection = database.Connect.getConnection();
		}

		try (PreparedStatement statement = connection.prepareStatement(
				"INSERT INTO Vol (Id_Vol, date_vol, reservation, annulation, Id_Avion, Id_Ville_Depart, Id_Ville_Arrivee) VALUES (default, ?, ?, ?, ?, ?, ?)",
				PreparedStatement.RETURN_GENERATED_KEYS)) {

			statement.setTimestamp(1, Timestamp.valueOf(this.dateVol));
			statement.setTimestamp(2, this.reservation != null ? Timestamp.valueOf(this.reservation) : null);
			statement.setTimestamp(3, this.annulation != null ? Timestamp.valueOf(this.annulation) : null);
			statement.setInt(4, this.idAvion);
			statement.setInt(5, this.idVilleDepart);
			statement.setInt(6, this.idVilleArrivee);

			statement.executeUpdate();

			try (ResultSet generatedKeys = statement.getGeneratedKeys()) { // Get the generated keys
				if (generatedKeys.next()) {
					this.idVol = generatedKeys.getInt(1);
					List<SiegesAvions> siegesAvions = getAvion(connection).getSieges(connection);
					for (SiegesAvions sa : siegesAvions) {
						SiegeVol sv = new SiegeVol();
						sv.setIdSiege(sa.getIdSiege());
						sv.setIdVol(this.idVol);
						sv.saveOrUpdate(connection);
					}
				} else {
					throw new SQLException("Could not retrieve generated key.");
				}
			}

		} finally {
			if (nullConn) {
				connection.close();
			}
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

	public static void teminerVol(java.sql.Connection connection, int idVol) {
		boolean nullConn = connection == null;
		if (nullConn)
			connection = database.Connect.getConnection();
		try {
			String query = "UPDATE Vol SET etat = ? WHERE Id_Vol = ?";
			java.sql.PreparedStatement statement = connection.prepareStatement(query);
			statement.setBoolean(1, true);
			statement.setInt(2, idVol);
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
			this.avion.getSieges(c);
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

	public void getVilles(Connection c) throws SQLException {
		boolean local = false;
		if (c == null) {
			c = database.Connect.getConnection();
			local = true;
		}
		getVilleArrivee(c);
		getVilleDepart(c);
		if (local)
			c.close();
	}

	public void getData(Connection c) throws SQLException {
		boolean local = false;
		if (c == null) {
			c = database.Connect.getConnection();
			local = true;
		}
		getVilles(c);
		getAvion(c);
		getSieges(c);
		if (local)
			c.close();
	}

	public Avion getAvion() {
		return avion;
	}

	public void setAvion(Avion avion) {
		this.avion = avion;
	}

	public Ville getVilleDepart() {
		return villeDepart;
	}

	public void setVilleDepart(Ville villeDepart) {
		this.villeDepart = villeDepart;
	}

	public Ville getVilleArrivee() {
		return villeArrivee;
	}

	public void setVilleArrivee(Ville villeArrivee) {
		this.villeArrivee = villeArrivee;
	}

	public List<SiegeVol> getSieges() {
		return sieges;
	}

	public void setSieges(List<SiegeVol> sieges) {
		this.sieges = sieges;
	}

	public void getSieges(Connection c) throws SQLException {
		boolean local = false;
		if (c == null) {
			c = database.Connect.getConnection();
			local = true;
		}
		sieges = SiegeVol.getByIdVol(c, idVol);
		if (local)
			c.close();
	}

	public boolean isEtat() {
		return etat;
	}

	public void setEtat(boolean etat) {
		this.etat = etat;
	}
}
