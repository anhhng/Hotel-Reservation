package projecttester;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
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
   
   public ViewCancelGUI(ArrayList<Reservation> aReservations)
   {
      reservations = aReservations;
      final boolean[] indexes = new boolean[reservations.size()];
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
                    }
                 });
         reservationInfo.add(line);
      }
      add(reservationInfo, BorderLayout.CENTER);
      
      JPanel buttonPanel = new JPanel();
      JButton confirmButton = new JButton("Confirm");
      confirmButton.addActionListener(new ActionListener()
      {
         public void actionPerformed(ActionEvent e)
         {
            System.out.println("Confirm!!!");
         }
      });
      JButton backButton = new JButton("Go back");
      backButton.addActionListener(new ActionListener()
      {
         public void actionPerformed(ActionEvent e)
         {
            System.out.println("Back button");
         }
      });
      JButton cancelButton = new JButton("Cancel");
      cancelButton.addActionListener(new ActionListener()
      {
         public void actionPerformed(ActionEvent e)
         {
            System.out.println("Cancel");
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
