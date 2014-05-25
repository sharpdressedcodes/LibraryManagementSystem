package lms.view.grid;

import java.awt.Color;
import javax.swing.BorderFactory;
import lms.model.Book;

@SuppressWarnings("serial")
public class BookCell extends HoldingCell {	
	
	public BookCell(Book model){
						
		super(model);
		
		setBorder(BorderFactory.createLineBorder(Color.BLUE, DEFAULT_BORDER_SIZE));
		
	}
	
}
