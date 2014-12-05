/**
Abstract BaseView class for group Project
@author David Delgado
*/

package projecttester;

/**
BaseView implements common functionality for all ReceiptViews
*/
public abstract class BaseView implements ReceiptView
{
   /**
   Creates the header String
   @param guestID Account ID of the guest making the reservations
   @param guestName Name of the guest making the reservations
   @return header String identifying the guest
   */
   public String header(int guestID, String guestName)
   {
      return "ID: " + guestID + "\tGuest name: " + guestName;
   }
   
   /**
   Creates the footer String
   @param totalCost total cost of all reservations made in the transaction
   @return footer String showing the total cost
   */
   public String footer(double totalCost)
   {
      return String.format("Total Cost: $%.2f", totalCost);
   }
}