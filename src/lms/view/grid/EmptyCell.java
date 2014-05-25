package lms.view.grid;

import java.awt.Color;

import lms.model.grid.visitor.Visitable;
import lms.model.grid.visitor.Visitor;

@SuppressWarnings("serial")
public class EmptyCell extends GridCell implements Visitable {

	public EmptyCell(){
		
		setBackground(Color.WHITE);
		
	}

	@Override
	public void accept(Visitor visitor) {
		
		visitor.visit(this);
		
	}
	
}
