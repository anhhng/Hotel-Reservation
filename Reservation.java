/**
Reservation class for Group Project
@author David Delgado, Daniel Nguyen, Anh Nguyen
*/

package projecttester;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
Reservation contains information about a reservation
*/
public class Reservation implements Serializable
{
   private static int nextReservationNumber = 1500;
   private GregorianCalendar startDate;
   private GregorianCalendar endDate;
   private int reservationNumber;
   private int acctID;
   private String guestName;
   private int roomNumber;
   private double cost;
   private double costPerDay;
   private int numberOfDays;
   
   /**
   Constructs Reservation object
   @param start date reservation starts
   @param end date reservation ends
   @param accountID account ID of guest
   @param room room number
   @param aCostPerDay cost of room per night
   */
   public Reservation(GregorianCalendar start, GregorianCalendar end, 
           int accountID, String guestName, int room, double aCostPerDay)
   {
      initReservation(start, end, accountID, guestName, room, aCostPerDay, 
              nextReservationNumber++);
   }
   
   /**
   Constructs Reservation object with manually set reservation number
   @param start date reservation starts
   @param end date reservation ends
   @param accountID account ID of guest
   @param room room number
   @param aCostPerDay cost of room per night
   @param aReservationNumber reservation number
   */
   public Reservation(GregorianCalendar start, GregorianCalendar end, 
           int accountID, String guestName, int room, double aCostPerDay, int aReservationNumber)
   {
      initReservation(start, end, accountID, guestName, room, aCostPerDay, 
              aReservationNumber);
   }
   
   /**
   Sets the information for a new Reservation
   @param start date reservation starts
   @param end date reservation ends
   @param accountID account ID of guest
   @param room room number
   @param aCostPerDay cost of room per night
   @param aNumber reservation number
   postcondition: the new reservation has the given data
   */
   private void initReservation(GregorianCalendar start, GregorianCalendar end, 
                           int accountID, String aGuestName, int room, 
                           double aCostPerDay, int aNumber)
   {
      startDate = start;
      endDate = end;
      acctID = accountID;
      guestName = aGuestName;
      roomNumber = room;
      reservationNumber = aNumber;
      costPerDay = aCostPerDay;
      if (startDate.get(Calendar.YEAR) == endDate.get(Calendar.YEAR))
         numberOfDays = endDate.get(Calendar.DAY_OF_YEAR) - 
                 startDate.get(Calendar.DAY_OF_YEAR);
      else
         numberOfDays = 365 - startDate.get(Calendar.DAY_OF_YEAR) + 
                 endDate.get(Calendar.DAY_OF_YEAR);
      cost = costPerDay * (double)(numberOfDays);
   }
   
   /**
   Gets the duration of the reservation
   @return number of days
   */
   public int getNumberOfDays()
   {
      return numberOfDays;
   }
   
   /**
   Gets the account ID of the guest associated with the reservation
   @return account ID
   */
   public int getAcctID()
   {
      return acctID;
   }
   
   /**
   Gets the name of the guest associated with the reservation
   @return guest's name
   */
   public String getGuestName()
   {
      return guestName;
   }

   /**
   Gets the date the reservation starts
   @return start date
   */
   public GregorianCalendar getArrivalDate()
   {
      return startDate;
   }
   
   /**
   Gets the date the reservation ends
   @return end date
   */
   public GregorianCalendar getDepartDate()
   {
      return endDate;
   }
   
   /**
   Gets the reservation number
   @return reservation number
   */
   public int getReservationNumber()
   {
      return reservationNumber;
   }
   
   /**
   Gets the number of the room reserved
   @return room number
   */
   public int getRoomNumber()
   {
      return roomNumber;
   }
   
   /**
   Gets the total cost of the reservation
   @return total cost
   */
   public double getCost()
   {
      return cost;
   }
   
   /**
   Gets the nightly cost of the room reserved
   @return cost per day
   */
   public double getCostPerDay()
   {
      return costPerDay;
   }
   
   /**
   Gets a string representation of the start date
   @return start date string
   */
   public String printStartDate()
   {
      SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy"); 
      format.setCalendar(startDate);
      return format.format(startDate.getTime());
   }
   
   /**
   Gets a string representation of the end date
   @return end date string
   */
   public String printEndDate()
   {
      SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy"); 
      format.setCalendar(endDate);
      return format.format(endDate.getTime());
   }
   
   /**
   Sets the next reservation number in the system
   @param aNumber next reservation number
   postcondition: next reservation number is set to aNumber
   */
   public static void setNextReserverationNumber(int aNumber) 
   {
      nextReservationNumber = aNumber;
   }
   
   /**
   Gets the next reservation number in the system
   @return next reservation number
   */
   public static int getNextReserverationNumber() 
   {
      return nextReservationNumber;
   }
}