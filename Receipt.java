package projecttester;

import java.util.ArrayList;

public class Receipt
{
   private ArrayList<Reservation> lineItems;
   private String guestName;
   private int guestID;
   private double totalCost;
   private ReceiptView view;
   
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
   
   public void setView(ReceiptView aView)
   {
      view = aView;
   }
   
   public String print()
   {
      return String.format("%s\n\n%s\n\n%s", view.header(guestID, guestName), view.lineItem(lineItems), view.footer(totalCost));
   }
}