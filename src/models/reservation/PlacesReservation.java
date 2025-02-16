package models.reservation;

import java.math.BigDecimal;

public class PlacesReservation {
	private int idPlacesReservation;
	private int nombre;
	private BigDecimal prixUnitaire;
	private int idSiege;
	private int idReservation;

	public PlacesReservation() {
	}

	public int getIdPlacesReservation() {
		return idPlacesReservation;
	}

	public void setIdPlacesReservation(int idPlacesReservation) {
		this.idPlacesReservation = idPlacesReservation;
	}

	public int getNombre() {
		return nombre;
	}

	public void setNombre(int nombre) {
		this.nombre = nombre;
	}

	public BigDecimal getPrixUnitaire() {
		return prixUnitaire;
	}

	public void setPrixUnitaire(BigDecimal prixUnitaire) {
		this.prixUnitaire = prixUnitaire;
	}

	public int getIdSiege() {
		return idSiege;
	}

	public void setIdSiege(int idSiege) {
		this.idSiege = idSiege;
	}

	public int getIdReservation() {
		return idReservation;
	}

	public void setIdReservation(int idReservation) {
		this.idReservation = idReservation;
	}
}
