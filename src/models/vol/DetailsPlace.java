package models.vol;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DetailsPlace {

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

	// Getters (indispensables pour accéder aux attributs)
	public int getIdSiegeVol() {
		return idSiegeVol;
	}

	public int getIdVol() {
		return idVol;
	}

	public int getIdSiege() {
		return idSiege;
	}

	public int getPlaces() {
		return places;
	}

	public int getDisponible() {
		return disponible;
	}

	public double getPrix() {
		return prix;
	}

	public int getSiegesPromo() {
		return siegesPromo;
	}

	public double getPrixPromo() {
		return prixPromo;
	}

	// Setters (si vous avez besoin de modifier les attributs après la création de
	// l'objet)
	public void setIdSiegeVol(int idSiegeVol) {
		this.idSiegeVol = idSiegeVol;
	}

	public void setIdVol(int idVol) {
		this.idVol = idVol;
	}

	public void setIdSiege(int idSiege) {
		this.idSiege = idSiege;
	}

	public void setPlaces(int places) {
		this.places = places;
	}

	public void setDisponible(int disponible) {
		this.disponible = disponible;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}

	public void setSiegesPromo(int siegesPromo) {
		this.siegesPromo = siegesPromo;
	}

	public void setPrixPromo(double prixPromo) {
		this.prixPromo = prixPromo;
	}

	public static List<DetailsPlace> getAll(int page, int size) {
		List<DetailsPlace> list = new ArrayList<>();
		String sql = "SELECT * FROM details_place LIMIT ? OFFSET ?";
		try (Connection conn = database.Connect.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, size);
			pstmt.setInt(2, (page - 1) * size);
			try (ResultSet rs = pstmt.executeQuery()) { // Try-with-resources pour ResultSet
				while (rs.next()) {
					list.add(createDetailsPlaceFromResultSet(rs));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace(); // À remplacer par une gestion plus robuste des exceptions
		}
		return list;
	}

	public static DetailsPlace getByIdVolAndIdSiege(int idVol, int idSiege) {
		String sql = "SELECT * FROM details_place WHERE id_vol = ? AND id_siege = ?";
		return getDetailsPlace(sql, idVol, idSiege);
	}

	public static DetailsPlace getByIdSiegeVol(int idSiegeVol) {
		String sql = "SELECT * FROM details_place WHERE Id_Siege_Vol = ?";
		return getDetailsPlace(sql, idSiegeVol);
	}

	// Méthode utilitaire pour factoriser la création d'un DetailsPlace à partir
	// d'un ResultSet
	private static DetailsPlace createDetailsPlaceFromResultSet(ResultSet rs) throws SQLException {
		return new DetailsPlace(
				rs.getInt("Id_Siege_Vol"),
				rs.getInt("id_vol"),
				rs.getInt("id_siege"),
				rs.getInt("places"),
				rs.getInt("disponible"),
				rs.getDouble("prix"),
				rs.getInt("sieges_promo"),
				rs.getDouble("prix_promo"));
	}

	// Méthode utilitaire pour factoriser la logique de récupération d'un
	// DetailsPlace
	private static DetailsPlace getDetailsPlace(String sql, int... params) { // Varargs pour plus de flexibilité
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
			e.printStackTrace(); // À remplacer par une gestion plus robuste
		}
		return null;
	}
}