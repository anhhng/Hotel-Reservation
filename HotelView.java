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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.event.MouseInputAdapter;

public class HotelView extends JFrame
{
   private static final int WIDTH = 510;
   private static final int HEIGHT = 300;
   private static final int SECTION_HEIGHT = 50;
   private static final int TEXT_HEIGHT = 12;
   private static final int BORDER_OFFSET = 20;
   CardLayout cards;
   private final Hotel hotel;
   private final JPanel panelContainer;
   Date ReservationStartDate;
   Date ReservationEndDate;
   boolean luxuryRoom;
           
   public HotelView(Hotel aHotel)
   {
      hotel = aHotel;
      
      cards = new CardLayout();
      panelContainer = new JPanel();
      panelContainer.setLayout(cards);
      panelContainer.add(loginCard(), "loginCard");
      panelContainer.add(guestLoginCard(), "guestLoginCard");
      panelContainer.add(managerLoginCard(), "managerLoginCard");
      
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
      
      // Add buttons to login panels
      labelPanel.add(loginLabel);
      loginPanel.add(guestButton);
      loginPanel.add(managerButton);
      quitPanel.add(quitButton);
      
      // Add panels to login card
      panel.add(labelPanel);
      panel.add(loginPanel);
      panel.add(quitPanel);
            
      // Guest Button action listeners
      guestButton.addActionListener(new ActionListener()
         {
            @Override
            public void actionPerformed(ActionEvent e)
            {
               System.out.println("LoginCard - Guest button Pushed!");
               cards.show(panelContainer, "guestLoginCard");
            }
         });

      // Manager Button action listener
      managerButton.addActionListener(new ActionListener()
         {
            @Override
            public void actionPerformed(ActionEvent e)
            {
               System.out.println("LoginCard - Manager button Pushed!");
               cards.show(panelContainer, "managerLoginCard");
            }
         });

      // Quit Button action listner
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
      final JTextField userIdText = new JTextField(22);
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
      guestLoginCard.add(new JPanel());
      guestLoginCard.add(userNamePanel);
      guestLoginCard.add(userPwdPanel);
      guestLoginCard.add(buttonPanel);
      guestLoginCard.add(messageLabel);
              
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
                  
                  // clear user and password
                  userIdText.setText(null);
                  userPwdText.setText(null);
                     
                  // Show guest option card
                  panelContainer.add(guestOptionsCard(), "guestOptionsCard");
                  cards.show(panelContainer, "guestOptionsCard");
                  panelContainer.add(guestLoginCard(), "guestLoginCard");
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
               
               // clear user and password
               userIdText.setText(null);
               userPwdText.setText(null);
                     
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
               
               // clear user and password
               userIdText.setText(null);
               userPwdText.setText(null);
                     
               panelContainer.add(newAccountCard(), "newAccountCard");
               cards.show(panelContainer, "newAccountCard");
            }
         });
      return guestLoginCard;
   }

   class ReservationView extends JPanel
   {
        final JTextField checkinDateText; // checkin date text box
        final JTextField checkoutDateText; // checkout date text box
        final JButton transactionDoneButton; // transactionDone button
        final JButton confirmationButton; // confirm button
        final JButton economyButton; // $100 button
        final JButton luxuryButton; // $200 button
        final JLabel messageLabel;  // message label
        final JTextArea roomsTextArea;
       
        /**
         * ReservationView constructor (MVC View)
         */
        ReservationView()
        {
            ReservationStartDate = null;
            ReservationEndDate = null;
            luxuryRoom = false;

            this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

            // create checkin date panel
            JPanel checkinPanel = new JPanel();
            JLabel checkInLabel = new JLabel("   Check-In:");
            checkInLabel.setFont(new Font("Calibri", Font.BOLD, 18));
            checkInLabel.setHorizontalAlignment(JLabel.CENTER);
            checkInLabel.setVerticalAlignment(JLabel.BOTTOM);
            this.checkinDateText = new JTextField(8);
            checkinPanel.add(checkInLabel); // check-in label
            checkinPanel.add(this.checkinDateText); // start Date text field
       
            // create checkInOut panel
            JPanel checkInOutPanel = new JPanel();

            // create Check-out label
            JLabel checkoutLabel = new JLabel("Check-Out:");
            checkoutLabel.setFont(new Font("Calibri", Font.BOLD, 18));
            checkoutLabel.setHorizontalAlignment(JLabel.CENTER);
            checkoutLabel.setVerticalAlignment(JLabel.TOP);

            // Check-out text field
            this.checkoutDateText = new JTextField(8);
            checkInOutPanel.add(checkoutLabel);   // checkout label
            checkInOutPanel.add(checkoutDateText); // checkout text field

            // create roomType buttons for panel
            JPanel buttonPanel = new JPanel();
            this.economyButton = new JButton("$100");
            this.luxuryButton = new JButton("$200");
            buttonPanel.add(new JLabel("Room Type:"));
            buttonPanel.add(this.luxuryButton);
            buttonPanel.add(this.economyButton);
            
            // transaction panel (Confirm/TransactionDone)
            JPanel transactionPanel = new JPanel();
            transactionPanel.setMinimumSize(new Dimension(400,30));
            this.transactionDoneButton = new JButton("Transaction Done");
            this.confirmationButton = new JButton("Confirm");
            transactionPanel.add(this.transactionDoneButton);

            // Room available text area
            this.roomsTextArea = new JTextArea(5, 10);
            Border border = BorderFactory.createLineBorder(Color.GRAY);
            this.roomsTextArea.setBorder(border);
            
            // error message label
            this.messageLabel = new JLabel("          ");
            this.messageLabel.setMinimumSize(new Dimension(400,25));
            this.messageLabel.setHorizontalAlignment(JLabel.LEFT);
            this.messageLabel.setForeground(Color.red);

            // reservationPanel (checkin/out, room type button)
            JPanel reservationPanel = new JPanel();
            reservationPanel.setLayout(new BoxLayout(reservationPanel, BoxLayout.Y_AXIS));
            reservationPanel.add(checkinPanel);
            reservationPanel.add(checkInOutPanel);
            reservationPanel.add(buttonPanel);
            
            // confirmation panel (Confirm/TransactionDone)
            JPanel confirmationPanel = new JPanel();
            confirmationPanel.setLayout(new BoxLayout(confirmationPanel, BoxLayout.X_AXIS));
            confirmationPanel.add(this.confirmationButton);
            confirmationPanel.add(this.transactionDoneButton);
            
            // room available text area panel 
            JPanel roomsPanel = new JPanel();
            roomsPanel.setLayout(new BoxLayout(roomsPanel, BoxLayout.Y_AXIS));
            roomsPanel.add(new JLabel("Available Rooms"));
            roomsPanel.add(this.roomsTextArea);
            
            // container panel (reservation panel, rooms panel)
            JPanel containerPanel = new JPanel();
            JPanel space1 = new JPanel();
            containerPanel.setLayout(new BoxLayout(containerPanel, BoxLayout.X_AXIS));
            containerPanel.add(reservationPanel);
            containerPanel.add(roomsPanel);
            containerPanel.add(space1);
            
            // Top space panel
            JPanel topPanel = new JPanel();
            this.add(topPanel);
            this.add(containerPanel);
            JPanel middlePanel = new JPanel();
            this.add(middlePanel);
            this.add(confirmationPanel);
            this.add(this.messageLabel);
        }
        
        /**
         * Add Action Listener to Checkin Date
         * @param aListener 
         */
        void addCheckinDateListener(ActionListener aListener)
        {
            this.checkinDateText.addActionListener(aListener);
        }
        
        /**
         * Add Action Listener to Checkout Date
         * @param aListener 
         */
        void addCheckoutDateListener(ActionListener aListener)
        {
            this.checkoutDateText.addActionListener(aListener);
        }
        
        /**
         * Add Action Listener luxury button
         * @param aListener 
         */
        void addLuxuryButtonListener(ActionListener aListener)
        {
            this.luxuryButton.addActionListener(aListener);
        }
        
        /**
         * Add Action Listener Economy Button
         * @param aListener 
         */
        void addEcomonyButtonListener(ActionListener aListener)
        {
            this.economyButton.addActionListener(aListener);
        }
        
        /**
         * Add Action Listener TransactionDone button
         * @param aListener 
         */
        void addTransactionDoneListener(ActionListener aListener)
        {
            this.transactionDoneButton.addActionListener(aListener);
        }
        
        /**
         * Add Action Listener Confirm button
         * @param aListener 
         */
        void addConfirmListener(ActionListener aListener)
        {
            this.confirmationButton.addActionListener(aListener);
        }
        
        /**
         * Get Checkin Date
         * @return checkin date string
         */
        String getCheckinDate()
        {
            return this.checkinDateText.getText();
        }
        
        /**
         * Set checkin date text
         * @param aString text string
         */
        void setCheckinDate(String aString)
        {
            this.checkinDateText.setText(aString);
        }
        
        /**
         * Get Checkout Date
         * @return checkout date string
         */
        String getCheckoutDate()
        {
            return this.checkoutDateText.getText();
        }
        
        /**
         * Set checkout date text
         * @param aString text string
         */
        void setCheckoutDate(String aString)
        {
            this.checkoutDateText.setText(aString);
        }
        
        /**
         * Set error message text
         * @param aString text string
         */
        void setMessageText(String aString)
        {
            this.messageLabel.setText(aString);
        }
        
        /**
         * Clear Text available rooms
         */
        void clearTextArea()
        {
            this.roomsTextArea.setText(null);
        }
        
        /**
         * Update Room available list
         * @param aList 
         */
        void updateRoomList(ArrayList<String> aList)
        {
            clearTextArea();
            for (String s: aList)
                this.roomsTextArea.append(s);
        }
    }
    
    
    class ReservationModel
    {
        String checkinDate;
        String checkoutDate;
        boolean luxury;
        boolean NoConfirmation;
        Hotel hotel;
        Room reserveRoom;  // room this reserve
        ArrayList<String> roomList;
        
        /**
         * ReservationModel constructor (MVC Model)
         * @param aHotel object
         */
        ReservationModel(Hotel aHotel)
        {
            hotel = aHotel;
            this.checkinDate = "00/00/0000";
            this.checkoutDate = "00/00/0000";
            this.NoConfirmation = false;
            this.roomList = new ArrayList<>();
        }

        /**
         * Get list of available rooms
         * @return 
         */
        ArrayList<String> getRoomList()
        {
            return roomList;
        }
        
        /**
         * Set Checkin Date
         * @param aText Date
         * @return error message string
         */
        String setCheckinDate(String aText)
        {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
            try {
                ReservationStartDate = simpleDateFormat.parse(aText);
            } 
            catch (ParseException ex)
            {
                return "Bad Checkin Date";
            }
            checkinDate = aText;
            
            return confirmUpdate(true);
        }
        
        /**
         * set Checkout Date
         * @param aText Date
         * @return error message string
         */
        String setCheckoutDate(String aText)
        {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
            try {
                ReservationEndDate = simpleDateFormat.parse(aText);
            } 
            catch (ParseException ex)
            {
                return "Bad Checkout Date";
            }
            checkoutDate = aText;
            return confirmUpdate(true);
        }
        
        /**
         * Set luxury or economy room
         * @param aValue  boolean true for luxury
         */
        void luxury(boolean aValue)
        {
            luxury = aValue;
        }
        
        /**
         * reservation confirm/update available rooms
         * @param aUpdate  boolean true for update room list
         * @return  String error message
         */
        String confirmUpdate(boolean aUpdate)
        {
            roomList.clear();
            
            if (NoConfirmation == true)
                return null;
            else
                NoConfirmation = true;
            
            // Check start end end date set
            if (ReservationStartDate == null)
            {
                NoConfirmation = false;
                return "No Checkin Date";
            }
            else if (ReservationEndDate == null)
            {
                NoConfirmation = false;
                return "No Checkout Date";
            }

            // Check start date before today
            Date todayDate = new Date();
            if (ReservationStartDate.getTime() + 86400000 < todayDate.getTime())
            {
                ReservationStartDate = null;
                NoConfirmation = false;
                return "Start Date before today";
            }

            // Check end date before today
            if (ReservationEndDate.compareTo(new Date()) < 0)
            {
                ReservationEndDate = null;
                NoConfirmation = false;
                return "End Date before today";
            }

            // Check end date before start date
            if (ReservationStartDate.getTime() >= ReservationEndDate.getTime())
            {
                NoConfirmation = false;
                return "End Date before Start Date";
            }
            else if ((ReservationStartDate.getTime() + 5184000000L) < ReservationEndDate.getTime())
            {
                NoConfirmation = false;
                return "Reservation to long";
            }

            // Make array rooms available
            ArrayList<Room> rooms = hotel.getRooms();
            reserveRoom = null;
            for (Room room: rooms)
            {
                boolean available = true;
                
                // correct type room?
                if (room.isLuxury() == luxury) {
                    ArrayList<Integer> reservations = room.getReservations();
                    for (Integer reservationNumber: reservations)
                    {
                        // room reservation from reservation number
                        Reservation reservation = hotel.getReservation(reservationNumber);
                        
                        // room reservation start and end date
                        Date startDate = reservation.getArrivalDate().getTime();
                        Date endDate = reservation.getDepartDate().getTime();
                        
                        // Check can reserve this room?
                        if ((ReservationStartDate.getTime() >= startDate.getTime() &&
                             ReservationStartDate.getTime() < endDate.getTime()) || 
                            (ReservationEndDate.getTime() > startDate.getTime() &&
                             ReservationEndDate.getTime() <= endDate.getTime()))
                        {
                            // no reserve this room
                            available = false;
                            break;
                        }
                    }
                    
                    if (available == true)
                    {
                        // room available add room
                        reserveRoom = room;
                        roomList.add("   #" + String.valueOf(room.getRoomNumber()) + "\n");
                    }
                }
            }
            
            NoConfirmation = false;
            
            if (aUpdate) 
                return null;  // return no message for update
            
            if (reserveRoom == null)
            {
                return "No room available";
            }
            
            // Use last room available
            // add to reservations
            GregorianCalendar rStart = new GregorianCalendar();
            GregorianCalendar rEnd = new GregorianCalendar();
            rStart.setTime(ReservationStartDate);
            rEnd.setTime(ReservationEndDate);
            
            // create reservation
            Reservation reservation = new Reservation(rStart, rEnd, hotel.getCurrentAccount().getAcctID(),
                      reserveRoom.getRoomNumber(), reserveRoom.getCostPerDay());
            
            // add reservation to hotel reservations
            hotel.addReservation(reservation);
            
            // add reservation number to account
            hotel.getCurrentAccount().addReservationNumber(reservation.getReservationNumber());
            reserveRoom.addReservationNumber(reservation.getReservationNumber());
            
            // no error message
            return null;
        }
    }
       
    class ReservationController
    {
       ReservationModel model;
       ReservationView view;
       
       /**
        * ReservationController constructor (MVC Controller)
        * @param aModel model
        * @param aView  view
        */
       ReservationController(ReservationModel aModel, ReservationView aView)
       {
           this.model = aModel;
           this.view = aView;
           
           /**
            * Checkin Date change process
            */
           this.view.addCheckinDateListener(new ActionListener () {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String result;
                    view.clearTextArea();
                    model.setCheckoutDate(view.getCheckoutDate());
                    result = model.setCheckinDate(view.getCheckinDate());
                    if (result != null) {
                        // have error return
                        view.setMessageText(result);
                        return;
                    }
                    else
                    {
                        view.setMessageText("     ");
                    }
                    model.confirmUpdate(true);
                    view.updateRoomList(model.getRoomList());
                 }}
           );
           
           /**
            * Checkout Date change process
            */
           this.view.addCheckoutDateListener(new ActionListener () {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String result;
                    view.clearTextArea();
                    model.setCheckinDate(view.getCheckinDate());
                    result = model.setCheckoutDate(view.getCheckoutDate());
                    if (result != null) {
                        // have error return
                        view.setMessageText(result);
                        return;
                    }
                    else
                    {
                        view.setMessageText("     ");
                    }
                    model.confirmUpdate(true);
                    view.updateRoomList(model.getRoomList());
                 }}
           );
           
           /**
            * Transaction Done button process
            */
           this.view.addTransactionDoneListener(new ActionListener () {
                @Override
                public void actionPerformed(ActionEvent e) {
                    panelContainer.add(chooseReceiptCard(),"chooseReceiptCard");
                    cards.show(panelContainer,"chooseReceiptCard");
                }}
           );
           
           /**
            * Luxury button process
            */
           this.view.addLuxuryButtonListener(new ActionListener () {
                @Override
                public void actionPerformed(ActionEvent e) {
                    view.clearTextArea();
                    model.luxury(true);
                    model.setCheckinDate(view.getCheckinDate());
                    model.setCheckoutDate(view.getCheckoutDate());
                    String result = model.confirmUpdate(true);
                    if (result != null)
                        view.setMessageText(result);
                    else
                        view.setMessageText("    ");
                    view.updateRoomList(model.getRoomList());
                }}
           );
           
           /**
            * Economy button process
            */
           this.view.addEcomonyButtonListener(new ActionListener () {
                @Override
                public void actionPerformed(ActionEvent e) {
                    view.clearTextArea();
                    model.luxury(false);
                    model.setCheckinDate(view.getCheckinDate());
                    model.setCheckoutDate(view.getCheckoutDate());
                    String result = model.confirmUpdate(true);
                    if (result != null)
                        view.setMessageText(result);
                    else
                        view.setMessageText("    ");
                    view.updateRoomList(model.getRoomList());
                }}
           );
           
           /**
            * Confirm button process
            */
           this.view.addConfirmListener(new ActionListener () {
                @Override
                public void actionPerformed(ActionEvent e) {
                    
                    model.setCheckinDate(view.getCheckinDate());
                    model.setCheckoutDate(view.getCheckoutDate());
                    String result = model.confirmUpdate(false);
                    if (result == null)
                    {
                        // show make another reservation card
                        panelContainer.add(makeAnotherCard(),"makeAnotherCard");
                        cards.show(panelContainer,"makeAnotherCard");
                    }
                    else /* if (result.equals("update")) */
                    {
                        view.setMessageText(result);
                        view.updateRoomList(model.getRoomList());
                    }
                }}
           );
       }
   }
   
    /**
     * MakeReservation Card
     * @return JPanel reservation card panel
     */
    private JPanel makeReservationCard()
    {
       ReservationView view = new ReservationView();
       ReservationModel model = new ReservationModel(hotel);
       ReservationController controller = new ReservationController(model, view);
       return view;
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
            panelContainer.add(makeReservationCard(), "makeReservationCard");
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
            panelContainer.add(confirmCancellationCard(), "confirmCancellationCard");
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
      final Reservation r = reservation;
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
      JPanel panel = new JPanel();
		
      JLabel label = new JLabel("Are you sure you want to cancel reservations?",
              SwingConstants.CENTER);
      JButton noButton = new JButton("No");
	
      JButton yesButton = new JButton("Yes");
	
      panel.add(label);
      panel.add(yesButton);
      panel.add(noButton);
      noButton.addActionListener(new ActionListener() 
      { 
         @Override 
         public void actionPerformed(ActionEvent arg0)
         { 
            //add our code to cancel reservation
            System.out.println("Didn't cancel");
            cards.show(panelContainer, "showReservationsCard");
         } 
      }); 
      yesButton.addActionListener(new ActionListener() 
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
      
      JLabel nameLabel = new JLabel("   Full Name");
      JLabel userNameLabel = new JLabel("   Choose your username");
      JLabel passLabel = new JLabel("   Create a password");
      JLabel confPassLabel = new JLabel("   Confirm your password");
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
            // to create manager
            if (userName.getText().equals("manager"))
               hotel.createAccount(true, "manager", "manager", password.getText());
            boolean flag = false;
            if (!password.getText().equals(confirmPass.getText()))
               messageLabel.setText("Passwords don't match");
            else
            {
               for (int i = 0; i < hotel.getAccounts().size(); i++)
               {
                  if (userName.getText().equals(hotel.getAccounts().get(i).getUsername()))
                  {
                     flag = true;
                  }
               }
               if (flag && !userName.getText().equals("manager"))
               {
                  messageLabel.setText("Username already taken");
               }
               else if (!userName.getText().equals("manager"))
               {
                  hotel.createAccount(false, name.getText(), userName.getText(), password.getText());
                  hotel.login(userName.getText(), password.getText());
                  panelContainer.add(guestOptionsCard(), "guestOptionsCard");
                  cards.show(panelContainer, "guestOptionsCard");
                  panelContainer.add(newAccountCard(), "newAccountCard");
               }
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
   
   private JPanel makeAnotherCard()
   {
      JPanel panel = new JPanel();
		
      JLabel label = new JLabel("Do you want to make another reservation?",
              SwingConstants.CENTER);
      JButton noButton = new JButton("No");
	
      JButton yesButton = new JButton("Yes");
	
      panel.add(label);
      panel.add(yesButton);
      panel.add(noButton);
      noButton.addActionListener(new ActionListener() 
      { 
         @Override 
         public void actionPerformed(ActionEvent arg0)
         { 
            //add our code to cancel reservation
            System.out.println("No button clicked");
            panelContainer.add(chooseReceiptCard(), "chooseReceiptCard");
            cards.show(panelContainer, "chooseReceiptCard");
         } 
      }); 
      yesButton.addActionListener(new ActionListener() 
      { 
         @Override 
         public void actionPerformed(ActionEvent arg0)
         { 
            //add our code here to make a confirmation
            System.out.println("Yes button clicked");
            panelContainer.add(makeReservationCard(), "makeReservationCard");
            cards.show(panelContainer, "makeReservationCard");
         } 
      }); 
      
      return panel;
   }
   
   private JPanel chooseReceiptCard()
   {
      JPanel panel = new JPanel();
		
      JLabel label = new JLabel("Which type of receipt would you like?",
              SwingConstants.CENTER);
      JButton simpleButton = new JButton("Simple");
	
      JButton compButton = new JButton("Comprehensive");
	
      panel.add(label);
      panel.add(simpleButton);
      panel.add(compButton);
      simpleButton.addActionListener(new ActionListener() 
      { 
         @Override 
         public void actionPerformed(ActionEvent arg0)
         { 
            //add our code to cancel reservation
            System.out.println("Simple button clicked");
            hotel.createReceipt();
            panelContainer.add(receiptCard(), "receiptCard");
            cards.show(panelContainer, "receiptCard");
         } 
      }); 
      compButton.addActionListener(new ActionListener() 
      { 
         @Override 
         public void actionPerformed(ActionEvent arg0)
         { 
            //add our code here to make a confirmation
            System.out.println("Comprehensive button clicked");
            hotel.createReceipt();
            hotel.setReceiptView(new ComprehensiveView());
            panelContainer.add(receiptCard(), "receiptCard");
            cards.show(panelContainer, "receiptCard");
         } 
      }); 
      
      return panel;
   }
   
   private JPanel receiptCard()
   {
      JPanel panel = new JPanel();
      
      JTextArea area = new JTextArea();
      area.setText(hotel.getCurrentReceipt().print());
      //area.setPreferredSize(new Dimension(WIDTH - BORDER_OFFSET, HEIGHT - SECTION_HEIGHT * 2));
      panel.add(area, BorderLayout.NORTH);
      
      JPanel aPanel = new JPanel();
      JButton button = new JButton("Continue");
      aPanel.add(button);
      button.addActionListener(new ActionListener()
      {
         public void actionPerformed(ActionEvent e)
         {
            System.out.println("Continue button pressed!");
            cards.show(panelContainer, "guestOptionsCard");
         }
      });
      panel.add(aPanel);
      
      return panel;
   }
   
   private JPanel managerLoginCard()
   {
      JPanel panel = new JPanel();
      panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
       
      // create Welcome panel
      JPanel welcomePanel = new JPanel();
      JLabel welcomeLabel = new JLabel("Welcome, Mr. Manager!");
      welcomeLabel.setFont(new Font("Calibri", Font.BOLD, 18));
      welcomeLabel.setHorizontalAlignment(JLabel.CENTER);
      welcomeLabel.setVerticalAlignment(JLabel.BOTTOM);
      welcomePanel.add(welcomeLabel); // add user ID label
       
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
      buttonPanel.add(loginButton);
      buttonPanel.add(cancelButton);
      
      // message if failed login
      final JLabel messageLabel = new JLabel();
       
      // Add panels to guestLoginCard
      panel.add(welcomePanel);
      panel.add(userPwdPanel);
      panel.add(buttonPanel);
      panel.add(messageLabel);
              
      //panelContainer.add(guestLoginCard,"guestLoginCard");
       
      // Action Listner for login button
      loginButton.addActionListener(new ActionListener()
         {
            @Override
            public void actionPerformed(ActionEvent e)
            {
               // Login Button Pushed. Check Password
               System.out.println("   Passwd: " + userPwdText.getText());
               if (hotel.login("manager", userPwdText.getText()))
               {
                  System.out.println("Login successful");
                     
                  // Show manager view card
                  userPwdText.setText(null);
                  cards.show(panelContainer, "managerViewCard");
               } 
               else 
               {
                  System.out.println("Login failed");
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

      return panel;
   }
}
