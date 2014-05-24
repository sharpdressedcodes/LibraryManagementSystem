package lms.view.dialog;

import java.text.ParseException;

import javax.swing.*;
import javax.swing.event.DocumentListener;
import javax.swing.text.*;

import lms.model.dialog.verifier.*;

@SuppressWarnings("serial")
public class AddHoldingDialog extends FieldsDialog {

	private JLabel lblId;
	private JLabel lblTitle;
	private JLabel lblFee;
	private JFormattedTextField txtId;
	private JTextField txtTitle;
	private JComboBox<String> cboFee;
	
	public static final char NUMERIC_PLACEHOLDER = '_';
	
	public AddHoldingDialog(JFrame frame, String holdingType){
		
		this(frame, holdingType, null);
		
	}
	
	public AddHoldingDialog(JFrame frame, String holdingType, String[] holdingFees) {
		
		super(frame);
		
		setTitle("Add " + holdingType);
		
		lblId = new JLabel(holdingType + " Id:");
		lblTitle = new JLabel(holdingType + " Title:");
		lblFee = new JLabel(holdingType + " Fee:");		
		txtId = new JFormattedTextField(createFormatter("#######"));	
		
		txtTitle = new JTextField("", AbstractDialog.DEFAULT_TEXTFIELD_COLUMNS);
		cboFee = holdingFees == null ? new JComboBox<String>() : new JComboBox<String>(holdingFees);			
		
		txtId.setColumns(AbstractDialog.DEFAULT_TEXTFIELD_COLUMNS);
		cboFee.setAlignmentX(RIGHT_ALIGNMENT);
		
		//txtId.setInputVerifier(new LengthInputVerifier(7, 7));
		txtId.setInputVerifier(new NumericInputVerifier());
		txtTitle.setInputVerifier(new LengthInputVerifier(3, 0));
		
		DocumentListener listener = createDocumentListener();
		
		txtId.getDocument().addDocumentListener(listener);
		txtTitle.getDocument().addDocumentListener(listener);
		
		addField(lblId, txtId, new JLabel("7 digits only. Eg: 1234567"));
		addField(lblTitle, txtTitle, new JLabel("All chars allowed. Min: 3"));
		
		if (holdingFees != null)
			addField(lblFee, cboFee, new JLabel("Australian Dollars (AUD)"));
	
		display();
		
	}
	
	@Override
	public void checkComponents(){
				
		getOkButton().setEnabled(
				txtId.getInputVerifier().verify(txtId) && 
				txtTitle.getInputVerifier().verify(txtTitle)
		);
		
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
			formatter.setPlaceholderCharacter(NUMERIC_PLACEHOLDER);
			//formatter.setValueClass(String.class);
			
		} catch (ParseException ex){}
		
		return formatter;
		
	}
	
}
