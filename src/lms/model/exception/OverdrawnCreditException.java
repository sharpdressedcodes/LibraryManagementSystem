package lms.model.exception;

/**
 * @author Greg Kappatos
 */

@SuppressWarnings("serial")
public class OverdrawnCreditException extends LMSException {

	public OverdrawnCreditException() {
		
		super("Overdrawn Credit Exception");
		
	}

	public OverdrawnCreditException(String message) {
		
		super(message);
		
	}

}
