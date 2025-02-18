package models.exception;

public class ReservationDeadlineException extends ReservationValidationException {
	public ReservationDeadlineException(String message) {
		super(message);
	}
}
