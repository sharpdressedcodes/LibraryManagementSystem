package lms.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import lms.view.MainView;
import lms.view.ToolBar;

public class ToolBarOptionsController implements ActionListener {

	private MainView mainView;
	
	public static enum SortActions {
		none,
		code,
		type
	} 
	
	public ToolBarOptionsController(ToolBar toolBar) {
		
		this.mainView = toolBar.getMainView();
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getActionCommand().equals(SortActions.none.name()))
			this.mainView.setSortOrder(SortActions.none.ordinal());
		
		else if (e.getActionCommand().equals(SortActions.code.name()))
			this.mainView.setSortOrder(SortActions.code.ordinal());
		
		else if (e.getActionCommand().equals(SortActions.type.name()))
			this.mainView.setSortOrder(SortActions.type.ordinal());
		
		this.mainView.refreshLibraryGrid();

	}

}
