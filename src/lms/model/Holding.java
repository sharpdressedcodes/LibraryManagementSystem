package lms.model;

public interface Holding extends Cloneable {
	
	public int calculateLateFee();
	public String getBorrowDate();
	public int getCode();
	public int getDefaultLoanFee();
	public int getMaxLoanPeriod();
	public String getTitle();
	public boolean isOnLoan();
	public void setBorrowDate(String newDate);	
	public String getType(); //custom
	
	//cloneable
	public Object clone() throws CloneNotSupportedException;
}
