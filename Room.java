/**
Room class for Group Project
@author David Delgado, Daniel Nguyen, Anh Nguyen
*/

package projecttester;

import java.io.Serializable;
import java.util.ArrayList;

/**
Room manages information about a room of the hotel
*/
public class Room implements Serializable
{
   private static final double LUXURY_COST = 200.00;
   private static final double ECONOMY_COST = 100.00;
   private final int roomNumber;
   private final boolean luxury;
   private final double costPerDay;
   private final String roomDescription;
   private ArrayList<Integer> reservationNumbers;
   
   /**
   Constructs Room object
   @param number the room number
   @param isLuxury true if the room is luxury
   */
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
   
   /**
   Gets the description of the room
   @return room description
   */
   public String getDescription()
   {
      return roomDescription;
   }
   
   /**
   Checks if the room is a luxury room
   @return true if luxury
   */
   public boolean isLuxury()
   {
      return luxury;
   }
   
   /**
   Gets the number of the room
   @return room number
   */
   public int getRoomNumber()
   {
      return roomNumber;
   }
   
   /**
   Gets the nightly cost of the room
   @return cost
   */
   public double getCostPerDay()
   {
      return costPerDay;
   }
   
   /**
   Adds a reservation number to the list of reservations made for the room
   @param aReservationNumber reservation number
   */
   public void addReservationNumber(int aReservationNumber)
   {
       reservationNumbers.add(aReservationNumber);
   }
   
   /**
   Removes a reservation number from the list of reservations made for the room
   @param aReservationNumber reservation number
   */
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
   
   /**
   Gets the list of reservation numbers associated with the room
   @return reservation numbers
   */
   public ArrayList<Integer> getReservations()
   {
       return reservationNumbers;
   }
}