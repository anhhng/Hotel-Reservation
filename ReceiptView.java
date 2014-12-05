/**
ReceiptView interface for Strategy pattern in Group Project
@author David Delgado
*/

package projecttester;

import java.util.ArrayList;

/**
ReceiptView describes methods required for a concrete ReceiptView
*/
public interface ReceiptView
{
   /**
   Creates the header String
   @param guestID Account ID of the guest making the reservations
   @param guestName Name of the guest making the reservations
   @return header String identifying the guest
   */
   public String header(int guestID, String guestName);
   
   /**
   Creates a String that shows information about reservations made in a
   transaction
   @param lineItems reservations made in this transaction
   @return String showing information
   */
   public String lineItem(ArrayList<Reservation> lineItems);
   
   /**
   Creates the footer String
   @param totalCost total cost of all reservations made in the transaction
   @return footer String showing the total cost
   */
   public String footer(double totalCost);
}