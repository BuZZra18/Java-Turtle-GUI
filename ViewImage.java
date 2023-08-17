package FinalAssignment;

import java.awt.FlowLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import uk.ac.leedsbeckett.oop.LBUGraphics;

public class ViewImage extends LBUGraphics
{
	
	ViewImage()
		{	
			JFrame view = new JFrame("Turtle Image");
			view.setLayout(new FlowLayout());
	        view.add(this);                                        
	        view.pack();                                          
	        view.setVisible(true); 
	        view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	        
	        setGUIVisible(false);
	        loadImg();
		}

	@Override
	public void processCommand(String arg0) 
		{
			// TODO Auto-generated method stub
			
		}
	
	public void loadImg()
	 	{		        
		 	JLabel background;
		 	setSize(getWidth(),getHeight());
		 	ImageIcon img = new ImageIcon("Turtle.png");
		 	background = new JLabel("", img, JLabel.CENTER);
		 	background.setBounds(0,0,getWidth(),(getHeight()));
		 	add(background);
		 	setVisible(true);
		 	setGUIVisible(false);
	 	}
	
}
