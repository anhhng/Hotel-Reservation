package projecttester;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class Hotel
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
         rooms.add(new Room(100 + i, LUXURY));
      for (int i = NUM_OF_ROOMS / 2 + 1; i < NUM_OF_ROOMS; i++)
         rooms.add(new Room(100 + i, ECONOMY));
      
      // get save account reservation data
      loadInfo();
   }
   
   public Receipt getCurrentReceipt()
   {
      return currentReceipt;
   }
   
   public boolean login(String accountName, String aPassword)
   {
      // look all account for this account
      for (Account a: accounts) 
      {
         String name = a.getName().toLowerCase();
         String loginName = accountName.toLowerCase();
         if (name.compareTo(loginName) == 0)
         {
            if (aPassword.compareTo(a.getPassword()) == 0)
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
   
   public void makeReservation()
   {
      
   }
   
   public void viewReservation()
   {
      
   }
   
   public void cancelReservation()
   {
      
   }
   
   public void newUser(boolean isManager, String name, String aPassword)
   {
      accounts.add(new Account(isManager, name, aPassword));
   }
   
   public void createReceipt()
   {
      
   }
   
   private void loadInfo()
   {
        try{
            String line;
            BufferedReader br = new BufferedReader(new FileReader("initialData.txt"));

            // read lines from initData file
            while ( (line = br.readLine()) != null) {
                String [] strings = line.split("[\t ]+");
                if (strings[0].compareToIgnoreCase("//") == 0)
                    continue;
                if (strings[0].compareToIgnoreCase("nextReservationNumber") == 0)
                {
                    // next reservation number line
                    if (strings.length >= 2)
                    {
                       Reservation.setNextReserverationNumber(Integer.valueOf(strings[1]));
                    }
                }
                if (strings[0].compareToIgnoreCase("nextID") == 0)
                {
                    // next account ID number line
                    if (strings.length >= 2)
                    {
                       Account.setNextID(Integer.valueOf(strings[1]));
                    }
                }
                if (strings[0].compareToIgnoreCase("account") == 0)
                {
                    // account information line
                    if (strings.length >= 5)
                    {
                        accounts.add(new Account(Boolean.parseBoolean(strings[1]),
                                     strings[2], strings[3],
                                     Integer.parseInt(strings[4])));
                    }
                }
                if (strings[0].compareToIgnoreCase("reservation") == 0)
                {
                    // reservation line
                    if (strings.length >= 7)
                    {
                        GregorianCalendar start = new GregorianCalendar();
                        GregorianCalendar end = new GregorianCalendar();
                        start.setTime(new SimpleDateFormat("MM/dd/yyyy").parse(strings[1]));
                        end.setTime(new SimpleDateFormat("MM/dd/yyyy").parse(strings[2]));
                        Integer accountID = Integer.parseInt(strings[3]);
                        int room = Integer.parseInt(strings[4]);
                        double costPerDay = Double.parseDouble(strings[5]);
                        int reservationNumber = Integer.valueOf(strings[6]);
                        
                        reservations.add(new Reservation(start, end, accountID,
                                         room, costPerDay, reservationNumber));
                        rooms.get(room-1).addReservationNumber(reservationNumber);
                        accounts.get(accountID-1).addReservationNumber(reservationNumber);
                    }
                }
            }
            br.close();
        } catch(Exception e) {
            System.out.println(e);
        }     
   }
   
   public void saveInfo()
   {
       try {
           PrintWriter write = new PrintWriter("initialData.txt");
           
           // save next reservation number
           write.println("nextReservationNumber\t"+Reservation.getNextReserverationNumber());
           write.println("");
           
           // save next new account number
           write.println("nextID\t"+Account.getNextID());
           write.println("");
           
           // save accounts           write.println("// accounts");
           write.println("//\t\tmanager\tname\tpassword\tacct#");
           for (Account account : accounts) {
               write.print("account\t\t"+(account.isManager()?"true":"false")+"\t");
               write.print(account.getName()+"\t"+account.getPassword()+"\t\t");
               write.println(account.getAcctID());
           }
           write.println("");
           write.println("// reservations");
           write.println("//                Arrival         Depart      AcctId  Room      Cost   ResNumber");
           DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
           
           // save reservations
           for (Reservation reservation : reservations) {
               write.print("reservation\t"+dateFormat.format(reservation.getArrivalDate().getTime())+"\t");
               write.print(dateFormat.format(reservation.getDepartDate().getTime())+"\t");
               write.print(reservation.getAcctID()+"\t"+reservation.getRoomNumber()+"\t");
               write.println(reservation.getCostPerDay()+"\t"+reservation.getReservationNumber());
           }
           write.println("");
           
           // close file
           write.close();
       } catch (FileNotFoundException ex) {
           System.out.println(ex);
       }
   }
}