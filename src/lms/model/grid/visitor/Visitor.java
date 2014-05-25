package lms.model.grid.visitor;

import lms.view.grid.*;

public interface Visitor {

	public void visit(HoldingCell cell);
	public void visit(EmptyCell cell);
	
}
