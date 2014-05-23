package lms.view.grid.cells;

import java.awt.Color;
import javax.swing.BorderFactory;

@SuppressWarnings("serial")
public class BookCell extends HoldingCell {	
	
	public BookCell(String holdingInfo){
						
		super(holdingInfo);
		this.setBorder(BorderFactory.createLineBorder(Color.BLUE, 3));
		
	}
	
}
