package models.exception;

public class InvalidSeatQuantityException extends ReservationValidationException {
	public InvalidSeatQuantityException(String message) {
		super(message);
	}
}
