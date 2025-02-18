package models.vol;

public record DetailsPlace(int idVol, int idSiege, int places, int disponible, double prix, int siegesPromo,
		double prixPromo) {

	public static java.util.List<DetailsPlace> getAll(int page, int size) {
		java.util.List<DetailsPlace> list = new java.util.ArrayList<>();
		String sql = "SELECT * FROM details_place LIMIT ? OFFSET ?";
		try (java.sql.Connection conn = database.Connect.getConnection();
				java.sql.PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, size);
			pstmt.setInt(2, (page - 1) * size);
			java.sql.ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				DetailsPlace dp = new DetailsPlace(
						rs.getInt("id_vol"),
						rs.getInt("id_siege"),
						rs.getInt("places"),
						rs.getInt("disponible"),
						rs.getDouble("prix"),
						rs.getInt("sieges_promo"),
						rs.getDouble("prix_promo"));
				list.add(dp);
			}
		} catch (java.sql.SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public static DetailsPlace getByIdVolAndIdSiege(int idVol, int idSiege) {
		String sql = "SELECT * FROM details_place WHERE id_vol = ? AND id_siege = ?";
		try (java.sql.Connection conn = database.Connect.getConnection();
				java.sql.PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, idVol);
			pstmt.setInt(2, idSiege);
			java.sql.ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				return new DetailsPlace(
						rs.getInt("id_vol"),
						rs.getInt("id_siege"),
						rs.getInt("places"),
						rs.getInt("disponible"),
						rs.getDouble("prix"),
						rs.getInt("sieges_promo"),
						rs.getDouble("prix_promo"));
			}
		} catch (java.sql.SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}