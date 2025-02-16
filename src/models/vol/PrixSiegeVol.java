package models.vol;

import java.math.BigDecimal;

public class PrixSiegeVol {
	private int idPrixSiegeVol;
	private BigDecimal montant;
	private BigDecimal prom;
	private int siegeProm;
	private int idSiege;
	private int idVol;

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
}
