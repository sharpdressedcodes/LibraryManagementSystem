package lms.view.main;

import lms.model.facade.LMSFacade;
import lms.model.facade.LMSModel;
import lms.view.MainView;

/**
 * @author Greg Kappatos
 * @date 25 May 2014
 * 
 */
public class LMSDriver {

	public static void main(String[] args) {

		// Create the model
		LMSModel model = new LMSFacade();
		
		// Create the custom view. Also pass the model to the view.
		MainView mainView = new MainView(model);
		
		// Display.
		mainView.setVisible(true);
		
	}

}
