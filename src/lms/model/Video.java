package lms.model;

import lms.model.util.DateUtil;

public class Video extends AbstractHolding {

	public static final String TYPE = "VIDEO";
	public static final int MAX_LOAN_PERIOD = 7; // days
	
	public Video(int code, String title, int standardLoanFee) {
		super(code, title, standardLoanFee, Video.MAX_LOAN_PERIOD, Video.TYPE); 
	}

	@Override
	public int calculateLateFee() {
		
		int lateFee = 0;		
		int daysLate = DateUtil.getInstance().getElapsedDays(this.getBorrowDate()) - MAX_LOAN_PERIOD;
		
		if (daysLate > 0)
			lateFee = daysLate * (this.getDefaultLoanFee() >> 1);
		
		return lateFee;
		
	}
	
	@Override
	public String toString(){
		return String.format("%s:%s:%s", super.toString(), this.getMaxLoanPeriod(), this.getType());
	}

}
