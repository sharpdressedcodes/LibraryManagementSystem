package lms.model;

import java.util.*;

public class LibraryCollection {
	
	private Map<Integer, Holding> holdings;
	private String code;
	private String name;
	
	public LibraryCollection(String code, String name){
		
		this.code = code;
		this.name = name;
		this.holdings = new LinkedHashMap<Integer, Holding>();
		
	}
	
	public boolean addHolding(Holding holding){
		
		boolean result = false;
				
		if (!this.holdings.containsValue(holding)){		
			this.holdings.put(holding.getCode(), holding);
			result = true;
		}
		
		return result;		
		
	}
	
	public Holding[] getAllHoldings(){						
		
		return this.holdings.size() == 0 ? null : this.holdings.values().toArray(new Holding[this.holdings.size()]);				
		
	}
	
	public Holding getHolding(int code){

		Holding result = null;
		
		if (this.holdings.containsKey(code))
			result = this.holdings.get(code);			
		
		return result;
		
	}
	
	public boolean removeHolding(Holding holding){
		
		boolean result = false;
		
		if (!holding.isOnLoan() && this.holdings.containsValue(holding))
			this.holdings.remove(holding.getCode());
		
		return result;
		
	}
	
	@Override
	public String toString(){
		
		StringBuilder keys = new StringBuilder();		
		int i = 0;		
		
		for (Map.Entry<Integer, Holding> entry : this.holdings.entrySet()){
			keys.append(entry.getKey());
			if (++i < this.holdings.size())
				keys.append(",");
		}
		
		if (this.holdings.size() == 0)
			return String.format("%s:%s", this.code, this.name);
		else
			return String.format("%s:%s:%s", this.code, this.name, keys.toString());
		
	}
	
	public int countBooks(){
		
		int result = 0;
		
		for (Map.Entry<Integer, Holding> entry : this.holdings.entrySet())
			if (entry.getValue() instanceof Book)
				result++;
		
		return result;
		
	}
	
	public int countVideos(){
		
		int result = 0;
		
		for (Map.Entry<Integer, Holding> entry : this.holdings.entrySet())
			if (entry.getValue() instanceof Video)
				result++;
		
		return result;
		
	}
	
	// custom methods
	public String getCode(){
		return this.code;
	}
	
	public String getName(){
		return this.name;
	}

}
