package lms.view.dialog;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

@SuppressWarnings("serial")
public class RemoveHoldingsDialog extends AbstractDialog implements ListSelectionListener {

	private JList<String> list;
	
	public RemoveHoldingsDialog(JFrame frame, String holdingType, String[] holdingIds) {
		
		super(frame);
		
		setTitle(String.format("Remove %ss", holdingType));
		
		list = new JList<String>(holdingIds);
		list.setVisibleRowCount(20);
		list.addListSelectionListener(this);
		
		JScrollPane scroller = new JScrollPane(
				list, 
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, 
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED
		);		
		
		getContentPanel().add(scroller);
		display();
		
	}

	public int[] getSelectedHoldingIds(){
				
		String[] holdings = (String[]) list.getSelectedValuesList().toArray(new String[list.getSelectedValuesList().size()]);
		int[] ids = new int[holdings.length];
		
		for (int i = 0; i < holdings.length; i++)
			ids[i] = Integer.parseInt(holdings[i].substring(0, holdings[i].indexOf(':')));
		
		return ids;				
		
	}
	
	@Override
	public void checkComponents(){
	
		getOkButton().setEnabled(list.getSelectedIndices().length > 0);
		
	}

	// ListSelectionListener implementation.
	@Override
	public void valueChanged(ListSelectionEvent arg0) {

		checkComponents();
		
	}
	
}
