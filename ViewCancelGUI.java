package projecttester;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ViewCancelGUI extends JFrame
{
   private static final int WIDTH = 400;
   private static final int HEIGHT = 300;
   private static final int SECTION_HEIGHT = 50;
   private static final int TEXT_HEIGHT = 12;
   private static final int BORDER_OFFSET = 20;
   private ArrayList<Reservation> reservations;
   
   public ViewCancelGUI()
   {
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
      
      JLabel headLabel = new JLabel("Confirm Reservation?");
      headLabel.setPreferredSize(new Dimension(WIDTH - BORDER_OFFSET, SECTION_HEIGHT));
      headPanel.add(headLabel);
      
      JPanel reservationInfo = new JPanel();
      reservationInfo.setLayout(new BoxLayout(reservationInfo, BoxLayout.Y_AXIS));
      //reservationInfo.setPreferredSize(new Dimension(WIDTH, SECTION_HEIGHT));
      JLabel info = new JLabel("Reservation #" + aReservation.getReservationNumber());
      JLabel info2 = new JLabel("Room #" + aReservation.getRoomNumber());
      JLabel info3 = new JLabel("From " + aReservation.printStartDate() + " to " + aReservation.printEndDate());
      JLabel info4 = new JLabel("Total Cost: $" + String.format("%.2f", aReservation.getCost()));
      info.setPreferredSize(new Dimension(WIDTH - BORDER_OFFSET, TEXT_HEIGHT));
      info2.setPreferredSize(new Dimension(WIDTH - BORDER_OFFSET, TEXT_HEIGHT));
      info3.setPreferredSize(new Dimension(WIDTH - BORDER_OFFSET, TEXT_HEIGHT));
      info4.setPreferredSize(new Dimension(WIDTH - BORDER_OFFSET, TEXT_HEIGHT));
      reservationInfo.add(info);
      reservationInfo.add(info2);
      reservationInfo.add(info3);
      reservationInfo.add(info4);
      add(reservationInfo, BorderLayout.CENTER);
      
      JPanel buttonPanel = new JPanel();
      JButton confirmButton = new JButton("Confirm");
      confirmButton.addActionListener(new ActionListener()
      {
         public void actionPerformed(ActionEvent e)
         {
            System.out.println("Test");
         }
      });
      JButton backButton = new JButton("Go back");
      backButton.addActionListener(new ActionListener()
      {
         public void actionPerformed(ActionEvent e)
         {
            
         }
      });
      JButton cancelButton = new JButton("Cancel");
      cancelButton.addActionListener(new ActionListener()
      {
         public void actionPerformed(ActionEvent e)
         {
            
         }
      });
      buttonPanel.setPreferredSize(new Dimension(WIDTH, SECTION_HEIGHT));
      buttonPanel.add(confirmButton);
      buttonPanel.add(backButton);
      buttonPanel.add(cancelButton);
      confirmButton.setLocation(WIDTH, SECTION_HEIGHT * 3);
      backButton.setLocation(WIDTH / 3, SECTION_HEIGHT * 3);
      cancelButton.setLocation(WIDTH * 2 / 3, SECTION_HEIGHT * 3);
      add(buttonPanel, BorderLayout.SOUTH);
   }
}
