package projecttester;

import java.util.ArrayList;

public class ComprehensiveView extends BaseView
{
   public String lineItem(ArrayList<Reservation> lineItems)
   {
      String output = "Reservation#\tRoom#\tFrom:\tTo\tCost\n";
      for (Reservation r: lineItems)
      {
         output = output + r.getReservationNumber() + "\t" + r.getRoomNumber() +
                 "\t" + r.getArrivalDate() + "\t" + r.getDepartDate() + "\t" + 
                 r.getCost() + "\n";
      }
      return output;
   }
}