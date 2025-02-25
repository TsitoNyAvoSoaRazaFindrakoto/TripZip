package models.vol;

import java.math.BigDecimal;
import java.util.List;

import mg.itu.prom16.annotations.request.Exclude;

import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import models.Siege;

public class SiegeVol {
	private int idSiegeVol;
	private BigDecimal montant = BigDecimal.ZERO;
	private BigDecimal prom = BigDecimal.ZERO;
	private int siegeProm;
	private int idSiege;
	private int idVol;

	@Exclude
	private Siege siege;
	@Exclude
	private Vol vol;

	public SiegeVol() {
	}

	public int getIdSiegeVol() {
		return idSiegeVol;
	}

	public void setIdSiegeVol(int idPrixSiegeVol) {
		this.idSiegeVol = idPrixSiegeVol;
	}

	public BigDecimal getMontant() {
		return montant;
	}

	public void setMontant(BigDecimal montant) {
		this.montant = montant;
	}

	public BigDecimal getProm() {
		return prom;
	}

	public void setProm(BigDecimal prom) {
		this.prom = prom;
	}

	public int getSiegeProm() {
		return siegeProm;
	}

	public void setSiegeProm(int siegeProm) {
		this.siegeProm = siegeProm;
	}

	public int getIdSiege() {
		return idSiege;
	}

	public void setIdSiege(int idSiege) {
		this.idSiege = idSiege;
	}

	public int getIdVol() {
		return idVol;
	}

	public void setIdVol(int idVol) {
		this.idVol = idVol;
	}

	public Siege getSiege(Connection c) throws Exception {
		boolean local = false;
		if (c == null) {
			c = database.Connect.getConnection();
			local = true;
		}
		if (this.siege == null) {
			this.siege = new models.Siege().getById(c, this.idSiege);
		}
		if (local)
			c.close();
		return this.siege;
	}

	public Siege getSiege() {
		return siege;
	}

	public void setSiege(Siege siege) {
		this.siege = siege;
	}

	public Vol getVol(Connection c) throws Exception {
		boolean local = false;
		if (c == null) {
			c = database.Connect.getConnection();
			local = true;
		}
		if (this.vol == null) {
			this.vol = new Vol().getById(c, this.idVol);
		}
		if (local)
			c.close();
		return this.vol;
	}

	public SiegeVol getById(java.sql.Connection connection, int id) {
		boolean nullConn = connection == null;
		if (nullConn)
			connection = database.Connect.getConnection();
		try {
			java.sql.PreparedStatement statement = connection
					.prepareStatement("SELECT * FROM Siege_Vol WHERE Id_Siege_Vol = ? order by id_siege asc");
			statement.setInt(1, id);
			java.sql.ResultSet result = statement.executeQuery();
			if (result.next()) {
				this.idSiegeVol = result.getInt("Id_Siege_Vol");
				this.montant = result.getBigDecimal("montant");
				this.prom = result.getBigDecimal("prom");
				this.siegeProm = result.getInt("siege_prom");
				this.idSiege = result.getInt("Id_Siege");
				this.idVol = result.getInt("Id_Vol");
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

	public static List<SiegeVol> getByIdVol(java.sql.Connection connection, int idVol) {
		List<SiegeVol> list = new ArrayList<>();
		boolean nullConn = connection == null;
		if (nullConn)
			connection = database.Connect.getConnection();
		try {
			java.sql.PreparedStatement statement = connection
					.prepareStatement("SELECT * FROM Siege_Vol WHERE id_vol = ? order by id_siege asc");
			statement.setInt(1, idVol);
			java.sql.ResultSet result = statement.executeQuery();
			while (result.next()) {
				SiegeVol sv = new SiegeVol();
				sv.idSiegeVol = result.getInt("Id_Siege_Vol");
				sv.montant = result.getBigDecimal("montant");
				sv.prom = result.getBigDecimal("prom");
				sv.siegeProm = result.getInt("siege_prom");
				sv.idSiege = result.getInt("Id_Siege");
				sv.idVol = result.getInt("Id_Vol");
				sv.getSiege(connection);
				list.add(sv);
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

	public static List<SiegeVol> getAll(java.sql.Connection connection) {
		List<SiegeVol> list = new ArrayList<>();
		boolean nullConn = connection == null;
		if (nullConn)
			connection = database.Connect.getConnection();
		try {
			java.sql.PreparedStatement statement = connection.prepareStatement("SELECT * FROM Siege_Vol");
			java.sql.ResultSet result = statement.executeQuery();
			while (result.next()) {
				SiegeVol sv = new SiegeVol();
				sv.idSiegeVol = result.getInt("Id_Siege_Vol");
				sv.montant = result.getBigDecimal("montant");
				sv.prom = result.getBigDecimal("prom");
				sv.siegeProm = result.getInt("siege_prom");
				sv.idSiege = result.getInt("Id_Siege");
				sv.idVol = result.getInt("Id_Vol");
				list.add(sv);
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

	public void saveOrUpdate(Connection connection) throws SQLException {
		boolean nullConn = connection == null;
		if (nullConn) {
			connection = database.Connect.getConnection();
		}

		String sql;
		if (idSiegeVol == 0) { // Nouvelle entree (id auto-genere)
			sql = "INSERT INTO Siege_Vol (montant, prom, siege_prom, Id_Siege, Id_Vol) VALUES (?, ?, ?, ?, ?)";
		} else { // Mise a jour
			sql = "UPDATE Siege_Vol SET montant = ?, prom = ?, siege_prom = ?, Id_Siege = ?, Id_Vol = ? WHERE Id_Siege_Vol = ?";
		}

		try (PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setBigDecimal(1, montant);
			statement.setBigDecimal(2, prom);
			statement.setInt(3, siegeProm);
			statement.setInt(4, idSiege);
			statement.setInt(5, idVol);

			if (idSiegeVol != 0) { // Pour la mise a jour
				statement.setInt(6, idSiegeVol);
			}

			statement.executeUpdate();

			if (idSiegeVol == 0) { // Recuperer l'id auto-genere apres l'insertion
				try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
					if (generatedKeys.next()) {
						idSiegeVol = generatedKeys.getInt(1);
					}
				}
			}

		} finally {
			if (nullConn) {
				connection.close();
			}
		}
	}

	public Vol getVol() {
		return vol;
	}

	public void setVol(Vol vol) {
		this.vol = vol;
	}
}
