package models.avion;

import models.Siege;
import java.sql.Connection;
import java.sql.SQLException;

import mg.itu.prom16.annotations.request.Exclude;

public class SiegesAvions {
	private int idSiege;
	private int idAvion;
	private int nombre;

	@Exclude
	private Siege siege;
	@Exclude
	private Avion avion;

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

	public Siege getSiege(Connection c) throws SQLException {
		boolean local = false;
		if (c == null) {
			c = database.Connect.getConnection();
			local = true;
		}
		if (this.siege == null) {
			this.siege = new models.Siege().getById(c, this.idSiege);
		}
		if (local)
			c.close();
		return this.siege;
	}

	public Avion getAvion(Connection c) throws SQLException {
		boolean local = false;
		if (c == null) {
			c = database.Connect.getConnection();
			local = true;
		}
		if (this.avion == null) {
			this.avion = new Avion().getById(c, this.idAvion);
		}
		if (local)
			c.close();
		return this.avion;
	}

	// Added getById and getAll methods using Siege.java as template

	public SiegesAvions getById(java.sql.Connection connection, int Id_Siege, int Id_Avion) {
		boolean nullConn = connection == null;
		if (nullConn)
			connection = database.Connect.getConnection();
		try {
			java.sql.PreparedStatement statement = connection
					.prepareStatement("SELECT * FROM Sieges_Avions WHERE Id_Siege = ? and Id_Avion = ?");
			statement.setInt(1, Id_Siege);
			statement.setInt(2, Id_Avion);
			java.sql.ResultSet result = statement.executeQuery();
			if (result.next()) {
				// assuming fields: idSiege, idAvion, nombre
				this.idSiege = result.getInt("Id_Siege");
				this.idAvion = result.getInt("Id_Avion");
				this.nombre = result.getInt("nombre");
			}
			statement.close();
			if (nullConn) {
				connection.close();
			}
		} catch (Exception e) {
			try {
				connection.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			e.printStackTrace();
		}
		return this;
	}

	public static java.util.List<SiegesAvions> getByIdAvion(java.sql.Connection connection, int Id_Avion) {
		java.util.List<SiegesAvions> list = new java.util.ArrayList<>();
		boolean nullConn = connection == null;
		if (nullConn)
			connection = database.Connect.getConnection();
		try {
			java.sql.PreparedStatement statement = connection
					.prepareStatement("SELECT * FROM Sieges_Avions WHERE Id_Avion = ?");
			statement.setInt(1, Id_Avion);
			java.sql.ResultSet result = statement.executeQuery();
			while (result.next()) {
				SiegesAvions sa = new SiegesAvions();
				sa.idSiege = result.getInt("Id_Siege");
				sa.idAvion = result.getInt("Id_Avion");
				sa.nombre = result.getInt("nombre");
				list.add(sa);
			}
			statement.close();
			if (nullConn) {
				connection.close();
			}
		} catch (Exception e) {
			try {
				connection.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			e.printStackTrace();
		}
		return list;
	}

	public static java.util.List<SiegesAvions> getAll(java.sql.Connection connection) {
		java.util.List<SiegesAvions> list = new java.util.ArrayList<>();
		boolean nullConn = connection == null;
		if (nullConn)
			connection = database.Connect.getConnection();
		try {
			java.sql.PreparedStatement statement = connection.prepareStatement("SELECT * FROM Sieges_Avions");
			java.sql.ResultSet result = statement.executeQuery();
			while (result.next()) {
				SiegesAvions sa = new SiegesAvions();
				sa.idSiege = result.getInt("Id_Siege");
				sa.idAvion = result.getInt("Id_Avion");
				sa.nombre = result.getInt("nombre");
				list.add(sa);
			}
			statement.close();
			if (nullConn) {
				connection.close();
			}
		} catch (Exception e) {
			try {
				connection.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			e.printStackTrace();
		}
		return list;
	}
}
