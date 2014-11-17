package projecttester;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;


public class CancellationAndConfirmation extends JFrame
{

	public void CancelAndConfirm()
	{
		setSize(50,50);
		JPanel panel = new JPanel();
		
		JLabel label = new JLabel("Click Confirm to confirm your reservation or "
				+ "	click Cancel to cancel your resveration", SwingConstants.CENTER);
		JButton cancel = new JButton("Cancel");
	
		JButton confirm = new JButton("Confirm");
	
		panel.add(label);
		panel.add(confirm);
		panel.add(cancel);
		cancel.addActionListener(new ActionListener() 
		{ 
			@Override 
			public void actionPerformed(ActionEvent arg0)
			{ 
				//add our code to cancel reservation
				System.out.println("Cancelled");
			
			} 
		}); 
		confirm.addActionListener(new ActionListener() 
		{ 
			@Override 
			public void actionPerformed(ActionEvent arg0)
			{ 
				//add our code here to make a confirmation
				System.out.println("Confirmed");
			
			} 
		}); 
	
	
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE ); 
		frame.setLayout(new BorderLayout());
		frame.add(panel);
		frame.pack();
		frame.setVisible(true);
	}
}
