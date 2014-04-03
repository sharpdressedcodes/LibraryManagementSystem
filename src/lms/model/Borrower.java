package lms.model;

/**
 * @author Greg Kappatos
 */

import lms.model.exception.*;

public interface Borrower
{

   public void borrowHolding(Holding holding)
            throws InsufficientCreditException, MultipleBorrowingException;

   public void returnHolding(Holding holding) throws OverdrawnCreditException;

}
