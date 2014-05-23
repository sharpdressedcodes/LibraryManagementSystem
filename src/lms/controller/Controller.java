package lms.controller;

import java.util.*;

import javax.swing.JOptionPane;

import lms.model.Book;
import lms.model.Holding;
import lms.model.LibraryCollection;
import lms.model.Video;
import lms.model.facade.LMSModel;
import lms.model.grid.cells.visitor.HoldingCellVisitor;
import lms.view.LibraryGrid;
import lms.view.MainView;
import lms.view.grid.cells.*;
import lms.view.test.Tester;

public class Controller {

	private LMSModel model;
	private MainView mainView; 
	
	public static enum Actions {
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
	
	public Controller(MainView view) {
		
		this.mainView = view;
		this.model = this.mainView.getModel();		
				
	}
	public void addBook(){
		
		// TODO: get data from user through custom dialogs
		// validate data + check if already exists

		Book book = new Book(1000091, "Introduction to Java Programming");
		this.model.addHolding(book);
				
		updateDisplay();				
		
	}
	public void addVideo(){
		
		// TODO: get data from user through custom dialogs
		// validate data
		Video video = new Video(2020091, "Java 1", 4);
		this.model.addHolding(video);
		
		updateDisplay();
		
	}
	public void removeHolding(int holdingId){
		
		// TODO: confirmation
		
		this.model.removeHolding(holdingId);
		
		updateDisplay();
		
	}
	public void removeHoldings(int[] holdingIds){
		
		// TODO: confirmation
		
		for (int holdingId : holdingIds)
			this.model.removeHolding(holdingId);
		
		updateDisplay();
		
	}
	public boolean addLibraryCollection(){
		
		/** (1) display input dialog box
     *  (2) get data from the dialog box
     *  (3) validate the data (code and name)
     *  (4) if (3) valid, call
     *      model.addCollection (new LibraryCollection (code, name));
     *      return true;
     *  (5) if (3) invalid, show error message, clear data in the input dialog
     *      and repeat (2) and (3) until valid
     */ 
		
		//JOptionPane inputDialog = JOptionPane.
		
		this.model.addCollection(new LibraryCollection("some code", "some name"));
		return true;
		
	}
	public void resetLibraryCollection(){
		
		GridCell[] cells = this.mainView.getCells();
		
		if (cells != null && 
				cells.length > 0 && 
				JOptionPane.showConfirmDialog(
						this.mainView, 
						"Reset Library Collection", 
						"Confirmation", 
						JOptionPane.QUESTION_MESSAGE & JOptionPane.OK_CANCEL_OPTION
				) == JOptionPane.OK_OPTION){
										
				this.model.addCollection(new LibraryCollection(
						this.model.getCollection().getCode(), 
						this.model.getCollection().getName()
				));
			
				this.clearDisplay();			
				
		}		
		
	}
	public void populateHoldings(){
		
		Tester tester = new Tester();
    tester.setupTestData(model);
    
	}
	public GridCell[] getHoldingCells(){
		
		// get all holdings from the Model
    Holding[] temp = model.getAllHoldings();

    // initialise array of grid cells to cover retrieved holdings
    GridCell[] cells = new HoldingCell[temp.length];

    
    //HoldingCellVisitor visitor = new HoldingCellVisitor();
    //for (HoldingCell cell : temp)
    
    // extract data from holdings and create appropriate grid cells
    for (int i = 0; i < temp.length; i++) {
        // use the Visitor pattern instead +1 BONUS MARK
        if (temp[i].getClass().getSimpleName().equals("Book"))
            cells[i] = new BookCell(temp[i].toString());
        else
            cells[i] = new VideoCell(temp[i].toString());
    }
        
    return cells;
		
	}
	public GridCell[] sortHoldingCells(GridCell[] cells){				
		
		if (this.mainView.getSortOrder() == ToolBarOptionsController.SortActions.code.ordinal()){
			HoldingCell[] holdingCells = convertCells(cells);
			Arrays.sort(holdingCells, HoldingCell.CodeComparator);
			cells = holdingCells;
		} else if (this.mainView.getSortOrder() == ToolBarOptionsController.SortActions.type.ordinal()){
			HoldingCell[] holdingCells = convertCells(cells);
			Arrays.sort(holdingCells, HoldingCell.TypeComparator);
			cells = holdingCells;		
		}
		
		return cells;
		
	}
	private HoldingCell[] convertCells(GridCell[] cells){
		
		int i = 0;
		HoldingCell[] holdings = new HoldingCell[cells.length];
		
		for (GridCell cell : cells)
			holdings[i++] = (HoldingCell)cell;
		
		return holdings;
		
	}
	public int calculatePadCount(int holdingCellCount, int maxColumns){		
		
		int remaining = holdingCellCount % maxColumns;
		return holdingCellCount < maxColumns + 1  || remaining == 0 ? 0 : maxColumns - remaining;
		
	}
	public GridCell[] createSortAndCombineCells(GridCell[] holdingCells, int padCount){
		
		GridCell[] cells = null;
		GridCell[] sorted = sortHoldingCells(holdingCells);
		
		if (padCount > 0){
			
			cells = new GridCell[holdingCells.length + padCount];			
			
			for (int i = 0; i < cells.length; i++)
				cells[i] = i < holdingCells.length ? sorted[i] : new EmptyCell();					
		
		} else {
			
			cells = sorted;
			
		}
		
		return cells;		
		
	}
	public String[] getStatusData() {
    return new String []{
    		model.getCollection().getCode(),
        String.valueOf(model.countBooks()), 
        String.valueOf(model.countVideos())
     };
	}

	public void updateDisplay(){
		
		if (this.model.getCollection().countBooks() == 0 && this.model.getCollection().countVideos() == 0){
			
			this.clearDisplay();
			
		} else {
		
			GridCell[] holdingCells = getHoldingCells();			
			int padCount = calculatePadCount(holdingCells.length, LibraryGridController.MAX_CELLS_PER_COLUMN);			
			GridCell[] cells = createSortAndCombineCells(holdingCells, padCount);			
			
			this.mainView.setCells(holdingCells, padCount);
			this.mainView.updateLibraryGrid(cells);
			this.mainView.updateStatusBar(getStatusData());
		
		}
		
	}
	public void clearDisplay(){
		
		this.mainView.clearLibraryGrid();
		this.mainView.updateStatusBar(getStatusData());
		
	}
	public void handleExitAction(){
		
//	if (JOptionPane.showConfirmDialog(
//	this.mainView, 
//	"Are you sure you wish to exit the application?", 
//	"Confirmation", 
//	JOptionPane.QUESTION_MESSAGE & JOptionPane.YES_NO_OPTION
//	) == JOptionPane.YES_OPTION)
	System.exit(0);

	}
}
