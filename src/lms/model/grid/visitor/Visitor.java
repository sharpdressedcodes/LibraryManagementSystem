package lms.model.grid.visitor;

import lms.view.grid.*;

/**
 * @author Greg Kappatos
 * @date 25 May 2014
 * 
 */
public interface Visitor {

	public void visit(HoldingCell cell);
	public void visit(EmptyCell cell);
	
}
