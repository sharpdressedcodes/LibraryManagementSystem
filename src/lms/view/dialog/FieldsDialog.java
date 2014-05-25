package lms.view.dialog;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @author Greg Kappatos
 * @date 25 May 2014
 * 
 */
@SuppressWarnings("serial")
public class FieldsDialog extends AbstractDialog {

	private JPanel panel;
	private GridBagConstraints labelConstraints;
	private GridBagConstraints componentConstraints;
	
	public FieldsDialog(JFrame frame) {
		
		super(frame);				
					
		panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		
		// Setup right side constraints. This is where the text fields will go.
		componentConstraints = new GridBagConstraints();
		componentConstraints.anchor = GridBagConstraints.NORTHWEST;
		componentConstraints.fill = GridBagConstraints.HORIZONTAL;				
		componentConstraints.gridwidth = GridBagConstraints.REMAINDER;
		componentConstraints.insets = new Insets(1, 1, 1, 1);
		componentConstraints.weightx = 1.0;
		
		// Setup left side constraints. This is where the labels will go.
		labelConstraints = (GridBagConstraints)componentConstraints.clone();
		labelConstraints.anchor = GridBagConstraints.CENTER;
		labelConstraints.gridwidth = 1;
		componentConstraints.insets = new Insets(1, 10, 5, 1);
		labelConstraints.weightx = 0.0;		
		
		getContentPanel().setLayout(new BorderLayout());
		getContentPanel().add(panel, BorderLayout.NORTH);
		
	}
	
	protected void addField(JLabel label, JComponent component){
		
		addField(label, component, null);
		
	}

	protected void addField(JLabel label, JComponent component, JLabel suggestion){
		
		GridBagLayout layout = (GridBagLayout)panel.getLayout();
		
		// Associate this label with the component.
		label.setLabelFor(component);
		
		// If there is no suggestion supplied, just put the label and be done with it.
		if (suggestion == null){
			
			layout.setConstraints(label, labelConstraints);
			panel.add(label);
			
		// Otherwise, create a new panel, and size the
		// suggestion to be a bit smaller than main label.
		} else {
			
			JPanel labelPanel = new JPanel();
			labelPanel.setLayout(new BoxLayout(labelPanel, BoxLayout.Y_AXIS));
			labelPanel.add(label);
			labelPanel.add(suggestion);
						
			// Copy font from main label, and just style it and shrink it.
			Font font = label.getFont();
			suggestion.setFont(font.deriveFont(Font.ITALIC, font.getSize() - 2));
			
			int anchor = labelConstraints.anchor;
			labelConstraints.anchor = GridBagConstraints.NORTH;
			
			layout.setConstraints(labelPanel, labelConstraints);
			panel.add(labelPanel);
			
			labelConstraints.anchor = anchor;
			
		}
		
		layout.setConstraints(component, componentConstraints);
		panel.add(component);
		
	}

}
