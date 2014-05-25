package lms.model.grid.visitor;

import java.util.LinkedHashMap;
import java.util.Map;

import lms.view.grid.EmptyCell;
import lms.view.grid.HoldingCell;

/**
 * @author Greg Kappatos
 * @date 25 May 2014
 * 
 */
public class HoldingCellVisitor implements Visitor {

	// Simple class to separate the EmptyCells and the HoldingCells.
	
	private Map<Integer, HoldingCell> holdingCells;
	private Map<Integer, EmptyCell> emptyCells;
	
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
