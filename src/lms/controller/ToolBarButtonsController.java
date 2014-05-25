package lms.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import lms.controller.util.ControllerUtil;
import lms.model.grid.listener.GridListener;
import lms.view.MainView;
import lms.view.ToolBar;

/**
 * @author Greg Kappatos
 * @date 25 May 2014
 * 
 */
public class ToolBarButtonsController implements ActionListener, GridListener {

	private ToolBar toolBar;
	//private LMSModel model;
	private MainView mainView;
	private ControllerUtil helper;
	
	public ToolBarButtonsController(ToolBar toolBar) {
		
		this.toolBar = toolBar;
		mainView = toolBar.getMainView();
		//model = mainView.getModel();
		helper = new ControllerUtil(mainView);
		
		// Tell the main controller that we want to be notified
		// when the grid state changes.
		mainView.getController().addGridListener(this);
		
	}
	
	public void handleInitAction(ActionEvent e){
		
		// Create or Reset?
		switch (toolBar.getInitButton().getToolTipText().toLowerCase()){
		
		case "init library":
			
			// Only populate if adding the collection was successful.
			if (helper.addLibraryCollection()){
				
				helper.populateHoldings();
				helper.updateDisplay();
									
			}			
			break;
			
		case "reset library":
			
			helper.resetLibraryCollection();
			break;
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
		
		// Pass the buck to the internal implementations.
		
		if (e.getActionCommand().equals(MainController.CommandActions.init.name()))
			handleInitAction(e);
		
		else if (e.getActionCommand().equals(MainController.CommandActions.addBook.name()))
			handleAddBookAction(e);
		
		else if (e.getActionCommand().equals(MainController.CommandActions.removeBook.name()))
			handleRemoveBookAction(e);
		
		else if (e.getActionCommand().equals(MainController.CommandActions.addVideo.name()))
			handleAddVideoAction(e);
		
		else if (e.getActionCommand().equals(MainController.CommandActions.removeVideo.name()))
			handleRemoveVideoAction(e);					

	}
	
	// GridListener implementation.
	@Override
	public void gridChanged(GridState state) {
		
		// Change the enabled state on certain menu items
		// based on the current grid state.
		
		// No items, no collection - nothing.		
		if (state.equals(GridState.uninitialised)){
			
			toolBar.getInitButton().setToolTipText("Init Library");
			toolBar.getAddBookButton().setEnabled(false);
			toolBar.getRemoveBookButton().setEnabled(false);
			toolBar.getAddVideoButton().setEnabled(false);
			toolBar.getRemoveVideoButton().setEnabled(false);
			
		// Collection has been created.
		} else if (state.equals(GridState.empty)){
			
			toolBar.getInitButton().setToolTipText("Init Library");
			toolBar.getAddBookButton().setEnabled(true);
			toolBar.getRemoveBookButton().setEnabled(false);
			toolBar.getAddVideoButton().setEnabled(true);
			toolBar.getRemoveVideoButton().setEnabled(false);
			
		// No books.
		} else if (state.equals(GridState.noBooks)){
			
			toolBar.getInitButton().setToolTipText("Reset Library");
			toolBar.getAddBookButton().setEnabled(true);
			toolBar.getRemoveBookButton().setEnabled(false);
			toolBar.getAddVideoButton().setEnabled(true);
			toolBar.getRemoveVideoButton().setEnabled(true);
			
		// No videos.
		} else if (state.equals(GridState.noVideos)){
			
			toolBar.getInitButton().setToolTipText("Reset Library");
			toolBar.getAddBookButton().setEnabled(true);
			toolBar.getRemoveBookButton().setEnabled(true);
			toolBar.getAddVideoButton().setEnabled(true);
			toolBar.getRemoveVideoButton().setEnabled(false);
			
		// Fully operational.
		} else if (state.equals(GridState.initialised)){
			
			toolBar.getInitButton().setToolTipText("Reset Library");
			toolBar.getAddBookButton().setEnabled(true);
			toolBar.getRemoveBookButton().setEnabled(true);
			toolBar.getAddVideoButton().setEnabled(true);
			toolBar.getRemoveVideoButton().setEnabled(true);
			
		}
		
	}
	
}
