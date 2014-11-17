package projecttester;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Reservation
{
   private static int nextReservationNumber = 1500;
   private GregorianCalendar startDate;
   private GregorianCalendar endDate;
   private int reservationNumber;
   private int acctID;
   private int roomNumber;
   private double cost;
   private double costPerDay;
   private int numberOfDays;
   
   public Reservation(GregorianCalendar start, GregorianCalendar end, int accountID, int room, double aCostPerDay)
   {
      startDate = start;
      endDate = end;
      acctID = accountID;
      roomNumber = room;
      reservationNumber = nextReservationNumber++;
      costPerDay = aCostPerDay;
      if (startDate.get(Calendar.YEAR) == endDate.get(Calendar.YEAR))
         numberOfDays = endDate.get(Calendar.DAY_OF_YEAR) - startDate.get(Calendar.DAY_OF_YEAR);
      else
         numberOfDays = 365 - startDate.get(Calendar.DAY_OF_YEAR) + endDate.get(Calendar.DAY_OF_YEAR);
      
      cost = costPerDay * (double)(numberOfDays);
   }
   
   public int getNumberOfDays()
   {
      return numberOfDays;
   }
   
   public int getAcctID()
   {
      return acctID;
   }

   public Calendar getArrivalDate()
   {
      return startDate;
   }
   
   public Calendar getDepartDate()
   {
      return endDate;
   }
   
   public int getReservationNumber()
   {
      return reservationNumber;
   }
   
   public int getRoomNumber()
   {
      return roomNumber;
   }
   
   public double getCost()
   {
      return cost;
   }
   
   public double getCostPerDay()
   {
      return costPerDay;
   }
   
   public String printStartDate()
   {
      SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy"); 
      format.setCalendar(startDate);
      return format.format(startDate.getTime());
   }
   
   public String printEndDate()
   {
      SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy"); 
      format.setCalendar(endDate);
      return format.format(endDate.getTime());
   }
   
   public String toString()
   {
      String output = "Reservation #" + reservationNumber + "\nRoom #" + 
              roomNumber + "\tFrom: " + printStartDate() + "\tTo: " + 
              printEndDate() + "\n\nNights: " + numberOfDays + "\tRate: %.2f"
              + "\n\nTotal Cost: %.2f", costPerDay, cost;
      return output;
   }
}