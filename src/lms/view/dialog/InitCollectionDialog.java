package lms.view.dialog;

import java.awt.*;

import javax.swing.*;

import lms.model.dialog.verifier.LengthInputVerifier;

@SuppressWarnings("serial")
public class InitCollectionDialog extends AbstractDialog {

	private JLabel lblCode;
	private JLabel lblName;
	private JTextField txtCode;
	private JTextField txtName;
	
	public InitCollectionDialog(JFrame frame, String collectionCode, String collectionName) {
		
		super(frame);
		
		setTitle("Init Collection");					
		setResizable(false);
		
		JPanel panel = new JPanel();
		lblCode = new JLabel("Collection Code:");
		lblName = new JLabel("Collection Name:");
		txtCode = new JTextField(collectionCode, AbstractDialog.DEFAULT_TEXTFIELD_COLUMNS);
		txtName = new JTextField(collectionName, AbstractDialog.DEFAULT_TEXTFIELD_COLUMNS);
		
		lblCode.setLabelFor(txtCode);
		lblName.setLabelFor(txtName);;
		
		txtCode.setInputVerifier(new LengthInputVerifier());
		txtName.setInputVerifier(new LengthInputVerifier());
		
		panel.setLayout(new GridLayout(2, 2, 10, 5));
		panel.add(lblCode);
		panel.add(txtCode);
		panel.add(lblName);
		panel.add(txtName);
								
		getContentPanel().add(panel);		
		display();
		
	}
	
	public String getCollectionCode(){
		
		return getCollectionCode(false);
		
	}	
	public String getCollectionName(){

		return getCollectionName(false);
		
	}
	public String getCollectionCode(boolean getLabel){
		
		return getLabel ? lblCode.getText().replace(":", "") : txtCode.getText();
		
	}	
	public String getCollectionName(boolean getLabel){

		return getLabel ? lblName.getText().replace(":", "") : txtName.getText();
		
	}
	
}
