package lms.controller;

import java.awt.event.*;

import javax.swing.JLabel;

import lms.view.MainView;
import lms.view.grid.cells.*;

public class LibraryGridController extends MouseAdapter {

	private Controller helper;
	
	public static final int MAX_CELLS_PER_COLUMN = 4;
	
	public LibraryGridController(MainView view) {
		
		helper = new Controller(view);
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
		if (e.getButton() == MouseEvent.BUTTON1)
			handleRemoveHoldingAction(e);		

	}
	
	public void handleRemoveHoldingAction(MouseEvent e){
		
		JLabel source = (JLabel)e.getSource();
		HoldingCell cell = (HoldingCell)source.getParent().getParent().getParent();
				
		helper.removeHolding(Integer.parseInt(cell.getHoldingId()));			
		
	}

}
