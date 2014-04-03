package lms.model;

/**
 * @author Greg Kappatos
 */

import lms.model.exception.OverdrawnCreditException;

public class StandardMember extends AbstractMember {

	public static final String TYPE = "STANDARD";
	public static final int INITIAL_CREDIT = 30; // dollars
	
	public StandardMember(String id, String fullName) {
		
		super(id, fullName, StandardMember.INITIAL_CREDIT, StandardMember.TYPE);
		
	}
	
	///////////////////////////////////////////////////////////////////
	// AbstractMember implementation //////////////////////////////////
	///////////////////////////////////////////////////////////////////
	
	@Override
	public void returnHolding(Holding holding) throws OverdrawnCreditException {
		
		int fee = holding.calculateLateFee();
		int credit = this.getRemainingCredit();
	
		// Do they have enough credit?
		if (credit < fee)
			throw new OverdrawnCreditException();
		
		// At this point all is OK to return the item.		
		try {
			// Clone the holding because the date is about to be set to null.
			// This is to keep track of when it was borrowed.
			this.history.addHistoryRecord(new HistoryRecord((Holding)holding.clone(), holding.getDefaultLoanFee() + fee));
		} catch (CloneNotSupportedException ex){			
			System.err.println("Clone error: " + ex.getMessage());
		}
		
		// Set the date to null (this puts the item state in 'not on loan').
		holding.setBorrowDate(null);
		// Remove any late fees incurred.
		this.calculateRemainingCredit(fee);	
		this.currentLoans.remove(holding.getCode());		
		
	}

	///////////////////////////////////////////////////////////////////
	// Object /////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////
	
	@Override
	public String toString(){
		
		return String.format("%s:%s", super.toString(), this.getType());
		
	}
	
}
