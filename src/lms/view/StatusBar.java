package lms.view;

import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @author Greg Kappatos
 * @date 25 May 2014
 * 
 */
@SuppressWarnings("serial")
public class StatusBar extends JPanel {

	private JLabel label;	
	//private MainView mainView;
		
	public StatusBar(MainView view) {
		
		//this.mainView = view;				
		label = new JLabel("");
		
		// Set appearance.
		setLayout(new FlowLayout(FlowLayout.LEFT));
		
		// Add component.
		add(label);
		
	}
	
	public String getText(){
		
		return label.getText();
		
	}
	public void setText(String newValue){
		
		label.setText(newValue);
		
	}
	public String getToolTipText(){
		
		return label.getToolTipText();
		
	}
	public void setToolTipText(String newValue){
		
		label.setToolTipText(newValue);
		
	}

}
