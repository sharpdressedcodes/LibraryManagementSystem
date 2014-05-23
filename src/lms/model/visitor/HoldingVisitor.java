package lms.model.visitor;

import java.util.LinkedHashMap;

import lms.model.Book;
import lms.model.Holding;
import lms.model.Video;

public class HoldingVisitor implements Visitor {

	private int bookCount;
	private int videoCount;
	private LinkedHashMap<String, Holding> holdings;
	
	public HoldingVisitor() {
		
		this.bookCount = 0;
		this.videoCount = 0;
		
	}

	public Holding[] getHoldings(){
		
		return this.holdings.entrySet().toArray(new Holding[this.holdings.size()]);
		
	}
	
	public int getBookCount(){
		
		return this.bookCount;
		
	}
	public int getVideoCount(){
		
		return this.videoCount;
		
	}
	@Override
	public void visit(Book book) {
		
		this.bookCount++;
		//this.holdings.put(book.getClass().getSimpleName(), book);

	}
	@Override
	public void visit(Video video) {
		
		this.videoCount++;
		//this.holdings.put(video.getClass().getSimpleName(), video);

	}

}
