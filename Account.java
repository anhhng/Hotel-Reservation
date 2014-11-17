package projecttester;

import java.util.ArrayList;

public class Account
{
  private static int nextID = 1000;
  private boolean manager;
  private int acctID;
  private String acctName;
  private String password;
  private ArrayList<Integer> reservations;
  
  public Account(boolean isManager, String name, String aPassword)
  {
     acctID = nextID++;
     acctName = name;
     password = aPassword;
     reservations = new ArrayList<Integer>();
     manager = isManager;
  }
  
  public int getReservationNumber(int index)
  {
     return reservations.get(index);
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
}