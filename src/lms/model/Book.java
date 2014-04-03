package lms.model;

/**
 * @author Greg Kappatos
 */

import lms.model.util.DateUtil;

public class Book extends AbstractHolding {

	public static final String TYPE = "BOOK";
	public static final int LOAN_FEE = 10; 				// dollars
	public static final int MAX_LOAN_PERIOD = 28; // days
	public static final int LATE_DAILY_RATE = 2; 	// dollars
	
	public Book(int code, String title) {
		
		super(code, title, Book.LOAN_FEE, Book.MAX_LOAN_PERIOD, Book.TYPE);		
		
	}

	///////////////////////////////////////////////////////////////////
	// AbstractHolding implementation /////////////////////////////////
	///////////////////////////////////////////////////////////////////
	
	@Override
	public int calculateLateFee() {
		
		int lateFee = 0;
		int daysLate = DateUtil.getInstance().getElapsedDays(this.getBorrowDate()) - MAX_LOAN_PERIOD;
		
		// Don't bother calculating the lateFee is the item isn't late.
		if (daysLate > 0)
			lateFee = daysLate * Book.LATE_DAILY_RATE;
		
		return lateFee;
		
	}
	
	///////////////////////////////////////////////////////////////////
	// Object /////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////
	
	@Override
	public String toString(){
		
		return String.format("%s:%s:%s", super.toString(), this.getMaxLoanPeriod(), this.getType());
		
	}

}
