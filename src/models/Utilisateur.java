package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import database.Connect;

public class Utilisateur {
	private int idUtilisateur;
	private String email;
	private String role;
	private String mdp;

	public int getIdUtilisateur() {
		return idUtilisateur;
	}

	public void setIdUtilisateur(int id_utilisateur) {
		this.idUtilisateur = id_utilisateur;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getMdp() {
		return mdp;
	}

	public void setMdp(String mdp) {
		this.mdp = mdp;
	}

	public static Utilisateur login(String email, String mdp) {
		Utilisateur utilisateur = null;
		Connection connection = Connect.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			String sql = "SELECT * FROM utilisateur WHERE email = ? AND mdp = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, email);
			preparedStatement.setString(2, mdp);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				utilisateur = new Utilisateur();
				utilisateur.setIdUtilisateur(resultSet.getInt("id_utilisateur"));
				utilisateur.setEmail(resultSet.getString("email"));
				utilisateur.setRole(resultSet.getString("role"));
				utilisateur.setMdp(resultSet.getString("mdp"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (resultSet != null)
					resultSet.close();
				if (preparedStatement != null)
					preparedStatement.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return utilisateur;
	}
}
