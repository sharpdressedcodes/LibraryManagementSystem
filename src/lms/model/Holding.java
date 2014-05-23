package lms.model;

import lms.model.visitor.Visitable;

/**
 * @author Greg Kappatos
 */

public interface Holding extends Visitable
{

   public int calculateLateFee();

   public String getBorrowDate();

   public int getCode();

   public int getDefaultLoanFee();

   public int getMaxLoanPeriod();

   public String getTitle();

   public boolean isOnLoan();

   public void setBorrowDate(String newDate);

   public String getType(); // custom
      
   public Object clone();

}
