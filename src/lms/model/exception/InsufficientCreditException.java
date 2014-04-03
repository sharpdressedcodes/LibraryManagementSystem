package lms.model.exception;

@SuppressWarnings("serial")
public class InsufficientCreditException extends LMSException {

	public InsufficientCreditException() {
		super("Insufficient Credit Exception");
	}

	public InsufficientCreditException(String message) {
		super(message);
	}

}
