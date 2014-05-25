package lms.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import lms.view.MainView;
import lms.view.ToolBar;

/**
 * @author Greg Kappatos
 * @date 25 May 2014
 * 
 */
public class ToolBarOptionsController implements ActionListener {

	private MainView mainView;
	
	// Actions used by radioButtons.
	public static enum SortActions {
		none,
		code,
		type
	} 
	
	public ToolBarOptionsController(ToolBar toolBar) {
		
		mainView = toolBar.getMainView();
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		// Set the sortOrder value on the mainView. (Pass the buck)
		
		if (e.getActionCommand().equals(SortActions.none.name()))
			mainView.setSortOrder(SortActions.none.ordinal());
		
		else if (e.getActionCommand().equals(SortActions.code.name()))
			mainView.setSortOrder(SortActions.code.ordinal());
		
		else if (e.getActionCommand().equals(SortActions.type.name()))
			mainView.setSortOrder(SortActions.type.ordinal());
		
		// Refresh the grid.
		mainView.refreshLibraryGrid();

	}

}
