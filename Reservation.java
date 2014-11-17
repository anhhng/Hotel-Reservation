package projecttester;

import java.text.SimpleDateFormat;
import java.util.Calendar;
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
   
   public Reservation(GregorianCalendar start, GregorianCalendar end, int accountID,
                      int room, double aCostPerDay)
   {
      initReservation(start, end, accountID, room, aCostPerDay, nextReservationNumber++);
   }
   
   public Reservation(GregorianCalendar start, GregorianCalendar end, int accountID,
                      int room, double aCostPerDay, int aReservationNumber)
   {
      initReservation(start, end, accountID, room, aCostPerDay, aReservationNumber);
   }
   
   private void initReservation(GregorianCalendar start, GregorianCalendar end, 
                           int accountID, int room, double aCostPerDay, int aNumber)
   {
      startDate = start;
      endDate = end;
      acctID = accountID;
      roomNumber = room;
      reservationNumber = aNumber;
      costPerDay = aCostPerDay;
      if (startDate.get(Calendar.YEAR) == endDate.get(Calendar.YEAR))
         numberOfDays = endDate.get(Calendar.DAY_OF_YEAR) - startDate.get(Calendar.DAY_OF_YEAR);
      else
         numberOfDays = 365 - startDate.get(Calendar.DAY_OF_YEAR) + endDate.get(Calendar.DAY_OF_YEAR);
      System.out.println("costPerDay: " + costPerDay + "   days: " + (double)numberOfDays);
      cost = costPerDay * (double)(numberOfDays);
      System.out.println(cost);
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
   
   public static void setNextReserverationNumber(int aNumber) {
       nextReservationNumber = aNumber;
   }
   
   public static int getNextReserverationNumber() {
       return nextReservationNumber;
   }
}