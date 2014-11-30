package projecttester;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;


import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 * This class consider as View.
 * Responsibilities are the View is to contain View Components, 
 * present the whole Panel, and display content.
 * A view requests information from the model that
 *  it uses to generate an output representation to the user.
 */
	public class RoomsViewGUI extends JFrame
	{
	    
		private JLabel AvaRoomLabel = new JLabel("Available Rooms"+"     ");
		private JLabel ResRoomLabel = new JLabel("Reserved Rooms");
		private JLabel emptyLabel = new JLabel("                                                    ");
		private JButton button = new JButton("Book the selected room");
		private JCheckBox AgreeButton = new JCheckBox("Agree");
		
		private JTextArea screen1 = new JTextArea(20,25);
		private JTextArea screen2 = new JTextArea(20,25);

		
		public RoomsViewGUI() throws Exception
		{
			JPanel  panel = new JPanel();
			JPanel panel1 = new JPanel();
		
			 panel1.setLayout(new BorderLayout());
			 panel1.add(AvaRoomLabel, BorderLayout.WEST);
			 panel1.add(ResRoomLabel, BorderLayout.EAST);
			 panel1.add(emptyLabel, BorderLayout.CENTER);
			 panel.add(panel1);
			 panel.add(screen1);  
			 panel.add(screen2);
			
			panel.add(AgreeButton);
			panel.add(button);
			
			try {
				FileReader reader = new FileReader( "RoomsAvailable.txt" );
                BufferedReader br = new BufferedReader(reader);
                screen1.add(new JCheckBox());
                screen1.read( br, null );
                br.close();       
		    }
		    catch(Exception e2) 
		    { 
		        System.out.println(e2);            
		    }
			
			try {
				FileReader reader = new FileReader( "RoomResvered.txt" );
                BufferedReader br = new BufferedReader(reader);
                screen2.read( br, null );
                br.close();
		    }
		    catch(Exception e2) 
		    { 
		        System.out.println(e2);            
		    }
			
			AgreeButton.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					System.out.println("Agreed");
				}
			});
			button.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					System.out.println("Booked");
				}
			});
		  this.add(panel);
		  this.setPreferredSize(new Dimension(600, 500));	
		}
	
	}
