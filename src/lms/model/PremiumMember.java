package lms.model;

/**
 * @author Greg Kappatos
 */

public class PremiumMember extends AbstractMember
{

   public static final String TYPE = "PREMIUM";
   public static final int INITIAL_CREDIT = 45; // dollars

   public PremiumMember(String id, String fullName)
   {

      super(id, fullName, PremiumMember.INITIAL_CREDIT, PremiumMember.TYPE);

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
