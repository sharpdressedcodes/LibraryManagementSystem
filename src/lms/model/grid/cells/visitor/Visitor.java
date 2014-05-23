package lms.model.grid.cells.visitor;

import lms.view.grid.cells.HoldingCell;

public interface Visitor {

	public void visit(HoldingCell cell);
	
}
