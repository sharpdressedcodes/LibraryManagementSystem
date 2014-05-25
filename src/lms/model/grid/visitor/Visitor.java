package lms.model.grid.cells.visitor;

import lms.view.grid.cells.*;

public interface Visitor {

	public void visit(HoldingCell cell);
	public void visit(EmptyCell cell);
	
}
