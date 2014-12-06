package projecttester;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import static java.lang.Integer.parseInt;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Iterator;

public class Hotel implements Serializable
{
   private static final int NUM_OF_ROOMS = 20;
   private static final boolean LUXURY = true;
   private static final boolean ECONOMY = false;
   private ArrayList<Room> rooms;
   private ArrayList<Reservation> reservations;
   private ArrayList<Account> accounts;
   private ArrayList<Reservation> currentReservations;
   private Account currentAccount;
   private Receipt currentReceipt;

   public Hotel()
   {
      rooms = new ArrayList<Room>();
      reservations = new ArrayList<Reservation>();
      accounts = new ArrayList<Account>();
      currentReservations = new ArrayList<Reservation>();      
      
      for (int i = 0; i < NUM_OF_ROOMS / 2; i++)
         rooms.add(new Room(200 + i, LUXURY));
      for (int i = 0; i < NUM_OF_ROOMS / 2; i++)
         rooms.add(new Room(100 + i, ECONOMY));
      
      // get save account reservation data
      loadInfo();
   }
   
   public ArrayList<Account> getAccounts()
   {
      return accounts;
   }
   
   public Account getCurrentAccount()
   {
      return currentAccount;
   }
   
   public Receipt getCurrentReceipt()
   {
      return currentReceipt;
   }
   
   public ArrayList<Reservation> getReservations()
   {
      return reservations;
   }
   
   public ArrayList<Reservation> getCurrentReservations()
   {
      return currentReservations;
   }
   
   public boolean login(String accountName, String aPassword)
   {
      // look all account for this account
      for (Account a: accounts) 
      {
         String name = a.getUsername().toLowerCase();
         String loginName = accountName.toLowerCase();
         if (name.equals(accountName))
         {
            if (aPassword.equals(a.getPassword()))
            {
               // find match - set currentAccount
               currentAccount = a;
               return true;
            }
         }
      }
      return false;
   }
   
   public void logout()
   {
      currentAccount = null;
      currentReceipt = null;
      currentReservations = new ArrayList<Reservation>();
   }
   
   public void makeReservation(GregorianCalendar start, GregorianCalendar end, 
           int accountID, int room, double aCostPerDay)
   {
      currentReservations.add(new Reservation(start, end, accountID, room, aCostPerDay));
   }
   
   public void confirmReservations()
   {
      for (int i = 0; i < currentReservations.size(); i++)
      {
         reservations.add(currentReservations.get(i));
      }
   }
   
   public void cancelReservation(int reservationNumber)
   {
      System.out.println(reservations);
      for (int i = reservations.size() - 1; i >= 0; i--)
         if (reservations.get(i).getReservationNumber() == reservationNumber)
         {
            System.out.println(reservations.get(i));
            reservations.remove(i);
         }
   }
   
   public void createAccount(boolean isManager, String name, String username, String aPassword)
   {
      accounts.add(new Account(isManager, name, username, aPassword));
   }
   
   public void deleteAccount(String username)
   {
      for (int i = accounts.size() - 1; i >= 0; i--)
         if (accounts.get(i).getUsername() == username)
            accounts.remove(i);
   }
   
   public void createReceipt()
   {
      if (currentAccount != null)
         currentReceipt = new Receipt(currentReservations, 
                 currentAccount.getName(), currentAccount.getAcctID());
   }
   
   public void setReceiptView(ReceiptView view)
   {
      currentReceipt.setView(view);
   }
   
   private void loadInfo()
   {
      // load accounts and reservations
      try
      {
         ObjectInputStream in = new ObjectInputStream(new FileInputStream("lists.data"));
         accounts = (ArrayList<Account>) in.readObject();
         reservations = (ArrayList<Reservation>) in.readObject();
         in.close();
      }
      catch (Exception e)
      {
         System.out.println(e);
      }
      
      //load next account and next reservation numbers
      try
      {
         BufferedReader br = new BufferedReader(new FileReader("nexts.data"));
         Account.setNextID(parseInt(br.readLine()));
         Reservation.setNextReserverationNumber(parseInt(br.readLine()));
         br.close();
      }
      catch (Exception e)
      {
         System.out.println(e);
      }
   }
   
   public void saveInfo()
   {
      // use serialization to save accounts and reservations
      try
      {
         ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("lists.data"));
         out.writeObject(accounts);
         out.writeObject(reservations);
         out.close();
      }
      catch (Exception e)
      {
         System.out.println(e);
      }
      
      // manually write values for nextAccountId and nextReservationNumber
      try
      {
         PrintWriter write = new PrintWriter("nexts.data");
         write.println(Account.getNextID());
         write.println(Reservation.getNextReserverationNumber());
         write.close();
      }
      catch (Exception e)
      {
         System.out.println(e);
      }
   }
   
   public ArrayList<Room> getRooms()
   {
       return rooms;
   }
   
   public Reservation getReservation(Integer aReservation)
   {
       for(Reservation r: reservations)
           if (r.getReservationNumber() == aReservation)
                return r;
       for(Reservation r: currentReservations)
           if (r.getReservationNumber() == aReservation)
                return r;
       return null;
   }
   
   public void addReservation(Reservation aReservation)
   {
       currentReservations.add(aReservation);
   }
   
   public void clearCurrentReservations()
   {
      currentReservations = new ArrayList<Reservation>();
   }
}
