/**
SimpleView class for Group Project
@author David Delgado
*/

package projecttester;

import java.util.ArrayList;

/**
SimpleView shows basic information about a completed transaction
*/
public class SimpleView extends BaseView
{
   /**
   Creates a String that shows basic information about reservations made in a
   transaction
   @param lineItems reservations made in this transaction
   @return String showing information
   */
   public String lineItem(ArrayList<Reservation> lineItems)
   {
      String output = "";
      for (Reservation r: lineItems)
      {
         output = output + "Room " + r.getRoomNumber() + "\n";
      }
      return output;
   }
}