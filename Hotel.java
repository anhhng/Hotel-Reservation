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
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
/**
 * Hotel , manager, data. provide access to room, account, reservation records.
 * @author Anhhong Nguyen
 *
 */
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
	/**
	 * Hotel constructor
	 */
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
	/**
	 * getAccounts mehtod gets Accounts
	 * @return accounts
	 */
	public ArrayList<Account> getAccounts()
	{
		return accounts;
	}
	/**
	 * getCurrentAccount gets currentAccount
	 * @return currentAccount
	 */
	public Account getCurrentAccount()
	{
		return currentAccount;
	}
	/**
	 * getCurrentReceipt gets currentReceipt
	 * @return currentRecipt
	 */
	public Receipt getCurrentReceipt()
	{
		return currentReceipt;
	}
	/**
	 * getReservation method gets reservations
	 * @return reservations
	 */
	public ArrayList<Reservation> getReservations()
	{
		return reservations;
	}
	/**
	 * getCurrentReservation gets currentReservation
	 * @return currentReservation
	 */
	public ArrayList<Reservation> getCurrentReservations()
	{
		return currentReservations;
	}
	/**
	 * login checks username and password
	 * @param accountName the account ID
	 * @param aPassword login password
	 * @return boolean  false no account found
	 */
	public boolean login(String accountName, String aPassword)
	{
		// look all account for this account
		for (Account a: accounts) 
		{
			String name = a.getUsername().toLowerCase();
			String loginName = accountName.toLowerCase();
			//check username and name on account
			if (name.equals(accountName))
			{
				//check the password on account and password on login
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
	/**
	 * logout method logouts the account
	 */
	public void logout()
	{
		currentAccount = null;
		currentReceipt = null;
		currentReservations = new ArrayList<Reservation>();
	}
	/**
	 * makeReservation method makes reservations
	 * @param start  reservation start date
	 * @param end  reservation end date
	 * @param accountID   account ID number
	 * @param guestName  guest name
	 * @param room  room number
	 * @param aCostPerDay  cost per day
	 */
	public void makeReservation(GregorianCalendar start, GregorianCalendar end, 
			int accountID, String guestName, int room, double aCostPerDay)
	{
		//add start and end day, add ID, guest name, room number and the cose to reservation.
		currentReservations.add(new Reservation(start, end, accountID, guestName, 
				room, aCostPerDay));
	}
	/**
	 * confirmReservation method confirms reservation you chose
	 */
	public void confirmReservations()
	{
		for (int i = 0; i < currentReservations.size(); i++)
		{
			reservations.add(currentReservations.get(i));
		}
	}
	/**
	 * cancelReservation method cancels reseverations you chose
	 * @param reservationNumber reservation number
	 */
	public void cancelReservation(int reservationNumber)
	{
		for (int i = reservations.size() - 1; i >= 0; i--)
			if (reservations.get(i).getReservationNumber() == reservationNumber)
			{
				reservations.remove(i);
			}
	}
	/**
	 * createAccount methhod creates a new account
	 * @param isManager  manager account true=yes false=no
	 * @param name account owner
	 * @param username account login name
	 * @param aPassword account password
	 */
	public void createAccount(boolean isManager, String name, String username, String aPassword)
	{
		accounts.add(new Account(isManager, name, username, aPassword));
	}
	/**
	 * deleteAccount method deleted Manager account if 
	 * you make a new one
	 * @param username  user name
	 */
	public void deleteAccount(String username)
	{
		for (int i = accounts.size() - 1; i >= 0; i--)
			if (accounts.get(i).getUsername() == username)
				accounts.remove(i);
	}
	/**
	 * createReceipt methods creates receipt
	 */
	public void createReceipt()
	{
		if (currentAccount != null)
			currentReceipt = new Receipt(currentReservations, 
					currentAccount.getName(), currentAccount.getAcctID());
	}
	/**
	 * setReceiptView method sets the view of Receipt
	 * @param view
	 */
	public void setReceiptView(ReceiptView view)
	{
		currentReceipt.setView(view);
	}
	/**
	 * loadInfo loads accounts and reservations
	 */
	private void loadInfo()
	{
		// load accounts and reservations
		try
		{
			//reading object from lists.data file
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
	/**
	 * saveInfor method uses serialization to save account and reservation
	 */
	public void saveInfo()
	{
		// use serialization to save accounts and reservations
		try
		{
			//write objects to lists.data file
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
	/**
	 * getRooms gets rooms from Arraylist
	 * @return
	 */
	public ArrayList<Room> getRooms()
	{
		return rooms;
	}
	/**
	 * getReservation gets reservations
	 * @param aReservation get reservation record
	 * @return
	 */
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
	/**
	 * addReservation adds reservations
	 * @param aReservation  add reservation record
	 */
	public void addReservation(Reservation aReservation)
	{
		currentReservations.add(aReservation);
	}
	/**
	 * clearCurrentReservation clear current reservation
	 */
	public void clearCurrentReservations()
	{
		currentReservations = new ArrayList<Reservation>();
	}
	/**
	 * CheckAvailability check whether the room is available 
	 * by checking on dates (ManagerView)
	 * @param date date
	 * @return boolean array of rooms available
	 */
	public boolean[] checkAvailability(Date date)
	{
		boolean[] roomsAvailable = new boolean[rooms.size()];
		for (int i = 0; i < roomsAvailable.length; i++)
		{
			roomsAvailable[i] = true;
		}
		
		for (Reservation r: reservations)
		{
			if (date.after(r.getArrivalDate().getTime()) && 
					date.before(r.getDepartDate().getTime()) ||
					date.equals(r.getArrivalDate().getTime()))
			{
				for (int i = 0; i < rooms.size(); i++)
				{
					if (r.getRoomNumber() == rooms.get(i).getRoomNumber())
					{
						roomsAvailable[i] = false;  
					}
				}
			}
		}

		return roomsAvailable;
	}
	/**
	 * checkAvailability check whether the room is available or not
	 * by checking dates (guest only)
	 * @param start start date
	 * @param end end date
	 * @return boolean array of rooms available
	 */
	public boolean[] checkAvailability(Date start, Date end)
	{
		boolean[] roomsAvailable = new boolean[rooms.size()];
		// loop for available rooms
		for (int i = 0; i < roomsAvailable.length; i++)
		{
			roomsAvailable[i] = true;
		}

		for (Reservation r: reservations)
		{ // if the date you want to book the room is in interval of date reserved
			// then no room available
			if ((start.after(r.getArrivalDate().getTime()) && 
					start.before(r.getDepartDate().getTime())) || 
					(end.after(r.getArrivalDate().getTime()) && 
							end.before(r.getDepartDate().getTime())) ||
							(start.before(r.getArrivalDate().getTime()) &&
									end.after(r.getDepartDate().getTime())) ||
									start.equals(r.getArrivalDate().getTime()) ||
									end.equals(r.getDepartDate().getTime()))
			{
				for (int i = 0; i < rooms.size(); i++)
				{
					if (r.getRoomNumber() == rooms.get(i).getRoomNumber())
					{
						//mark room is unavailable
						roomsAvailable[i] = false;  
					}
				}
			}
		}

		for (Reservation r: currentReservations)
		{
			// if the date you want to book the room is in interval of date reserved
			// then no room available
			if ((start.after(r.getArrivalDate().getTime()) && 
					start.before(r.getDepartDate().getTime())) || 
					(end.after(r.getArrivalDate().getTime()) && 
							end.before(r.getDepartDate().getTime())) ||
							(start.before(r.getArrivalDate().getTime()) &&
									end.after(r.getDepartDate().getTime())) ||
									start.equals(r.getArrivalDate().getTime()) ||
									end.equals(r.getDepartDate().getTime()))
			{
				for (int i = 0; i < rooms.size(); i++)
				{
					if (r.getRoomNumber() == rooms.get(i).getRoomNumber())
					{
						//mark the room is unavailable
						roomsAvailable[i] = false;  
					}
				}
			}
		}
		
		return roomsAvailable;
	}
	/**
	 * getDateReservations get dates that you reserved
	 * @param date
	 * @return day as indexes 
	 */
	public ArrayList<Integer> getDatedReservations(Date date)
	{
		ArrayList<Integer> indexes = new ArrayList<Integer>();

		for (int i = 0; i < reservations.size(); i++)
		{
			//compare date with startday and endday of reservations and then
			//return index of Arrylist
			if (date.after(reservations.get(i).getArrivalDate().getTime()) && 
					date.before(reservations.get(i).getDepartDate().getTime()))
				indexes.add(i);           
		}

		return indexes;
	}
}
