package lms.model;

/**
 * @author Greg Kappatos
 */

import java.util.*;

import lms.model.exception.*;
import lms.model.util.DateUtil;

public abstract class AbstractMember implements Member
{

   private String id;
   private String fullName;
   private int remainingCredit;
   private String type;
   private int maxCredit;

   protected BorrowingHistory history;
   protected Map<Integer, Holding> currentLoans;

   public AbstractMember(String id, String fullName, int remainingCredit,
                         String type)
   {

      this.id = id;
      this.fullName = fullName;
      this.remainingCredit = this.maxCredit = remainingCredit;
      this.type = type;
      this.history = new BorrowingHistory();
      this.currentLoans = new HashMap<Integer, Holding>();

   }

   // /////////////////////////////////////////////////////////////////
   // Borrower implementation ////////////////////////////////////////
   // /////////////////////////////////////////////////////////////////

   @Override
   public void borrowHolding(Holding holding)
            throws InsufficientCreditException, MultipleBorrowingException
   {

      int fee = holding.getDefaultLoanFee();

      // Is this Holding already on loan?
      // Or has this Holding already been borrowed?
      if (this.currentLoans.containsKey(holding.getCode())
          || this.history.getHistoryRecord(holding.getCode()) != null)
         throw new MultipleBorrowingException();

      // Can the member afford to borrow this Holding?
      else if (this.getRemainingCredit() < fee)
         throw new InsufficientCreditException();

      // All clear, go ahead and borrow.
      this.calculateRemainingCredit(fee);
      // Setting date to not null puts Holding into 'on loan' state.
      holding.setBorrowDate(DateUtil.getInstance().getDate());
      this.currentLoans.put(holding.getCode(), holding);

   }

   public abstract void returnHolding(Holding holding)
            throws OverdrawnCreditException;

   // /////////////////////////////////////////////////////////////////
   // Member implementation //////////////////////////////////////////
   // /////////////////////////////////////////////////////////////////

   @Override
   public int calculateRemainingCredit(int creditToSubtract)
   {

      this.remainingCredit -= creditToSubtract;

      return this.remainingCredit;

   }

   @Override
   public String getType()
   {

      return this.type;

   }

   @Override
   public int getRemainingCredit()
   {

      return this.remainingCredit;

   }

   @Override
   public BorrowingHistory getBorrowingHistory()
   {

      return this.history;

   }

   @Override
   public Holding[] getCurrentHoldings()
   {

      // Only return an array if the list isn't empty.
      return this.currentLoans.size() == 0 ? null : this.currentLoans.values()
               .toArray(new Holding[this.currentLoans.size()]);

   }

   @Override
   public String getFullName()
   {

      return this.fullName;

   }

   @Override
   public int getMaxCredit()
   {

      return this.maxCredit;

   }

   @Override
   public String getMemberId()
   {

      return this.id;

   }

   @Override
   public void resetCredit()
   {

      this.remainingCredit = this.maxCredit;

   }

   // /////////////////////////////////////////////////////////////////
   // Object /////////////////////////////////////////////////////////
   // /////////////////////////////////////////////////////////////////

   @Override
   public String toString()
   {

      return String.format("%s:%s:%s", this.id, this.fullName,
                           this.remainingCredit);

   }

}
