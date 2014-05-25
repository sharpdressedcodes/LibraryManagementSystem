package lms.view.grid;

import java.awt.*;
import java.util.*;
import javax.swing.*;
import lms.model.Holding;
import lms.model.grid.visitor.*;

@SuppressWarnings("serial")
public abstract class HoldingCell extends GridCell implements Visitable {

	private JLabel lbl;
	private Holding model;
		
	public static final int DEFAULT_BORDER_SIZE = 3;
	public static final String DEFAULT_FORMAT = "<html>Holding ID: %s<br><br>Title: %s<br><br>Standard Loan Fee: %s<br><br>Loan Period: %s</html>";
	
	public HoldingCell(Holding model){
		
		this.model = model;	
		
		lbl = new JLabel(createInfoFromModel());
		lbl.setBorder(BorderFactory.createEmptyBorder(1, 5, 1, 5));				
		
		JScrollPane scroller = new JScrollPane(
				lbl, 
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, 
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED
		);		
		scroller.getViewport().setBackground(Color.GRAY);
		scroller.setBorder(null);
		
		setBackground(Color.GRAY);
		setLayout(new BorderLayout());
		
		add(scroller);
		
	}
	
	public JLabel getLabel(){
		
		return lbl;
		
	}
	
	public Holding getModel(){
		
		return model;
		
	}
	
	public static Comparator<HoldingCell> CodeComparator = new Comparator<HoldingCell>(){

		@Override
		public int compare(HoldingCell cell1, HoldingCell cell2) {


			Integer code1 = cell1.getModel().getCode();
			Integer code2 = cell2.getModel().getCode();
			
			// ascending order
			return code1.compareTo(code2);
			
		}
					
	};
	
	public static Comparator<HoldingCell> TypeComparator = new Comparator<HoldingCell>(){

		@Override
		public int compare(HoldingCell cell1, HoldingCell cell2) {

			String code1 = cell1.getModel().getType().toLowerCase();
			String code2 = cell2.getModel().getType().toLowerCase();
			
			// ascending order
			return code1.compareTo(code2);
			
		}
					
	};
	
	private String createInfoFromModel(){
		
		return String.format(
				DEFAULT_FORMAT, 
				model.getCode(), 
				model.getTitle(), 
				model.getDefaultLoanFee(), 
				model.getMaxLoanPeriod()
		);
		
	}

	// Visitable implementation.
	@Override
	public void accept(Visitor visitor) {
		
		visitor.visit(this);
		
	}
	
}
