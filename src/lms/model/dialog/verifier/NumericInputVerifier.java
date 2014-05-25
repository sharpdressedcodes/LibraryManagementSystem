package lms.model.dialog.verifier;

import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JTextField;

/**
 * @author Greg Kappatos
 * @date 25 May 2014
 * 
 */
public class NumericInputVerifier extends InputVerifier {
	
	public NumericInputVerifier() {}

	@Override
	public boolean verify(JComponent component) {

		// Simple check that converts the String to a charArray
		// and then loops through each character and confirms
		// it is a number.
		
		JTextField txt = (JTextField)component;
		char[] chars = ((String)txt.getText()).trim().toCharArray();
		boolean result = true;
		
		for (char c : chars){
			if (!Character.isDigit(c)){
				result = false;
				break;
			}
		}
		
		return result;
		
	}

}
