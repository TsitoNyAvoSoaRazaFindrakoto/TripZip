package models.vol;

import java.time.LocalDateTime;

public class Vol {
	private int idVol;
	private LocalDateTime dateVol;
	private LocalDateTime reservation; // Nullable
	private LocalDateTime annulation; // Nullable
	private int idVille;
	private int idVille1;

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

	public int getIdVille() {
		return idVille;
	}

	public void setIdVille(int idVille) {
		this.idVille = idVille;
	}

	public int getIdVille1() {
		return idVille1;
	}

	public void setIdVille1(int idVille1) {
		this.idVille1 = idVille1;
	}
}
