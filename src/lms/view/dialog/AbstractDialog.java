package lms.view.dialog;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * @author Greg Kappatos
 * @date 25 May 2014
 * 
 */
@SuppressWarnings("serial")
public abstract class AbstractDialog extends JDialog implements ActionListener {

	private JButton cmdOk;
	private JButton cmdCancel;
	private Actions result;
	
	private JPanel mainPanel;		
	private JPanel pnlButtons;
	private JPanel pnlInnerButtons;
	private JPanel pnlContent;
	private JFrame frame; 	
	
	public static final int DEFAULT_TEXTFIELD_COLUMNS = 20; // JTextField width
 	public static enum Actions {
		OK,
		Cancel
	}
	
	public AbstractDialog(JFrame frame) {
		
		this(frame, true);
		
	}
	public AbstractDialog(JFrame frame, boolean modal) {
		
		super(frame, modal);
		
		this.frame = frame;				
		
		// Create components.
		result = Actions.Cancel;
		cmdOk = new JButton(Actions.OK.name());
		cmdCancel = new JButton(Actions.Cancel.name());
		
		pnlContent = new JPanel();
		mainPanel = new JPanel();		
		pnlButtons = new JPanel();
		pnlInnerButtons = new JPanel();
		
		// Set action commands.
		cmdOk.setActionCommand(Actions.OK.name());
		cmdCancel.setActionCommand(Actions.Cancel.name());
		
		// Add button listeners.
		cmdOk.addActionListener(this);
		cmdCancel.addActionListener(this);
		
		// Set mnemonics.
		cmdOk.setMnemonic('O');
		cmdCancel.setMnemonic('C');
				
		// Add components.
		pnlInnerButtons.add(cmdOk);
		pnlInnerButtons.add(cmdCancel);
		
		pnlButtons.setLayout(new BorderLayout());
		pnlButtons.add(pnlInnerButtons, BorderLayout.EAST);
		
		mainPanel.setLayout(new BorderLayout());
		mainPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		
		pnlContent.setLayout(new GridLayout(0,1));
		pnlContent.setBorder(BorderFactory.createEmptyBorder(5, 5, 0, 5));		
		
		// Set default button.
		getRootPane().setDefaultButton(cmdOk);
		
	}

	public JPanel getContentPanel(){
		
		return pnlContent;
		
	}
	public void display(){
		
		display(pnlContent);
		
	}
	public void display(JPanel content){
		
		mainPanel.removeAll();
		
		mainPanel.add(content);
		mainPanel.add(pnlButtons, BorderLayout.SOUTH);
		
		add(mainPanel);
		
		// By default, I have chosen to disable this button until
		// valid data has been entered into the dialog.
		// To override this behavior, just enable it in the
		// sub classed constructor.
		cmdOk.setEnabled(false);
		// Call checkComponents immediately.
		checkComponents();
		
		reDisplay();
		
	}
	public void reDisplay(){
		
		result = Actions.Cancel;
		
		pack();
		setMinimumSize(new Dimension(
			pnlInnerButtons.getWidth() + 27, 
			pnlContent.getHeight() + (pnlButtons.getHeight() << 1) + 12
		));
		
		setLocationRelativeTo(frame);
		setVisible(true);
		
	}
	protected JButton getOkButton(){
		
		return this.cmdOk;
		
	}
	public Actions getResult(){
		
		return result;
		
	} 
	
	public DocumentListener createDocumentListener(){
		
		// This is used to see if the OK button can be enabled or not.
		
		DocumentListener listener = new DocumentListener(){

			@Override
			public void changedUpdate(DocumentEvent arg0) {
				checkComponents();		
			}

			@Override
			public void insertUpdate(DocumentEvent arg0) {
				checkComponents();				
			}

			@Override
			public void removeUpdate(DocumentEvent arg0) {
				checkComponents();				
			}
			
		};
		
		return listener;
		
	}
	
	// Override this function to enable/disable cmdOk
	public void checkComponents(){}
	
	private void handleOkAction(){
		
		result = Actions.OK;
		setVisible(false);
		
	}
	private void handleCancelAction(){
		
		result = Actions.Cancel;
		setVisible(false);
		
	}
	
	// ActionListener implementation.
	@Override
	public void actionPerformed(ActionEvent e){
		
		// Pass the buck to the internal implementations.
		
		if (e.getActionCommand().equals(Actions.OK.name()))
			handleOkAction();
		
		else if (e.getActionCommand().equals(Actions.Cancel.name()))
			handleCancelAction();
		
	}

}
