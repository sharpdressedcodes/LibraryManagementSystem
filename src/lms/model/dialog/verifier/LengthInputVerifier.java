package lms.model.dialog.verifier;

import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JTextField;

/**
 * @author Greg Kappatos
 * @date 25 May 2014
 * 
 */
public class LengthInputVerifier extends InputVerifier {

	private int minLength;
	private int maxLength;
	
 	public LengthInputVerifier() {
		
		this(1, 0);
		
	}

	public LengthInputVerifier(int maxLength) {
		
		this(1, maxLength);
	}
	
	public LengthInputVerifier(int minLength, int maxLength) {
		
		this.minLength = minLength;
		this.maxLength = maxLength;
		
	}

	// InputVerifier implementation.
	@Override
	public boolean verify(JComponent component) {
					
		// Is the result in the specified limits?
		// Return true if so, false otherwise.
		
		JTextField txt = (JTextField)component;
		int length = txt.getText().length();					
		boolean result = length >= minLength && (maxLength == 0 ? true : length <= maxLength);
		
		return result;
				
	}

}
