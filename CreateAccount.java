package projecttester;

import java.awt.BorderLayout;
import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * This class consider as View.
 * Responsibilities are the View is to contain View Components, 
 * present the whole Panel, and display content.
 * A view requests information from the model that
 *  it uses to generate an output representation to the user.
 */
	public class CreateAccount extends JFrame
	{
		    
		private JTextField FirstName = new JTextField(26);
		private JTextField LastName = new JTextField(26);
		private JTextField UserName = new JTextField(20);
		private JTextField Password = new JTextField(22);
		private JTextField ConfirmPass = new JTextField(20);
    
		private JLabel NameLabel = new JLabel("First Name");
		private JLabel LastNameLabel = new JLabel("Last Name");
		private JLabel UserNameLabel = new JLabel("Choose your username");
		private JLabel PassLabel = new JLabel("Greate a password");
		private JLabel ConfPassLabel = new JLabel("Confirm your password");
		private JButton Createbutton = new JButton("Create");
		private JCheckBox AgreeButton = new JCheckBox("I agree to Hotel Terms of Service and Privacy Policy");
		private JTextArea screen = new JTextArea(30,30);
		private String display;
		
		public CreateAccount() throws Exception
		{
			JPanel  panel = new JPanel();
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.setSize(400, 800);
			
			panel.add(NameLabel);

			panel.add(FirstName);	
			panel.add(LastNameLabel);
			panel.add(LastName);
					
			panel.add(UserNameLabel);
			panel.add(UserName);
			
			panel.add(PassLabel);
			panel.add(Password);
			
			panel.add(ConfPassLabel);
			panel.add(ConfirmPass);
			
			panel.add(AgreeButton);
			panel.add(Createbutton);
			panel.add(screen );
			
			//Listener is implemented in an anonymous class.
			//This listener is for checked box
			AgreeButton.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent arg0) 
				{					
					clearTextArea();		
					//display
					setDisplay(getLine());	
					
					//Listener is implemented in an anonymous class.
					//This listener is for Create Button
					Createbutton.addActionListener(new ActionListener()
					{
						@Override
						public void actionPerformed(ActionEvent arg0) 
						{				
							clearTextArea();
					
							String first = getFirstName();
							String last =  getLastName();
							String username = getUserName();
							String pass =  getPassword();
							// call Model to add the line
							addLine(first);
							addLine(last);
							addLine(username);
							addLine(pass);
							//call View to display
							setDisplay2(getLine());
							
							try {
								saveToFile();
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						
						}
					
					});
				}		
			});
			
			
			this.add(panel);
	
		}
		public void addLine(String line)
		{
				display += line;		
		}
		/**
		 * Get a Line 
		 * @return Display
		 */
		public String getLine()
		{
			return display;
		}

		public String getFirstName()
		{
			return FirstName.getText();
		}
		public String getLastName()
		{
			return LastName.getText();
		}
		public String getUserName()
		{
			return UserName.getText();
		}
		public String getPassword()
		{
			if (Password.getText().equals(ConfirmPass.getText()))
				{
					return  Password.getText();
				}
			else return "Password doesn't match";
		}
		/**
		 * serve as View
		 * @return
		 */
		public String getDisplay()
		{
			return screen.getText();
		}
		
		
		public void setDisplay(String result)
		{
			
			String input = "          Must be 21 or older to book a room.\n"
					+ "          Please note that for security purposes.\n"
					+ "          You will be asked to provide a valid government or state-issued\n"
					+ "          photo ID at check-in.\n"
					+ "\n"
					+ "          Guarantee Policy:\n"
					+ "          Reservation must be guaranteed by a major credit card at the\n"
					+ "          time of booking.\n "
					+ "          Card will not be charged until time of departure.\n"
					+"\n"
					+ "          Cancellation Policy:\n"
					+ "          Reservations must be canceled 24 hours prior to arrival to avoid\n"
					+ "          a no-show penalty of one nights room and tax.\n"
					+ "\n"
					+ "          Amenities:\n"
					+ "          Complimentary Wireless Internet.\n"
					+ "          Off-Site parking is available at a discounted rate.\n"
					+ "          Completely Non-Smoking. $250 smoking fee + taxes penalty.\n"
					+ "          $45 nightly charge per rollaway bed.\n"
					+"\n"
					+ "          CHANGES TO OUR PRIVACY POLICY\n"
					+ "          Any changes we may make to our privacy policy in the future will\n"
					+ "          be posted on this page and, where appropriate, notified to you\n"
					+ "          by e-mail.\n";		


			if(input.equals(""))
				return;
			screen.append(input+"\n");
	
		}

		public void setDisplay2(String result)
		{
			String input2 ="First Name :" +  FirstName.getText().trim();
			String input3 = "Last Name: " + LastName.getText().trim();
			String input4 =  "UserName: " + UserName.getText().trim();
			String input5 =  "Password: " + getPassword();
			if(input2.equals(""))
				return; // return empty
			// display first name and make a new line
			screen.append(input2+"\n");
			// set JtextField of first name empty
			FirstName.setText("");
			
			if(input3.equals(""))
				return;
			screen.append(input3+"\n");
			LastName.setText("");
			if(input4.equals(""))
				return;
			screen.append(input4+"\n");
			UserName.setText("");
			
			if(input5.equals(""))
				return;
			screen.append(input5+"\n");
			Password.setText("");
			ConfirmPass.setText("");
		
		}
				
		// Clear all textArea
		public void clearTextArea()
		{
			screen.setText("");
		}
		public void saveToFile() throws IOException
		{
			String input2 ="First Name :" +  FirstName.getText().trim();
			String input3 = "Last Name: " + LastName.getText().trim();
			String input4 =  "UserName: " + UserName.getText().trim();
			String input5 =  "Password: " + getPassword();
			String[] lines = {input2, input3, input4, input5};
			
			 	//write String to file
		        BufferedWriter out = new BufferedWriter(new FileWriter("GuestsList.txt", true));
		        for(String line : lines) {
		            out.write(line);
		            out.write(System.getProperty("line.separator"));
		        }
		        //close file
		        out.close();
		}
	
	}
