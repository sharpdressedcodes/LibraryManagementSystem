package lms.model;

import java.util.*;

public class BorrowingHistory {
	
	private Map<Integer, HistoryRecord> historyRecords;
	
	public BorrowingHistory() {
		
		historyRecords = new HashMap<Integer, HistoryRecord>();	
		
	}
	
	public boolean addHistoryRecord(HistoryRecord historyRecord){
		
		boolean result = false;
		
		if (!this.historyRecords.containsValue(historyRecord)){
			this.historyRecords.put(historyRecord.getHolding().getCode(), historyRecord);
			result = true;
		}
		
		return result;
				
	}
	
	public int calculateTotalLateFees(){
		
		int result = 0;	
		
		for (Map.Entry<Integer, HistoryRecord> entry : this.historyRecords.entrySet())
			result += entry.getValue().getFeePayed() - entry.getValue().getHolding().getDefaultLoanFee();
		
		return result;
		
	}
	
	public HistoryRecord[] getAllHistoryRecords(){
		
		return this.historyRecords.size() == 0 ? null : this.historyRecords.values().toArray(new HistoryRecord[this.historyRecords.size()]);
		
	}
	
	public HistoryRecord getHistoryRecord(int code){
	
		HistoryRecord result = null;
		
		if (this.historyRecords.containsKey(code))
			result = this.historyRecords.get(code);
		
		return result;
		
	}

}
