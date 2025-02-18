package models.vol;

import java.math.BigDecimal;
import java.util.List;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import models.Siege;

public class SiegeVol {
	private int idSiegeVol;
	private BigDecimal montant;
	private BigDecimal prom;
	private int siegeProm;
	private int idSiege;
	private int idVol;

	private Siege siege;
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

	public Siege getSiege(Connection c) throws SQLException {
		boolean local = false;
		if(c == null) {
			c = database.Connect.getConnection();
			local = true;
		}
		if(this.siege == null) {
			this.siege = new models.Siege().getById(c, this.idSiege);
		}
		if(local) c.close();
		return this.siege;
	}

	public Siege getSiege() {
		return siege;
	}

	public void setSiege(Siege siege) {
		this.siege = siege;
	}

	public Vol getVol(Connection c) throws SQLException {
		boolean local = false;
		if(c == null) {
			c = database.Connect.getConnection();
			local = true;
		}
		if(this.vol == null) {
			this.vol = new Vol().getById(c, this.idVol);
		}
		if(local) c.close();
		return this.vol;
	}

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
