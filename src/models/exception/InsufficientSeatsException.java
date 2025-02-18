package models.exception;

public class InsufficientSeatsException extends ReservationValidationException {
	public InsufficientSeatsException(String message) {
		super(message);
	}
}
