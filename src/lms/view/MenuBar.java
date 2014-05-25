package lms.view;

import javax.swing.*;
import lms.controller.Controller;
import lms.controller.MenuBarController;

@SuppressWarnings("serial")
public class MenuBar extends JMenuBar {

	private MainView mainView;
	private MenuBarController controller;
	
	// Menus.
	private JMenu mnuFile;
	private JMenu mnuLibrary;
	private JMenu mnuHoldings;
	private JMenu mnuHelp;
	
	// mnuFile sub items.
	private JMenuItem mnuExit;
	
	// mnuLibrary sub items.
	private JMenuItem mnuInit;
	private JMenuItem mnuReset;
	
	// mnuHoldings sub items.
	private JMenuItem mnuAddBook;
	private JMenuItem mnuRemoveBook;
	private JMenuItem mnuAddVideo;
	private JMenuItem mnuRemoveVideo;
	
	// mnuHelp sub items.
	private JMenuItem mnuAbout;
	
	public MenuBar(MainView view) {
		
		mainView = view;
		controller = new MenuBarController(this);
		
		mnuFile = new JMenu("File");
		mnuLibrary = new JMenu("Library");
		mnuHoldings = new JMenu("Holdings");
		mnuHelp = new JMenu("Help");
		
		// mnuFile sub items.
		mnuExit = new JMenuItem("Exit");
		
		// mnuLibrary sub items.
		mnuInit = new JMenuItem("Init Library");
		mnuReset = new JMenuItem("Reset Library");
		
		// mnuHoldings sub items.
		mnuAddBook = new JMenuItem("Add Book");
		mnuRemoveBook = new JMenuItem("Remove Book");
		mnuAddVideo = new JMenuItem("Add Video");
		mnuRemoveVideo = new JMenuItem("Remove Video");
		
		// mnuHelp sub items.
		mnuAbout = new JMenuItem("About");
		
		
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
		
		mnuExit.addActionListener(controller);
		mnuInit.addActionListener(controller);
		mnuReset.addActionListener(controller);
		mnuAddBook.addActionListener(controller);
		mnuRemoveBook.addActionListener(controller);
		mnuAddVideo.addActionListener(controller);
		mnuRemoveVideo.addActionListener(controller);
		mnuAbout.addActionListener(controller);
		
		
		mnuFile.add(mnuExit);
		
		mnuLibrary.add(mnuInit);
		mnuLibrary.add(mnuReset);
		
		mnuHoldings.add(mnuAddBook);
		mnuHoldings.add(mnuRemoveBook);
		mnuHoldings.add(new JSeparator());
		mnuHoldings.add(mnuAddVideo);
		mnuHoldings.add(mnuRemoveVideo);
		
		mnuHelp.add(mnuAbout);
		
		add(mnuFile);
		add(mnuLibrary);
		add(mnuHoldings);
		add(mnuHelp);
		
	}

	public MainView getMainView(){

		return this.mainView;
		
	}
	
	public JMenuItem getResetMenu(){
		
		return mnuReset;
		
	}
	
	public JMenuItem getAddBookMenu(){
			
			return mnuAddBook;
			
		}
	
	public JMenuItem getRemoveBookMenu(){
		
		return mnuRemoveBook;
		
	}
	
	public JMenuItem getAddVideoMenu(){
		
		return mnuAddVideo;
		
	}
	
	public JMenuItem getRemoveVideoMenu(){
		
		return mnuRemoveVideo;
		
	}

}
