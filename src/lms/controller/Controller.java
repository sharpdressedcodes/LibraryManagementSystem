package lms.controller;

import java.util.Arrays;

import javax.swing.JOptionPane;

import lms.model.Book;
import lms.model.Holding;
import lms.model.LibraryCollection;
import lms.model.Video;
import lms.model.facade.LMSModel;
import lms.model.grid.listener.GridListener.GridState;
import lms.model.holding.visitor.HoldingVisitor;
import lms.view.MainView;
import lms.view.dialog.AbstractDialog;
import lms.view.dialog.AddHoldingDialog;
import lms.view.dialog.InitCollectionDialog;
import lms.view.dialog.RemoveHoldingsDialog;
import lms.view.grid.EmptyCell;
import lms.view.grid.GridCell;
import lms.view.grid.HoldingCell;
import lms.view.test.Tester;

/**
 * @author Greg Kappatos
 * @date 25 May 2014
 * 
 */
public class Controller {

	private LMSModel model;
	private MainView mainView; 
	
	// Actions that will be used by the toolBar buttons and the menuBar items.
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
		
		mainView = view;
		model = mainView.getModel();		
				
	}
	
	public void addBook(){
		
	    String code = "";
        String title = "";
		AddHoldingDialog dialog = new AddHoldingDialog(mainView, "Book");						
		
		// Keep looping until user either cancels or adds valid data.
		while (dialog.getResult().equals(AbstractDialog.Actions.OK)){
			
			code = dialog.getHoldingId();
			title = dialog.getHoldingTitle();			
						
			// Does this holding already exist? Abort if so.
			if (model.getHolding(Integer.parseInt(code)) != null)
				showErrorDialog(String.format(
			        "Cannot add Book.\nA holding with id %s already exists.", 
			        code
				));
			
			// All good, we can now add the Book.
			else
				break;
			
			dialog.reDisplay();
			
		}

		// Did the user enter valid data? Add the Book if so.
		if (!code.equals("") && !title.equals("")){								
			
			model.addHolding(new Book(Integer.parseInt(code), title));					
			updateDisplay();
			
		}
		
	}
	
	public void addVideo(){
		
	    String code = "";
        String title = "";        
		AddHoldingDialog dialog = new AddHoldingDialog(
			mainView, 
			"Video", 
			new String[] {"4", "6"}
		);		
		
		// Keep looping until user either cancels or adds valid data.
		while (dialog.getResult().equals(AbstractDialog.Actions.OK)){
			
			code = dialog.getHoldingId();
			title = dialog.getHoldingTitle();			
				
			// Does this holding already exist? Abort if so.
			if (model.getHolding(Integer.parseInt(code)) != null)
				showErrorDialog(String.format(
					"Cannot add Video.\nA holding with id %s already exists.",
					code
				));
			
			// All good, we can now add the Video.
			else
				break;
			
			dialog.reDisplay();
			
		}

		// Did the user enter valid data? Add the Video if so.
		if (!code.equals("") && !title.equals("")){								
			
			model.addHolding(new Video(
				Integer.parseInt(code), 
				title, 
				Integer.parseInt(dialog.getHoldingFee())
			));					
			updateDisplay();
			
		}
		
	}
	
	public void removeHolding(int holdingId){
		
		// Confirm that the user really wants to remove this item.
		if (showConfirmDialog(String.format(
				"Permanently remove this %s?\n%s", 
				toTitleCase(model.getHolding(holdingId).getType()), 
				holdingId
			))){
		
			// Remove holding and update display.
			model.removeHolding(holdingId);			
			updateDisplay();
			
		}
		
	}	
	
	public void removeBooks(){
		
		HoldingVisitor visitor = new HoldingVisitor();
		Holding[] holdings = model.getAllHoldings();
		
		// Gather books with the visitor.
		for (Holding holding : holdings)
	  		 holding.accept(visitor);
		
		// Remove gathered books.
		removeHoldings(visitor.getBooks());
		
	}
	
	public void removeVideos(){
		
		HoldingVisitor visitor = new HoldingVisitor();
		Holding[] holdings = model.getAllHoldings();
		
		// Gather videos with the visitor.
		for (Holding holding : holdings)
	  		 holding.accept(visitor);
		
		// Remove gathered videos.
		removeHoldings(visitor.getVideos());
		
	}
	
	public void removeHoldings(Holding[] holdings){
		
		// This is called internally by removeBooks() and removeVideos()		
		
		String[] holdingStrings = new String[holdings.length];
		String type = toTitleCase(holdings[0].getType());
		
		// Gather holding representations by collecting the
		// toString() and trimming off the BOOK or VIDEO part.
		for (int i = 0; i < holdings.length; i++)
			holdingStrings[i] = holdings[i].toString().substring(
				0, 
				holdings[i].toString().lastIndexOf(':')
			);
		
		// Now create the dialog, and pass the previously gathered
		// data to it, so the user can select.
		int[] holdingIds = null;
		RemoveHoldingsDialog dialog = new RemoveHoldingsDialog(
			mainView, 
			type, 
			holdingStrings
		);						
		
		// Keep looping until the user either presses Cancel or commits.
		while (dialog.getResult().equals(AbstractDialog.Actions.OK)){
			
			// Get selection from dialog.
			holdingIds = dialog.getSelectedHoldingIds();
			
			// If we have valid data, convert it to a
			// comma separated string.
			if (holdingIds != null && holdingIds.length > 0){
				StringBuffer sb = new StringBuffer();
				
				// Append the data with the comma
				for (int holdingId : holdingIds)
					sb.append(String.format("%s,", holdingId));
				
				// Trim last comma
				if (sb.length() > 0)
					sb.deleteCharAt(sb.length() - 1);
				
				// Show the confirmation with the selected id's.
				if (showConfirmDialog(String.format(
						"Permanently remove these %ss?\n(%s)", 
						type, 
						sb.toString()
					))){
					
					// Delete the selected holdings, and update.
					for (int holdingId : holdingIds)		
						model.removeHolding(holdingId);

					updateDisplay();
					break;
					
				}
			}
				
			dialog.reDisplay();						
			
		}
		
	}
	
	public void showErrorDialog(String message){
	
		// Helper method to keep the call short.
		JOptionPane.showMessageDialog(
			mainView, 
			message, 
			"Error", 
			JOptionPane.ERROR_MESSAGE
		);
		
	}
	
	public boolean showConfirmDialog(String message){
		
		// Helper method to keep the call short.
		return JOptionPane.showConfirmDialog(
				mainView, message, 
				"Confirmation", 
				JOptionPane.QUESTION_MESSAGE & JOptionPane.OK_CANCEL_OPTION
		) == JOptionPane.OK_OPTION;
		
	}
	
	public boolean addLibraryCollection(){
		
		// Create dialog, passing in old code and name (if any).
		InitCollectionDialog dialog = new InitCollectionDialog(
			mainView, 
			getCollectionCode(), 
			getCollectionName()
		);
		
		// What did the user select?
		if (dialog.getResult().equals(AbstractDialog.Actions.OK)){
			
			// Create the collection.
			model.addCollection(new LibraryCollection(
				dialog.getCollectionCode(), 
				dialog.getCollectionName()
			));
			
			// Success.
			return true;
			
		}
			
		// Failed.
		return false;
		
	}
	
	public void resetLibraryCollection(){
		
		GridCell[] cells = mainView.getCells();
		
		// Only reset if we can actually reset.
		// If we can, confirm this with the user first.
		if (cells != null && 
				cells.length > 0 && 
				showConfirmDialog("Reset Library Collection?")){
										
			// Now add a new collection, with the old collection's
			// code and name.
			model.addCollection(new LibraryCollection(
				getCollectionCode(), 
				getCollectionName()
			));
		
			clearDisplay();			
				
		}		
		
	}
	
	public String getCollectionCode(){
		
		// Has the collection been created before?
		if (model.getCollection() != null)
			return model.getCollection().getCode();
		
		// Return blank data.
		return "";
					
	}
	
	public String getCollectionName(){
		
		// Has the collection been created before?
		if (model.getCollection() != null)
			return model.getCollection().getName();
		
		// Return blank data.
		return "";
		
	}
	
	public void populateHoldings(){
		
		// Testing.
		Tester tester = new Tester();
		tester.setupTestData(model);
    
	}
	
	public GridCell[] getHoldingCells(){
		
		Holding[] holdings = model.getAllHoldings();
		HoldingVisitor visitor = new HoldingVisitor();
		
		// Get the cells (holding and not empty).
		for (Holding holding : holdings)
			holding.accept(visitor);				
    			
		return visitor.getCells();
		
	}
	
	public GridCell[] sortHoldingCells(GridCell[] cells){
		
		// Sort the cells based on what is selected in 'Sort Order'.
		// Look inside HoldingCell to see how the comparison is done.		
		// Unsorted cells are just passed back to the caller.
		
		// Sort by CODE
		if (mainView.getSortOrder() == ToolBarOptionsController.SortActions.code.ordinal()){
			
			HoldingCell[] holdingCells = convertCells(cells);
			Arrays.sort(holdingCells, HoldingCell.CodeComparator);
			
			cells = holdingCells;
			
		// Sort by TYPE
		} else if (mainView.getSortOrder() == ToolBarOptionsController.SortActions.type.ordinal()){
			
			HoldingCell[] holdingCells = convertCells(cells);
			Arrays.sort(holdingCells, HoldingCell.TypeComparator);
			
			cells = holdingCells;		
		}		
		
		return cells;
		
	}
	
	private HoldingCell[] convertCells(GridCell[] cells){
		
		// This is just a helper method to convert the cells
		// for the sort comparison as it expects HoldingCells
		// and not GridCells.
		
		int i = 0;
		HoldingCell[] holdings = new HoldingCell[cells.length];
		
		// Loop through the cells, casting them to HoldingCells.
		for (GridCell cell : cells)
			holdings[i++] = (HoldingCell)cell;
		
		return holdings;
		
	}
	
	public int calculatePadCount(int holdingCellCount, int maxColumns){
		
		// This is used to calculate the number of EmptyCells required.
		
		int remaining = holdingCellCount % maxColumns;
		
		// If the result is below maxColumns or is 0, return 0.
		// Otherwise return maxColumns subtracted by the remainder. 
		return holdingCellCount < maxColumns + 1  || remaining == 0 ?
				0 : 
				maxColumns - remaining;
		
	}
	
	public GridCell[] createSortAndCombineCells(GridCell[] holdingCells, int padCount){
		
		// This is used to sort the HoldingCells, then combine
		// with the desired number of EmptyCells.
		
		GridCell[] cells = null;
		GridCell[] sorted = sortHoldingCells(holdingCells);
		
		// Don't bother adding EmptyCells if there is no need.
		if (padCount > 0){
			
			// Create new array large enough for old cells + empty cells.
			cells = new GridCell[holdingCells.length + padCount];			
			
			// Create the cells. First the HoldingCells get created.
			// Then the EmptyCells are appended.
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
		
		int bookCount = model.countBooks();
		int videoCount = model.countVideos();
		
		// If there are no holdings, then just clear display and get out of here.
		if (bookCount == 0 && videoCount == 0){
			
			clearDisplay();
						
		} else {
		
			// Get current holdings.
			GridCell[] holdingCells = getHoldingCells();
			// Calculate number of EmptyCells required.
			int padCount = calculatePadCount(holdingCells.length, LibraryGridController.MAX_CELLS_PER_COLUMN);
			// Create the cells.
			GridCell[] cells = createSortAndCombineCells(holdingCells, padCount);			
			
			// Update the LibraryGrid and StatusBar.
			mainView.setCells(holdingCells, padCount);
			mainView.updateLibraryGrid(cells);
			mainView.updateStatusBar(getStatusData());
						
			// Set GridState to a default value.
			GridState state = GridState.initialised;
			
			// Disable remove book buttons, etc.
			if (bookCount == 0)				
				state = GridState.noBooks;
			
			// Disable remove video buttons, etc.
			else if (videoCount == 0)
				state = GridState.noVideos;
			
			// Notify listeners of grid state change.
			mainView.getController().notifyGridListeners(state);
		
		}			
		
	}
	
	public void clearDisplay(){
				
		// Update the LibraryGrid and StatusBar. 
		mainView.clearLibraryGrid();
		mainView.updateStatusBar(getStatusData());
		
		// Notify listeners of grid state change.
		mainView.getController().notifyGridListeners(GridState.empty);
		
	}
	
	public String toTitleCase(String str){
		
		// Convert STRING or string to String
		return String.format(
			"%s%s", 
			str.substring(0, 1).toUpperCase(), 
			str.substring(1).toLowerCase()
		);
		
	}
	
	public void handleExitAction(){
		
		// This is handled here because it is called more than once.
		
		// Confirm the user wants to go home.
		if (showConfirmDialog("Are you sure you wish to exit the application?"))
			System.exit(0);

	}
	
}
