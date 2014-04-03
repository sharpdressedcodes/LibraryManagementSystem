package lms.model;

import lms.model.exception.OverdrawnCreditException;

public class PremiumMember extends AbstractMember {

	public static final String TYPE = "PREMIUM";
	public static final int INITIAL_CREDIT = 45;
	
	public PremiumMember(String id, String fullName) {
		super(id, fullName, PremiumMember.INITIAL_CREDIT, PremiumMember.TYPE);
	}
	
	@Override
	public void returnHolding(Holding holding) throws OverdrawnCreditException {
		
		int fee = holding.calculateLateFee();
	
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
