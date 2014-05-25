package lms.view.grid;

import java.awt.Color;
import lms.model.grid.visitor.Visitable;
import lms.model.grid.visitor.Visitor;

/**
 * @author Greg Kappatos
 * @date 25 May 2014
 * 
 */
@SuppressWarnings("serial")
public class EmptyCell extends GridCell implements Visitable {

	public EmptyCell(){
		
		// Set default background colour.
		setBackground(Color.WHITE);
		
	}

	// Visitable implementation.
	@Override
	public void accept(Visitor visitor) {
		
		visitor.visit(this);
		
	}
	
}
