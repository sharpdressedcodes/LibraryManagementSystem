package lms.model.grid.listener;

/**
 * @author Greg Kappatos
 * @date 25 May 2014
 * 
 */
public interface GridListener {

	// Simple interface that notifies listeners when the grid state changes.
	
	public static enum GridState {
		uninitialised, 		// No items, no collection - nothing.
		empty,				// Collection has been created.
		noBooks, 			// No books.
		noVideos, 			// No videos.
		initialised 		// Fully operational.
	}
	
	// Interested parties can listen for this event.
	public void gridChanged(GridState state);
	
}
