/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package projecttester;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.JFrame;

public class ProjectTester
{
   public static void main(String[] args) throws InterruptedException, Exception
   {
      Hotel ADD = new Hotel();
      CreateAccount create = new CreateAccount();
      Account account = new Account(false, "Bob", "password");
      GregorianCalendar start = new GregorianCalendar(2014, 10, 12);
      GregorianCalendar end = new GregorianCalendar(2014, 10, 19);
      Reservation r = new Reservation(start, end, 1010, 102, 100.00);
      Reservation r2 = new Reservation(start, end, 1011, 106, 100.00);
      Reservation r3 = new Reservation(start, end, 1012, 111, 200.00);
      //JFrame frame = new GuestMenu(ADD, account);
      ArrayList<Reservation> reservations = new ArrayList<Reservation>();
      reservations.add(r);
      reservations.add(r2);
      reservations.add(r3);
      Receipt receipt = new Receipt(reservations, account.getName(), account.getAcctID());
      receipt.setView(new ComprehensiveView());
      //JFrame frame = new ViewCancelGUI(reservations);
      //JFrame frame = new ReservationConfirmationGUI(r);
      JFrame frame = new ReceiptGUI(receipt);
      JFrame f = new JFrame("Cal");
      Container c = f.getContentPane();
      c.setLayout(new FlowLayout());
      c.add(new CalendarGUI());
      // if you wanna see CalendarGUI.
      // instead 3 lines below, replace frame with f.
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setVisible(true);
      frame.pack();
   }
}
