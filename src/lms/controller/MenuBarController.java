package lms.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import lms.model.grid.listener.GridListener;
import lms.view.MainView;
import lms.view.MenuBar;

public class MenuBarController implements ActionListener, GridListener {

	//private LMSModel model;
	private MainView mainView;
	private Controller helper;
	private MenuBar menuBar;
	
	public MenuBarController(MenuBar menuBar) {
		
		this.menuBar = menuBar;
		this.mainView = menuBar.getMainView();
		//this.model = this.mainView.getModel();
		this.helper = new Controller(this.mainView);
		
		this.mainView.getController().addGridListener(this);
		
	}
	
	public void handleExitAction(ActionEvent e){
		
		this.helper.handleExitAction();
		
	}
	
	public void handleInitAction(ActionEvent e){
		
		if (this.helper.addLibraryCollection()){
			
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
				this.mainView, 
				sb.toString(), 
				"About", 
				JOptionPane.INFORMATION_MESSAGE
		);
		
	}
	
	//ActionListener implementation.
	@Override
	public void actionPerformed(ActionEvent e) {

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
		
		if (state.equals(GridState.uninitialised)){
			
			menuBar.getResetMenu().setEnabled(false);
			menuBar.getAddBookMenu().setEnabled(false);
			menuBar.getRemoveBookMenu().setEnabled(false);
			menuBar.getAddVideoMenu().setEnabled(false);
			menuBar.getRemoveVideoMenu().setEnabled(false);
			
		} else if (state.equals(GridState.empty)){
			
			menuBar.getResetMenu().setEnabled(false);
			menuBar.getAddBookMenu().setEnabled(true);
			menuBar.getRemoveBookMenu().setEnabled(false);
			menuBar.getAddVideoMenu().setEnabled(true);
			menuBar.getRemoveVideoMenu().setEnabled(false);
			
		} else if (state.equals(GridState.noBooks)){
			
			menuBar.getResetMenu().setEnabled(true);
			menuBar.getAddBookMenu().setEnabled(true);
			menuBar.getRemoveBookMenu().setEnabled(false);
			menuBar.getAddVideoMenu().setEnabled(true);
			menuBar.getRemoveVideoMenu().setEnabled(true);
			
		} else if (state.equals(GridState.noVideos)){
			
			menuBar.getResetMenu().setEnabled(true);
			menuBar.getAddBookMenu().setEnabled(true);
			menuBar.getRemoveBookMenu().setEnabled(true);
			menuBar.getAddVideoMenu().setEnabled(true);
			menuBar.getRemoveVideoMenu().setEnabled(false);
			
		} else if (state.equals(GridState.initialised)){
			
			menuBar.getResetMenu().setEnabled(true);
			menuBar.getAddBookMenu().setEnabled(true);
			menuBar.getRemoveBookMenu().setEnabled(true);
			menuBar.getAddVideoMenu().setEnabled(true);
			menuBar.getRemoveVideoMenu().setEnabled(true);
			
		}
		
	}		

}
