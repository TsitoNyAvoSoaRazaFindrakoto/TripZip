package models.avion;

import models.Siege;

public class SiegesAvions {
	private int idSiege;
	private int idAvion;
	private int nombre;

	private Siege siege;

	public SiegesAvions() {
	}

	public int getIdSiege() {
		return idSiege;
	}

	public void setIdSiege(int idSiege) {
		this.idSiege = idSiege;
	}

	public int getIdAvion() {
		return idAvion;
	}

	public void setIdAvion(int idAvion) {
		this.idAvion = idAvion;
	}

	public int getNombre() {
		return nombre;
	}

	public void setNombre(int nombre) {
		this.nombre = nombre;
	}

	public Siege getSiege() {
		return siege;
	}

	public void setSiege(Siege siege) {
		this.siege = siege;
	}

	public Siege getSiege(java.sql.Connection c) {
		setSiege(siege.getById(c, idSiege));
		return siege;
	}
}
