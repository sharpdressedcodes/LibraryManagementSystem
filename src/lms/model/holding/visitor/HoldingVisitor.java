package lms.model.holding.visitor;

import java.util.*;

import lms.model.Book;
import lms.model.Video;
import lms.view.grid.*;

public class HoldingVisitor implements Visitor {

	private Map<Integer, Book> books;
	private Map<Integer, Video> videos;
	private Map<Integer, GridCell> cells;
	
	public HoldingVisitor() {
		
		books = new LinkedHashMap<Integer, Book>();
		videos = new LinkedHashMap<Integer, Video>();
		cells = new LinkedHashMap<Integer, GridCell>();
		
	}

	public GridCell[] getCells(){
		
		return cells.values().toArray(new GridCell[cells.size()]);
		
	}
	
	public Book[] getBooks(){
		
		return books.values().toArray(new Book[books.size()]);
		
	}	
	public Video[] getVideos(){
		
		return videos.values().toArray(new Video[videos.size()]);
		
	}	
	public int getBookCount(){
		
		return books.size();
		
	}
	public int getVideoCount(){
		
		return videos.size();
		
	}
	@Override
	public void visit(Book book) {
				
		books.put(books.size(), book);
		cells.put(cells.size(), new BookCell(book));

	}
	@Override
	public void visit(Video video) {
		
		videos.put(videos.size(), video);
		cells.put(cells.size(), new VideoCell(video));

	}

}
