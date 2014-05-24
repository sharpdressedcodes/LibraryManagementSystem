package lms.model.dialog.verifier;

import javax.swing.*;

public class NumericInputVerifier extends InputVerifier {
	
	public NumericInputVerifier() {}

	@Override
	public boolean verify(JComponent component) {

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
