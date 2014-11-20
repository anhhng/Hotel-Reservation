package projecttester;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

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
   private final JLabel loginLabel = new JLabel("Login");

   JFrame frame = new JFrame("Hotel Reservation");
   
   // Cards layout and cards
   CardLayout cl = new CardLayout();
   JPanel panelContainer = new JPanel();
   JPanel loginCard = new JPanel();
   JPanel guestLoginCard = new JPanel();
   JPanel guestOptionCard = new JPanel();
   JPanel newAccountCard = new JPanel();
   JPanel managerLoginCard = new JPanel();
   
   public Hotel() throws InterruptedException
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
      
      panelContainer.setLayout(cl);
      
      // setup card
      loginCard("Login");
      guestLoginCard();
      guestOptionsCard();
      createNewAcctCard();
      managerLoginCard();
      
      cl.show(panelContainer, "loginCard");
      frame.add(panelContainer);
      frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      frame.pack();
      frame.setVisible(true);
      
      // wait for frame to dispose
       while (frame.isVisible() == true)
           Thread.sleep(100);
       
      // save information to file
      saveInfo();
   }
   
   private void loginCard(String aMessage) throws InterruptedException
   {
       // create login panels
       JPanel labelPanel = new JPanel();
       JPanel loginPanel = new JPanel();
       JPanel quitPanel = new JPanel();
       loginCard.setLayout(new BoxLayout(loginCard, BoxLayout.Y_AXIS));
       
       // create buttons
       JButton guestButton = new JButton("Guest");
       JButton managerButton = new JButton("Manager");
       JButton quitButton = new JButton("Quit");
       
       // create label
       loginLabel.setFont(new Font("Calibri", Font.BOLD, 18));
       loginLabel.setHorizontalAlignment(JLabel.CENTER);
       loginLabel.setVerticalAlignment(JLabel.CENTER);
       loginLabel.setPreferredSize(new Dimension(450,50));
       
       // Add buttons to panels
       labelPanel.add(loginLabel);
       loginPanel.add(guestButton);
       loginPanel.add(managerButton);
       quitPanel.add(quitButton);
       
       // Add panels to login card
       loginCard.add(labelPanel);
       loginCard.add(loginPanel);
       loginCard.add(quitPanel);
       
       
       panelContainer.add(loginCard,"loginCard");
       
       // Button action listeners
       guestButton.addActionListener(new ActionListener()
            {
              @Override
              public void actionPerformed(ActionEvent e)
              {
                 System.out.println("Guest button Pushed!");
                 cl.show(panelContainer, "guestLoginCard");
              }
            });

       managerButton.addActionListener(new ActionListener()
            {
              @Override
              public void actionPerformed(ActionEvent e)
              {
                 System.out.println("Manager button Pushed!");
                 cl.show(panelContainer, "managerLoginCard");
              }
            });

       quitButton.addActionListener(new ActionListener()
            {
              @Override
              public void actionPerformed(ActionEvent e)
              {
                 System.out.println("LoginCard - Quit button Pushed!");
                 saveInfo();
                 System.exit(0);
              }
            });
   }
   
   private void guestLoginCard() throws InterruptedException
   {
       // create login frame
       guestLoginCard.setLayout(new BoxLayout(guestLoginCard, BoxLayout.Y_AXIS));
       
       // create UserID panel
       JPanel userNamePanel = new JPanel();
       JLabel userIdLabel = new JLabel("User ID:");
       userIdLabel.setFont(new Font("Calibri", Font.BOLD, 18));
       userIdLabel.setHorizontalAlignment(JLabel.CENTER);
       userIdLabel.setVerticalAlignment(JLabel.BOTTOM);
       JTextField userIdText = new JTextField(20);
       userNamePanel.add(userIdLabel); // add user ID label
       userNamePanel.add(userIdText); // add user ID text field
       
       // create Password panel
       JPanel userPwdPanel = new JPanel();
       
       // password label
       JLabel pwdLabel = new JLabel("Password:");
       pwdLabel.setFont(new Font("Calibri", Font.BOLD, 18));
       pwdLabel.setHorizontalAlignment(JLabel.CENTER);
       pwdLabel.setVerticalAlignment(JLabel.TOP);
       
       // password text field
       JTextField userPwdText = new JPasswordField(20);
       userPwdPanel.add(pwdLabel);   // add pwd label
       userPwdPanel.add(userPwdText); // add pwd text field
       
       // create buttons for panel
       JPanel buttonPanel = new JPanel();
       JButton loginButton = new JButton("Login");
       JButton cancelButton = new JButton("Cancel");
       JButton createNewAccount = new JButton("Create New Account");
       buttonPanel.add(loginButton);
       buttonPanel.add(cancelButton);
       buttonPanel.add(createNewAccount);
       
       // Add panels to guestLoginCard
       guestLoginCard.add(userNamePanel);
       guestLoginCard.add(userPwdPanel);
       guestLoginCard.add(buttonPanel);
              
       panelContainer.add(guestLoginCard,"guestLoginCard");
       
       // Action Listner for login button
       loginButton.addActionListener(new ActionListener()
            {
              @Override
              public void actionPerformed(ActionEvent e)
              {
                 // Login Button Pushed. Check User/Password
                 System.out.println("Login button Pushed!");
                 System.out.println("User: " + userIdText.getText());
                 System.out.println("Passwd: " + userPwdText.getText());
                 if (login(userIdText.getText(), userPwdText.getText()) == true)
                 {
                     System.out.println("Login Success");
                     loginLabel.setText("Login");
                     
                     // Show guest option card
                     cl.show(panelContainer, "guestOptionCard");
                 } else {
                     System.out.println("Login unsuccess");
                     userIdText.setText(null);
                     userPwdText.setText(null);
                     loginLabel.setText("Login - unsuccess");
                     cl.show(panelContainer, "loginCard");
                 }
             }
            });

       // Action Listner for cancel button
       cancelButton.addActionListener(new ActionListener()
            {
              @Override
              public void actionPerformed(ActionEvent e)
              {
                 System.out.println("Cancel button Pushed!");
                 loginLabel.setText("Login");
                 cl.show(panelContainer, "loginCard");
              }
            });

       // Action Listner for Create User Account button
       createNewAccount.addActionListener(new ActionListener()
            {
              @Override
              public void actionPerformed(ActionEvent e)
              {
                 System.out.println("Create New Account button Pushed!!");
                 loginLabel.setText("Login");
                 cl.show(panelContainer, "newAccountCard");
              }
            });
   }
   
   private void guestOptionsCard() throws InterruptedException
   {
       JLabel guestOptionLabel = new JLabel("Guest Option no done yet");
       guestOptionCard.add(guestOptionLabel);
       panelContainer.add(guestOptionCard,"guestOptionCard");
   }
   
   private void createNewAcctCard() throws InterruptedException
   {
       JLabel newAccountLabel = new JLabel("create new account no done yet");
       newAccountCard.add(newAccountLabel);
       panelContainer.add(newAccountCard,"newAccountCard");
   }
   
   private void managerLoginCard() throws InterruptedException
   {
       JLabel managerLoginLabel = new JLabel("manager login no done yet");
       managerLoginCard.add(managerLoginLabel);
       panelContainer.add(managerLoginCard,"managerLoginCard");
   }
   
   public boolean login(String accountName, String aPassword)
   {
      // look all account for this account
      for (Account a: accounts) {
          if (accountName.compareTo(a.getName()) == 0)
          {
              if (aPassword.compareTo(a.getPassword()) == 0)
              {
                  // find match - set currentAccount
                  currentAccount = a;
                  return true;
              }
          }
      }
      
      // No find match
      System.out.println("BAD LOGIN: " + accountName + " " + aPassword);
      loginLabel.setText("Login - Bad Login");
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
   
   public void newUser()
   {
      
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