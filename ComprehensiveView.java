package projecttester;

import java.util.ArrayList;

public class ComprehensiveView extends BaseView
{
   public String lineItem(ArrayList<Reservation> lineItems)
   {
      String output = "Reservation#\tRoom#\tFrom:\tTo\tNights\tCost\n";
      for (Reservation r: lineItems)
      {
         output = output + r.getReservationNumber() + "\t" + r.getRoomNumber() +
                 "\t" + r.printStartDate() + "\t" + r.printEndDate() + "\t" + r.getNumberOfDays() + "\t" + 
                 r.getCost() + "\n";
      }
      return output;
   }
}