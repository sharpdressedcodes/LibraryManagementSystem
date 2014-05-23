package lms.view.dialog;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

@SuppressWarnings("serial")
public class RemoveHoldingsDialog extends AbstractDialog {

	private JList<String> list;
	
	public RemoveHoldingsDialog(JFrame frame, String[] holdingIds) {
		
		super(frame);
		
		setTitle("Remove Holdings");
		
		list = new JList<String>(holdingIds);
		list.setVisibleRowCount(20);
		
		JScrollPane scroller = new JScrollPane(list, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);		
		JPanel contentPanel = new JPanel();
		//contentPanel.setLayout(new GridLayout(0,1));
		//contentPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 0, 5));
		
		contentPanel.add(scroller);
		
		show(contentPanel);
		
	}

	public int[] getSelectedHoldingIds(){
		
		int[] ids = new int[list.getSelectedValuesList().size()];
		String[] holdings = (String[]) list.getSelectedValuesList().toArray(new String[list.getSelectedValuesList().size()]);
		
		for (int i = 0; i < holdings.length; i++)
			ids[i] = Integer.parseInt(holdings[i].substring(0, holdings[i].indexOf(':')));
		
		return ids;				
		
	}
	
}
