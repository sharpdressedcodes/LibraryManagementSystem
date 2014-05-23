package lms.view.grid.cells;

import java.awt.*;
import java.util.Comparator;

import javax.swing.*;

import lms.model.grid.cells.visitor.Visitable;
import lms.model.grid.cells.visitor.Visitor;

@SuppressWarnings("serial")
public abstract class HoldingCell extends GridCell implements Visitable {

	private String holdingInfo;
	private JLabel lbl;
	private String holdingId;
	private String holdingType;
		
	public final String DEFAULT_FORMAT = "<html>Holding ID: %s<br><br>Title: %s<br><br>Standard Loan Fee: %s<br><br>Loan Period: %s</html>";
	
	public HoldingCell(String holdingInfo){
		
		this.setBackground(Color.GRAY);
		
		this.holdingInfo = this.parseHoldingInfo(holdingInfo);
		this.lbl = new JLabel(this.holdingInfo);
		
		this.setLayout(new BorderLayout());
		
		JScrollPane scroller = new JScrollPane(this.lbl, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);		
		scroller.getViewport().setBackground(Color.GRAY);
		scroller.setBorder(null);
		
		this.add(scroller);
		
	}
	
	public JLabel getLabel(){
		
		return this.lbl;
		
	}
	public String getHoldingId(){
		
		return this.holdingId;
		
	}
	public String getHoldingType() {

		return this.holdingType;

	}
	
	public static Comparator<HoldingCell> CodeComparator = new Comparator<HoldingCell>(){

		@Override
		public int compare(HoldingCell cell1, HoldingCell cell2) {

			String code1 = cell1.getHoldingId().toLowerCase();
			String code2 = cell2.getHoldingId().toLowerCase();
			
			// ascending order
			return code1.compareTo(code2);
			
		}
					
	};
	
	public static Comparator<HoldingCell> TypeComparator = new Comparator<HoldingCell>(){

		@Override
		public int compare(HoldingCell cell1, HoldingCell cell2) {

			String code1 = cell1.getHoldingType().toLowerCase();
			String code2 = cell2.getHoldingType().toLowerCase();
			
			// ascending order
			return code1.compareTo(code2);
			
		}
					
	};
	
	private String parseHoldingInfo(String holdingInfo){
		
		String holdingId, holdingTitle, holdingFee, holdingPeriod;
		
		// Have to parse it this way in case the Holding Title has : in it
		// Strip off start
		int pos = holdingInfo.indexOf(":");
		holdingId = holdingInfo.substring(0, pos);
		holdingInfo = holdingInfo.substring(pos + 1);
		
		// Then work from the end, trimming off each part as we go.
		pos = holdingInfo.lastIndexOf(":");
		this.holdingType = holdingInfo.substring(pos + 1);
		holdingInfo = holdingInfo.substring(0, pos);
		
		pos = holdingInfo.lastIndexOf(":");
		holdingPeriod = holdingInfo.substring(pos + 1);
		holdingInfo = holdingInfo.substring(0, pos);
		
		pos = holdingInfo.lastIndexOf(":");
		holdingFee = holdingInfo.substring(pos + 1);
		holdingTitle = holdingInfo.substring(0, pos);
		
		this.holdingId = holdingId;
		
		return String.format(
				this.DEFAULT_FORMAT, 
				holdingId, holdingTitle, holdingFee, holdingPeriod
		);
		
	}

	@Override
	public void accept(Visitor visitor) {
		
		visitor.visit(this);
		
	}
	
}
