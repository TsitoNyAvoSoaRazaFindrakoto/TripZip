package models.vol;

import java.math.BigDecimal;

import models.Siege;

public class PrixSiegeVol {
	private int idPrixSiegeVol;
	private BigDecimal montant;
	private BigDecimal prom;
	private int siegeProm;
	private int idSiege;
	private int idVol;

	private Siege siege;

	public PrixSiegeVol() {
	}

	public int getIdPrixSiegeVol() {
		return idPrixSiegeVol;
	}

	public void setIdPrixSiegeVol(int idPrixSiegeVol) {
		this.idPrixSiegeVol = idPrixSiegeVol;
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
}
