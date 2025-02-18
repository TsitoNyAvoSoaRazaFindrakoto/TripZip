package models.reservation;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import mg.itu.prom16.annotations.request.Exclude;
import models.exception.InsufficientSeatsException;
import models.exception.InvalidSeatQuantityException;
import models.exception.ReservationDeadlineException;
import models.exception.ReservationValidationException;
import models.vol.DetailsPlace;
import models.vol.SiegeVol;
import models.vol.Vol;

import java.sql.Connection;
import java.sql.SQLException;

public class Reservation {
	@Exclude
	private int idReservation;
	private int idSiegeVol;
	private String contact;
	private LocalDateTime dateReservation;
	private int idVol;
	private BigDecimal prix;
	private int nombre;

	@Exclude
	private SiegeVol siegeVol;

	public Reservation() {
	}

	public int getIdReservation() {
		return idReservation;
	}

	public void setIdReservation(int idReservation) {
		this.idReservation = idReservation;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public LocalDateTime getDateReservation() {
		return dateReservation;
	}

	public void setDateReservation(LocalDateTime dateReservation) {
		this.dateReservation = dateReservation;
	}

	public int getIdVol() {
		return idVol;
	}

	public void setIdVol(int idVol) {
		this.idVol = idVol;
	}

	public Reservation getById(java.sql.Connection connection, int id) {
		boolean nullConn = connection == null;
		if (nullConn)
			connection = database.Connect.getConnection();
		try {
			java.sql.PreparedStatement statement = connection
					.prepareStatement("SELECT * FROM Reservation WHERE Id_Reservation = ?");
			statement.setInt(1, id);
			java.sql.ResultSet result = statement.executeQuery();
			if (result.next()) {
				// assuming fields: idReservation, contact, dateReservation, prix, nombre,
				// Id_Siege_Vol
				this.idReservation = result.getInt("Id_Reservation");
				this.contact = result.getString("contact");
				this.dateReservation = result.getTimestamp("date_reservation").toLocalDateTime();
				this.prix = result.getBigDecimal("prix");
				this.nombre = result.getInt("nombre");
				this.idSiegeVol = result.getInt("Id_Siege_Vol");
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

	public static List<Reservation> getAll(java.sql.Connection connection) {
		List<Reservation> list = new ArrayList<>();
		boolean nullConn = connection == null;
		if (nullConn)
			connection = database.Connect.getConnection();
		try {
			java.sql.PreparedStatement statement = connection.prepareStatement("SELECT * FROM Reservation");
			java.sql.ResultSet result = statement.executeQuery();
			while (result.next()) {
				Reservation r = new Reservation();
				r.idReservation = result.getInt("Id_Reservation");
				r.contact = result.getString("contact");
				r.dateReservation = result.getTimestamp("date_reservation").toLocalDateTime();
				r.prix = result.getBigDecimal("prix");
				r.nombre = result.getInt("nombre");
				r.idSiegeVol = result.getInt("Id_Siege_Vol");
				list.add(r);
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

	public BigDecimal getPrix() {
		return prix;
	}

	public void setPrix(BigDecimal prix) {
		this.prix = prix;
	}

	public int getNombre() {
		return nombre;
	}

	public void setNombre(int nombre) {
		this.nombre = nombre;
	}

	public int getIdSiegeVol() {
		return idSiegeVol;
	}

	public void setIdSiegeVol(int idSiegeVol) {
		this.idSiegeVol = idSiegeVol;
	}

	public SiegeVol getSiegeVol(Connection c) throws SQLException {
		boolean local = false;
		if (c == null) {
			c = database.Connect.getConnection();
			local = true;
		}
		if (this.siegeVol == null) {
			this.siegeVol = new SiegeVol().getById(c, this.idSiegeVol);
		}
		if (local)
			c.close();
		return this.siegeVol;
	}

	public Reservation validateReservation() throws ReservationValidationException, SQLException {
		SiegeVol siegeVol = this.getSiegeVol(null);
		if (siegeVol == null) {
			throw new ReservationValidationException("Associated SiegeVol not found.");
		}

		Vol vol = siegeVol.getVol(null);
		if (vol == null) {
			throw new ReservationValidationException("Associated Vol not found.");
		}

		LocalDateTime reservationDeadline = vol.getReservation();
		if (reservationDeadline == null || this.dateReservation.isAfter(reservationDeadline)) {
			throw new ReservationDeadlineException("Reservation is past the deadline.");
		}

		if (this.nombre <= 0) {
			throw new InvalidSeatQuantityException("Number of seats requested must be positive.");
		}

		DetailsPlace details = DetailsPlace.getByIdVolAndIdSiege(vol.getIdVol(), siegeVol.getIdSiege());
		if (details == null) {
			throw new ReservationValidationException("Seat details not found.");
		}

		if (this.nombre > details.disponible()) {
			throw new InsufficientSeatsException("Not enough seats available.");
		}

		int discountedSeats = details.siegesPromo();
		int normalSeats = this.nombre - discountedSeats;
		if (discountedSeats > 0) {
			Reservation discountedReservation = new Reservation();
			discountedReservation.setIdSiegeVol(this.idSiegeVol);
			discountedReservation.setContact(this.contact);
			discountedReservation.setDateReservation(this.dateReservation);
			discountedReservation.setNombre(discountedSeats);
			discountedReservation.setPrix(BigDecimal.valueOf(details.prixPromo()));
			this.nombre = normalSeats;
			return discountedReservation;
		}
		return null;
	}

}
