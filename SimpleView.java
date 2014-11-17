package projecttester;

import java.util.ArrayList;

public class SimpleView extends BaseView
{
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