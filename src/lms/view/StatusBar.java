package lms.view;

import java.awt.*;
import javax.swing.*;

public class StatusBar extends JPanel {

	private JLabel label;	
	private MainView mainView;
		
	public StatusBar(MainView view) {
		
		this.mainView = view;				
		this.label = new JLabel("");
		
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		this.add(this.label);
		
	}
	
	public String getText(){
		
		return this.label.getText();
		
	}
	public void setText(String newValue){
		
		this.label.setText(newValue);
		
	}
	public String getToolTipText(){
		
		return this.label.getToolTipText();
		
	}
	public void setToolTipText(String newValue){
		
		this.label.setToolTipText(newValue);
		
	}

}
