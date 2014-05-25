package lms.view;

import java.awt.BorderLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import lms.controller.MainController;
import lms.controller.ToolBarButtonsController;
import lms.controller.ToolBarOptionsController;

/**
 * @author Greg Kappatos
 * @date 25 May 2014
 * 
 */
@SuppressWarnings("serial")
public class ToolBar extends JPanel {

	// Panels.
	private JPanel leftPanel;
	private JPanel rightPanel;
	private JPanel rightLowerPanel;
	
	// Buttons.
	private JButton cmdInit;
	private JButton cmdAddBook;
	private JButton cmdRemoveBook;
	private JButton cmdAddVideo;
	private JButton cmdRemoveVideo;	
	
	// Sort Order.
	private JLabel lblTitle;
	private ButtonGroup pnlOpt;
	private JRadioButton optNone;
	private JRadioButton optCode;
	private JRadioButton optType;
	
	// Main components.
	private MainView mainView;
	private ToolBarButtonsController buttonController;
	private ToolBarOptionsController optionsController;
	
	public ToolBar(MainView view) {
				
		mainView = view;
		
		// Create components.
		leftPanel = new JPanel();
		rightPanel = new JPanel();
		rightLowerPanel = new JPanel();
		
		// Create buttons.
		cmdInit = new JButton(new ImageIcon(getClass().getResource("/img/collection.png")));
		cmdAddBook = new JButton(new ImageIcon(getClass().getResource("/img/book_add.png")));
		cmdRemoveBook = new JButton(new ImageIcon(getClass().getResource("/img/book_delete.png")));
		cmdAddVideo = new JButton(new ImageIcon(getClass().getResource("/img/film_add.png")));
		cmdRemoveVideo = new JButton(new ImageIcon(getClass().getResource("/img/film_delete.png")));		
		
		// Create labels.
		lblTitle = new JLabel("Sort Order");
		optNone = new JRadioButton("None");
		optCode = new JRadioButton("Code");
		optType = new JRadioButton("Type");
		pnlOpt = new ButtonGroup();
		
		// Create controllers.
		buttonController = new ToolBarButtonsController(this);
		optionsController = new ToolBarOptionsController(this);

		// Set ToolTips.
		cmdInit.setToolTipText("Init/Reset");
		cmdAddBook.setToolTipText("Add Book");
		cmdRemoveBook.setToolTipText("Remove Book");
		cmdAddVideo.setToolTipText("Add Video");
		cmdRemoveVideo.setToolTipText("Remove Video");
		
		// Set default option.
		optNone.setSelected(true);
		
		// Set mnemonics.
		optNone.setMnemonic('N');
		optCode.setMnemonic('C');
		optType.setMnemonic('T');
							
		// Set appropriate layouts.
		setLayout(new BorderLayout());
		rightPanel.setLayout(new BorderLayout());
		
		// Add components to left panel.
		leftPanel.add(cmdInit);
		leftPanel.add(cmdAddBook);
		leftPanel.add(cmdRemoveBook);
		leftPanel.add(cmdAddVideo);
		leftPanel.add(cmdRemoveVideo);
		
		// Group radio buttons.
		pnlOpt.add(optNone);
		pnlOpt.add(optCode);
		pnlOpt.add(optType);		
		
		// Add components to right panel.
		rightLowerPanel.add(optNone);
		rightLowerPanel.add(optCode);
		rightLowerPanel.add(optType);
		
		rightPanel.add(lblTitle, BorderLayout.NORTH);	
		rightPanel.add(rightLowerPanel, BorderLayout.SOUTH);
		
		add(leftPanel, BorderLayout.WEST);
		add(rightPanel, BorderLayout.EAST);
				
		// Set action commands.
		cmdInit.setActionCommand(MainController.CommandActions.init.name());
		cmdAddBook.setActionCommand(MainController.CommandActions.addBook.name());
		cmdRemoveBook.setActionCommand(MainController.CommandActions.removeBook.name());
		cmdAddVideo.setActionCommand(MainController.CommandActions.addVideo.name());
		cmdRemoveVideo.setActionCommand(MainController.CommandActions.removeVideo.name());
				
		// Add Button listeners.
		cmdInit.addActionListener(buttonController);
		cmdAddBook.addActionListener(buttonController);
		cmdRemoveBook.addActionListener(buttonController);
		cmdAddVideo.addActionListener(buttonController);
		cmdRemoveVideo.addActionListener(buttonController);
		
		// Set action commands.
		optNone.setActionCommand(MainController.SortActions.none.name());
		optCode.setActionCommand(MainController.SortActions.code.name());
		optType.setActionCommand(MainController.SortActions.type.name());
		
		// Add RadioButton listeners.
		optNone.addActionListener(optionsController);
		optCode.addActionListener(optionsController);
		optType.addActionListener(optionsController);				
		
	}

	public MainView getMainView(){

		return mainView;
		
	}
	
	public JButton getInitButton(){
		
		return cmdInit;
		
	}

	public JButton getAddBookButton(){
		
		return cmdAddBook;
		
	}
	
	public JButton getRemoveBookButton(){
		
		return cmdRemoveBook;
		
	}
	
	public JButton getAddVideoButton(){
		
		return cmdAddVideo;
		
	}
	
	public JButton getRemoveVideoButton(){
		
		return cmdRemoveVideo;
		
	}

}
