package models.reservation;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import database.Connect;
import mg.itu.prom16.annotations.request.Exclude;
import models.exception.InsufficientSeatsException;
import models.exception.InvalidSeatQuantityException;
import models.exception.ReservationDeadlineException;
import models.exception.ReservationValidationException;
import models.vol.DetailsPlace;
import models.vol.SiegeVol;
import models.vol.Vol;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class Reservation {
	@Exclude
	private int idReservation;
	private int idSiegeVol;
	private int idUtilisateur;
	private LocalDateTime dateReservation;
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

	public LocalDateTime getDateReservation() {
		return dateReservation;
	}

	public void setDateReservation(LocalDateTime dateReservation) {
		this.dateReservation = dateReservation;
	}

	public Reservation getById(Connection connection, int id) throws SQLException {
		boolean nullConn = connection == null;
		if (nullConn) {
			connection = database.Connect.getConnection();
		}

		try (PreparedStatement statement = connection
				.prepareStatement("SELECT * FROM Reservation WHERE Id_Reservation = ?")) {
			statement.setInt(1, id);
			try (ResultSet result = statement.executeQuery()) {
				if (result.next()) {
					this.idReservation = result.getInt("Id_Reservation");
					this.idUtilisateur = result.getInt("id_utilisateur"); // Get idUtilisateur from the database
					this.dateReservation = result.getTimestamp("date_reservation").toLocalDateTime();
					this.prix = result.getBigDecimal("prix");
					this.nombre = result.getInt("nombre");
					this.idSiegeVol = result.getInt("Id_Siege_Vol");
					return this;
				}
			}
		} finally {
			if (nullConn) {
				connection.close();
			}
		}
		return null;
	}

	public static List<Reservation> getAll(Connection connection) throws SQLException {
		List<Reservation> list = new ArrayList<>();
		boolean nullConn = connection == null;
		if (nullConn) {
			connection = database.Connect.getConnection();
		}

		try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM Reservation")) {
			try (ResultSet result = statement.executeQuery()) {
				while (result.next()) {
					Reservation r = new Reservation();
					r.idReservation = result.getInt("Id_Reservation");
					r.idUtilisateur = result.getInt("id_utilisateur"); // Get idUtilisateur from the database
					r.dateReservation = result.getTimestamp("date_reservation").toLocalDateTime();
					r.prix = result.getBigDecimal("prix");
					r.nombre = result.getInt("nombre");
					r.idSiegeVol = result.getInt("Id_Siege_Vol");
					list.add(r);
				}
			}
		} finally {
			if (nullConn) {
				connection.close();
			}
		}
		return list;
	}

	// ... (Other methods: getPrix, setPrix, getNombre, setNombre, getIdSiegeVol,
	// setIdSiegeVol, getSiegeVol, validateReservation)

	public void save(Connection connection) throws SQLException {
		boolean nullConn = connection == null;
		if (nullConn) {
			connection = database.Connect.getConnection();
		}

		try (PreparedStatement statement = connection.prepareStatement(
				"INSERT INTO Reservation (Id_Siege_Vol, id_utilisateur, date_reservation, prix, nombre, Id_Vol) VALUES (?, ?, ?, ?, ?, ?)",
				PreparedStatement.RETURN_GENERATED_KEYS)) {
			statement.setInt(1, this.idSiegeVol);
			statement.setInt(2, this.idUtilisateur); // Set idUtilisateur
			statement.setTimestamp(3, Timestamp.valueOf(this.dateReservation));
			statement.setBigDecimal(4, this.prix);
			statement.setInt(5, this.nombre);

			statement.executeUpdate();

			try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					this.idReservation = generatedKeys.getInt(1);
				} else {
					throw new SQLException("Could not retrieve generated key.");
				}
			}

		} finally {
			if (nullConn) {
				connection.close();
			}
		}
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

	public void validateReservation() throws ReservationValidationException, SQLException {
		try (Connection c = Connect.getConnection()) {
			SiegeVol siegeVol = this.getSiegeVol(c);
			if (siegeVol == null) {
				throw new ReservationValidationException("Associated SiegeVol not found.");
			}

			Vol vol = siegeVol.getVol(c);
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

			if (this.nombre > details.getDisponible()) {
				throw new InsufficientSeatsException("Not enough seats available.");
			}

			int discountedSeats = details.getSiegesPromo();
			int normalSeats = this.nombre - discountedSeats;
			setPrix(
					BigDecimal.valueOf(
							Math.round(discountedSeats * details.getPrixPromo() + normalSeats * details.getPrix() / this.nombre)));
		} catch (Exception e) {
			throw e;
		}
	}

	public int getIdUtilisateur() {
		return idUtilisateur;
	}

	public void setIdUtilisateur(int idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
	}

	public SiegeVol getSiegeVol() {
		return siegeVol;
	}

	public void setSiegeVol(SiegeVol siegeVol) {
		this.siegeVol = siegeVol;
	}

}
