package lms.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.HeadlessException;
import javax.swing.JFrame;
import lms.controller.MainController;
import lms.controller.util.ControllerUtil;
import lms.model.facade.LMSModel;
import lms.model.grid.listener.GridListener.GridState;
import lms.view.grid.EmptyCell;
import lms.view.grid.GridCell;

/**
 * @author Greg Kappatos
 * @date 25 May 2014
 * 
 */
@SuppressWarnings("serial")
public class MainView extends JFrame {

	private LMSModel model;
	private MenuBar menuBar;
	private ToolBar toolBar; 			// sub-view (JPanel)
	private LibraryGrid libraryGrid; 	// sub-view (JPanel)
	private StatusBar statusBar; 		// sub-view (JPanel)
	private MainController controller;
	private ControllerUtil helper;		

	public MainView(LMSModel model) throws HeadlessException {
		
		super("Library Management System");
	
		this.model = model;
		controller = new MainController(this);
		helper = new ControllerUtil(this);									
		menuBar = new MenuBar(this);
		toolBar = new ToolBar(this);
		libraryGrid = new LibraryGrid(this);
		statusBar = new StatusBar(this);
		
		// Set default values.
		updateStatusBar(new String[] {"-", "0", "0"});
		
		// Set appearance.
		setLayout(new BorderLayout());
		setJMenuBar(menuBar);

		// Window listener.
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		addWindowListener(controller);
		
		// Add components.
		add(toolBar, BorderLayout.NORTH);
		add(libraryGrid, BorderLayout.CENTER);
		add(statusBar, BorderLayout.SOUTH);
				
		// Notify listeners.
		controller.notifyGridListeners(GridState.uninitialised);
		
		// Set size, give ourselves some room for the grid, then set
		// the minimum size to prevent the user making the window too small.
		pack();
		setSize(getWidth(), getHeight() + 500);
		setMinimumSize(new Dimension(
			getWidth(), 
			menuBar.getHeight() + toolBar.getHeight() + statusBar.getHeight() + 38
		));
		
		// Center frame.
		setLocationRelativeTo(null);
		
	}
	
	public LMSModel getModel(){
		
		return model;
		
	}
	
	public ToolBar getToolBar(){
		
		return toolBar;
		
	}
	
	public LibraryGrid getLibraryGrid(){
		
		return libraryGrid;
		
	}
	
	public StatusBar getStatusBar(){
		
		return statusBar;
		
	}
	
	public MainController getController(){
		
		return controller;		
		
	}
	
	public void refreshLibraryGrid(){
		
		// Only refresh if there are cells present.
		if (controller.getCells() != null){			
						
			// Sort the cells.
			GridCell[] sortedCells = helper.sortHoldingCells(controller.getCells());			
			GridCell[] cells = new GridCell[controller.getTotalCells()];
			
			// Set cells to current cells. Then append EmptyCells.
			for (int i = 0; i < controller.getTotalCells(); i++)
				cells[i] = i < sortedCells.length? sortedCells[i] : new EmptyCell();
			
			// Update.
			libraryGrid.refresh(cells);
			validate();
			
		}
		
	}
	
	public void clearLibraryGrid(){

		controller.setCells(null, 0);
		libraryGrid.clear();
		validate();
		
	}
	
	public void updateLibraryGrid(GridCell[] cells){
				
		libraryGrid.update(cells);
		validate();
		
	}
	
	public void updateStatusBar(String[] data){
		
		statusBar.setText(data);
		validate();
		
	}
		
}
