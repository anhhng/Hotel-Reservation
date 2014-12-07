package projecttester;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
/**
 * CalendarGUI displays Calendar.Manager can click on the day to change 
 * the view of available rooms or reserved rooms
 * @author Team ADD
 *
 */
public class CalendarGUI extends JPanel
{
	private HotelView view;
	/** The current year, month, and day*/
	protected int yy, mm, dd;;

	/** The label to be displayed */
	protected JLabel labs[][];

	/** The number of day squares to leave blank at the start of this month */
	protected int leadGap = 0;

	/** A Calendar object */
	Calendar calendar = new GregorianCalendar();

	/** current year */
	protected final int thisYear = calendar.get(Calendar.YEAR);
	protected final int thisDay = calendar.get(Calendar.DATE);

	/** current month */
	protected final int thisMonth = calendar.get(Calendar.MONTH);
	/** picked date from calendar */
	private String datepicked;

	/** One of the label */
	private JLabel b0;

	/** The month choice by JCombobox */
	private JComboBox monthChoice;

	/** The year choice by JSpinner */
	private JSpinner yearChoice;
	/**
	 * Construct Calendar, starting with today.
	 */
	CalendarGUI(HotelView aView)
	{
		super();
		setYYMMDD(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
				calendar.get(Calendar.DAY_OF_MONTH));
		buildGUI();
		//display calendar
		printCalendarMonthYear();
		view = aView;

	}
	/*
	 * set Current Date
	 */
	private void setYYMMDD(int year, int month, int today)
	{
		yy = year;
		mm = month;
		dd = today;
	}

	//String Array for month and year
	String[] months = { "January", "February", "March", "April", "May", "June",
			"July", "August", "September", "October", "November", "December" };
	String[] years = {"2005", "2006", "2007", "2008", "2009", "2010", "2014", "2015", "2016", "2018"};

	/*
	 * BuildGUI
	 */
	private void buildGUI()
	{
		setLayout(new BorderLayout());

		JPanel tp = new JPanel();
		tp.add(monthChoice = new JComboBox());
		for (int i = 0; i < months.length; i++)
			//add months to JComboBox
			monthChoice.addItem(months[i]);
		monthChoice.setSelectedItem(months[mm]);
		//month choice listener 
		monthChoice.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				int i = monthChoice.getSelectedIndex();
				if (i >= 0) 
				{
					//set choice to mm
					mm = i;
					//update calendar
					printCalendarMonthYear() ;
				}
			}
		});


		SpinnerDateModel model = new SpinnerDateModel(new Date(), null, null,
				Calendar.DAY_OF_YEAR);
		//create JSpinner object
		final JSpinner spinner = new JSpinner(model);
		//add spninner to tp panel
		tp.add(spinner);
		String format = "yyyy";
		JSpinner.DateEditor editor = new JSpinner.DateEditor(spinner, format);
		// edit spinner to display Year only.
		spinner.setEditor(editor);  
		model.addChangeListener(new ChangeListener() 
		{
			public void stateChanged(ChangeEvent e)
			{
				//Date from spinner
				Date date = ((SpinnerDateModel)e.getSource()).getDate();
				Calendar calendar = Calendar.getInstance();
				//set date we chose to Calendar
				calendar.setTime(date);
				int YearIndex = calendar.get(Calendar.YEAR);
				// set year choice to yy
				yy= YearIndex;
				//update calendar
				printCalendarMonthYear();
			}
		});
		//JComboBox and JSpinner will be on the North
		add(tp, BorderLayout.NORTH);

		JPanel bp = new JPanel();
		bp.setLayout(new GridLayout(7, 7));
		//maximum 6 rows and 7 colums
		labs = new JLabel[6][7];
		//Create day label and set color
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

		// Construct all the labels, and add them.
		for (int i = 0; i < 6; i++)
			for (int j = 0; j < 7; j++)
			{
				bp.add(labs[i][j] = new JLabel(""));
				final int col = i;
				final int row = j;
				//make day label is clickable
				labs[i][j].addMouseListener(new MouseAdapter()
				{

					public void mouseClicked(MouseEvent e)
					{
						String picked = labs[col][row].getText();
						//get the day picked as a String
						datepicked = Integer.toString(mm+1) + "/" + picked+ "/"+ Integer.toString(yy);
						int month;
						int day;
						int year;
						//check if datepicked is format MM//dd/yyyy or not
						if (datepicked.charAt(1) == '/')
						{
							month = Integer.parseInt(datepicked.substring(0,1));
							if (datepicked.charAt(3) == '/')
							{
								day = Integer.parseInt(datepicked.substring(2,3));
								year = Integer.parseInt(datepicked.substring(4,8));
							}
							else
							{
								day = Integer.parseInt(datepicked.substring(2,4));
								year = Integer.parseInt(datepicked.substring(5,9));
							}
						}
						else
						{
							month = Integer.parseInt(datepicked.substring(0,2));
							if (datepicked.charAt(4) == '/')
							{
								day = Integer.parseInt(datepicked.substring(3,4));
								year = Integer.parseInt(datepicked.substring(5,9));
							}
							else
							{
								day = Integer.parseInt(datepicked.substring(3,5));
								year = Integer.parseInt(datepicked.substring(6,10));
							}
						}
						//get current date as Date type
						//and pass it to updateManagerView method
						Calendar tempCal = Calendar.getInstance();
						tempCal.set(Calendar.YEAR, year);
						tempCal.set(Calendar.MONTH, month - 1);
						tempCal.set(Calendar.DATE, day);
						Date date = tempCal.getTime();
						view.updateManagerView(date);
					}
				});

			}
		add(bp, BorderLayout.CENTER);
	}

	private int activeDay = -1;
	/**
	 * check if it is a leap year
	 * @param year
	 * @return Boolean
	 */
	public boolean isLeap(int year)
	{
		if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0)
			return true;
		return false;
	}
	/**
	 * show Calendar
	 */
	public void printCalendarMonthYear()
	{
		//number of days for each months
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
		labs[0][0].setText("");
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
	//set color for current day.
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

		activeDay = newDay;
	}
	/**No color for any previously highlighted day
	 * 
	 */
	private void clearDayActive()
	{
		JLabel b;
		JLabel translucent = new JLabel("");
		// First un-shade the previously-selected square, if any
		if (activeDay > 0)
		{
			b = labs[(leadGap + dd - 1) / 7][(leadGap + dd - 1) % 7];
			// get translucent 
			b.setBackground(translucent.getBackground());
		}
	}
}
