package lms.model;

import java.util.*;

import lms.model.exception.*;
import lms.model.util.DateUtil;

public abstract class AbstractMember implements Member {

	private String id;
	private String fullName;
	private int remainingCredit;
	private String type;
	private int maxCredit;
	
	protected BorrowingHistory history;
	protected Map<Integer, Holding> currentLoans;	
	
	public AbstractMember(String id, String fullName, int remainingCredit, String type){
		
		this.id = id;
		this.fullName = fullName;
		this.remainingCredit = this.maxCredit = remainingCredit;
		this.type = type;		
		this.history = new BorrowingHistory();
		this.currentLoans = new HashMap<Integer, Holding>();
		
	}
	
	// from Borrower interface
	@Override
	public void borrowHolding(Holding holding) throws InsufficientCreditException, MultipleBorrowingException {				
			
		int fee = holding.getDefaultLoanFee();
		
		if (this.currentLoans.containsKey(holding.getCode()) || this.history.getHistoryRecord(holding.getCode()) != null)
			throw new MultipleBorrowingException();
		
		else if (this.getRemainingCredit() < fee)
			throw new InsufficientCreditException();
						
		this.calculateRemainingCredit(fee);
		holding.setBorrowDate(DateUtil.getInstance().getDate());
				
		this.currentLoans.put(holding.getCode(), holding);
				
	}

	public abstract void returnHolding(Holding holding) throws OverdrawnCreditException;

	
	// from Member interface
	@Override
	public int calculateRemainingCredit(int creditToSubtract) {
		
		this.remainingCredit -= creditToSubtract;
		
		return this.remainingCredit;
		
	}

	@Override
	public String getType(){
		return this.type;
	}
	
	@Override
	public int getRemainingCredit(){
		return this.remainingCredit;
	}
	
	@Override
	public BorrowingHistory getBorrowingHistory() {

		return this.history;
		
	}

	@Override
	public Holding[] getCurrentHoldings() {
		return this.currentLoans.size() == 0 ? null : this.currentLoans.values().toArray(new Holding[this.currentLoans.size()]);
	}

	@Override
	public String getFullName() {	
		return this.fullName;
	}

	@Override
	public int getMaxCredit() {
		return this.maxCredit;
	}

	@Override
	public String getMemberId() {		
		return this.id;
	}

	@Override
	public void resetCredit() {
		this.remainingCredit = this.maxCredit;
	}
	
	@Override
	public String toString(){		
		return String.format("%s:%s:%s", this.id, this.fullName, this.remainingCredit);
	}

}
