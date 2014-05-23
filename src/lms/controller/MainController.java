package lms.controller;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import lms.view.MainView;

public class MainController extends WindowAdapter {

	//private LMSModel model;
	//private MainView mainView;
	private Controller helper;
	
	public MainController(MainView view) {
		
		//this.mainView = view;
		//this.model = this.mainView.getModel();
		this.helper = new Controller(view);
		
	}
	
	// WindowAdapter implementation.
	@Override
	public void windowClosing(WindowEvent e){
		
		handleExitAction(e);
		
	}
	
	public void handleExitAction(WindowEvent e){
		
		this.helper.handleExitAction();
		
	}

}
