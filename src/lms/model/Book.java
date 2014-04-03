package lms.model;

import lms.model.util.DateUtil;

public class Book extends AbstractHolding {

	public static final String TYPE = "BOOK";
	public static final int LOAN_FEE = 10; 				// $
	public static final int MAX_LOAN_PERIOD = 28; // days
	public static final int LATE_DAILY_RATE = 2; 	// $
	
	public Book(int code, String title) {
		super(code, title, Book.LOAN_FEE, Book.MAX_LOAN_PERIOD, Book.TYPE);		
	}

	@Override
	public int calculateLateFee() {
		
		int lateFee = 0;
		int daysLate = DateUtil.getInstance().getElapsedDays(this.getBorrowDate()) - MAX_LOAN_PERIOD;
		
		if (daysLate > 0)
			lateFee = daysLate * Book.LATE_DAILY_RATE;
		
		return lateFee;
		
	}
	
	@Override
	public String toString(){
		return String.format("%s:%s:%s", super.toString(), this.getMaxLoanPeriod(), this.getType());
	}

}
