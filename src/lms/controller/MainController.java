package lms.controller;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import lms.controller.util.ControllerUtil;
import lms.model.grid.listener.GridListener;
import lms.view.MainView;
import lms.view.grid.GridCell;

/**
 * @author Greg Kappatos
 * @date 25 May 2014
 * 
 */
public class MainController extends WindowAdapter {

	//private LMSModel model;
	//private MainView mainView;
	private ControllerUtil helper;
	private List<GridListener> gridListeners;
	private GridCell[] cells;
	private SortActions sortOrder;
	private int totalCells;
	
	// Actions used by the toolBar buttons and the menuBar items.
	public static enum CommandActions {
		exit,
		init,
		reset,
		addBook,
		removeBook,
		addVideo,
		removeVideo,
		about,
		removeHolding
	}
	
	// Actions used by radioButtons.
	public static enum SortActions {
		none,
		code,
		type
	}
		
	public MainController(MainView view) {
		
		//this.mainView = view;
		//this.model = this.mainView.getModel();
		helper = new ControllerUtil(view);
		gridListeners = new ArrayList<GridListener>();
		
		totalCells = 0;
		cells = null;
		sortOrder = SortActions.none;
		
	}
	
	public SortActions getSortOrder(){
		
		return sortOrder;
		
	}
	
	public void setSortOrder(SortActions newValue){
		
		sortOrder = newValue;
		
	}
	
	public GridCell[] getCells(){
		
		return cells;
		
	}
	
	public void setCells(GridCell[] cells, int emptyCellCount){
		
		this.cells = cells;	
		totalCells = emptyCellCount;
		
		// Only add the length if the cells aren't null.
		if (cells != null)
			totalCells += cells.length;
		
	}
	
	public int getTotalCells(){
		
		return totalCells;
		
	}
	
	public void addGridListener(GridListener listener){
		
		gridListeners.add(listener);
		
	}
	
	public void removeGridListener(GridListener listener){
		
		// Only remove the listener if it exists.
		if (gridListeners.contains(listener))
			gridListeners.remove(listener);		
		
	}
	
	public void clearGridListeners(){
		
		gridListeners.clear();
		
	}
	
	public List<GridListener> getGridListeners(){
	
		return gridListeners;
		
	}
	
	public void notifyGridListeners(GridListener.GridState state){
		
		// Tell all interested parties that the grid state has changed.
		for (GridListener listener : gridListeners)
			listener.gridChanged(state);
		
	}	
	
	public void handleExitAction(WindowEvent e){
		
		helper.handleExitAction();
		
	}

	// WindowAdapter implementation.
	@Override
	public void windowClosing(WindowEvent e){
		
		// Pass the buck to the helper.
		handleExitAction(e);
		
	}
	
}
