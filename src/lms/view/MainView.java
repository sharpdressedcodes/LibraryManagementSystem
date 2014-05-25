package lms.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.HeadlessException;

import javax.swing.JFrame;

import lms.controller.Controller;
import lms.controller.MainController;
import lms.controller.ToolBarOptionsController;
import lms.model.facade.LMSModel;
import lms.model.grid.listener.GridListener.GridState;
import lms.view.grid.EmptyCell;
import lms.view.grid.GridCell;

@SuppressWarnings("serial")
public class MainView extends JFrame {

	private LMSModel model;
	private MenuBar menuBar;
	private ToolBar toolBar; // sub-view (JPanel)
	private LibraryGrid libraryGrid; // sub-view (JPanel)
	private StatusBar statusBar;
	private MainController controller;
	private Controller helper;
	private GridCell[] cells;
	private int sortOrder;
	private int totalCells;	
	
	private final static String STATUSBAR_FORMAT = "Collection Code: [%s] | Total Books: [%s] | Total Videos: [%s]";

	public MainView(LMSModel model) throws HeadlessException {
		
		super("Library Management System");
	
		this.model = model;
		this.controller = new MainController(this);
		this.helper = new Controller(this);
		this.cells = null;
		this.sortOrder = ToolBarOptionsController.SortActions.none.ordinal();
						
		this.setLayout(new BorderLayout());
		
		this.menuBar = new MenuBar(this);
		this.toolBar = new ToolBar(this);
		this.libraryGrid = new LibraryGrid(this);
		this.statusBar = new StatusBar(this);
		
		updateStatusBar(new String[] {"-", "0", "0"});
		
		this.setJMenuBar(this.menuBar);

		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.addWindowListener(this.controller);
		
		this.add(this.toolBar, BorderLayout.NORTH);
		this.add(this.libraryGrid, BorderLayout.CENTER);
		this.add(this.statusBar, BorderLayout.SOUTH);
				
		this.controller.notifyGridListeners(GridState.uninitialised);
		
		this.pack();
		this.setSize(this.getWidth(), this.getHeight() + 500);
		this.setMinimumSize(new Dimension(
				this.getWidth(), 
				this.menuBar.getHeight() + this.toolBar.getHeight() + this.statusBar.getHeight() + 38
		));
		this.setLocationRelativeTo(null);
		
	}
	
	public LMSModel getModel(){
		
		return this.model;
		
	}
	
	public ToolBar getToolBar(){
		
		return this.toolBar;
		
	}
	
	public LibraryGrid getLibraryGrid(){
		
		return this.libraryGrid;
		
	}
	
	public StatusBar getStatusBar(){
		
		return this.statusBar;
		
	}
	
	public MainController getController(){
		
		return this.controller;		
		
	}
	
	public int getSortOrder(){
		
		return this.sortOrder;
		
	}
	
	public void setSortOrder(int newValue){
		
		this.sortOrder = newValue;
		
	}
	
	public GridCell[] getCells(){
		
		return this.cells;
		
	}
	
	public void setCells(GridCell[] cells, int emptyCellCount){
		
		this.cells = cells;
		this.totalCells = this.cells.length + emptyCellCount;
		
	}
	
	public void refreshLibraryGrid(){
		
		if (this.cells != null){			
						
			GridCell[] sortedCells = this.helper.sortHoldingCells(this.cells);			
			GridCell[] cells = new GridCell[this.totalCells];
			
			for (int i = 0; i < this.totalCells; i++)
				cells[i] = i < sortedCells.length? sortedCells[i] : new EmptyCell();
			
			this.libraryGrid.refresh(cells);
			this.validate();
			
		}
		
	}
	
	public void clearLibraryGrid(){

		this.cells = null;
		this.totalCells = 0;
		
		this.libraryGrid.clear();
		this.validate();
		
	}
	
	public void updateLibraryGrid(GridCell[] cells){
				
		this.libraryGrid.update(cells);
		this.validate();
		
	}
	
	public void updateStatusBar(String[] data){
		
		this.statusBar.setText(String.format(
				STATUSBAR_FORMAT, 
				data[0],
				data[1],
				data[2]
		));
		this.validate();
		
	}
		
}
