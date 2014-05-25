package lms.controller;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import lms.model.grid.listener.GridListener;
import lms.view.MainView;

public class MainController extends WindowAdapter {

	//private LMSModel model;
	//private MainView mainView;
	private Controller helper;
	private List<GridListener> gridListeners;
	
	public MainController(MainView view) {
		
		//this.mainView = view;
		//this.model = this.mainView.getModel();
		this.helper = new Controller(view);
		gridListeners = new ArrayList<GridListener>();
		
	}
	
	public void addGridListener(GridListener listener){
		
		gridListeners.add(listener);
		
	}
	
	public void removeGridListener(GridListener listener){
		
		try {
			
			gridListeners.remove(listener);
			
		} catch (Exception ex){}
		
	}
	
	public void clearGridListeners(){
		
		gridListeners.clear();
		
	}
	
	public List<GridListener> getGridListeners(){
	
		return gridListeners;
		
	}
	
	public void notifyGridListeners(GridListener.GridState state){
		
		for (GridListener listener : gridListeners)
			listener.gridChanged(state);
		
	}
	
	public void handleExitAction(WindowEvent e){
		
		this.helper.handleExitAction();
		
	}

	// WindowAdapter implementation.
	@Override
	public void windowClosing(WindowEvent e){
		
		handleExitAction(e);
		
	}
	
	

}
