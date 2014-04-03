package lms.model.exception;

/**
 * @author Greg Kappatos
 */

@SuppressWarnings("serial")
public class MultipleBorrowingException extends LMSException
{

   public MultipleBorrowingException()
   {

      super("Multiple Borrowing Exception");

   }

   public MultipleBorrowingException(String message)
   {

      super(message);

   }

}
