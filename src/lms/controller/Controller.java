package lms.controller;

import java.util.*;
import javax.swing.JOptionPane;
import lms.model.Book;
import lms.model.Holding;
import lms.model.LibraryCollection;
import lms.model.Video;
import lms.model.facade.LMSModel;
import lms.model.visitor.HoldingVisitor;
import lms.view.MainView;
import lms.view.dialog.*;
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
		
		AddHoldingDialog dialog = new AddHoldingDialog(mainView, "Book");
		String code = "";
		String title = "";
		
		while (dialog.getResult().equals(AbstractDialog.Actions.OK)){
			
			code = dialog.getHoldingId();
			title = dialog.getHoldingTitle();			
						
			//if (code.equals(""))
				//showErrorDialog(String.format("%s cannot be empty.", dialog.getHoldingId(true)));
			
			/*else*/ if (code.contains(String.valueOf(AddHoldingDialog.NUMERIC_PLACEHOLDER)))
				showErrorDialog(String.format("Invalid %s.", dialog.getHoldingId(true)));
			
			else if (title.equals(""))
				showErrorDialog(String.format("%s cannot be empty.", dialog.getHoldingTitle(true)));
			
			else if (this.model.getHolding(Integer.parseInt(code)) != null)
				showErrorDialog(String.format("Cannot add Book.\nA holding with id %s already exists.", code));
			
			else
				break;
			
			dialog.reDisplay();
			
		}

		if (!code.contains(String.valueOf(AddHoldingDialog.NUMERIC_PLACEHOLDER)) && !title.equals("")){								
			
			this.model.addHolding(new Book(Integer.parseInt(code), title));					
			updateDisplay();
			
		}
		
	}
	public void addVideo(){
		
		AddHoldingDialog dialog = new AddHoldingDialog(mainView, "Video", new String[]{"4", "6"});
		String code = "";
		String title = "";
		
		while (dialog.getResult().equals(AbstractDialog.Actions.OK)){
			
			code = dialog.getHoldingId();
			title = dialog.getHoldingTitle();			
						
			/*if (code.equals(""))
				showErrorDialog(String.format("%s cannot be empty.", dialog.getHoldingId(true)));*/
			
			if (code.contains(String.valueOf(AddHoldingDialog.NUMERIC_PLACEHOLDER)))
				showErrorDialog(String.format("Invalid %s.", dialog.getHoldingId(true)));
			
			else if (title.equals(""))
				showErrorDialog(String.format("%s cannot be empty.", dialog.getHoldingTitle(true)));
			
			else if (this.model.getHolding(Integer.parseInt(code)) != null)
				showErrorDialog(String.format("Cannot add Video.\nA holding with id %s already exists.", code));
			
			else
				break;
			
			dialog.reDisplay();
			
		}

		if (!code.contains(String.valueOf(AddHoldingDialog.NUMERIC_PLACEHOLDER)) && !title.equals("")){								
			
			this.model.addHolding(new Video(
					Integer.parseInt(code), 
					title, 
					Integer.parseInt(dialog.getHoldingFee())
			));					
			updateDisplay();
			
		}
		
	}
	public void removeHolding(int holdingId){
		
		if (showConfirmDialog(String.format("Permanently remove this %s?\n%s", toTitleCase(this.model.getHolding(holdingId).getType()), holdingId))){
		
			this.model.removeHolding(holdingId);			
			updateDisplay();
			
		}
		
	}	
	public void removeBooks(){
		
		HoldingVisitor visitor = new HoldingVisitor();
		Holding[] holdings = this.model.getAllHoldings();
		
		for (Holding holding : holdings)
	  		 holding.accept(visitor);
		
		removeHoldings(visitor.getBooks());
		
	}
	public void removeVideos(){
		
		HoldingVisitor visitor = new HoldingVisitor();
		Holding[] holdings = this.model.getAllHoldings();
		
		for (Holding holding : holdings)
	  		 holding.accept(visitor);
		
		removeHoldings(visitor.getVideos());
		
	}
	public void removeHoldings(Holding[] holdings){
		
		String[] holdingStrings = new String[holdings.length];
		String type = toTitleCase(holdings[0].getType());
		
		for (int i = 0; i < holdings.length; i++)
			holdingStrings[i] = holdings[i].toString().substring(0, holdings[i].toString().lastIndexOf(':'));
							
		RemoveHoldingsDialog dialog = new RemoveHoldingsDialog(this.mainView, type, holdingStrings);
		
		int[] holdingIds = null;
		
		while (dialog.getResult().equals(AbstractDialog.Actions.OK)){
			
			holdingIds = dialog.getSelectedHoldingIds();
			
			if (holdingIds.length > 0)
				break;
				
			showErrorDialog("You must select at least one item to remove.");
			dialog.reDisplay();						
			
		}
		
		if (holdingIds != null && holdingIds.length > 0){
			StringBuffer sb = new StringBuffer();
			
			for (int holdingId : holdingIds)
				sb.append(String.format("%s,", holdingId));
			
			if (sb.length() > 0)
				sb.deleteCharAt(sb.length() - 1);
			
			if (showConfirmDialog(String.format("Permanently remove these %ss?\n(%s)", type, sb.toString()))){
				
				for (int holdingId : holdingIds)		
					this.model.removeHolding(holdingId);

				updateDisplay();
				
			}
		}		
		
	}
	public void showErrorDialog(String message){
		
		JOptionPane.showMessageDialog(this.mainView, message, "Error", JOptionPane.ERROR_MESSAGE);
		
	}
	public boolean showConfirmDialog(String message){
		
		return JOptionPane.showConfirmDialog(
				this.mainView, message, 
				"Confirmation", 
				JOptionPane.QUESTION_MESSAGE & JOptionPane.OK_CANCEL_OPTION
		) == JOptionPane.OK_OPTION;
		
	}
	public boolean addLibraryCollection(){				
		
		String code = "";
		String name = "";
		
		if (this.model.getCollection() != null){
			code = this.model.getCollection().getCode();
			name = this.model.getCollection().getName();
		}
		
		InitCollectionDialog dialog = new InitCollectionDialog(mainView, code, name);
		code = "";
		name = "";
		
		while (dialog.getResult().equals(AbstractDialog.Actions.OK)){
			
			code = dialog.getCollectionCode();
			name = dialog.getCollectionName();
			
			if (code.equals(""))
				showErrorDialog(String.format("%s cannot be empty.", dialog.getCollectionCode(true)));
			else if (name.equals(""))
				showErrorDialog(String.format("%s cannot be empty.", dialog.getCollectionName(true)));						
			else
				break;
			
			dialog.reDisplay();
			
		}
		
		if (!code.equals("") && !name.equals("")){
			this.model.addCollection(new LibraryCollection(code, name));			
			return true;
		}
			
		return false;
		
	}
	public void resetLibraryCollection(){
		
		GridCell[] cells = this.mainView.getCells();
		
		if (cells != null && 
				cells.length > 0 && 
				showConfirmDialog("Reset Library Collection?")){
										
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
		
		Holding[] holdings = model.getAllHoldings();
		HoldingVisitor visitor = new HoldingVisitor();
		
		for (Holding holding : holdings)
			holding.accept(visitor);				
        
    return visitor.getCells();
		
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
		
		//this.mainView.toggleControls(false);
		this.mainView.clearLibraryGrid();
		this.mainView.updateStatusBar(getStatusData());
		
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
		
	if (showConfirmDialog("Are you sure you wish to exit the application?"))
		System.exit(0);

	}
}
