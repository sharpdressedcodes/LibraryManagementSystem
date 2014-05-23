package lms.view.grid.cells;

import java.awt.Color;

import javax.swing.BorderFactory;

@SuppressWarnings("serial")
public class VideoCell extends HoldingCell {

public VideoCell(String holdingInfo){

		super(holdingInfo);
		this.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
		
	}
	
}
