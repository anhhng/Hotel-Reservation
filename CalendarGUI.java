package projecttester;

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
   }
}