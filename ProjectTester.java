/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package projecttester;

import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.JFrame;

public class ProjectTester
{
   public static void main(String[] args)
   {
      Hotel ADD = new Hotel();
      Account account = new Account(false, "Bob", "password");
      GregorianCalendar start = new GregorianCalendar(2014, 10, 12);
      GregorianCalendar end = new GregorianCalendar(2014, 10, 19);
      Reservation r = new Reservation(start, end, 1010, 102, 100.00);
      //JFrame frame = new GuestMenu(ADD, account);
      JFrame frame = new ReservationConfirmationGUI(r);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setVisible(true);
      frame.pack();
   }
}
