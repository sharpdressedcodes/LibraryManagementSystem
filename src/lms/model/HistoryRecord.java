package lms.model;

/**
 * @author Greg Kappatos
 */

public class HistoryRecord {

	private Holding holding;
	private int feePayed;
	
	public HistoryRecord(Holding holding, int feePayed){
		
		this.holding = holding;
		this.feePayed = feePayed;
		
	}
	
	public int getFeePayed(){	
		
		return this.feePayed;
		
	}
	
	public Holding getHolding(){
		
		return this.holding;
		
	}
	
	///////////////////////////////////////////////////////////////////
	// Object /////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////
	
	@Override
	public String toString(){
		
		return String.format("%s:%s", this.holding.getCode(), this.feePayed);
		
	}

	
}
