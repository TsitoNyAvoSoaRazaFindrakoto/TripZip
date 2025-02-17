package models.vol;

import database.Connect;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 */
public record PlaceDetails(
		int idVol,
		int idSiege,
		int availableSeats,
		BigDecimal normalPrice,
		BigDecimal promotionPrice,
		int promotionSeats) {

	public static List<PlaceDetails> getAll() {
		List<PlaceDetails> list = new ArrayList<>();
		String sql = "SELECT id_vol, id_siege, available_seats, normal_price, promotion_price, promotion_seats FROM place_details";
		try (Connection con = Connect.getConnection();
				PreparedStatement pst = con.prepareStatement(sql);
				ResultSet rs = pst.executeQuery()) {
			while (rs.next()) {
				list.add(new PlaceDetails(
						rs.getInt("id_vol"),
						rs.getInt("id_siege"),
						rs.getInt("available_seats"),
						rs.getBigDecimal("normal_price"),
						rs.getBigDecimal("promotion_price"),
						rs.getInt("promotion_seats")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public static PlaceDetails getById(int idVol, int idSiege) {
		String sql = "SELECT id_vol, id_siege, available_seats, normal_price, promotion_price, promotion_seats " +
				"FROM place_details WHERE id_vol = ? AND id_siege = ?";
		try (Connection con = Connect.getConnection();
				PreparedStatement pst = con.prepareStatement(sql)) {
			pst.setInt(1, idVol);
			pst.setInt(2, idSiege);
			try (ResultSet rs = pst.executeQuery()) {
				if (rs.next()) {
					return new PlaceDetails(
							rs.getInt("id_vol"),
							rs.getInt("id_siege"),
							rs.getInt("available_seats"),
							rs.getBigDecimal("normal_price"),
							rs.getBigDecimal("promotion_price"),
							rs.getInt("promotion_seats"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static List<PlaceDetails> getByVolId(int idVol) {
		List<PlaceDetails> list = new ArrayList<>();
		String sql = "SELECT id_vol, id_siege, available_seats, normal_price, promotion_price, promotion_seats " +
				"FROM place_details WHERE id_vol = ?";
		try (Connection con = Connect.getConnection();
				PreparedStatement pst = con.prepareStatement(sql)) {
			pst.setInt(1, idVol);
			try (ResultSet rs = pst.executeQuery()) {
				while (rs.next()) {
					list.add(new PlaceDetails(
							rs.getInt("id_vol"),
							rs.getInt("id_siege"),
							rs.getInt("available_seats"),
							rs.getBigDecimal("normal_price"),
							rs.getBigDecimal("promotion_price"),
							rs.getInt("promotion_seats")));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
}
