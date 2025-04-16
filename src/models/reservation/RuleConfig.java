package models.reservation;

import java.sql.Time;
import java.time.LocalTime;

public class RuleConfig {
	private String id;
	private LocalTime value;

	public RuleConfig() {
	}

	public String getId() {
		return id;
	}

	public void setId(String libelle) {
		this.id = libelle;
	}

	public LocalTime getValue() {
		return value;
	}

	public void setValue(LocalTime duree) {
		this.value = duree;
	}

	public RuleConfig getById(java.sql.Connection connection, String libelle) {
		boolean nullConn = connection == null;
		if (nullConn)
			connection = database.Connect.getConnection();
		try {
			java.sql.PreparedStatement statement = connection
					.prepareStatement("SELECT * FROM rule_config WHERE libelle = ?");
			statement.setString(1, libelle);
			java.sql.ResultSet result = statement.executeQuery();
			if (result.next()) {
				this.id = result.getString("id");
				this.value = LocalTime.parse(result.getString("value"));
			}
			statement.close();
			if (nullConn)
				connection.close();
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

	public static java.util.List<RuleConfig> getAll(java.sql.Connection connection) {
		java.util.List<RuleConfig> list = new java.util.ArrayList<>();
		boolean nullConn = connection == null;
		if (nullConn)
			connection = database.Connect.getConnection();
		try {
			java.sql.PreparedStatement statement = connection.prepareStatement("SELECT * FROM rule_config");
			java.sql.ResultSet result = statement.executeQuery();
			while (result.next()) {
				RuleConfig cr = new RuleConfig();
				cr.id = result.getString("id");
				cr.value = result.getTime("value").toLocalTime();
				list.add(cr);
			}
			statement.close();
			if (nullConn)
				connection.close();
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

	public void update(java.sql.Connection connection) throws Exception {
		boolean nullConn = connection == null;
		System.out.println(toString());
		if (nullConn)
			connection = database.Connect.getConnection();
		try {
			connection.setAutoCommit(!nullConn);
			java.sql.PreparedStatement statement = connection
					.prepareStatement("update rule_config set duree=? where libelle=?");
			statement.setTime(1, Time.valueOf(value));
			statement.setString(2, id);
			statement.executeUpdate();
			statement.close();
			if (nullConn) {
				connection.close();
			}
		} catch (Exception e) {
			if (nullConn) {
				connection.close();
			}
			throw e;
		}
	}

	@Override
	public String toString() {
		return "ConfigReservation [libelle=" + id + "datetime, duree=" + value.toString() + "] and in time "
				+ Time.valueOf(value);
	}
}
