package lms.view.dialog;

import java.text.ParseException;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.event.DocumentListener;
import javax.swing.text.MaskFormatter;
import lms.model.dialog.verifier.LengthInputVerifier;
import lms.model.dialog.verifier.NumericInputVerifier;

/**
 * @author Greg Kappatos
 * @date 25 May 2014
 * 
 */
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
		// Mask the input for 7 digits.
		txtId = new JFormattedTextField(createFormatter("#######"));	
		
		txtTitle = new JTextField("", AbstractDialog.DEFAULT_TEXTFIELD_COLUMNS);
		// Only add the items to the comboBox if they are not null.
		cboFee = holdingFees == null ? new JComboBox<String>() : new JComboBox<String>(holdingFees);			
		
		// Set the width because we can't set it in constructor like JTextField.
		txtId.setColumns(AbstractDialog.DEFAULT_TEXTFIELD_COLUMNS);
		
		// Set the custom input verifiers.
		txtId.setInputVerifier(new NumericInputVerifier());
		txtTitle.setInputVerifier(new LengthInputVerifier(3, 0));
		
		// Get document listener.
		DocumentListener listener = createDocumentListener();
		
		// Add the listener to the text field's document.
		txtId.getDocument().addDocumentListener(listener);
		txtTitle.getDocument().addDocumentListener(listener);
		
		// Add the label and text field (with suggestions).
		addField(lblId, txtId, new JLabel("7 digits only. Eg: 1234567"));
		addField(lblTitle, txtTitle, new JLabel("All chars allowed. Min: 3"));
		
		// If this holding has multiple selections for fees, show it.
		if (holdingFees != null)
			addField(lblFee, cboFee, new JLabel("Australian Dollars (AUD)"));
	
		display();
		
	}
	
	@Override
	public void checkComponents(){
	
		// This method is called by the superclass AbstractDialog
		// when activity happens within the selected text fields.
		
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
		
		// Either return the label minus the : at the end, or return the text value.
		return getLabel ? lblId.getText().replace(":", "") : txtId.getText().trim();
		
	}
	
	public String getHoldingTitle(boolean getLabel){
		
		// Either return the label minus the : at the end, or return the text value.
		return getLabel ? lblTitle.getText().replace(":", "") : txtTitle.getText();
		
	}
	
	public String getHoldingFee(boolean getLabel){
		
		// Either return the label minus the : at the end, or return the text value.
		return getLabel ? lblFee.getText().replace(":", "") : cboFee.getItemAt(cboFee.getSelectedIndex());
		
	}

	protected MaskFormatter createFormatter(String s){
		
		MaskFormatter formatter = null;
		
		// Try to create formatter, based on supplied mask.
		try {
			
			formatter = new MaskFormatter(s);	
			formatter.setPlaceholderCharacter(NUMERIC_PLACEHOLDER);
			
		} catch (ParseException ex){}
		
		return formatter;
		
	}
	
}
