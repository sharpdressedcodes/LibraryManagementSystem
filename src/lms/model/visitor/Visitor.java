package lms.model.visitor;

import lms.model.Book;
import lms.model.Video;

public interface Visitor {

	public void visit(Book book);
	public void visit(Video video);
	
}
