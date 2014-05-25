package lms.model;

import lms.model.holding.visitor.Visitor;

/**
 * @author Greg Kappatos
 */

public abstract class AbstractHolding implements Holding {

	private int code;
	private String title;
	private int maxLoanPeriod;
	private String borrowDate;
	private int standardLoanFee;
	private String type;

	public AbstractHolding(int code, String title, int standardLoanFee,
			int maxLoadPeriod, String type) {

		this.code = code;
		this.title = title;
		this.standardLoanFee = standardLoanFee;
		this.maxLoanPeriod = maxLoadPeriod;
		this.type = type;

	}

	// /////////////////////////////////////////////////////////////////
	// Holding implementation /////////////////////////////////////////
	// /////////////////////////////////////////////////////////////////

	@Override
	public abstract int calculateLateFee();

	@Override
	public String getType() {

		return this.type;

	}

	@Override
	public String getBorrowDate() {

		return this.borrowDate;

	}

	@Override
	public int getCode() {

		return this.code;

	}

	@Override
	public int getDefaultLoanFee() {

		return this.standardLoanFee;

	}

	@Override
	public int getMaxLoanPeriod() {

		return this.maxLoanPeriod;

	}

	@Override
	public String getTitle() {

		return this.title;

	}

	@Override
	public boolean isOnLoan() {

		// Use the date to determine if this Holding is on loan.
		return this.borrowDate != null;

	}

	@Override
	public void setBorrowDate(String newDate) {

		this.borrowDate = newDate;

	}

	// /////////////////////////////////////////////////////////////////
	// Visitor ////////////////////////////////////////////////////////
	// /////////////////////////////////////////////////////////////////
	@Override
	public abstract void accept(Visitor visitor);

	// /////////////////////////////////////////////////////////////////
	// Object /////////////////////////////////////////////////////////
	// /////////////////////////////////////////////////////////////////

	@Override
	public abstract Object clone();

	@Override
	public String toString() {

		return String.format("%s:%s:%s", this.code, this.title,
				this.standardLoanFee);

	}

}
