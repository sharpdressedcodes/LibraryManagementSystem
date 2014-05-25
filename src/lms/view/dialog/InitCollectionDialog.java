package lms.view.dialog;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.event.DocumentListener;
import lms.model.dialog.verifier.LengthInputVerifier;

/**
 * @author Greg Kappatos
 * @date 25 May 2014
 * 
 */
@SuppressWarnings("serial")
public class InitCollectionDialog extends FieldsDialog {

	private JLabel lblCode;
	private JLabel lblName;
	private JTextField txtCode;
	private JTextField txtName;
	
	public InitCollectionDialog(JFrame frame, String collectionCode, String collectionName) {
		
		super(frame);
		
		setTitle("Init Collection");					

		// Create components.
		lblCode = new JLabel("Collection Code:");
		lblName = new JLabel("Collection Name:");
		txtCode = new JTextField(collectionCode, AbstractDialog.DEFAULT_TEXTFIELD_COLUMNS);
		txtName = new JTextField(collectionName, AbstractDialog.DEFAULT_TEXTFIELD_COLUMNS);
		
		// Set the custom input verifiers.
		txtCode.setInputVerifier(new LengthInputVerifier());
		txtName.setInputVerifier(new LengthInputVerifier());
		
		// Get document listener.
		DocumentListener listener = createDocumentListener();
		
		// Add the listener to the text field's document.
		txtCode.getDocument().addDocumentListener(listener);
		txtName.getDocument().addDocumentListener(listener);
		
		// Add the label and text field (with suggestions).
		addField(lblCode, txtCode, new JLabel("All chars allowed. Min: 1"));
		addField(lblName, txtName, new JLabel("All chars allowed. Min: 1"));
	
		display();
		
	}
	
	@Override
	public void checkComponents(){
		
		// This method is called by the superclass AbstractDialog
		// when activity happens within the selected text fields.
		
		getOkButton().setEnabled(
			txtCode.getInputVerifier().verify(txtCode) && 
			txtName.getInputVerifier().verify(txtName)
		);
		
	}
	
	public String getCollectionCode(){
		
		return getCollectionCode(false);
		
	}	
	public String getCollectionName(){

		return getCollectionName(false);
		
	}
	public String getCollectionCode(boolean getLabel){
		
		// Either return the label minus the : at the end, or return the text value.
		return getLabel ? lblCode.getText().replace(":", "") : txtCode.getText();
		
	}	
	public String getCollectionName(boolean getLabel){

		// Either return the label minus the : at the end, or return the text value.
		return getLabel ? lblName.getText().replace(":", "") : txtName.getText();
		
	}
	
}
