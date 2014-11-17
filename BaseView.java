package projecttester;

public abstract class BaseView implements ReceiptView
{
   public String header(int guestID, String guestName)
   {
      return "ID: " + guestID + "\tGuest name: " + guestName;
   }
   
   public String footer(double totalCost)
   {
      return String.format("Total Cost: $%.2f", totalCost);
   }
}