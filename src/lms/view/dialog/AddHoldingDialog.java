package lms.view.dialog;

import java.awt.GridLayout;
import java.text.ParseException;

import javax.swing.*;
import javax.swing.text.*;

import lms.model.dialog.verifier.LengthInputVerifier;

@SuppressWarnings("serial")
public class AddHoldingDialog extends AbstractDialog {

	//private String type;
	private JLabel lblId;
	private JLabel lblTitle;
	private JLabel lblFee;
	//private JTextField txtId;
	private JFormattedTextField txtId;
	private JTextField txtTitle;
	private JComboBox<String> cboFee;
	
	public AddHoldingDialog(JFrame frame, String holdingType){
		
		this(frame, holdingType, null);
		
	}
	
	public AddHoldingDialog(JFrame frame, String holdingType, String[] holdingFees) {
		
		super(frame);
		
		//this.type = type;
		
		setTitle("Add " + holdingType);
		setResizable(false);
		
		lblId = new JLabel(holdingType + " Id:");
		lblTitle = new JLabel(holdingType + " Title:");
		lblFee = new JLabel(holdingType + " Fee: $");
		
		txtId = new JFormattedTextField(createFormatter("#######"));
		txtId.setColumns(AbstractDialog.DEFAULT_TEXTFIELD_COLUMNS);
		txtTitle = new JTextField("", AbstractDialog.DEFAULT_TEXTFIELD_COLUMNS);
		cboFee = holdingFees == null ? new JComboBox<String>() : new JComboBox<String>(holdingFees);			
		
		txtId.setInputVerifier(new LengthInputVerifier(7, 7));
		txtTitle.setInputVerifier(new LengthInputVerifier(3, 0));
		
		lblId.setLabelFor(txtId);
		lblTitle.setLabelFor(lblTitle);
		lblFee.setLabelFor(cboFee);
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(0, 2, 10, 5));
		
		panel.add(lblId);
		panel.add(txtId);
		panel.add(lblTitle);
		panel.add(txtTitle);
		
		if (holdingFees != null){
			panel.add(lblFee);
			panel.add(cboFee);
		}
														
		getContentPanel().add(panel);		
		display();
		
	}
	
	public String getHoldingId(){
		
		return getHoldingId(false);
		
	}
	
	public String getHoldingTitle(){
		
		return getHoldingTitle(false);
		
	}
	
	public String getHoldingFee(){
		
		return getHoldingFee(false);
		
	}
	
	public String getHoldingId(boolean getLabel){
		
		return getLabel ? lblId.getText().replace(":", "") : txtId.getText().trim();
		
	}
	
	public String getHoldingTitle(boolean getLabel){
		
		return getLabel ? lblTitle.getText().replace(":", "") : txtTitle.getText();
		
	}
	
	public String getHoldingFee(boolean getLabel){
		
		return getLabel ? lblFee.getText().replace(":", "") : cboFee.getItemAt(cboFee.getSelectedIndex());
		
	}

	protected MaskFormatter createFormatter(String s){
		
		MaskFormatter formatter = null;
		
		try {
			formatter = new MaskFormatter(s);			
		} catch (ParseException ex){}
		
		return formatter;
		
	}
	
}
