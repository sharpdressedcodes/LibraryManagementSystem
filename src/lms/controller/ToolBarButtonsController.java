package lms.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import lms.model.Holding;
import lms.model.facade.LMSModel;
import lms.model.grid.listener.GridListener;
import lms.model.grid.listener.GridListener.GridState;
import lms.view.MainView;
import lms.view.ToolBar;

public class ToolBarButtonsController implements ActionListener, GridListener {

	private ToolBar toolBar;
	private LMSModel model;
	private MainView mainView;
	private Controller helper;
	
	public ToolBarButtonsController(ToolBar toolBar) {
		
		this.toolBar = toolBar;
		this.mainView = toolBar.getMainView();
		this.model = this.mainView.getModel();
		this.helper = new Controller(this.mainView);
		
		this.mainView.getController().addGridListener(this);
		
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
		
		helper.removeBooks();
		
	}
	
	public void handleAddVideoAction(ActionEvent e){
		
		helper.addVideo();
		
	}
	
	public void handleRemoveVideoAction(ActionEvent e){
		
		helper.removeVideos();
		
	}

	// ActionListener implementation.
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
	
	// GridListener implementation.
	@Override
	public void gridChanged(GridState state) {
		
		if (state.equals(GridState.uninitialised)){
			
			toolBar.getAddBookButton().setEnabled(false);
			toolBar.getRemoveBookButton().setEnabled(false);
			toolBar.getAddVideoButton().setEnabled(false);
			toolBar.getRemoveVideoButton().setEnabled(false);
			
		} else if (state.equals(GridState.empty)){
			
			toolBar.getAddBookButton().setEnabled(true);
			toolBar.getRemoveBookButton().setEnabled(false);
			toolBar.getAddVideoButton().setEnabled(true);
			toolBar.getRemoveVideoButton().setEnabled(false);
			
		} else if (state.equals(GridState.noBooks)){
			
			toolBar.getAddBookButton().setEnabled(true);
			toolBar.getRemoveBookButton().setEnabled(false);
			toolBar.getAddVideoButton().setEnabled(true);
			toolBar.getRemoveVideoButton().setEnabled(true);
			
		} else if (state.equals(GridState.noVideos)){
			
			toolBar.getAddBookButton().setEnabled(true);
			toolBar.getRemoveBookButton().setEnabled(true);
			toolBar.getAddVideoButton().setEnabled(true);
			toolBar.getRemoveVideoButton().setEnabled(false);
			
		} else if (state.equals(GridState.initialised)){
			
			toolBar.getAddBookButton().setEnabled(true);
			toolBar.getRemoveBookButton().setEnabled(true);
			toolBar.getAddVideoButton().setEnabled(true);
			toolBar.getRemoveVideoButton().setEnabled(true);
			
		}
		
	}
	
}
