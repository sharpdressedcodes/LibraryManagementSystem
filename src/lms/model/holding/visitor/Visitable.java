package lms.model.holding.visitor;

/**
 * @author Greg Kappatos
 * @date 25 May 2014
 * 
 */
public interface Visitable {

	public void accept(Visitor visitor);
	
}
