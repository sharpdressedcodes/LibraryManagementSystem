package lms.model.grid.cells.visitor;

import java.util.LinkedHashMap;
import lms.view.grid.cells.HoldingCell;

public class HoldingCellVisitor implements Visitor {

	private LinkedHashMap<Integer, HoldingCell> cells;
	
	public HoldingCellVisitor() {
		
		this.cells = new LinkedHashMap<Integer, HoldingCell>();		
		
	}

	public HoldingCell[] getCells(){
		
		return this.cells.entrySet().toArray(new HoldingCell[this.cells.size()]);
		
	}
	
	@Override
	public void visit(HoldingCell cell) {
		
		this.cells.put(this.cells.size(), cell);

	}

}
