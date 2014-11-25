package projecttester;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class HotelView extends JFrame
{
   private static final int WIDTH = 450;
   private static final int HEIGHT = 300;
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
      //panelContainer.add(guestOptionCard(), "guestLoginCard");
      //panelContainer.add(chooseDatesCard(), "guestLoginCard");
      //panelContainer.add(chooseRoomCard(), "guestLoginCard");
      //panelContainer.add(confirmationCard(), "guestLoginCard");
      //panelContainer.add(makeAnotherCard(), "guestLoginCard");
      //panelContainer.add(transactionCompleteCard(), "guestLoginCard");
      //panelContainer.add(receiptCard(), "guestLoginCard");
      //panelContainer.add(showReservationsCard(), "guestLoginCard");
      //panelContainer.add(confirmCancellationCard(), "guestLoginCard");
      //panelContainer.add(newAccountCard(), "guestLoginCard");
      //panelContainer.add(managerLoginCard(), "guestLoginCard");
      //panelContainer.add(managerViewCard(), "guestLoginCard");
      
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
               cards.next(panelContainer);
            }
         });

      managerButton.addActionListener(new ActionListener()
         {
            @Override
            public void actionPerformed(ActionEvent e)
            {
               System.out.println("LoginCard - Manager button Pushed!");
               cards.show(panelContainer, "managerLogin");
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
      
      // message if failed login
      JLabel messageLabel = new JLabel();
       
      // Add panels to guestLoginCard
      guestLoginCard.add(userNamePanel);
      guestLoginCard.add(userPwdPanel);
      guestLoginCard.add(buttonPanel);
      guestLoginCard.add(messageLabel);
              
      panelContainer.add(guestLoginCard,"guestLoginCard");
       
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
                  cards.show(panelContainer, "guestOptionCard");
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
               cards.show(panelContainer, "newAccountCard");
            }
         });
      return guestLoginCard;
   }
}