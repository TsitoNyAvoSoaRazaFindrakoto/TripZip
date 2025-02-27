package models.vol;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import database.Connect;
import models.Siege;

public class DetailsPlace extends Vol {

	private int idSiegeVol;
	private int idSiege;
	private int places;
	private int disponible;
	private double prix;
	private int siegesPromo;
	private double prixPromo;

	private Siege siege;

	public DetailsPlace(int idSiegeVol, int idVol, int idSiege, int places, int disponible, double prix, int siegesPromo,
			double prixPromo) {
		this.idSiegeVol = idSiegeVol;
		setIdVol(idVol);
		this.idSiege = idSiege;
		this.places = places;
		this.disponible = disponible;
		this.prix = prix;
		this.siegesPromo = siegesPromo;
		this.prixPromo = prixPromo;
	}

	public DetailsPlace(int idVol, int idAvion, LocalDateTime dateVol, LocalDateTime reservation,
			LocalDateTime annulation, int idVilleDepart, int idVilleArrivee, int idSiegeVol,
			int idSiege, int places, int disponible, double prix, int siegesPromo, double prixPromo) {
		super(idVol, idAvion, dateVol, reservation, annulation, idVilleDepart, idVilleArrivee);
		this.idSiegeVol = idSiegeVol;
		this.idSiege = idSiege;
		this.places = places;
		this.disponible = disponible;
		this.prix = prix;
		this.siegesPromo = siegesPromo;
		this.prixPromo = prixPromo;
	}

	public int getIdSiegeVol() {
		return idSiegeVol;
	}

	public void setIdSiegeVol(int idSiegeVol) {
		this.idSiegeVol = idSiegeVol;
	}

	public int getIdSiege() {
		return idSiege;
	}

	public void setIdSiege(int idSiege) {
		this.idSiege = idSiege;
	}

	public int getPlaces() {
		return places;
	}

	public void setPlaces(int places) {
		this.places = places;
	}

	public int getDisponible() {
		return disponible;
	}

	public void setDisponible(int disponible) {
		this.disponible = disponible;
	}

	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}

	public int getSiegesPromo() {
		return siegesPromo;
	}

	public void setSiegesPromo(int siegesPromo) {
		this.siegesPromo = siegesPromo;
	}

	public double getPrixPromo() {
		return prixPromo;
	}

	public void setPrixPromo(double prixPromo) {
		this.prixPromo = prixPromo;
	}

	public static List<DetailsPlace> getAll(Connection conn, Integer page, int size, boolean onlyDispo)
			throws Exception {
		boolean inside = false;
		if (conn == null) {
			conn = Connect.getConnection();
			inside = true;
		}
		List<DetailsPlace> list = new ArrayList<>();
		String sql = "SELECT * FROM " + (onlyDispo ? "vols_dispo" : "vols_details") + " LIMIT ? OFFSET ?";
		try (
				PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setInt(1, size);
			pstmt.setInt(2, page == null ? 0 : (page - 1) * size);

			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					list.add(createDetailsPlaceFromResultSet(rs));
				}
			}
		} catch (SQLException e) {
			conn.close();
			throw e;
		}
		if (inside)
			conn.close();
		return list;
	}

	public static List<DetailsPlace> getByIdVol(Connection conn, int idVol)
			throws Exception {
		boolean inside = false;
		if (conn == null) {
			conn = Connect.getConnection();
			inside = true;
		}
		List<DetailsPlace> list = new ArrayList<>();
		String sql = "SELECT * FROM details_place where id_vol = ?";
		try (
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, idVol);
			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					list.add(createDetailsPlaceFromResultSet(rs));
				}
			}
		} catch (SQLException e) {
			conn.close();
			throw e;
		}
		if (inside)
			conn.close();
		return list;
	}

	public static DetailsPlace getByIdVolAndIdSiege(int idVol, int idSiege, boolean onlyDispo) throws SQLException {
		String sql = "SELECT * FROM " + (onlyDispo ? "vols_dispo" : "vols_details") + " where id_vol = ?";
		return getDetailsPlace(sql, idVol);
	}

	public static DetailsPlace getByIdSiegeVol(int idSiegeVol, boolean onlyDispo) throws SQLException {
		String sql = "SELECT * FROM " + (onlyDispo ? "vols_dispo" : "vols_details") + " WHERE Id_Siege_Vol = ?";
		return getDetailsPlace(sql, idSiegeVol);
	}

	private static DetailsPlace createDetailsPlaceFromResultSet(ResultSet rs) throws SQLException {
		int idVol = rs.getInt("Id_Vol");
		int idAvion = rs.getInt("Id_Avion");
		LocalDateTime dateVol = rs.getObject("date_vol", LocalDateTime.class);
		LocalDateTime reservation = rs.getObject("reservation", LocalDateTime.class);
		LocalDateTime annulation = rs.getObject("annulation", LocalDateTime.class);
		int idVilleDepart = rs.getInt("Id_Ville_Depart");
		int idVilleArrivee = rs.getInt("Id_Ville_Arrivee");
		int idSiegeVol = rs.getInt("Id_Siege_Vol");
		int idSiege = rs.getInt("Id_Siege");
		int places = rs.getInt("places");
		int disponible = rs.getInt("disponible");
		double prix = rs.getDouble("prix");
		int siegesPromo = rs.getInt("sieges_Promo");
		double prixPromo = rs.getDouble("prix_Promo");

		return new DetailsPlace(idVol, idAvion, dateVol, reservation, annulation, idVilleDepart, idVilleArrivee, idSiegeVol,
				idSiege, places, disponible, prix, siegesPromo, prixPromo);
	}

	private static DetailsPlace getDetailsPlace(String sql, int... params) throws SQLException {
		try (Connection conn = database.Connect.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {

			for (int i = 0; i < params.length; i++) {
				pstmt.setInt(i + 1, params[i]);
			}

			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					return createDetailsPlaceFromResultSet(rs);
				}
			}
		} catch (SQLException e) {
			throw e;
		}
		return null;
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

	public Siege getSiege() {
		return siege;
	}

	public void setSiege(Siege siege) {
		this.siege = siege;
	}
}