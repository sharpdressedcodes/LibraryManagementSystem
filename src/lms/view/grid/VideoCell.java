package lms.view.grid;

import java.awt.Color;
import javax.swing.BorderFactory;
import lms.model.Video;

/**
 * @author Greg Kappatos
 * @date 25 May 2014
 * 
 */
@SuppressWarnings("serial")
public class VideoCell extends HoldingCell {

public VideoCell(Video model){

		super(model);
		
		// Set default border.
		setBorder(BorderFactory.createLineBorder(Color.RED, DEFAULT_BORDER_SIZE));
		
	}
	
}
