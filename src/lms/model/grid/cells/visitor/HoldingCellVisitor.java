package lms.model.grid.cells.visitor;

import java.util.LinkedHashMap;
import lms.view.grid.cells.*;

public class HoldingCellVisitor implements Visitor {

	private LinkedHashMap<Integer, HoldingCell> holdingCells;
	private LinkedHashMap<Integer, EmptyCell> emptyCells;
	
	public HoldingCellVisitor() {
		
		holdingCells = new LinkedHashMap<Integer, HoldingCell>();
		emptyCells = new LinkedHashMap<Integer, EmptyCell>();
		
	}

	public HoldingCell[] getHoldingCells(){
		
		return this.holdingCells.values().toArray(new HoldingCell[this.holdingCells.size()]);
		
	}
	
	public EmptyCell[] getEmptyCells(){
		
		return this.emptyCells.values().toArray(new EmptyCell[this.emptyCells.size()]);
		
	}
	
	@Override
	public void visit(HoldingCell cell) {
		
		this.holdingCells.put(this.holdingCells.size(), cell);

	}
	
	@Override
	public void visit(EmptyCell cell) {
		
		this.emptyCells.put(this.emptyCells.size(), cell);

	}

}
