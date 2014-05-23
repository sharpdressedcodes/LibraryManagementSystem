package lms.view;

import javax.swing.*;

import lms.controller.Controller;
import lms.controller.MenuBarController;

@SuppressWarnings("serial")
public class MenuBar extends JMenuBar implements Toggleable {

	private MainView mainView;
	private MenuBarController controller;
	
	public MenuBar(MainView view) {
		
		this.mainView = view;
		this.controller = new MenuBarController(this);
		
		JMenu mnuFile = new JMenu("File");
		JMenu mnuLibrary = new JMenu("Library");
		JMenu mnuHoldings = new JMenu("Holdings");
		JMenu mnuHelp = new JMenu("Help");
		
		// mnuFile sub items.
		JMenuItem mnuExit = new JMenuItem("Exit");
		
		// mnuLibrary sub items.
		JMenuItem mnuInit = new JMenuItem("Init Library");
		JMenuItem mnuReset = new JMenuItem("Reset Library");
		
		// mnuHoldings sub items.
		JMenuItem mnuAddBook = new JMenuItem("Add Book");
		JMenuItem mnuRemoveBook = new JMenuItem("Remove Book");
		JMenuItem mnuAddVideo = new JMenuItem("Add Video");
		JMenuItem mnuRemoveVideo = new JMenuItem("Remove Video");
		
		// mnuHelp sub items.
		JMenuItem mnuAbout = new JMenuItem("About");
		
		
		mnuFile.setMnemonic('F');
		mnuLibrary.setMnemonic('L');
		mnuHoldings.setMnemonic('H');
		mnuHelp.setMnemonic('P');
		
		mnuExit.setMnemonic('x');
		
		// set actions
		mnuExit.setActionCommand(Controller.Actions.exit.name());		
		mnuInit.setActionCommand(Controller.Actions.init.name());
		mnuReset.setActionCommand(Controller.Actions.reset.name());
		mnuAddBook.setActionCommand(Controller.Actions.addBook.name());
		mnuRemoveBook.setActionCommand(Controller.Actions.removeBook.name());
		mnuAddVideo.setActionCommand(Controller.Actions.addVideo.name());
		mnuRemoveVideo.setActionCommand(Controller.Actions.removeVideo.name());
		mnuAbout.setActionCommand(Controller.Actions.about.name());
		
		mnuExit.addActionListener(this.controller);
		mnuInit.addActionListener(this.controller);
		mnuReset.addActionListener(this.controller);
		mnuAddBook.addActionListener(this.controller);
		mnuRemoveBook.addActionListener(this.controller);
		mnuAddVideo.addActionListener(this.controller);
		mnuRemoveVideo.addActionListener(this.controller);
		mnuAbout.addActionListener(this.controller);
		
		
		mnuFile.add(mnuExit);
		
		mnuLibrary.add(mnuInit);
		mnuLibrary.add(mnuReset);
		
		mnuHoldings.add(mnuAddBook);
		mnuHoldings.add(mnuRemoveBook);
		mnuHoldings.add(new JSeparator());
		mnuHoldings.add(mnuAddVideo);
		mnuHoldings.add(mnuRemoveVideo);
		
		mnuHelp.add(mnuAbout);
		
		this.add(mnuFile);
		this.add(mnuLibrary);
		this.add(mnuHoldings);
		this.add(mnuHelp);
		
	}

	public MainView getMainView(){

		return this.mainView;
		
	}

	// Toggleable implementation.
	@Override
	public void toggle(boolean state) {
		// TODO Auto-generated method stub
		
	}
}
