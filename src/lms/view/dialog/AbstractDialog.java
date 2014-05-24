package lms.view.dialog;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

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
	
	public static final int DEFAULT_TEXTFIELD_COLUMNS = 10;
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
		
		result = Actions.Cancel;
		cmdOk = new JButton(Actions.OK.name());
		cmdCancel = new JButton(Actions.Cancel.name());
		
		cmdOk.setActionCommand(Actions.OK.name());
		cmdCancel.setActionCommand(Actions.Cancel.name());
		
		cmdOk.addActionListener(this);
		cmdCancel.addActionListener(this);
		
		cmdOk.setMnemonic('O');
		cmdCancel.setMnemonic('C');
		
		pnlContent = new JPanel();
		mainPanel = new JPanel();		
		pnlButtons = new JPanel();
		pnlInnerButtons = new JPanel();

		//pnlInnerButtons.setLayout(new FlowLayout(FlowLayout.RIGHT));
		pnlInnerButtons.add(cmdOk);
		pnlInnerButtons.add(cmdCancel);
		
		pnlButtons.setLayout(new BorderLayout());
		pnlButtons.add(pnlInnerButtons, BorderLayout.EAST);
		
		mainPanel.setLayout(new BorderLayout());
		//mainPanel.setLayout(new GridLayout(2, 1));
		
		pnlContent.setLayout(new GridLayout(0,1));
		pnlContent.setBorder(BorderFactory.createEmptyBorder(5, 5, 0, 5));
		
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
		
		//content.setLayout(new GridLayout(0,1));
		//content.setBorder(BorderFactory.createEmptyBorder(5, 5, 0, 5));
		
		mainPanel.add(content);//, BorderLayout.NORTH);
		mainPanel.add(pnlButtons, BorderLayout.SOUTH);
		
		//getContentPane().add(mainPanel);
		add(mainPanel);
		
		reDisplay();
		
	}
	public void reDisplay(){
		
		result = Actions.Cancel;
		
		pack();
		setMinimumSize(new Dimension(getWidth(), pnlButtons.getHeight() << 1));
		
		setLocationRelativeTo(frame);
		setVisible(true);
		
	}
	
	public Actions getResult(){
		
		return result;
		
	} 
	
//	public Component getComponent(String name){
//		
//		Component result = null;
//		
//		
//		for (Component component : ((JPanel)pnlContent.getComponent(0)).getComponents()){
////			if (component.getName() != null && component.getName().equals(name)){
////				result = component;
////				break;
////			}
//			
//			System.out.println(component);
//		}
//		
//		return result;
//		
//	}
//	
//	public String getTextFieldValue(String name){
//		
//		JTextField txt = (JTextField)getComponent("txt" + name);
//		
//		return txt == null ? "" : txt.getText();
//		
//	}
//	public String getLabelValue(String name){
//		
//		JLabel lbl = (JLabel)getComponent("lbl" + name);
//		
//		return lbl == null ? "" : lbl.getText().replace(":", "");
//		
//	}
	
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
		
		if (e.getActionCommand().equals(Actions.OK.name()))
			handleOkAction();
		
		else if (e.getActionCommand().equals(Actions.Cancel.name()))
			handleCancelAction();
		
	}

}
