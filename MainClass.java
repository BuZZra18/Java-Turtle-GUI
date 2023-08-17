package FinalAssignment;

import java.awt.FlowLayout;

import javax.swing.JFrame;

import uk.ac.leedsbeckett.oop.LBUGraphics;

public class MainClass extends LBUGraphics
	{
	       
	        public MainClass()
		        {
		            JFrame MainFrame = new JFrame();           //Create a frame to display the turtle panel on.
		            MainFrame.setLayout(new FlowLayout());      //not strictly necessary
		            MainFrame.add(this);                                        //"this" is this object that extends turtle graphics so we are adding a turtle graphics panel to the frame
		            MainFrame.pack();                                           //set the frame to a size we can see
		            MainFrame.setVisible(true);                         //now display it
		            about();                                                            //call the LBUGraphics about method to display version information.
		        }
	        
	        @Override
			public void processCommand(String arg0) {
				// TODO Auto-generated method stub
				
			}
	        
	        public static void main(String[] args)
		        {
		                MainClass Draw = new MainClass(); //Creating instance of MainClass that extends LBUGraphics.
		        }
		
	}
