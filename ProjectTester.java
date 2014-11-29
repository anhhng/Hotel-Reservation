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
      HotelView frame = new HotelView(ADD);
      
      CreateAccount create = new CreateAccount();
      Account account = new Account(false, "Bob", "password");
      GregorianCalendar start = new GregorianCalendar(2014, 10, 12);
      GregorianCalendar end = new GregorianCalendar(2014, 10, 19);
      Reservation r = new Reservation(start, end, 1010, 102, 100.00);
      Reservation r2 = new Reservation(start, end, 1011, 106, 100.00);
      Reservation r3 = new Reservation(start, end, 1012, 111, 200.00);
      ArrayList<Reservation> reservations = new ArrayList<Reservation>();
      reservations.add(r);
      reservations.add(r2);
      reservations.add(r3);
      Receipt receipt = new Receipt(reservations, account.getName(), account.getAcctID());
      receipt.setView(new ComprehensiveView());
      //JFrame frame = new ReservationConfirmationGUI(r);
      //JFrame frame = new ReceiptGUI(receipt);
      //JFrame frame = new CreateAccount();
      
      //JFrame frame = new JFrame();
      //frame.add(new CalendarGUI());
              
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setVisible(true);
      frame.pack();
   }
}
