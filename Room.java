package projecttester;

public class Room
{
   private static final double LUXURY_COST = 200.00;
   private static final double ECONOMY_COST = 100.00;
   private int roomNumber;
   private boolean luxury;
   private double costPerDay;
   private String roomDescription;
   
   public Room(int number, boolean isLuxury)
   {
      roomNumber = number;
      luxury = isLuxury;
      if (isLuxury)
      {
         costPerDay = LUXURY_COST;
         roomDescription = "A luxury room";
      }
      else
      {
         costPerDay = ECONOMY_COST;
         roomDescription = "An economy room";
      }
   }
   
   public String getDescription()
   {
      return roomDescription;
   }
   
   public boolean isLuxury()
   {
      return luxury;
   }
   
   public int getRoomNumber()
   {
      return roomNumber;
   }
   
   public double getCostPerDay()
   {
      return costPerDay;
   }
}