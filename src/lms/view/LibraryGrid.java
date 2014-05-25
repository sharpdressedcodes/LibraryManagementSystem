package lms.view;

import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JPanel;
import lms.controller.LibraryGridController;
import lms.model.grid.visitor.HoldingCellVisitor;
import lms.view.grid.GridCell;
import lms.view.grid.HoldingCell;

/**
 * @author Greg Kappatos
 * @date 25 May 2014
 * 
 */
@SuppressWarnings("serial")
public class LibraryGrid extends JPanel {

	//private MainView mainView;	
	private LibraryGridController controller;
		
	public LibraryGrid(MainView view) {
		
		controller = new LibraryGridController(view);
		//mainView = view;
		
		setBackground(Color.WHITE);
		setDoubleBuffered(true);
		
	}
	
	public void update(GridCell[] cells){	
		
		removeAll();
		setLayout(new GridLayout(0, LibraryGridController.MAX_CELLS_PER_COLUMN, 1, 1));							
		
		HoldingCellVisitor visitor = new HoldingCellVisitor();
		
		// Add cells. Also get all the HoldingCells.
		for (GridCell cell : cells){
			cell.accept(visitor);
			add(cell);
		}
		
		HoldingCell[] holdingCells = visitor.getHoldingCells();		
		
		// Add mouseListener to HoldingCells.
		for (HoldingCell cell : holdingCells)
			cell.getLabel().addMouseListener(controller);
		
		// This is here because it wasn't removing all the cells
		// on the last row, when an item has been removed.
		repaint();
		
	}
	
	public void refresh(GridCell[] cells){
		
		removeAll();		
		
		// Just add the cells without adding listener.
		for (GridCell cell : cells)
			add(cell);
		
	}
	
	public void clear(){
		
		removeAll();
		
		// Had to use repaint because just validate alone was leaving
		// unpainted parts behind.
		repaint();
		
	}

}
