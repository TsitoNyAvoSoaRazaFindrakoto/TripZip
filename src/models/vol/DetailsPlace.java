package models.vol;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DetailsPlace extends Vol {

	private int idSiegeVol;
	private int idVol;
	private int idSiege;
	private int places;
	private int disponible;
	private double prix;
	private int siegesPromo;
	private double prixPromo;

	public DetailsPlace(int idSiegeVol, int idVol, int idSiege, int places, int disponible, double prix, int siegesPromo,
			double prixPromo) {
		this.idSiegeVol = idSiegeVol;
		this.idVol = idVol;
		this.idSiege = idSiege;
		this.places = places;
		this.disponible = disponible;
		this.prix = prix;
		this.siegesPromo = siegesPromo;
		this.prixPromo = prixPromo;
	}

	public DetailsPlace(int idVol, int idAvion, LocalDateTime dateVol, LocalDateTime reservation,
			LocalDateTime annulation, int idVilleDepart, int idVilleArrivee, int idSiegeVol, int idVol2,
			int idSiege, int places, int disponible, double prix, int siegesPromo, double prixPromo) {
		super(idVol, idAvion, dateVol, reservation, annulation, idVilleDepart, idVilleArrivee);
		this.idSiegeVol = idSiegeVol;
		idVol = idVol2;
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

	public int getIdVol() {
		return idVol;
	}

	public void setIdVol(int idVol) {
		this.idVol = idVol;
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

	public static List<DetailsPlace> getAll(int page, int size) {
		List<DetailsPlace> list = new ArrayList<>();
		String sql = "SELECT * FROM details_place dp " +
				"JOIN vol v ON dp.id_vol = v.Id_Vol " +
				"JOIN ville vd ON v.Id_Ville_Depart = vd.Id_Ville " + // Join with Ville for depart
				"LIMIT ? OFFSET ?";

		try (Connection conn = database.Connect.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setInt(1, size);
			pstmt.setInt(2, (page - 1) * size);

			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					list.add(createDetailsPlaceFromResultSet(rs));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace(); // Replace with proper exception handling
		}
		return list;
	}

	public static DetailsPlace getByIdVolAndIdSiege(int idVol, int idSiege) {
		String sql = "SELECT * FROM details_place where id_vol = ? and id_siege = ?";
		return getDetailsPlace(sql, idVol, idSiege);
	}

	public static DetailsPlace getByIdSiegeVol(int idSiegeVol) {
		String sql = "SELECT * FROM details_place dp WHERE dp.Id_Siege_Vol = ?";
		return getDetailsPlace(sql, idSiegeVol);
	}

	private static DetailsPlace createDetailsPlaceFromResultSet(ResultSet rs) throws SQLException {
		int idVol = rs.getInt("v.Id_Vol"); // Example: Assuming "v" is the alias for the Vol table
		int idAvion = rs.getInt("v.Id_Avion");
		LocalDateTime dateVol = rs.getObject("v.dateVol", LocalDateTime.class); // Use getObject and specify the type
		LocalDateTime reservation = rs.getObject("v.reservation", LocalDateTime.class);
		LocalDateTime annulation = rs.getObject("v.annulation", LocalDateTime.class);
		int idVilleDepart = rs.getInt("v.Id_Ville_Depart");
		int idVilleArrivee = rs.getInt("v.Id_Ville_Arrivee");
		int idSiegeVol = rs.getInt("sv.Id_Siege_Vol"); // Example alias "sv"
		int idSiege = rs.getInt("s.Id_Siege"); // Example alias "s"
		int places = rs.getInt("sv.places");
		int disponible = rs.getInt("sv.disponible");
		double prix = rs.getDouble("sv.prix");
		int siegesPromo = rs.getInt("sv.siegesPromo");
		double prixPromo = rs.getDouble("sv.prixPromo");

		return new DetailsPlace(idVol, idAvion, dateVol, reservation, annulation, idVilleDepart, idVilleArrivee, idSiegeVol,
				idVol, idSiege, places, disponible, prix, siegesPromo, prixPromo);
	}

	private static DetailsPlace getDetailsPlace(String sql, int... params) {
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
			e.printStackTrace(); // Replace with proper exception handling
		}
		return null;
	}
}