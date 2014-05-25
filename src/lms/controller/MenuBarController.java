package lms.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import lms.model.grid.listener.GridListener;
import lms.view.MainView;
import lms.view.MenuBar;

/**
 * @author Greg Kappatos
 * @date 25 May 2014
 * 
 */
public class MenuBarController implements ActionListener, GridListener {

	//private LMSModel model;
	private MainView mainView;
	private Controller helper;
	private MenuBar menuBar;
	
	public MenuBarController(MenuBar menuBar) {
		
		this.menuBar = menuBar;
		mainView = menuBar.getMainView();
		//this.model = this.mainView.getModel();
		helper = new Controller(mainView);
		
		// Tell the main controller that we want to be notified
		// when the grid state changes.
		mainView.getController().addGridListener(this);
		
	}
	
	public void handleExitAction(ActionEvent e){
		
		helper.handleExitAction();
		
	}
	
	public void handleInitAction(ActionEvent e){
		
		// If the collection is added, populate and update the display.
		if (helper.addLibraryCollection()){
			
			helper.populateHoldings();
			helper.updateDisplay();
		
		}
		
	}
	
	public void handleResetAction(ActionEvent e){
		
		helper.resetLibraryCollection();
		
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
	
	public void handleAboutAction(ActionEvent e){
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("Library Management System\n");
		sb.append("By Greg Kappatos\n25th May, 2014\n\n");
		
		sb.append("Visitor Pattern Usage Locations:\n");
		sb.append("lms.controller.Controller:: removeBooks(), removeVideos(), getHoldingCells()\n");
		sb.append("lms.model.LibraryCollection:: countBooks(), countVideos()\n");
		sb.append("lms.view.LibraryGrid:: update()\n\n");
		
		sb.append("Observer Pattern Usage Locations:\n");
		sb.append("lms.controller.Controller:: updateDisplay(), clearDisplay()\n");
		sb.append("lms.controller.MainController:: notifyGridListeners() and all *GridListener() methods \n");
		sb.append("lms.controller.ToolBarButtonsController:: gridChanged()\n");
		sb.append("lms.controller.MenuBarController:: gridChanged()\n\n");		
		
		JOptionPane.showMessageDialog(
				mainView, 
				sb.toString(), 
				"About", 
				JOptionPane.INFORMATION_MESSAGE
		);
		
	}
	
	//ActionListener implementation.
	@Override
	public void actionPerformed(ActionEvent e) {

		// Pass the buck to the internal implementations.
		
		if (e.getActionCommand().equals(Controller.Actions.exit.name()))
			handleExitAction(e);
		
		else if (e.getActionCommand().equals(Controller.Actions.init.name()))
			handleInitAction(e);
		
		else if (e.getActionCommand().equals(Controller.Actions.reset.name()))
			handleResetAction(e);
		
		else if (e.getActionCommand().equals(Controller.Actions.addBook.name()))
			handleAddBookAction(e);
		
		else if (e.getActionCommand().equals(Controller.Actions.removeBook.name()))
			handleRemoveBookAction(e);
		
		else if (e.getActionCommand().equals(Controller.Actions.addVideo.name()))
			handleAddVideoAction(e);
		
		else if (e.getActionCommand().equals(Controller.Actions.removeVideo.name()))
			handleRemoveVideoAction(e);	
		
		else if (e.getActionCommand().equals(Controller.Actions.about.name()))
			handleAboutAction(e);	

	}

	// GridListener implementation.
	@Override
	public void gridChanged(GridState state) {
		
		// Change the enabled state on certain menu items
		// based on the current grid state.
		
		// No items, no collection - nothing.
		if (state.equals(GridState.uninitialised)){
			
			menuBar.getResetMenu().setEnabled(false);
			menuBar.getAddBookMenu().setEnabled(false);
			menuBar.getRemoveBookMenu().setEnabled(false);
			menuBar.getAddVideoMenu().setEnabled(false);
			menuBar.getRemoveVideoMenu().setEnabled(false);
			
		// Collection has been created.
		} else if (state.equals(GridState.empty)){
			
			menuBar.getResetMenu().setEnabled(false);
			menuBar.getAddBookMenu().setEnabled(true);
			menuBar.getRemoveBookMenu().setEnabled(false);
			menuBar.getAddVideoMenu().setEnabled(true);
			menuBar.getRemoveVideoMenu().setEnabled(false);
			
		// No books.
		} else if (state.equals(GridState.noBooks)){
			
			menuBar.getResetMenu().setEnabled(true);
			menuBar.getAddBookMenu().setEnabled(true);
			menuBar.getRemoveBookMenu().setEnabled(false);
			menuBar.getAddVideoMenu().setEnabled(true);
			menuBar.getRemoveVideoMenu().setEnabled(true);
			
		// No videos.
		} else if (state.equals(GridState.noVideos)){
			
			menuBar.getResetMenu().setEnabled(true);
			menuBar.getAddBookMenu().setEnabled(true);
			menuBar.getRemoveBookMenu().setEnabled(true);
			menuBar.getAddVideoMenu().setEnabled(true);
			menuBar.getRemoveVideoMenu().setEnabled(false);
			
		// Fully operational.
		} else if (state.equals(GridState.initialised)){
			
			menuBar.getResetMenu().setEnabled(true);
			menuBar.getAddBookMenu().setEnabled(true);
			menuBar.getRemoveBookMenu().setEnabled(true);
			menuBar.getAddVideoMenu().setEnabled(true);
			menuBar.getRemoveVideoMenu().setEnabled(true);
			
		}
		
	}		

}
