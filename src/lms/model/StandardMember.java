package lms.model;

import lms.model.exception.OverdrawnCreditException;

public class StandardMember extends AbstractMember {

	public static final String TYPE = "STANDARD";
	public static final int INITIAL_CREDIT = 30;
	
	public StandardMember(String id, String fullName) {
		super(id, fullName, StandardMember.INITIAL_CREDIT, StandardMember.TYPE);
	}
	
	@Override
	public void returnHolding(Holding holding) throws OverdrawnCreditException {
		
		int fee = holding.calculateLateFee();
		int credit = this.getRemainingCredit();
	
		if (credit < fee)
			throw new OverdrawnCreditException();
		
		try {						
			this.history.addHistoryRecord(new HistoryRecord((Holding)holding.clone(), holding.getDefaultLoanFee() + fee));
		} catch (CloneNotSupportedException ex){			
			System.err.println("Clone error: " + ex.getMessage());
		}
		holding.setBorrowDate(null);
		this.calculateRemainingCredit(fee);		
		this.currentLoans.remove(holding.getCode());		
		
	}

	@Override
	public String toString(){				
		return String.format("%s:%s", super.toString(), this.getType());
	}
	
}
