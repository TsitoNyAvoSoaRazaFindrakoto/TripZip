package models.avion;

import java.time.LocalDate;

public class Avion {
	private int idAvion;
	private String modele;
	private LocalDate fabrication;

	public Avion() {
	}

	public int getIdAvion() {
		return idAvion;
	}

	public void setIdAvion(int idAvion) {
		this.idAvion = idAvion;
	}

	public String getModele() {
		return modele;
	}

	public void setModele(String modele) {
		this.modele = modele;
	}

	public LocalDate getFabrication() {
		return fabrication;
	}

	public void setFabrication(LocalDate fabrication) {
		this.fabrication = fabrication;
	}
}
