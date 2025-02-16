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
}
