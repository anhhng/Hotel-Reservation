package projecttester;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.text.DateFormatSymbols;
import java.util.*;

import javax.swing.*;

public class CalendarGUI extends JPanel
{
   private GregorianCalendar cal;

   public CalendarGUI()
   {
	  // setSize(500,300);
      cal = (GregorianCalendar)Calendar.getInstance();
      JComboBox months = new JComboBox(new DateFormatSymbols().getMonths());
      JSpinner year = new JSpinner();
      year.setValue(cal.get(Calendar.YEAR));
      JPanel header = new JPanel();
      header.add(months);
      header.add(year);


      JPanel panel = new JPanel();
      panel.setLayout(new BorderLayout());
      JLabel weekdays = new JLabel("Su  Mo  Tu  We  Th  Fr  Sa ");
      weekdays.setForeground(Color.blue);

      panel.add(header, BorderLayout.NORTH);
      panel.add(weekdays, BorderLayout.CENTER);

      JPanel panel2 = new JPanel();
      // mistake here
      panel2.setLayout(new GridLayout(6,7));

      JLabel blanklabel = new JLabel("");
      // reset index of weekday
   		int weekdayIndex = 0;

   		int today = cal.get(Calendar.DATE);
   		int numberofdays = cal.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);


  		// skip weekdays before the first day of month
      for (int day = 1; day < cal.get(GregorianCalendar.DAY_OF_WEEK); day++)
      {
    	  panel2.add(blanklabel);
			weekdayIndex++;
      }
      for (int day = 1; day <= numberofdays&& day < today; day++)
      {
    	  //Convert int to String
    	  String strDay = Integer.toString(day);
    	  JLabel label = new JLabel(" "+ strDay);
    	  panel2.add(label);

    	  // next weekday
      		weekdayIndex++;
      		// if it is the last weekday
     			if (weekdayIndex == 7)
     			{
     				// reset it
     				weekdayIndex = 0;
     				// and go to next line
     				 panel2.add(blanklabel);

     			} else
     			{ 	// otherwise
     				// print space
     				panel2.add(blanklabel);
     			}
     	}
     		if (today < 10)
     		{
     			//Convert int to String
     	   		String strToday = Integer.toString(today);
     			JLabel labelcurrday = new JLabel(""+ "["+strToday+ "]");
     			labelcurrday.setForeground(Color.RED);

     			panel2.add(labelcurrday);
     		}
     		else
     		{
     			//Convert int to String
     	   		String strToday = Integer.toString(today);
     			JLabel labelcurrday = new JLabel("" + "["+strToday+ " ]");
     			labelcurrday.setForeground(Color.RED);
     			panel2.add(labelcurrday);
     		}



     		// print the days of month in tabular format.
     		for (int day = today+1; day <= numberofdays; day++)
     		{
     			//Convert int to String
     	   		String strToday = Integer.toString(day);

     			// print day
     			JLabel label = new JLabel(" " + strToday);
     			panel2.add(label);
     			//System.out.printf("%1$2d", day);



     			// next weekday
     			weekdayIndex++;
     			// if it is the last weekday
     			if (weekdayIndex == 6)
     			{
     				// reset it -1 because we have to substract today date
     				weekdayIndex = -1;
     				// and go to next line
     				panel2.add(blanklabel);
     			}

     			else
     			{ 	// otherwise
     				// print space
     				panel2.add(blanklabel);

     			}
     		}

      panel.add(panel2, BorderLayout.SOUTH);
      add(panel);

   }
}