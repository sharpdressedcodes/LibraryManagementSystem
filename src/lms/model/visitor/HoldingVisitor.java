package lms.model.visitor;

import java.util.LinkedHashMap;

import lms.model.Book;
import lms.model.Video;

public class HoldingVisitor implements Visitor {

	private LinkedHashMap<Integer, Book> books;
	private LinkedHashMap<Integer, Video> videos;
	
	public HoldingVisitor() {
		
		books = new LinkedHashMap<Integer, Book>();
		videos = new LinkedHashMap<Integer, Video>();
		
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

	}
	@Override
	public void visit(Video video) {
		
		videos.put(videos.size(), video);

	}

}
