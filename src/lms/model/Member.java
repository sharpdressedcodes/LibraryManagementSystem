package lms.model;

/**
 * @author Greg Kappatos
 */

public interface Member extends Borrower {

	public int calculateRemainingCredit(int creditToSubtract);
	public BorrowingHistory getBorrowingHistory();
	public Holding[] getCurrentHoldings();
	public String getFullName();
	public int getMaxCredit();
	public String getMemberId();
	public void resetCredit();
	
	// Custom methods I decided to add myself.
	
	// return remaining credit without any modifications
	public int getRemainingCredit();
	public String getType();
	
}
