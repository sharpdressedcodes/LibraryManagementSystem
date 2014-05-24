package lms.view.dialog;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;

@SuppressWarnings("serial")
public class RemoveHoldingsDialog extends AbstractDialog {

	private JList<String> list;
	
	public RemoveHoldingsDialog(JFrame frame, String holdingType, String[] holdingIds) {
		
		super(frame);
		
		setTitle(String.format("Remove %ss", holdingType));
		
		list = new JList<String>(holdingIds);
		list.setVisibleRowCount(20);
		
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
	
}
