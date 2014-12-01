package projecttester;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.font.TextAttribute;
import java.util.ArrayList;
import java.util.Map;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.MouseInputAdapter;

public class HotelView extends JFrame
{
   private static final int WIDTH = 450;
   private static final int HEIGHT = 300;
   private static final int SECTION_HEIGHT = 50;
   private static final int TEXT_HEIGHT = 12;
   private static final int BORDER_OFFSET = 20;
   CardLayout cards;
   private Hotel hotel;
   private JPanel panelContainer;
           
   public HotelView(Hotel aHotel)
   {
      hotel = aHotel;
      
      cards = new CardLayout();
      panelContainer = new JPanel();
      panelContainer.setLayout(cards);
      panelContainer.add(loginCard(), "loginCard");
      panelContainer.add(guestLoginCard(), "guestLoginCard");
      //panelContainer.add(makeReservationCard(), "makeReservationCard");
      //panelContainer.add(makeAnotherCard(), "makeAnotherCard");
      //panelContainer.add(transactionCompleteCard(), "transactionCompleteCard");
      //panelContainer.add(chooseReceipt
      //panelContainer.add(receiptCard(), "receiptCard");
      //panelContainer.add(newAccountCard(), "newAccountCard");
      //panelContainer.add(managerLoginCard(), "managerLoginCard");
      //panelContainer.add(managerViewCard(), "managerViewCard");
      
      cards.first(panelContainer);
      
      add(panelContainer);
      setPreferredSize(new Dimension(WIDTH, HEIGHT));
      setTitle("Hotel Reservation");
   }
   
   private JPanel loginCard()
   {
      JPanel panel = new JPanel();
      
      // create login panels
      JPanel labelPanel = new JPanel();
      JPanel loginPanel = new JPanel();
      JPanel quitPanel = new JPanel();
      panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
       
      // create buttons
      JButton guestButton = new JButton("Guest");
      JButton managerButton = new JButton("Manager");
      JButton quitButton = new JButton("Quit");
      
      // create label
      JLabel loginLabel = new JLabel("Login");
      loginLabel.setFont(new Font("Calibri", Font.BOLD, 18));
      loginLabel.setHorizontalAlignment(JLabel.CENTER);
      loginLabel.setVerticalAlignment(JLabel.CENTER);
      loginLabel.setPreferredSize(new Dimension(WIDTH, HEIGHT / 6));
      
      // Add buttons to panels
      labelPanel.add(loginLabel);
      loginPanel.add(guestButton);
      loginPanel.add(managerButton);
      quitPanel.add(quitButton);
      
      // Add panels to login card
      panel.add(labelPanel);
      panel.add(loginPanel);
      panel.add(quitPanel);
            
      // Button action listeners
      guestButton.addActionListener(new ActionListener()
         {
            @Override
            public void actionPerformed(ActionEvent e)
            {
               System.out.println("LoginCard - Guest button Pushed!");
               cards.show(panelContainer, "guestLoginCard");
            }
         });

      managerButton.addActionListener(new ActionListener()
         {
            @Override
            public void actionPerformed(ActionEvent e)
            {
               System.out.println("LoginCard - Manager button Pushed!");
               cards.show(panelContainer, "managerLoginCard");
            }
         });

      quitButton.addActionListener(new ActionListener()
         {
            @Override
            public void actionPerformed(ActionEvent e)
            {
               System.out.println("LoginCard - Quit button Pushed!");
               hotel.saveInfo();
               System.exit(0);
            }
         });
      return panel;
   }
   
