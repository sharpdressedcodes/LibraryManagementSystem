package lms.controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import lms.controller.util.ControllerUtil;
import lms.view.MainView;
import lms.view.grid.HoldingCell;

/**
 * @author Greg Kappatos
 * @date 25 May 2014
 * 
 */
public class LibraryGridController extends MouseAdapter {

	private ControllerUtil helper;
	
	// Only allow 4 cells to be displayed horizontally.
	public static final int MAX_CELLS_PER_COLUMN = 4;
	
	public LibraryGridController(MainView view) {
		
		helper = new ControllerUtil(view);
		
	}

	public void handleRemoveHoldingAction(MouseEvent e){
		
		JLabel source = (JLabel)e.getSource();
		HoldingCell cell = (HoldingCell)source.getParent().getParent().getParent();
			
		// Ask the helper to remove holding.
		// Confirmation is also performed in the helper.
		helper.removeHolding(cell.getModel().getCode());			
		
	}

	// MouseAdapter implementation.
	@Override
	public void mouseReleased(MouseEvent e) {
		
		// Did the user left click a cell?
		if (e.getButton() == MouseEvent.BUTTON1)
			handleRemoveHoldingAction(e);		

	}
	
	

}
