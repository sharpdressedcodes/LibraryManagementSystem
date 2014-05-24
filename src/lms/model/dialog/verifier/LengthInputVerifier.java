package lms.model.dialog.verifier;

//import java.awt.Color;
import javax.swing.*;

public class LengthInputVerifier extends InputVerifier {

	private int minLength;
	private int maxLength;
	
	//public static final String ERROR_COLOUR = "#ffaaaa";
	
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
					
		JTextField txt = (JTextField)component;
		int length = txt.getText().length();					
		boolean result = length >= minLength && (maxLength == 0 ? true : length <= maxLength);

		//txt.setBackground(result ? UIManager.getColor("TextField.background") : Color.decode(ERROR_COLOUR));
		
		return result;
				
	}

}
