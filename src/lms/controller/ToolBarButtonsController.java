package lms.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import lms.model.Holding;
import lms.model.LibraryCollection;
import lms.model.facade.LMSModel;
import lms.view.LibraryGrid;
import lms.view.MainView;
import lms.view.ToolBar;
import lms.view.grid.cells.*;
import lms.view.test.Tester;

public class ToolBarButtonsController implements ActionListener {

	private ToolBar toolBar;
	private LMSModel model;
	private MainView mainView;
	private Controller helper;
	
	public ToolBarButtonsController(ToolBar toolBar) {
		
		this.toolBar = toolBar;
		this.mainView = toolBar.getMainView();
		this.model = this.mainView.getModel();
		this.helper = new Controller(this.mainView);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		// Route command to method.
		if (e.getActionCommand().equals(Controller.Actions.init.name()))
			handleInitAction(e);
		
		else if (e.getActionCommand().equals(Controller.Actions.addBook.name()))
			handleAddBookAction(e);
		
		else if (e.getActionCommand().equals(Controller.Actions.removeBook.name()))
			handleRemoveBookAction(e);
		
		else if (e.getActionCommand().equals(Controller.Actions.addVideo.name()))
			handleAddVideoAction(e);
		
		else if (e.getActionCommand().equals(Controller.Actions.removeVideo.name()))
			handleRemoveVideoAction(e);					

	}
	
	public void handleInitAction(ActionEvent e){
			
		Holding[] holdings = null;
		
		// Make sure there is already a collection.
		if (this.model.getCollection() != null)
				holdings = this.model.getCollection().getAllHoldings();
		
		// If there are already holdings, then this command is actually 'reset'.
		if (holdings != null && holdings.length > 0){
			
			helper.resetLibraryCollection();
			
		// Otherwise the command is 'init'.
		} else if (helper.addLibraryCollection()){
		
			helper.populateHoldings();
			helper.updateDisplay();
								
		}
		
	}
	public void handleAddBookAction(ActionEvent e){
				
		helper.addBook();
		
	}
	public void handleRemoveBookAction(ActionEvent e){
		
		// TODO: sort removeBookAction - get bookId
		helper.removeHoldings(new int[] {1000091});
		
	}
	public void handleAddVideoAction(ActionEvent e){
		
		helper.addVideo();
		
	}
	public void handleRemoveVideoAction(ActionEvent e){
		
		// TODO: sort removeVideoAction - get videoId
		helper.removeHoldings(new int[] {2020091});
		
	}
	
}
