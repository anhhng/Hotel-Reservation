/**
Receipt class for Group Project
@author David Delgado
*/

package projecttester;

import java.util.ArrayList;

/**
Receipt contains information about a transaction
*/
public class Receipt
{
   private ArrayList<Reservation> lineItems;
   private String guestName;
   private int guestID;
   private double totalCost;
   private ReceiptView view;
   
   /**
   Constructs Receipt object
   @param reservations reservations made in the transaction
   @param name name of the guest
   @param ID ID of the guest account
   */
   public Receipt(ArrayList<Reservation> reservations, String name, int ID)
   {
      lineItems = reservations;
      guestName = name;
      guestID = ID;
      totalCost = 0;
      for (Reservation r: reservations)
         totalCost += r.getCost();
      view = new SimpleView();
   }
   
   /**
   Sets the view type
   @param aView type of view
   postcondition: the receipt is set to the view
   */
   public void setView(ReceiptView aView)
   {
      view = aView;
   }
   
   /**
   Displays the receipt information using the chosen view
   @return String of receipt information
   */
   public String print()
   {
      return String.format("%s\n\n%s\n\n%s", view.header(guestID, guestName), 
              view.lineItem(lineItems), view.footer(totalCost));
   }
}