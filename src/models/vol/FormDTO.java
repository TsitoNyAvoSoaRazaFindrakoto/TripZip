package models.vol;

import java.time.LocalDate;

public class FormDTO {
	LocalDate dateMin, dateMax;
	Integer villeArrivee, villeDepart, siege;
	
	public LocalDate getDateMin() {
		return dateMin;
	}
	public void setDateMin(LocalDate dateMin) {
		this.dateMin = dateMin;
	}
	public LocalDate getDateMax() {
		return dateMax;
	}
	public void setDateMax(LocalDate dateMax) {
		this.dateMax = dateMax;
	}
	public Integer getVilleArrivee() {
		return villeArrivee;
	}
	public void setVilleArrivee(Integer villeArrivee) {
		this.villeArrivee = villeArrivee;
	}
	public Integer getVilleDepart() {
		return villeDepart;
	}
	public void setVilleDepart(Integer villeDepart) {
		this.villeDepart = villeDepart;
	}
	public Integer getSiege() {
		return siege;
	}
	public void setSiege(Integer siege) {
		this.siege = siege;
	}
	
	
}
