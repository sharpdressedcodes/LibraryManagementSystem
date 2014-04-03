package lms.model.facade;

import lms.model.HistoryRecord;
import lms.model.Holding;
import lms.model.Library;
import lms.model.LibraryCollection;
import lms.model.Member;
import lms.model.exception.*;
import lms.model.util.DateUtil;

/**
 * @author Mikhail Perepletchikov
 * @author Greg Kappatos (modified by)
 */

public class LMSFacade implements LMSModel {	

	private Library library;
	
	public LMSFacade(){
		
		this.library = new Library();
		
	}

	//this shows an example of using the provided DateUtil class to set the current date
	public void setDate(String currentDate) {
		DateUtil.getInstance().setDate(currentDate);
	}
	
	///////////////////////////////////////////////////////////////////
	// LMSModel implementation ////////////////////////////////////////
	///////////////////////////////////////////////////////////////////
	
	@Override
	public void addMember(Member member) {
		
		this.library.addMember(member);
		
	}

	@Override
	public void addCollection(LibraryCollection collection) {
		
		this.library.addCollection(collection);
		
	}

	@Override
	public Member getMember() {
		
		return this.library.getMember();
		
	}

	@Override
	public LibraryCollection getCollection() {
		
		return this.library.getCollection();
		
	}

	@Override
	public boolean addHolding(Holding holding) {
		
		return this.library.addHolding(holding);
		
	}

	@Override
	public boolean removeHolding(int holdingId) {
		
		return this.library.removeHolding(holdingId);
		
	}

	@Override
	public Holding getHolding(int holdingId) {
		
		return this.library.getHolding(holdingId);
		
	}

	@Override
	public Holding[] getAllHoldings() {
		
		return this.library.getAllHoldings();
		
	}

	@Override
	public int countBooks() {
		
		return this.library.countBooks();
		
	}

	@Override
	public int countVideos() {
		
		return this.library.countVideos();
		
	}

	@Override
	public void borrowHolding(int holdingId) throws InsufficientCreditException, MultipleBorrowingException {
		
		this.library.borrowHolding(holdingId);
		
	}

	@Override
	public void returnHolding(int holdingId) throws OverdrawnCreditException {
		
		this.library.returnHolding(holdingId);
		
	}

	@Override
	public HistoryRecord[] getBorrowingHistory() {
		
		return this.library.getBorrowingHistory();
		
	}

	@Override
	public HistoryRecord getHistoryRecord(int holdingId) {
		
		return this.library.getHistoryRecord(holdingId);
		
	}

	@Override
	public Holding[] getBorrowedHoldings() {
		
		return this.library.getBorrowedHoldings();
		
	}

	@Override
	public void resetMemberCredit() {
		
		this.library.resetCredit();
		
	}

	@Override
	public int calculateRemainingCredit() {
		
		return this.library.calculateRemainingCredit();
		
	}

	@Override
	public int calculateTotalLateFees() {
		
		return this.library.calculateTotalLateFees();
		
	}
	
}