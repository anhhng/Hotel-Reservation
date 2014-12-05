/**
Account class for Group Project
*/

package projecttester;

import java.io.Serializable;
import java.util.ArrayList;

/**
Account class manages an account at the hotel
*/
public class Account implements Serializable
{
   private static int nextID = 1000;
   private boolean manager;
   private int acctID;
   private String acctName;
   private String username;
   private String password;
   private ArrayList<Integer> reservations;
  
   /**
   Constructs an Account object
   @param isManager true if the account is a manager
   @param name full name of the user
   @param aUsername username for the user
   @param aPassword password for the user
   */
   public Account(boolean isManager, String name, String aUsername, String aPassword)
   {
      acctID = nextID++;
      acctName = name;
      username = aUsername;
      password = aPassword;
      reservations = new ArrayList<Integer>();
      manager = isManager;
   }

   /**
   Constructs an Account object with a manually set account ID
   @param isManager true if the account is a manager
   @param name full name of the user
   @param aUsername username for the user
   @param aPassword password for the user
   @param aAcctID account ID for the account
   */
   public Account(boolean isManager, String name, String aUsername, String aPassword, int aAcctID)
   {
     acctID = aAcctID;
     acctName = name;
     username = aUsername;
     password = aPassword;
     reservations = new ArrayList<>();
     manager = isManager;
   }

   /**
   Gets the username of an account
   @return the username
   */
   public String getUsername()
   {
     return username;
   }

   /**
   Gets the reservation number of a chosen index
   @param index the location of the reservation number in the ArrayList
   @return the reservation number
   */
   public int getReservationNumber(int index)
   {
      if (index < reservations.size())
         return reservations.get(index);
      else 
      {
         System.out.println("getReservationNumber out of bounds");
         return 0;
      }
   }

   /**
   Gets the ArrayList of reservation numbers
   @return reservation number ArrayList
   */
   public ArrayList<Integer> getReservationNumbers()
   {
      return reservations;
   }

   /**
   Adds a reservation number to the list
   @param number reservation number to add
   postcondition: reservation number is added to the Account
   */
   public void addReservationNumber(int number)
   {
      reservations.add(number);
   }

   /**
   Removes a reservation number from the list
   @param reservationNumber reservation number to remove
   postcondition: the reservation number is removed from the Account
   */
   public void removeReservation(int reservationNumber)
   {
      for (int i = 0; i < reservations.size(); i++)
         if (reservationNumber == reservations.get(i))
            reservations.remove(i);
   }

   /**
   Gets the number of reservations associated with the account
   @return number of reservations
   */
   public int getNumberOfReservations()
   {
      return reservations.size();
   }

   /**
   Gets the account ID of the account
   @return account ID
   */
   public int getAcctID()
   {
      return acctID;
   }

   /**
   Check if the account is a manager
   @return true if the account is a manager
   */
   public boolean isManager()
   {
      return manager;
   }
   
   /**
   Sets the next account ID
   @param aNumber next account ID
   postcondition: next account ID is set
   */
   public static void setNextID(int aNumber) {
      nextID = aNumber;
   }
   
   /**
   Gets the next account ID
   @return next account ID
   */
   public static int getNextID() {
      return nextID;
   }
   
   /**
   Gets the name of the user
   @return user's name
   */
   public String getName()
   {
      return acctName;
   }
   
   /**
   Gets the password for the account
   @return account password
   */
   public String getPassword()
   {
      return password;
   }
}
