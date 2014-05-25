package lms.view;

import java.awt.*;

import javax.swing.JPanel;

import lms.controller.LibraryGridController;
import lms.model.grid.visitor.HoldingCellVisitor;
import lms.view.grid.*;

@SuppressWarnings("serial")
public class LibraryGrid extends JPanel {

	//private MainView mainView;	
	private LibraryGridController controller;
		
	public LibraryGrid(MainView view) {
		
		this.controller = new LibraryGridController(view);
		//this.mainView = view;
		
		this.setBackground(Color.WHITE);
		this.setDoubleBuffered(true);
		
	}
	
	public void update(GridCell[] cells){	
		
		this.removeAll();
		this.setLayout(new GridLayout(0, LibraryGridController.MAX_CELLS_PER_COLUMN, 1, 1));							
		
		HoldingCellVisitor visitor = new HoldingCellVisitor();
		
		for (GridCell cell : cells){
			cell.accept(visitor);
			this.add(cell);
		}
		
		HoldingCell[] holdingCells = visitor.getHoldingCells();		
		
		for (HoldingCell cell : holdingCells)
			cell.getLabel().addMouseListener(controller);
		
		// This is here because it wasn't removing all the cells
		// on the last row, when an item has been removed.
		this.repaint();
		
	}
	
	public void refresh(GridCell[] cells){
		
		this.removeAll();		
		
		// Just add the cells without adding listener.
		for (GridCell cell : cells)
			this.add(cell);
		
	}
	
	public void clear(){
		
		this.removeAll();
		
		// Had to use repaint because just validate alone was leaving
		// unpainted parts behind.
		this.repaint();
		
	}

}
