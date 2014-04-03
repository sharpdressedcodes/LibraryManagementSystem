package lms.model;

public interface Member extends Borrower {

	public int getRemainingCredit(); // custom
	public String getType(); // custom
	public int calculateRemainingCredit(int creditToSubtract);
	public BorrowingHistory getBorrowingHistory();
	public Holding[] getCurrentHoldings();
	public String getFullName();
	public int getMaxCredit();
	public String getMemberId();
	public void resetCredit();
	
	
}
