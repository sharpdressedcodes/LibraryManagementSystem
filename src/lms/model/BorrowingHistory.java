package lms.model;

/**
 * @author Greg Kappatos
 */

import java.util.*;

public class BorrowingHistory {
	
	private Map<Integer, HistoryRecord> historyRecords;
	
	public BorrowingHistory() {
		
		historyRecords = new HashMap<Integer, HistoryRecord>();	
		
	}
	
	public boolean addHistoryRecord(HistoryRecord historyRecord){
		
		boolean result = false;
		
		// Only add the HistoryRecord if it isn't already there.
		if (!this.historyRecords.containsValue(historyRecord)){
			this.historyRecords.put(historyRecord.getHolding().getCode(), historyRecord);
			result = true;
		}
		
		return result;
				
	}
	
	public int calculateTotalLateFees(){
		
		int result = 0;	
		
		// Go through all records, calculating the actual late fee by
		// deducting the initial loan fee, then adding it to the result.
		for (Map.Entry<Integer, HistoryRecord> entry : this.historyRecords.entrySet())
			result += entry.getValue().getFeePayed() - entry.getValue().getHolding().getDefaultLoanFee();
		
		return result;
		
	}
	
	public HistoryRecord[] getAllHistoryRecords(){
		
		// Only return an array if the list isn't empty.
		return this.historyRecords.size() == 0 ? null : this.historyRecords.values().toArray(new HistoryRecord[this.historyRecords.size()]);
		
	}
	
	public HistoryRecord getHistoryRecord(int code){
	
		HistoryRecord result = null;
		
		// Search for key
		if (this.historyRecords.containsKey(code))
			result = this.historyRecords.get(code);
		
		return result;
		
	}

}
