package lms.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import lms.controller.MainController.SortActions;
import lms.view.MainView;
import lms.view.ToolBar;

/**
 * @author Greg Kappatos
 * @date 25 May 2014
 * 
 */
public class ToolBarOptionsController implements ActionListener {

	private MainView mainView;
		 	
	public ToolBarOptionsController(ToolBar toolBar) {
		
		mainView = toolBar.getMainView();
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		// Set the sortOrder value in MainController. (Pass the buck)
				
		if (e.getActionCommand().equals(SortActions.none.name()))
			mainView.getController().setSortOrder(SortActions.none);
		
		else if (e.getActionCommand().equals(SortActions.code.name()))
			mainView.getController().setSortOrder(SortActions.code);
		
		else if (e.getActionCommand().equals(SortActions.type.name()))
			mainView.getController().setSortOrder(SortActions.type);
		
		// Refresh the grid.
		mainView.refreshLibraryGrid();

	}

}
