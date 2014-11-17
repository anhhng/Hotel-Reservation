// Guest Options page

package projecttester;
import java.awt.*;
import java.awt.event.*;
import java.awt.font.TextAttribute;
import java.util.*;
import javax.swing.*;
import javax.swing.event.MouseInputAdapter;

public class GuestMenu extends JFrame
{
   private static final int WIDTH = 400;
   private static final int HEIGHT = 300;
   private static final int SECTION_HEIGHT = 50;
   private String fullName;
   private Hotel hotel;
   private Account guest;
   
   public GuestMenu(Hotel aHotel, Account anAccount)
   {
      fullName = "Some Person";
      hotel = aHotel;
      guest = anAccount;
      setPreferredSize(new Dimension(WIDTH, HEIGHT));
      
      JPanel topPanel = new JPanel();
      topPanel.setPreferredSize(new Dimension(WIDTH, SECTION_HEIGHT * 2));
      topPanel.setLayout(new BorderLayout());
      add(topPanel, BorderLayout.NORTH);
      
      JPanel blankPanel = new JPanel();
      blankPanel.setPreferredSize(new Dimension(WIDTH, SECTION_HEIGHT));
      topPanel.add(blankPanel, BorderLayout.NORTH);
      
      JPanel headPanel = new JPanel();
      headPanel.setPreferredSize(new Dimension(WIDTH, SECTION_HEIGHT));
      topPanel.add(headPanel);
      
      JLabel welcomeLabel = new JLabel("Welcome, " + fullName);
      welcomeLabel.setPreferredSize(new Dimension(WIDTH * 5 / 6, SECTION_HEIGHT));
      headPanel.add(welcomeLabel);
      
      JLabel logoutLabel = new JLabel("Logout");
      Font font = logoutLabel.getFont();
      Map attributes = font.getAttributes();
      attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
      logoutLabel.setFont(font.deriveFont(attributes));
      logoutLabel.setLocation(WIDTH / 6, SECTION_HEIGHT);
      logoutLabel.addMouseListener(new MouseInputAdapter()
      {
         public void mouseClicked(MouseEvent e)
         {
            aHotel.logout();
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
            
         }
      });
      JButton vcButton = new JButton("View/Cancel Reservations");
      vcButton.addActionListener(new ActionListener()
      {
         public void actionPerformed(ActionEvent e)
         {
            
         }
      });
      buttonPanel.setPreferredSize(new Dimension(WIDTH, SECTION_HEIGHT));
      buttonPanel.add(rButton);
      buttonPanel.add(vcButton);
      rButton.setLocation(WIDTH / 3, SECTION_HEIGHT * 3);
      vcButton.setLocation(WIDTH * 2 / 3, SECTION_HEIGHT * 3);
      add(buttonPanel);
      
      addMouseMotionListener(new MouseMotionAdapter()
      {
         public void mouseMoved(MouseEvent e)
         {
            logoutLabel.setForeground(Color.BLACK);
         }
      });
   }
}