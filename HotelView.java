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
      
      cards.first(panelContainer);
      
      add(panelContainer);
      setPreferredSize(new Dimension(WIDTH, HEIGHT));
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
               System.out.println("Guest button Pushed!");
               cards.show(panelContainer, "guestLoginCard");
            }
         });

      managerButton.addActionListener(new ActionListener()
         {
            @Override
            public void actionPerformed(ActionEvent e)
            {
               System.out.println("Manager button Pushed!");
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
}