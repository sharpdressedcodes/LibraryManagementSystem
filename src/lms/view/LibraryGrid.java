package lms.view;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JPanel;

import lms.controller.LibraryGridController;
import lms.controller.ToolBarOptionsController;
import lms.model.facade.LMSFacade;
import lms.model.facade.LMSModel;
import lms.view.grid.cells.*;

@SuppressWarnings("serial")
public class LibraryGrid extends JPanel {

	private MainView mainView;	
	private LibraryGridController controller;
	
	public LibraryGrid(MainView view) {
		
		this.controller = new LibraryGridController(view);
		this.setBackground(Color.WHITE);
		this.mainView = view;
		
	}
	
	public void update(GridCell[] cells){	
		
		this.removeAll();
		this.setLayout(new GridLayout(0, LibraryGridController.MAX_CELLS_PER_COLUMN, 1, 1));					
		
		for (GridCell cell : cells)
			this.add(cell);
		
		// TODO: visitor pattern				
		for (GridCell cell : cells){
			if (cell instanceof HoldingCell){
				((HoldingCell) cell).getLabel().addMouseListener(this.controller);
			}
		}
		
		// This is here because it wasn't removing all the cells
		// on the last row, when an item has been removed.
		this.repaint();
		
	}
	
	public void refresh(GridCell[] cells){
		
		this.removeAll();		
		this.setLayout(new GridLayout(0, LibraryGridController.MAX_CELLS_PER_COLUMN, 1, 1));
		
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