   private JPanel guestLoginCard()
   {
       // create login frame
      JPanel guestLoginCard = new JPanel();
      guestLoginCard.setLayout(new BoxLayout(guestLoginCard, BoxLayout.Y_AXIS));
       
      // create UserID panel
      JPanel userNamePanel = new JPanel();
      JLabel userIdLabel = new JLabel("User ID:");
      userIdLabel.setFont(new Font("Calibri", Font.BOLD, 18));
      userIdLabel.setHorizontalAlignment(JLabel.CENTER);
      userIdLabel.setVerticalAlignment(JLabel.BOTTOM);
      final JTextField userIdText = new JTextField(20);
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
      final JTextField userPwdText = new JPasswordField(20);
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
      
      // message if failed login
      final JLabel messageLabel = new JLabel();
       
      // Add panels to guestLoginCard
      guestLoginCard.add(userNamePanel);
      guestLoginCard.add(userPwdPanel);
      guestLoginCard.add(buttonPanel);
      guestLoginCard.add(messageLabel);
              
      //panelContainer.add(guestLoginCard,"guestLoginCard");
       
      // Action Listner for login button
      loginButton.addActionListener(new ActionListener()
         {
            @Override
            public void actionPerformed(ActionEvent e)
            {
               // Login Button Pushed. Check User/Password
               System.out.println("Login button Pushed!");
               System.out.println("   User: " + userIdText.getText());
               System.out.println("   Passwd: " + userPwdText.getText());
               if (hotel.login(userIdText.getText(), userPwdText.getText()))
               {
                  System.out.println("Login successful");
                     
                  // Show guest option card
                  panelContainer.add(guestOptionsCard(), "guestOptionsCard");
                  cards.show(panelContainer, "guestOptionsCard");
               } 
               else 
               {
                  System.out.println("Login failed");
                  userIdText.setText(null);
                  userPwdText.setText(null);
                  messageLabel.setText("Login failed");
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
               cards.show(panelContainer, "loginCard");
            }
         });

      // Action Listner for Create User Account button
      createNewAccount.addActionListener(new ActionListener()
         {
            @Override
            public void actionPerformed(ActionEvent e)
            {
               System.out.println("Create New Account button Pushed!!");
               panelContainer.add(newAccountCard(), "newAccountCard");
               cards.show(panelContainer, "newAccountCard");
            }
         });
      return guestLoginCard;
   }
   
   private JPanel guestOptionsCard()
   {
      if (hotel.getCurrentAccount() == null)
         throw new IllegalStateException("Must login to access guest options");
      
      JPanel panel = new JPanel();
      panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
      
      JPanel topPanel = new JPanel();
      topPanel.setPreferredSize(new Dimension(WIDTH, SECTION_HEIGHT * 2));
      topPanel.setLayout(new BorderLayout());
      panel.add(topPanel, BorderLayout.NORTH);
      
      JPanel blankPanel = new JPanel();
      blankPanel.setPreferredSize(new Dimension(WIDTH, SECTION_HEIGHT));
      topPanel.add(blankPanel, BorderLayout.NORTH);
      
      JPanel headPanel = new JPanel();
      headPanel.setPreferredSize(new Dimension(WIDTH, SECTION_HEIGHT));
      topPanel.add(headPanel);
      
      String fullName = hotel.getCurrentAccount().getName();
      JLabel welcomeLabel = new JLabel("Welcome, " + fullName);
      welcomeLabel.setPreferredSize(new Dimension(WIDTH * 5 / 6, SECTION_HEIGHT));
      headPanel.add(welcomeLabel);
      
      final JLabel logoutLabel = new JLabel("Logout");
      Font font = logoutLabel.getFont();
      Map attributes = font.getAttributes();
      attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
      logoutLabel.setFont(font.deriveFont(attributes));
      logoutLabel.setLocation(WIDTH / 6, SECTION_HEIGHT);
      logoutLabel.addMouseListener(new MouseInputAdapter()
      {
         public void mouseClicked(MouseEvent e)
         {
            hotel.logout();
            cards.show(panelContainer, "loginCard");
         }
      });
      logoutLabel.addMouseMotionListener(new MouseMotionAdapter()
      {
         public void mouseMoved(MouseEvent e)
         {
            logoutLabel.setForeground(Color.BLUE);
         }
      });
      headPanel.add(logoutLabel);

      JPanel buttonPanel = new JPanel();
      JButton rButton = new JButton("Make a Reservation");
      rButton.addActionListener(new ActionListener()
      {
         public void actionPerformed(ActionEvent e)
         {
            //panelContainer.add(makeReservationCard(), "makeReservationCard");
            cards.show(panelContainer, "makeReservationCard");
         }
      });
      JButton vcButton = new JButton("View/Cancel Reservations");
      vcButton.addActionListener(new ActionListener()
      {
         public void actionPerformed(ActionEvent e)
         {
            panelContainer.add(showReservationsCard(), "showReservationsCard");
            cards.show(panelContainer, "showReservationsCard");
         }
      });
      buttonPanel.setPreferredSize(new Dimension(WIDTH, SECTION_HEIGHT));
      buttonPanel.add(rButton);
      buttonPanel.add(vcButton);
      rButton.setLocation(WIDTH / 3, SECTION_HEIGHT * 3);
      vcButton.setLocation(WIDTH * 2 / 3, SECTION_HEIGHT * 3);
      panel.add(buttonPanel);
      
      panel.addMouseMotionListener(new MouseMotionAdapter()
      {
         public void mouseMoved(MouseEvent e)
         {
            logoutLabel.setForeground(Color.BLACK);
         }
      });
      
      return panel;
   }
   
   private JPanel showReservationsCard()
   {
      
      JPanel panel = new JPanel();
      if (hotel.getCurrentAccount() == null)
         return panel;
      ArrayList<Reservation> reservations = new ArrayList<Reservation>();
      if (hotel.getCurrentAccount().getNumberOfReservations() != 0)
      {
         ArrayList<Integer> numbers = hotel.getCurrentAccount().getReservationNumbers();
         for (int i: numbers)
         {
            for (Reservation r: hotel.getReservations())
               if (r.getReservationNumber() == i)
                  reservations.add(r);
         }
      }
      final boolean[] indexes = new boolean[reservations.size()];
      //panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
      
      JPanel topPanel = new JPanel();
      topPanel.setPreferredSize(new Dimension(WIDTH, SECTION_HEIGHT * 2));
      topPanel.setLayout(new BorderLayout());
      panel.add(topPanel, BorderLayout.NORTH);
      
      JPanel blankPanel = new JPanel();
      blankPanel.setPreferredSize(new Dimension(WIDTH, SECTION_HEIGHT));
      topPanel.add(blankPanel, BorderLayout.NORTH);
      
      JPanel headPanel = new JPanel();
      headPanel.setPreferredSize(new Dimension(WIDTH, SECTION_HEIGHT));
      topPanel.add(headPanel);
      
      JLabel headLabel = new JLabel("Select reservations to cancel");
      headLabel.setPreferredSize(new Dimension(WIDTH - BORDER_OFFSET, SECTION_HEIGHT));
      headPanel.add(headLabel);
      
      JPanel reservationInfo = new JPanel();
      reservationInfo.setLayout(new BoxLayout(reservationInfo, BoxLayout.Y_AXIS));
      //reservationInfo.setPreferredSize(new Dimension(WIDTH, SECTION_HEIGHT));
      JLabel header = new JLabel(String.format(" Reservation  Room      From                 To                 Total Cost"));
      reservationInfo.add(header);
      for (int i = 0; i < reservations.size(); i++)
      {
         final int index = i;
         Reservation r = reservations.get(i);
         JCheckBox line = new JCheckBox();
         String info = String.format("%d         %d        %s     %s    %.2f", 
                 r.getReservationNumber(), r.getRoomNumber(), 
                 r.printStartDate(), r.printEndDate(), r.getCost());
         line.setText(info);
         line.addActionListener(new ActionListener()
                 {
                    public void actionPerformed(ActionEvent e)
                    {
                       indexes[index] = !indexes[index];
                       System.out.println("Toggle " + index);
                    }
                 });
         reservationInfo.add(line);
      }
      panel.add(reservationInfo, BorderLayout.CENTER);
      
      JPanel buttonPanel = new JPanel();
      JButton confirmButton = new JButton("Confirm");
      confirmButton.addActionListener(new ActionListener()
      {
         public void actionPerformed(ActionEvent e)
         {
            System.out.println("Confirm!!!");
            cards.show(panelContainer, "confirmCancellationCard");
         }
      });
      JButton backButton = new JButton("Go back");
      backButton.addActionListener(new ActionListener()
      {
         public void actionPerformed(ActionEvent e)
         {
            System.out.println("Back button");
            cards.show(panelContainer, "guestOptionsCard");
         }
      });
      /*JButton cancelButton = new JButton("Cancel");
      cancelButton.addActionListener(new ActionListener()
      {
         public void actionPerformed(ActionEvent e)
         {
            System.out.println("Cancel button pressed");
            cards.show(panelContainer, "guestOptionsCard");
         }
      });*/
      buttonPanel.setPreferredSize(new Dimension(WIDTH, SECTION_HEIGHT));
      buttonPanel.add(confirmButton);
      buttonPanel.add(backButton);
      //buttonPanel.add(cancelButton);
      confirmButton.setLocation(WIDTH, SECTION_HEIGHT * 3);
      backButton.setLocation(WIDTH / 2, SECTION_HEIGHT * 3);
      //cancelButton.setLocation(WIDTH * 2 / 3, SECTION_HEIGHT * 3);
      panel.add(buttonPanel, BorderLayout.SOUTH);
      
      return panel;
   }
   
   private JPanel confirmationCard(Reservation reservation)
   {    
      JPanel panel = new JPanel();
      Reservation r = reservation;
      //panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
      
      JPanel topPanel = new JPanel();
      topPanel.setPreferredSize(new Dimension(WIDTH, SECTION_HEIGHT * 2));
      topPanel.setLayout(new BorderLayout());
      panel.add(topPanel, BorderLayout.NORTH);
      
      JPanel blankPanel = new JPanel();
      blankPanel.setPreferredSize(new Dimension(WIDTH, SECTION_HEIGHT));
      topPanel.add(blankPanel, BorderLayout.NORTH);
      
      JPanel headPanel = new JPanel();
      headPanel.setPreferredSize(new Dimension(WIDTH, SECTION_HEIGHT));
      topPanel.add(headPanel);
      
      JLabel headLabel = new JLabel("Confirm Reservation?");
      headLabel.setPreferredSize(new Dimension(WIDTH - BORDER_OFFSET, SECTION_HEIGHT));
      headPanel.add(headLabel);
      
      JPanel reservationInfo = new JPanel();
      reservationInfo.setLayout(new BoxLayout(reservationInfo, BoxLayout.Y_AXIS));
      //reservationInfo.setPreferredSize(new Dimension(WIDTH, SECTION_HEIGHT));
      JLabel info = new JLabel("Reservation #" + r.getReservationNumber());
      JLabel info2 = new JLabel("Room #" + r.getRoomNumber());
      JLabel info3 = new JLabel("From " + r.printStartDate() + " to " + r.printEndDate());
      JLabel info4 = new JLabel("Total Cost: $" + String.format("%.2f", r.getCost()));
      info.setPreferredSize(new Dimension(WIDTH - BORDER_OFFSET, TEXT_HEIGHT));
      info2.setPreferredSize(new Dimension(WIDTH - BORDER_OFFSET, TEXT_HEIGHT));
      info3.setPreferredSize(new Dimension(WIDTH - BORDER_OFFSET, TEXT_HEIGHT));
      info4.setPreferredSize(new Dimension(WIDTH - BORDER_OFFSET, TEXT_HEIGHT));
      reservationInfo.add(info);
      reservationInfo.add(info2);
      reservationInfo.add(info3);
      reservationInfo.add(info4);
      panel.add(reservationInfo, BorderLayout.CENTER);
      
      JPanel buttonPanel = new JPanel();
      JButton confirmButton = new JButton("Confirm");
      confirmButton.addActionListener(new ActionListener()
      {
         public void actionPerformed(ActionEvent e)
         {
            hotel.makeReservation(r.getArrivalDate(), r.getDepartDate(), 
                    r.getAcctID(), r.getRoomNumber(), r.getCostPerDay());
            cards.show(panelContainer, "makeAnotherCard");
         }
      });
      JButton backButton = new JButton("Go back");
      backButton.addActionListener(new ActionListener()
      {
         public void actionPerformed(ActionEvent e)
         {
            cards.show(panelContainer, "makeReservationCard");
         }
      });
      JButton cancelButton = new JButton("Cancel");
      cancelButton.addActionListener(new ActionListener()
      {
         public void actionPerformed(ActionEvent e)
         {
            cards.show(panelContainer, "guestOptionsCard");
         }
      });
      buttonPanel.setPreferredSize(new Dimension(WIDTH, SECTION_HEIGHT));
      buttonPanel.add(confirmButton);
      buttonPanel.add(backButton);
      buttonPanel.add(cancelButton);
      confirmButton.setLocation(WIDTH, SECTION_HEIGHT * 3);
      backButton.setLocation(WIDTH / 3, SECTION_HEIGHT * 3);
      cancelButton.setLocation(WIDTH * 2 / 3, SECTION_HEIGHT * 3);
      panel.add(buttonPanel, BorderLayout.SOUTH);
      
      return panel;
   }
   
   private JPanel confirmCancellationCard()
   {
      setSize(50,50);
      JPanel panel = new JPanel();
		
      JLabel label = new JLabel("Are you sure you want to cancel reservations?",
              SwingConstants.CENTER);
      JButton cancel = new JButton("No");
	
      JButton confirm = new JButton("Yes");
	
      panel.add(label);
      panel.add(confirm);
      panel.add(cancel);
      cancel.addActionListener(new ActionListener() 
      { 
         @Override 
         public void actionPerformed(ActionEvent arg0)
         { 
            //add our code to cancel reservation
            System.out.println("Didn't cancel");
            cards.show(panelContainer, "showReservationsCard");
         } 
      }); 
      confirm.addActionListener(new ActionListener() 
      { 
         @Override 
         public void actionPerformed(ActionEvent arg0)
         { 
            //add our code here to make a confirmation
            System.out.println("Confirmed cancelling reservation");
            cards.show(panelContainer, "showReservationsCard");
         } 
      }); 
      
      return panel;
   }
   
   private JPanel newAccountCard()
   {
      JPanel panel = new JPanel();
      
      JLabel nameLabel = new JLabel("Full Name");
      JLabel userNameLabel = new JLabel("Choose your username");
      JLabel passLabel = new JLabel("Create a password");
      JLabel confPassLabel = new JLabel("Confirm your password");
      JButton createButton = new JButton("Create Account");
      JButton cancelButton = new JButton("Cancel");
      final JTextField name = new JTextField(26);
      final JTextField userName = new JTextField(20);
      final JTextField password = new JTextField(22);
      final JTextField confirmPass = new JTextField(20);
      
      // message if failed creating account
      final JLabel messageLabel = new JLabel();
      
      panel.add(nameLabel);	
      panel.add(name);	
					
      panel.add(userNameLabel);
      panel.add(userName);
	
      panel.add(passLabel);
      panel.add(password);
			
      panel.add(confPassLabel);
      panel.add(confirmPass);
			
      panel.add(createButton);
      panel.add(cancelButton);
      panel.add(messageLabel);
      
      createButton.addActionListener(new ActionListener()
      {
         public void actionPerformed(ActionEvent e)
         {
            boolean flag = false;
            if (!password.getText().equals(confirmPass.getText()))
               messageLabel.setText("Passwords don't match");
            else
            {
               for (int i = 0; i < hotel.getAccounts().size(); i++)
                  if (userName.getText().equals(hotel.getAccounts().get(i)))
                     flag = true;
            }
            if (flag)
               messageLabel.setText("Username already taken");
            else
            {
               hotel.createAccount(false, name.getText(), userName.getText(), password.getText());
               messageLabel.setText("Account created");
            }
         }
      });
      cancelButton.addActionListener(new ActionListener()
      {
         public void actionPerformed(ActionEvent e)
         {
            cards.show(panelContainer, "loginCard");
         }
      });
      
      return panel;
   }
}