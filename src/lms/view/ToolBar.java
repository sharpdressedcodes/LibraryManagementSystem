package lms.view;

import java.awt.*;
import java.util.*;

import javax.swing.*;

import lms.controller.Controller;
import lms.controller.MainController;
import lms.controller.ToolBarButtonsController;
import lms.controller.ToolBarOptionsController;
import lms.model.facade.LMSModel;

public class ToolBar extends JPanel {

	private JPanel leftPanel;
	private JPanel rightPanel;
	private JPanel rightLowerPanel;
	
	private JButton cmdInit;
	private JButton cmdAddBook;
	private JButton cmdRemoveBook;
	private JButton cmdAddVideo;
	private JButton cmdRemoveVideo;	
	
	private JLabel lblTitle;
	private ButtonGroup pnlOpt;
	private JRadioButton optNone;
	private JRadioButton optCode;
	private JRadioButton optType;
	
	private MainView mainView;
	private ToolBarButtonsController buttonController;
	private ToolBarOptionsController optionsController;
	
	public ToolBar(MainView view) {
		
		// Set class variables
		this.mainView = view;
		
		// Create components.
		this.leftPanel = new JPanel();
		this.rightPanel = new JPanel();
		this.rightLowerPanel = new JPanel();
		
		this.cmdInit = new JButton(new ImageIcon(getClass().getResource("/img/collection.png")));
		this.cmdAddBook = new JButton(new ImageIcon(getClass().getResource("/img/book_add.png")));
		this.cmdRemoveBook = new JButton(new ImageIcon(getClass().getResource("/img/book_delete.png")));
		this.cmdAddVideo = new JButton(new ImageIcon(getClass().getResource("/img/film_add.png")));
		this.cmdRemoveVideo = new JButton(new ImageIcon(getClass().getResource("/img/film_delete.png")));		
		
		this.lblTitle = new JLabel("Sort Order");
		this.optNone = new JRadioButton("None");
		this.optCode = new JRadioButton("Code");
		this.optType = new JRadioButton("Type");
		this.pnlOpt = new ButtonGroup();
		
		this.buttonController = new ToolBarButtonsController(this);
		this.optionsController = new ToolBarOptionsController(this);

		// Set tooltips
		this.cmdInit.setToolTipText("Init/Reset");
		this.cmdAddBook.setToolTipText("Add Book");
		this.cmdRemoveBook.setToolTipText("Remove Book");
		this.cmdAddVideo.setToolTipText("Add Video");
		this.cmdRemoveVideo.setToolTipText("Remove Video");
		
		// Set default option.
		this.optNone.setSelected(true);
		
		this.optNone.setMnemonic('N');
		this.optCode.setMnemonic('C');
		this.optType.setMnemonic('T');
							
		// Set appropriate layouts.
		this.setLayout(new BorderLayout());
		this.rightPanel.setLayout(new BorderLayout());
		
		// Add components to left panel.
		this.leftPanel.add(this.cmdInit);
		this.leftPanel.add(this.cmdAddBook);
		this.leftPanel.add(this.cmdRemoveBook);
		this.leftPanel.add(this.cmdAddVideo);
		this.leftPanel.add(this.cmdRemoveVideo);
		
		// Group radio buttons.
		this.pnlOpt.add(this.optNone);
		this.pnlOpt.add(this.optCode);
		this.pnlOpt.add(this.optType);		
		
		// Add components to right panel.
		this.rightLowerPanel.add(this.optNone);
		this.rightLowerPanel.add(this.optCode);
		this.rightLowerPanel.add(this.optType);
		
		this.rightPanel.add(this.lblTitle, BorderLayout.NORTH);	
		this.rightPanel.add(this.rightLowerPanel, BorderLayout.SOUTH);
		
		this.add(this.leftPanel, BorderLayout.WEST);
		this.add(this.rightPanel, BorderLayout.EAST);
				
		this.cmdInit.setActionCommand(Controller.Actions.init.name());
		this.cmdAddBook.setActionCommand(Controller.Actions.addBook.name());
		this.cmdRemoveBook.setActionCommand(Controller.Actions.removeBook.name());
		this.cmdAddVideo.setActionCommand(Controller.Actions.addVideo.name());
		this.cmdRemoveVideo.setActionCommand(Controller.Actions.removeVideo.name());
		
		
		// Add Button listeners.
		this.cmdInit.addActionListener(this.buttonController);
		this.cmdAddBook.addActionListener(this.buttonController);
		this.cmdRemoveBook.addActionListener(this.buttonController);
		this.cmdAddVideo.addActionListener(this.buttonController);
		this.cmdRemoveVideo.addActionListener(this.buttonController);
		
		this.optNone.setActionCommand(ToolBarOptionsController.SortActions.none.name());
		this.optCode.setActionCommand(ToolBarOptionsController.SortActions.code.name());
		this.optType.setActionCommand(ToolBarOptionsController.SortActions.type.name());
		
		// Add RadioButton listeners.
		this.optNone.addActionListener(this.optionsController);
		this.optCode.addActionListener(this.optionsController);
		this.optType.addActionListener(this.optionsController);
				
		//LMSModel model = this.mainView.getModel();
		
	}

	public MainView getMainView(){

		return this.mainView;
		
	}
	public ToolBarButtonsController getToolBarButtonsController(){
		return this.buttonController;
	}
}
