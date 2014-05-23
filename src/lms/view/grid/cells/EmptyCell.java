package lms.view.grid.cells;

import java.awt.Color;
import lms.model.grid.cells.visitor.Visitable;
import lms.model.grid.cells.visitor.Visitor;

@SuppressWarnings("serial")
public class EmptyCell extends GridCell implements Visitable {

	public EmptyCell(){
		
		this.setBackground(Color.WHITE);
		
	}

	@Override
	public void accept(Visitor visitor) {
		
		visitor.visit(this);
		
	}
	
}
