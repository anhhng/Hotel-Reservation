package projecttester;

import java.util.ArrayList;

public interface ReceiptView
{
   public String header(int guestID, String guestName);
   public String lineItem(ArrayList<Reservation> lineItems);
   public String footer(double totalCost);
}