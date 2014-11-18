package projecttester;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.text.DateFormatSymbols;
import java.util.*;
import javax.swing.*;

public class CalendarGUI extends JPanel
{
   private GregorianCalendar cal;
   
   public CalendarGUI()
   {
      cal = (GregorianCalendar)Calendar.getInstance();
      JComboBox months = new JComboBox(new DateFormatSymbols().getMonths());
      JSpinner year = new JSpinner();
      year.setValue(cal.get(Calendar.YEAR));
      setLayout(new BorderLayout());
      JPanel header = new JPanel();
      header.add(months);
      header.add(year);
      
      JPanel calendar = new JPanel();
      JLabel weekdays = new JLabel("Su  Mo  Tu  We  Th  Fr  Sa");
      weekdays.setForeground(Color.blue);
      calendar.add(weekdays);
      calendar.setLayout(new GridLayout(7,6));
      int count = 1;
      while (count < cal.get(GregorianCalendar.DAY_OF_WEEK))
      {
         JLabel label = new JLabel();
         calendar.add(label);
         count++;
      }
      for (int i = 1; i <= cal.getActualMaximum(GregorianCalendar.DAY_OF_MONTH); i++)
      {
         JLabel label = new JLabel("" + i);
         calendar.add(label);
      }
      
   }
}