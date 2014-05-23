package lms.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import lms.view.MainView;
import lms.view.MenuBar;

public class MenuBarController implements ActionListener {

	//private LMSModel model;
	private MainView mainView;
	private Controller helper;
	//private MenuBar menuBar;
	
	public MenuBarController(MenuBar menuBar) {
		
		//this.menuBar = menuBar;
		this.mainView = menuBar.getMainView();
		//this.model = this.mainView.getModel();
		this.helper = new Controller(this.mainView);
		
	}
	
	//ActionListener
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
		
		JOptionPane.showMessageDialog(
				this.mainView, 
				"Library Management System\nBy Greg Kappatos", 
				"About", 
				JOptionPane.INFORMATION_MESSAGE
		);
		
	}

}
