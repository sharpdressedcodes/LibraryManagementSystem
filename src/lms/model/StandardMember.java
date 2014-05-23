package lms.model;

/**
 * @author Greg Kappatos
 */

import lms.model.exception.OverdrawnCreditException;

public class StandardMember extends AbstractMember
{

   public static final String TYPE = "STANDARD";
   public static final int INITIAL_CREDIT = 30; // dollars

   public StandardMember(String id, String fullName)
   {

      super(id, fullName, StandardMember.INITIAL_CREDIT, StandardMember.TYPE);

   }

   // /////////////////////////////////////////////////////////////////
   // AbstractMember implementation //////////////////////////////////
   // /////////////////////////////////////////////////////////////////

   @Override
   public void returnHolding(Holding holding) throws OverdrawnCreditException
   {

      int fee = holding.calculateLateFee();
      int credit = this.getRemainingCredit();

      // Do they have enough credit?
      if (credit < fee)
         throw new OverdrawnCreditException();
      
      // At this point all is OK to return the item.
      else
      	super.returnHolding(holding);

   }

   // /////////////////////////////////////////////////////////////////
   // Object /////////////////////////////////////////////////////////
   // /////////////////////////////////////////////////////////////////

   @Override
   public String toString()
   {

      return String.format("%s:%s", super.toString(), this.getType());

   }

}
