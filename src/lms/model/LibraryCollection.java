package lms.model;

/**
 * @author Greg Kappatos
 */

import java.util.*;

public class LibraryCollection {
	
	private Map<Integer, Holding> holdings;
	private String code;
	private String name;
	
	public LibraryCollection(String code, String name){
		
		// Using a LinkedHashMap to keep the keys in the same order.
		this.code = code;
		this.name = name;		
		this.holdings = new LinkedHashMap<Integer, Holding>();
		
	}
	
	public boolean addHolding(Holding holding){
		
		boolean result = false;
				
		// Only add the Holding if it isn't already there.
		if (!this.holdings.containsValue(holding)){		
			this.holdings.put(holding.getCode(), holding);
			result = true;
		}
		
		return result;		
		
	}
	
	public Holding[] getAllHoldings(){						
		
		// Only return an array if the list isn't empty.
		return this.holdings.size() == 0 ? null : this.holdings.values().toArray(new Holding[this.holdings.size()]);				
		
	}
	
	public Holding getHolding(int code){

		Holding result = null;
		
		// Search for key
		if (this.holdings.containsKey(code))
			result = this.holdings.get(code);			
		
		return result;
		
	}
	
	public boolean removeHolding(Holding holding){
		
		boolean result = false;
		
		// Make sure this Holding is NOT on loan before removing it.
		// Also make sure the item is actually in the list.
		if (!holding.isOnLoan() && this.holdings.containsValue(holding))
			this.holdings.remove(holding.getCode());
		
		return result;
		
	}	
	
	public int countBooks(){
		
		int result = 0;
		
		// Loop through the Holdings, and search for Books
		for (Map.Entry<Integer, Holding> entry : this.holdings.entrySet())
			// Increment counter ONLY if this Holding is a Book
			if (entry.getValue() instanceof Book)
				result++;
		
		return result;
		
	}
	
	public int countVideos(){
		
		int result = 0;
		
		// Loop through the Holdings, and search for Videos
		for (Map.Entry<Integer, Holding> entry : this.holdings.entrySet())
			// Increment counter ONLY if this Holding is a Video
			if (entry.getValue() instanceof Video)
				result++;
		
		return result;
		
	}
	
	///////////////////////////////////////////////////////////////////
	// Custom /////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////
	
	public String getCode(){
		
		return this.code;
		
	}
	
	public String getName(){
		
		return this.name;
		
	}
	
	///////////////////////////////////////////////////////////////////
	// Object /////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////
	
	@Override
	public String toString(){
		
		int i = 0;
		StringBuilder keys = new StringBuilder();		
		
		// Build a comma separated string of keys.
		for (Map.Entry<Integer, Holding> entry : this.holdings.entrySet()){
			keys.append(entry.getKey());
			// Only add the comma if this isn't the last item.
			if (++i < this.holdings.size())
				keys.append(",");
		}
		
		// Don't append the ':' and the keys if the list is empty.
		if (this.holdings.size() == 0)
			return String.format("%s:%s", this.code, this.name);
		else
			return String.format("%s:%s:%s", this.code, this.name, keys.toString());
		
	}

}
