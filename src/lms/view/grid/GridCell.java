package lms.view.grid.cells;

import javax.swing.JPanel;

import lms.model.grid.cells.visitor.Visitable;
import lms.model.grid.cells.visitor.Visitor;

@SuppressWarnings("serial")
public abstract class GridCell extends JPanel implements Visitable {
	
	public GridCell(){}

	@Override
	public abstract void accept(Visitor visitor);
	
}
