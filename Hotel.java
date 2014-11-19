package projecttester;

import java.awt.Dimension;
import java.awt.FlowLayout;
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
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

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
   enum State {MgrOrGuest,
               MgrLogin,
               GuestLogin,
               logout,
               guestOptions,
               createNewAccount,
               viewReservation,
               cancelReservation,
               makeReservation,
               exit,
               testing };
   State currentState;
   String loginMsg;
   
   public Hotel() throws InterruptedException
   {
      rooms = new ArrayList<Room>();
      reservations = new ArrayList<Reservation>();
      accounts = new ArrayList<Account>();
      currentReservations = new ArrayList<Reservation>();
      loginMsg = "Login";
      
      
      for (int i = 0; i < NUM_OF_ROOMS / 2; i++)
         rooms.add(new Room(100 + i, LUXURY));
      for (int i = NUM_OF_ROOMS / 2 + 1; i < NUM_OF_ROOMS; i++)
         rooms.add(new Room(100 + i, ECONOMY));
      
      loadInfo();
      
      // Set Initial State for State Machine
      currentState = State.MgrOrGuest;
      
      // Uncomment following to use only test state
      // currentState = State.testing;
      
      // ReservationSystem State Machine
      while ( true )
      {
          switch (currentState) {
              case MgrOrGuest:
              {
                  loginGUI(loginMsg);
                  break;
              }
              case MgrLogin:
              {
                 loginMsg = "Login - Manager login not done Yet";
                 currentState = State.MgrOrGuest;
                 break;
              }
              case GuestLogin:
              {
                 guestLoginGUI();
                 break;
              }
              case logout:
              {
                  saveInfo();
                  System.exit(0);
              }
              case guestOptions:
              {
                  guestOptionsGUI();
                  break;
              }
              case createNewAccount:
              {
                  createNewAcctGUI();
                  currentState = State.GuestLogin;
                  break;
              }
              case cancelReservation:
              {
                  break;
              }
              case makeReservation:
              {
                  break;
              }
              case exit:
              {
                  return;
              }
              case testing:
              {
                  testCodeGUI();
                  break;
              }
              default:
              {
                  System.out.println("Bad State");
                  loginMsg = "Login";
                  currentState = State.MgrOrGuest;
                  break;
              }
          }
      }
   }
   
   private void loginGUI(String aMessage) throws InterruptedException
   {
       // create login frame
       final JFrame loginFrame = new JFrame();
       loginFrame.setSize(400, 300);
       loginFrame.setLayout(new BoxLayout(loginFrame.getContentPane(), BoxLayout.Y_AXIS));
       
       // create login panels
       JPanel labelPanel = new JPanel();
       JPanel loginPanel = new JPanel();
       JPanel exitPanel = new JPanel();
       loginPanel.setLayout(new FlowLayout());
       
       // create buttons
       JButton guestButton = new JButton("Guest");
       JButton managerButton = new JButton("Manager");
       JButton exitButton = new JButton("Quit");
       
       // create label
       JLabel label = new JLabel(aMessage);
       label.setFont(new Font("Calibri", Font.BOLD, 18));
       label.setHorizontalAlignment(JLabel.CENTER);
       label.setVerticalAlignment(JLabel.CENTER);
       label.setPreferredSize(new Dimension(450,50));
       
       // Add buttons to panels
       labelPanel.add(label);
       loginPanel.add(guestButton);
       loginPanel.add(managerButton);
       exitPanel.add(exitButton);
       
       // Add panels to frame
       loginFrame.add(labelPanel);
       loginFrame.add(loginPanel);
       loginFrame.add(exitPanel);
       
       // Button action listeners
       guestButton.addActionListener(new ActionListener()
            {
              public void actionPerformed(ActionEvent e)
              {
                 loginFrame.dispose();
                 System.out.println("Guest button Pushed!");
                 currentState = State.GuestLogin;
              }
            });

       managerButton.addActionListener(new ActionListener()
            {
              public void actionPerformed(ActionEvent e)
              {
                 System.out.println("Manager button Pushed!");
                 loginFrame.dispose();
                 currentState = State.MgrLogin;
                 loginMsg = "Login - Manager login not done Yet";
             }
            });

       exitButton.addActionListener(new ActionListener()
            {
              public void actionPerformed(ActionEvent e)
              {
                 currentState = State.exit;
                 System.out.println("Exit button Pushed!");
                 loginFrame.dispose();
              }
            });
       
       // Center frame in middle screen
       loginFrame.setLocationRelativeTo(null);
       loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       
       // make frame visable
       loginFrame.setVisible(true);
       
       // Wait frame to disposed
       while (loginFrame.isVisible() == true)
           Thread.sleep(100);
       
       return;
   }
   
   private void guestLoginGUI() throws InterruptedException
   {
       // create login frame
       final JFrame loginFrame = new JFrame();
       loginFrame.setSize(400, 300);
       loginFrame.setLayout(new BoxLayout(loginFrame.getContentPane(), BoxLayout.Y_AXIS));
       
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
       JButton createNewUser = new JButton("Create New Account");
       buttonPanel.add(loginButton);
       buttonPanel.add(cancelButton);
       buttonPanel.add(createNewUser);
       
       // Add panels to frame
       loginFrame.add(userNamePanel);
       loginFrame.add(userPwdPanel);
       loginFrame.add(buttonPanel);
              
       // Center frame in middle screen
       loginFrame.setLocationRelativeTo(null);
       loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       
       // Action Listner for login button
       loginButton.addActionListener(new ActionListener()
            {
              public void actionPerformed(ActionEvent e)
              {
                 // Login Button Pushed. Check User/Password
                 System.out.println("Login button Pushed!");
                 System.out.println("User: " + userIdText.getText());
                 System.out.println("Passwd: " + userPwdText.getText());
                 if (login(userIdText.getText(), userPwdText.getText()) == true)
                 {
                     System.out.println("Login Success");
                     currentState = State.guestOptions;
                 } else {
                     loginMsg = "Login unsuccess";
                     currentState = State.MgrOrGuest;
                 }
                 
                 // Close Frame
                 loginFrame.dispose();
             }
            });

       // Action Listner for cancel button
       cancelButton.addActionListener(new ActionListener()
            {
              public void actionPerformed(ActionEvent e)
              {
                 System.out.println("Cancel button Pushed!");
                 loginFrame.dispose();  // close frame
                 currentState = State.MgrOrGuest;
                 loginMsg = "Login";
              }
            });

       // Action Listner for Create User Account button
       createNewUser.addActionListener(new ActionListener()
            {
              public void actionPerformed(ActionEvent e)
              {
                 System.out.println("Create New User button Pushed!!");
                 currentState = State.createNewAccount;
                 loginMsg = "Login";
                 loginFrame.dispose();  // close frame
              }
            });

       // make frame visable
       loginFrame.setVisible(true);
       
       // Wait frame close
       while (loginFrame.isVisible() == true)
           Thread.sleep(100);

       return;
   }
   
   private void guestOptionsGUI() throws InterruptedException
   {
       // do new accout GUI here
       System.out.println("Need do guest options GUI");
       
       
       // Hi Guys!!! 
       // Important wait window done end of GUI because state machine mess up
       // loginFrame.setVisible(true);
       // while (loginFrame.isVisible() == true)
       //    Thread.sleep(100);
       
       // Next State - exit for now
       System.out.println("guestOptionGUI do exit");
       currentState = State.exit;
   }
   
   private void createNewAcctGUI() throws InterruptedException
   {
       // do new accout GUI here
       System.out.println("Need do create new account");
       
       
       // Hi Guys!!! 
       // Important wait window done end of GUI because state machine mess up
       // loginFrame.setVisible(true);
       // while (loginFrame.isVisible() == true)
       //    Thread.sleep(100);
        
       // Next State
       currentState = State.GuestLogin;
  }
   
   private void testCodeGUI() throws InterruptedException
   {
       System.out.println("Testing code here");
       currentState = State.exit;
   }
   
   public boolean login(String accountName, String aPassword)
   {
      // look every account for this account
      for (Account a: accounts) {
          if (accountName.compareToIgnoreCase(a.getName()) == 0)
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
      loginMsg = "Login - bad password";
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

            while ( (line = br.readLine()) != null) {
                String [] strings = line.split("[\t ]+");
                if (strings[0].compareToIgnoreCase("//") == 0)
                    continue;
                if (strings[0].compareToIgnoreCase("nextReservationNumber") == 0)
                {
                    if (strings.length >= 2)
                    {
                       Reservation.setNextReserverationNumber(Integer.valueOf(strings[1]));
                    }
                }
                if (strings[0].compareToIgnoreCase("nextID") == 0)
                {
                    if (strings.length >= 2)
                    {
                       Account.setNextID(Integer.valueOf(strings[1]));
                    }
                }
                if (strings[0].compareToIgnoreCase("account") == 0)
                {
                    if (strings.length >= 5)
                    {
                        accounts.add(new Account(Boolean.parseBoolean(strings[1]),
                                     strings[2], strings[3],
                                     Integer.parseInt(strings[4])));
                    }
                }
                if (strings[0].compareToIgnoreCase("reservation") == 0)
                {
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
           write.println("nextReservationNumber\t"+Reservation.getNextReserverationNumber());
           write.println("");
           write.println("nextID\t"+Account.getNextID());
           write.println("");
           write.println("// accounts");
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
           
           for (Reservation reservation : reservations) {
               write.print("reservation\t"+dateFormat.format(reservation.getArrivalDate().getTime())+"\t");
               write.print(dateFormat.format(reservation.getDepartDate().getTime())+"\t");
               write.print(reservation.getAcctID()+"\t"+reservation.getRoomNumber()+"\t");
               write.println(reservation.getCostPerDay()+"\t"+reservation.getReservationNumber());
           }
           write.println("");
           write.close();
       } catch (FileNotFoundException ex) {
           System.out.println(ex);
       }
   }
}