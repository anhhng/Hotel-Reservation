
package projecttester;

import javax.swing.JFrame;

public class ProjectTester
{
   public static void main(String[] args) throws InterruptedException, Exception
   {
	   //Create new Hotel object
      Hotel ADD = new Hotel();
      HotelView frame = new HotelView(ADD);
      //make the frame of HotelView is visible
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setVisible(true);
      frame.pack();
   }
}
