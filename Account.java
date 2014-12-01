package projecttester;

import java.io.Serializable;
import java.util.ArrayList;

public class Account implements Serializable
{
  private static int nextID = 1000;
  private boolean manager;
  private int acctID;
  private String acctName;
  private String username;
  private String password;
  private ArrayList<Integer> reservations;
  
  public Account(boolean isManager, String name, String aUsername, String aPassword)
  {
     acctID = nextID++;
     acctName = name;
     username = aUsername;
     password = aPassword;
     reservations = new ArrayList<Integer>();
     manager = isManager;
  }
  
  public Account(boolean isManager, String name, String aUsername, String aPassword, int aAcctID)
  {
     acctID = aAcctID;
     acctName = name;
     username = aUsername;
     password = aPassword;
     reservations = new ArrayList<>();
     manager = isManager;
  }
  
  public String getUsername()
  {
     return username;
  }
  
  public int getReservationNumber(int index)
  {
     if (index < reservations.size())
        return reservations.get(index);
     else {
         System.out.println("getReservationNumber out of bounds");
         return 0;
     }
  }
  
  public ArrayList<Integer> getReservationNumbers()
  {
     return reservations;
  }
  
  public void addReservationNumber(int number)
  {
     reservations.add(number);
  }
  
  public void removeReservation(int reservationNumber)
  {
     for (int i = 0; i < reservations.size(); i++)
        if (reservationNumber == reservations.get(i))
           reservations.remove(i);
  }
  
  public int getNumberOfReservations()
  {
     return reservations.size();
  }
  
  public int getAcctID()
  {
     return acctID;
  }
  
  public boolean isManager()
  {
     return manager;
  }
   
   public static void setNextID(int aNumber) {
       nextID = aNumber;
   }
   
   public static int getNextID() {
       return nextID;
   }
   
   public String getName()
   {
       return acctName;
   }
   
   public String getPassword()
   {
       return password;
   }
}
