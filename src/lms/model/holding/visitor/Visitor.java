package lms.model.holding.visitor;

import lms.model.Book;
import lms.model.Video;

/**
 * @author Greg Kappatos
 * @date 25 May 2014
 * 
 */
public interface Visitor {

	public void visit(Book book);
	public void visit(Video video);
	
}
