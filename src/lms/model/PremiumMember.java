package lms.model;

/**
 * @author Greg Kappatos
 */

import lms.model.exception.OverdrawnCreditException;

public class PremiumMember extends AbstractMember {

	public static final String TYPE = "PREMIUM";
	public static final int INITIAL_CREDIT = 45; // dollars
	
	public PremiumMember(String id, String fullName) {
		
		super(id, fullName, PremiumMember.INITIAL_CREDIT, PremiumMember.TYPE);
		
	}
	
	///////////////////////////////////////////////////////////////////
	// AbstractMember implementation //////////////////////////////////
	///////////////////////////////////////////////////////////////////
	
	@Override
	public void returnHolding(Holding holding) throws OverdrawnCreditException {
		
		int fee = holding.calculateLateFee();
	
		// Return the item, regardless of if the credit goes into negative.
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
