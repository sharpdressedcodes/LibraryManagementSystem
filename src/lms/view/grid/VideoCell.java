package lms.view.grid;

import java.awt.Color;
import javax.swing.BorderFactory;
import lms.model.Video;

@SuppressWarnings("serial")
public class VideoCell extends HoldingCell {

public VideoCell(Video model){

		super(model);
		
		setBorder(BorderFactory.createLineBorder(Color.RED, DEFAULT_BORDER_SIZE));
		
	}
	
}
