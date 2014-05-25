package lms.model.grid.listener;

public interface GridListener {

	public static enum GridState {
		uninitialised,
		empty,
		noBooks,
		noVideos,
		initialised
	}
	
	public void gridChanged(GridState state);
	
}
