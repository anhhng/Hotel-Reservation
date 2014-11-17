package projecttester;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class ReceiptGUI extends JFrame
{
   private Receipt receipt;
   
   public ReceiptGUI(Receipt aReceipt)
   {
      receipt = aReceipt;
      
      JTextArea area = new JTextArea();
      area.setText(receipt.print());
      area.setPreferredSize(new Dimension(500,300));
      add(area, BorderLayout.NORTH);
      
      JPanel panel = new JPanel();
      JButton button = new JButton("Continue");
      panel.add(button);
      button.addActionListener(new ActionListener()
      {
         public void actionPerformed(ActionEvent e)
         {
            System.out.println("Continue button pressed!");
         }
      });
      add(panel);
      
   }
}