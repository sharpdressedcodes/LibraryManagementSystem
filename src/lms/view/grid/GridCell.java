package lms.view.grid;

import javax.swing.JPanel;
import lms.model.grid.visitor.Visitable;
import lms.model.grid.visitor.Visitor;

/**
 * @author Greg Kappatos
 * @date 25 May 2014
 * 
 */
@SuppressWarnings("serial")
public abstract class GridCell extends JPanel implements Visitable {
	
	public GridCell(){}

	// Visitable implementation.
	@Override
	public abstract void accept(Visitor visitor);
	
}
