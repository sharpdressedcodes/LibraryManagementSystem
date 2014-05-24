package lms.view.dialog;

import java.awt.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class FieldsDialog extends AbstractDialog {

	private JPanel panel;
	private GridBagConstraints labelConstraints;
	private GridBagConstraints componentConstraints;
	
	public FieldsDialog(JFrame frame) {
		
		super(frame);				
					
		panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		
		componentConstraints = new GridBagConstraints();
		componentConstraints.anchor = GridBagConstraints.NORTHWEST;
		componentConstraints.fill = GridBagConstraints.HORIZONTAL;				
		componentConstraints.gridwidth = GridBagConstraints.REMAINDER;
		componentConstraints.insets = new Insets(1, 1, 1, 1);
		componentConstraints.weightx = 1.0;
		
		labelConstraints = (GridBagConstraints)componentConstraints.clone();
		labelConstraints.anchor = GridBagConstraints.CENTER;
		labelConstraints.gridwidth = 1;
		componentConstraints.insets = new Insets(1, 10, 5, 1);
		labelConstraints.weightx = 0.0;		
		
		getContentPanel().setLayout(new BorderLayout());
		getContentPanel().add(panel, BorderLayout.NORTH);
		
	}
	
	protected void addField(JLabel label, JComponent component){
		
		GridBagLayout layout = (GridBagLayout)panel.getLayout();
		
		label.setLabelFor(component);
		
		layout.setConstraints(label, labelConstraints);
		panel.add(label);
		
		layout.setConstraints(component, componentConstraints);
		panel.add(component);
		
	}

}
