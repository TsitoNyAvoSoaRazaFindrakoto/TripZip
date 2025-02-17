package models.vol;

import java.math.BigDecimal;
import java.util.List;
import java.util.ArrayList;

import models.Siege;

public class SiegeVol {
	private int idSiegeVol;
	private BigDecimal montant;
	private BigDecimal prom;
	private int siegeProm;
	private int idSiege;
	private int idVol;

	private Siege siege;

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

	public Siege getSiege(java.sql.Connection c) {
		if (this.siege != null) {
			return this.siege;
		}
		if (c == null) {
			return null;
		}
		try {
			java.sql.PreparedStatement ps = c.prepareStatement("SELECT * FROM Siege WHERE Id_Siege = ?");
			ps.setInt(1, this.idSiege);
			java.sql.ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				Siege siege = new Siege(rs.getInt("Id_Siege"), rs.getString("nom"));
				this.setSiege(siege);
				return siege;
			}
		} catch (java.sql.SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Siege getSiege() {
		return siege;
	}

	public void setSiege(Siege siege) {
		this.siege = siege;
	}

	// Added getById and getAll methods using Siege.java as template for SiegeVol
	// Table: Siege_Vol with fields: Id_Siege_Vol, montant, prom, siege_prom, Id_Siege, Id_Vol

	public SiegeVol getById(java.sql.Connection connection, int id) {
		boolean nullConn = connection == null;
		if (nullConn)
			connection = database.Connect.getConnection();
		try {
			java.sql.PreparedStatement statement = connection.prepareStatement("SELECT * FROM Siege_Vol WHERE Id_Siege_Vol = ?");
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
			if (nullConn) connection.close();
		} catch (Exception e) {
			try { connection.close(); } catch (Exception e2) { e2.printStackTrace(); }
			e.printStackTrace();
		}
		return this;
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
			if (nullConn) connection.close();
		} catch (Exception e) {
			try { connection.close(); } catch (Exception e2) { e2.printStackTrace(); }
			e.printStackTrace();
		}
		return list;
	}
}
