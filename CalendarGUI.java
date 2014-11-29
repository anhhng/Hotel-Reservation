package projecttester;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CalendarGUI extends JPanel {
  /** The currently-interesting year (not modulo 1900!) */
  protected int yy;

  /** Currently-interesting month and day */
  protected int mm, dd;

  /** The buttons to be displayed */
  protected JLabel labs[][];

  /** The number of day squares to leave blank at the start of this month */
  protected int leadGap = 0;

  /** A Calendar object used throughout */
  Calendar calendar = new GregorianCalendar();

  /** Today's year */
  protected final int thisYear = calendar.get(Calendar.YEAR);
  protected final int thisDay = calendar.get(Calendar.DATE);


  /** Today's month */
  protected final int thisMonth = calendar.get(Calendar.MONTH);

  /** One of the buttons. We just keep its reference for getBackground(). */
  private JLabel b0;

  /** The month choice */
  private JComboBox monthChoice;

  /** The year choice */
  private JComboBox yearChoice;

  /**
   * Construct a Cal, starting with today.
   */
  CalendarGUI()
  {
    super();
    setYYMMDD(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH));
    buildGUI();
    //recompute();
    printCalendarMonthYear();
  }


  private void setYYMMDD(int year, int month, int today)
  {
    yy = year;
    mm = month;
    dd = today;
  }

  String[] months = { "January", "February", "March", "April", "May", "June",
      "July", "August", "September", "October", "November", "December" };

  /** Build the GUI. Assumes that setYYMMDD has been called. */
  private void buildGUI()
  {
    getAccessibleContext().setAccessibleDescription(
        "Calendar not accessible yet. Sorry!");
    setBorder(BorderFactory.createEtchedBorder());

    setLayout(new BorderLayout());

    JPanel tp = new JPanel();
    tp.add(monthChoice = new JComboBox());
    for (int i = 0; i < months.length; i++)
      monthChoice.addItem(months[i]);
    monthChoice.setSelectedItem(months[mm]);
    monthChoice.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent ae)
      {
        int i = monthChoice.getSelectedIndex();
        if (i >= 0) {
          mm = i;
          printCalendarMonthYear() ;
        }
      }
    });

    tp.add(yearChoice = new JComboBox());
    yearChoice.setEditable(true);
    for (int i = yy - 5; i < yy + 5; i++)
      yearChoice.addItem(Integer.toString(i));
    yearChoice.setSelectedItem(Integer.toString(yy));
    yearChoice.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent ae)
      {
        int i = yearChoice.getSelectedIndex();
        if (i >= 0) {
          yy = Integer.parseInt(yearChoice.getSelectedItem()
              .toString());
          // System.out.println("Year=" + yy);
         // recompute();
          printCalendarMonthYear();
        }
      }
    });
    add(BorderLayout.CENTER, tp);

    JPanel bp = new JPanel();
    bp.setLayout(new GridLayout(7, 7));
    labs = new JLabel[6][7]; // first row is days

    bp.add(b0 = new JLabel("SUN"));
    b0.setForeground(Color.white);
    JLabel MON = new JLabel("MON");
    MON.setForeground(Color.white);
    JLabel TUE = new JLabel("TUE");
    TUE.setForeground(Color.white);
    JLabel WED = new JLabel("WED");
    WED.setForeground(Color.white);
    JLabel THU = new JLabel("THU");
    THU.setForeground(Color.white);
    JLabel FRI = new JLabel(" FRI");
    FRI.setForeground(Color.white);
    JLabel SAT = new JLabel("SAT");
    SAT.setForeground(Color.white);

    b0.setBackground(Color.blue);
    b0.setOpaque(true);
    MON.setBackground(Color.blue);
    MON.setOpaque(true);
    TUE.setBackground(Color.blue);
    TUE.setOpaque(true);
    WED.setBackground(Color.blue);
    WED.setOpaque(true);
    THU.setBackground(Color.blue);
    THU.setOpaque(true);
    FRI.setBackground(Color.blue);
    FRI.setOpaque(true);
    SAT.setBackground(Color.blue);
    SAT.setOpaque(true);
    bp.add(MON);
    bp.add(TUE);
    bp.add(WED);
    bp.add(THU);
    bp.add(FRI);
    bp.add(SAT);

    MouseAdapter dateSetter = new MouseAdapter()
    {
    	public void MousePressed(MouseEvent e)
      {

    		// click a day to display reservation

      }
    };

    // Construct all the labels, and add them.
    for (int i = 0; i < 6; i++)
      for (int j = 0; j < 7; j++) {
        bp.add(labs[i][j] = new JLabel(""));
        labs[i][j].addMouseListener(dateSetter);
      }

    add(BorderLayout.SOUTH, bp);
  }


  /** Set the year, month, and day */
  public void setDate(int yy, int mm, int dd) {
    // System.out.println("Cal::setDate");
    this.yy = yy;
    this.mm = mm; // starts at 0, like Date
    this.dd = dd;
    printCalendarMonthYear();
  }

  private int activeDay = -1;
  public boolean isLeap(int year)
  {
	    if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0)
	      return true;
	    return false;
	  }
  public void printCalendarMonthYear()
	{
	  int dom[] = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
	  clearDayActive();
	  int currentMonth = mm;
	  int currentYear = yy;
		// create a new GregorianCalendar object
		Calendar cal = new GregorianCalendar();

		// set its date to the first day of the month/year given by user
		cal.clear();
		cal.set(currentYear, currentMonth - 1, 1);
		leadGap = new GregorianCalendar(yy, mm, 1).get(Calendar.DAY_OF_WEEK) - 1;
		// get the weekday of the first week
		int firstWeekdayOfMonth = new GregorianCalendar(yy, mm, 1).get(Calendar.DAY_OF_WEEK) -1;

		// get the number of days in month.
		 int numberOfMonthDays = dom[mm];

		// print calendar month based on the weekday of the first
		// day of the month and the number of days in month:
		printCalendar(numberOfMonthDays, firstWeekdayOfMonth);
	}


	/*
	 * 	prints calendar month based on the weekday of the first
	 *  day of the month and the number of days in month:
	 */
	private void printCalendar(int numberOfMonthDays, int firstWeekdayOfMonth)
	{

		// skip weekdays before the first day of month
		for (int day = 1; day < firstWeekdayOfMonth; day++)
		{
			labs[0][day].setText("");
		}
		// display the days of month
		for (int day = 1; day <= numberOfMonthDays; day++)
		{
			int column = (firstWeekdayOfMonth + day -1)/7;
			int row = (firstWeekdayOfMonth + day -1 ) % 7;
			 JLabel b = labs[column][row];
		        if(thisYear == yy && mm == thisMonth && day == dd)
		        {
		      	  b.setText(Integer.toString(day));

		      	  b.setOpaque(true);
		        }
		        else
		        b.setText(Integer.toString(day));
		}
		 // 7 days/week * up to 6 rows
	    for (int i = firstWeekdayOfMonth + numberOfMonthDays; i < 6 * 7; i++)
	    {
	      labs[(i) / 7][(i) % 7].setText("");
	    }
	    // Now highlight current day

	    if(thisYear == yy && mm == thisMonth)
		{
			setDayActive(dd,firstWeekdayOfMonth);
		}
	}
	 public void setDayActive(int newDay, int firstWeekdayOfMonth)
	  {
		 clearDayActive();

	    // Set the new one
	    if (newDay <= 0)
	      dd = new GregorianCalendar().get(Calendar.DAY_OF_MONTH);
	    else
	      dd = newDay;
	    // Now shade the correct square
	    Component square = labs[(firstWeekdayOfMonth + newDay - 1) / 7][(firstWeekdayOfMonth + newDay - 1) % 7];
	    square.setBackground(Color.red);
	    square.repaint();
	    activeDay = newDay;
	  }
	 /** Unset any previously highlighted day */
	  private void clearDayActive()
	  {
		    JLabel b;
		    // First un-shade the previously-selected square, if any
		    if (activeDay > 0)
		    {
		      b = labs[(leadGap + dd - 1) / 7][(leadGap + dd - 1) % 7];
		      b.setOpaque(false);

		    }
	 }

}
