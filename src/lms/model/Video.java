package lms.model;

/**
 * @author Greg Kappatos
 */

import lms.model.holding.visitor.Visitor;
import lms.model.util.DateUtil;

public class Video extends AbstractHolding
{

   public static final String TYPE = "VIDEO";
   public static final int MAX_LOAN_PERIOD = 7; // days

   public Video(int code, String title, int standardLoanFee)
   {

      super(code, title, standardLoanFee, Video.MAX_LOAN_PERIOD, Video.TYPE);

   }

   // /////////////////////////////////////////////////////////////////
   // AbstractHolding implementation /////////////////////////////////
   // /////////////////////////////////////////////////////////////////

   @Override
   public int calculateLateFee()
   {

      int lateFee = 0;
      int daysLate =
               DateUtil.getInstance().getElapsedDays(this.getBorrowDate())
                        - MAX_LOAN_PERIOD;

      // Don't bother calculating the lateFee is the item isn't late.
      if (daysLate > 0)
         lateFee = daysLate * (this.getDefaultLoanFee() >> 1);

      return lateFee;

   }

   // /////////////////////////////////////////////////////////////////
   // Visitor ////////////////////////////////////////////////////////
   // /////////////////////////////////////////////////////////////////
   @Override
   public void accept(Visitor visitor){
  	 
  	 visitor.visit(this);
  	 
   }
   
   // /////////////////////////////////////////////////////////////////
   // Object /////////////////////////////////////////////////////////
   // /////////////////////////////////////////////////////////////////

   @Override
   public Object clone(){
  	   	 
  	 return new Video(this.getCode(), this.getTitle(), this.getDefaultLoanFee());
  	 
   }
   
   @Override
   public String toString()
   {

      return String.format("%s:%s:%s", super.toString(),
                           this.getMaxLoanPeriod(),
                           this.getType());

   }

}
