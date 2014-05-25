package lms.view.grid;

import javax.swing.JPanel;

import lms.model.grid.visitor.Visitable;
import lms.model.grid.visitor.Visitor;

@SuppressWarnings("serial")
public abstract class GridCell extends JPanel implements Visitable {
	
	public GridCell(){}

	@Override
	public abstract void accept(Visitor visitor);
	
}
