package lms.view.grid;

import java.awt.Color;
import javax.swing.BorderFactory;
import lms.model.Book;

/**
 * @author Greg Kappatos
 * @date 25 May 2014
 * 
 */
@SuppressWarnings("serial")
public class BookCell extends HoldingCell {	
	
	public BookCell(Book model){
						
		super(model);
		
		// Set default border.
		setBorder(BorderFactory.createLineBorder(Color.BLUE, DEFAULT_BORDER_SIZE));
		
	}
	
}
