package projecttester;

import java.io.Serializable;
import java.util.ArrayList;

public class Room implements Serializable
{
   private static final double LUXURY_COST = 200.00;
   private static final double ECONOMY_COST = 100.00;
   private final int roomNumber;
   private final boolean luxury;
   private final double costPerDay;
   private final String roomDescription;
   private ArrayList<Integer> reservationNumbers;
   
   public Room(int number, boolean isLuxury)
   {
      roomNumber = number;
      luxury = isLuxury;
      reservationNumbers = new ArrayList<>();
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
   
   public void addReservationNumber(int aReservationNumber)
   {
       reservationNumbers.add(aReservationNumber);
   }
   
   public void removeReservationNumber(int aReservationNumber)
   {
       for (int i=reservationNumbers.size(); i >= 0; i--)
       {
           if (reservationNumbers.get(i) == aReservationNumber)
           {
               reservationNumbers.remove(i);
           }
       }
   }
}