package lms.model;

/**
 * @author Greg Kappatos
 */

import lms.model.exception.*;

public class Library {
	
	private LibraryCollection libraryCollection;
	private Member member;
		
	public void addCollection(LibraryCollection collection){
		
		this.libraryCollection = collection;	
		
	}
	
	public boolean addHolding(Holding holding){			
		
		return this.libraryCollection.addHolding(holding);			
		
	}
	
	public void addMember(Member member){
		
		this.member = member;
		
	}
	
	public void borrowHolding(int code) throws InsufficientCreditException, MultipleBorrowingException {
		
		Holding holding = this.getHolding(code);		
		
		// Make sure the Holding exists before trying to borrow it.
		// Maybe throwing a HoldingNotFoundException would be better?
		if (holding != null)			
			this.member.borrowHolding(holding);		
		
	}
	
	public int calculateRemainingCredit(){				
		
		return this.member.getRemainingCredit();			
		
	}
	
	public int calculateTotalLateFees(){				
		
		return this.member.getBorrowingHistory().calculateTotalLateFees();
		
	}
	
	public Holding[] getAllHoldings(){
		
		return this.libraryCollection.getAllHoldings();
		
	}
	
	public Holding[] getBorrowedHoldings(){
		
		return this.member.getCurrentHoldings();
		
	}
	
	public HistoryRecord[] getBorrowingHistory(){			
		
		return this.member.getBorrowingHistory().getAllHistoryRecords();
		
	}
	
	public LibraryCollection getCollection(){
		
		return this.libraryCollection;
		
	}
	
	public HistoryRecord getHistoryRecord(int code){
		
		return this.member.getBorrowingHistory().getHistoryRecord(code);
		
	}
	
	public Holding getHolding(int code){
		
		return this.libraryCollection.getHolding(code);
		
	}
	
	public Member getMember(){
		
		return this.member;
		
	}
	
	public boolean removeHolding(int code){
		
		boolean result = false;		
		Holding holding = this.libraryCollection.getHolding(code);
		
		// Make sure the Holding exists before trying to remove it.
		// Maybe throwing a HoldingNotFoundException would be better?		
		if (holding != null)
			result = this.libraryCollection.removeHolding(holding);
						
		return result;
				
	}
	
	public void resetCredit(){
		
		this.member.resetCredit();
		
	}
	
	public void returnHolding(int code) throws OverdrawnCreditException{
		
		Holding holding = this.getHolding(code);		
		
		// Make sure the Holding exists before trying to borrow it.
		// Maybe throwing a HoldingNotFoundException would be better?
		if (holding != null)						
			this.member.returnHolding(holding);				
		
	}

	public int countBooks(){
		
		return this.libraryCollection.countBooks();
		
	}
	
	public int countVideos(){
		
		return this.libraryCollection.countVideos();
		
	}

}