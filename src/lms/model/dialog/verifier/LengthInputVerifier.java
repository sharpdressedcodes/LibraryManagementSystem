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
		
		int length = 0;
		
//		if (component.getClass().getSimpleName().equals("JFormattedTextField")){
//			
//			JFormattedTextField txt = (JFormattedTextField)component;
//			length = txt.getText().length();
//			
//		} else if (component.getClass().getSimpleName().equals("JTextField")){
				
			JTextField txt = (JTextField)component;
			length = txt.getText().length();
			
		//}
		
		boolean result = length >= minLength && (maxLength == 0 ? true : length <= maxLength);
		
		//txt.setBackground(result ? UIManager.getColor("TextField.background") : Color.decode(ERROR_COLOUR));
		
		return result;
				
	}

}
