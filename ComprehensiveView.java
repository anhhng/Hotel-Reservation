/**
ComprehensiveView class for Group Project
@author David Delgado
*/

package projecttester;

import java.util.ArrayList;

/**
ComprehensiveView shows detailed information about a completed transaction
*/
public class ComprehensiveView extends BaseView
{
   /**
   Creates a String that shows comprehensive information about reservations made
   in a transaction
   @param lineItems reservations made in this transaction
   @return String showing information
   */
   public String lineItem(ArrayList<Reservation> lineItems)
   {
      String output = "Reservation#\tRoom#\tFrom:\tTo\tNights\tCost\n";
      for (Reservation r: lineItems)
      {
         output = output + r.getReservationNumber() + "\t" + r.getRoomNumber() +
                 "\t" + r.printStartDate() + "\t" + r.printEndDate() + "\t" + 
                 r.getNumberOfDays() + "\t" + r.getCost() + "0\n";
      }
      return output;
   }
}