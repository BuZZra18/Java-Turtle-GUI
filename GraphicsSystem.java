package FinalAssignment;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import uk.ac.leedsbeckett.oop.LBUGraphics;

public class GraphicsSystem extends LBUGraphics implements ActionListener
	{	
		//Integers values stored to be used in fill method.
		public int radius, side, length, breadth;	
		public int squareX, squareY, rectangleX, rectangleY, circleX, circleY;
		
		//Integer array to store coordinates of triangle to be used in fill method.
		public int [] triangleX = new int[3];
		public int [] triangleY = new int[3];
		public int [] ScaleneTriangleX = new int[3];
		public int [] ScaleneTriangleY = new int[3];
				
		//GraphicsSystem constructor to create a frame with turtle.
		public GraphicsSystem()
			{
				JFrame MainFrame = new JFrame("Turtle GUI"); 
				MainFrame.setLayout(new FlowLayout());
		        MainFrame.add(this);                                        
		        MainFrame.pack();                                          
		        MainFrame.setVisible(true); 
		        MainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		        penDown();	        	        	        	        
		        
		        white = new JButton("");
		        white.setBackground(Color.white); 
		        white.addActionListener(this);
		        this.add(white); 
		        	        	        
		        black = new JButton("");
		        black.setBackground(Color.black); 
		        black.addActionListener(this);
		        this.add(black);         	        
		        
		        save = new JButton("Save"); 
		        save.addActionListener(this);
		        this.add(save);   
		        	           	        	        
		        load = new JButton("Load");	         
		        load.addActionListener(this);
		        this.add(load);  	              
		        	       
		        view = new JButton("View");  
		        view.addActionListener(this);
		        this.add(view); 
		        		        
		        colour = new JButton("Pen Colour");
		        colour.addActionListener(this);
		        this.add(colour);  
		        
		        exit = new JButton("Exit"); 
		        exit.addActionListener(this);
		        this.add(exit);  
		        
		        MainFrame.addWindowListener(new WindowAdapter() 
			        {
			        	@Override
			        	public void windowClosing(WindowEvent e) 
				        	{				        			
								try  
									{  
										File warningfile = new File("Useless.txt");      
										FileReader readWarning = new FileReader(warningfile);  
										BufferedReader readWarning_2 = new BufferedReader(readWarning); 
										String line, line2 = "";
										
										while((line = readWarning_2.readLine())!= null)
											{
												line2 = line;
											}
										
										if(!line2.contains("saveimg"))
										  	{
												int warning = JOptionPane.showConfirmDialog(MainFrame, "Are you sure you want to close the application without closing?", "Warning!!!", 
										        			  JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
										        		
										        if (warning == JOptionPane.YES_OPTION) 
											        {
											        	System.exit(0);
											        }
										        		
										        else if (warning == JOptionPane.NO_OPTION) 
											        {
										        		saveImg();
											        	displayMessage("Image has been saved successfully.");
											        														        	
														try (FileWriter writeCommand = new FileWriter("Useless.txt", true)) 
															{
																writeCommand.write("saveimg\n");
															}
											        			
											        	catch (IOException e1) 
												        	{
																e1.printStackTrace();
															}
											        	MainFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);	
											        }
										  	}
										JOptionPane.showMessageDialog(MainFrame, "Image has been saved successfully,\nYou may exit the program.");
										System.exit(0);
										
									}
								
								catch(IOException e1)  
									{  
										e1.printStackTrace();  
									}  
					        }								
			        });	        
			}	
		
		//setRadius method to save the radius value from circle method.
		public  void setRadius(int radius)
			{
				this.radius = radius;
			}
		
		//setSide method to save sides value from square method.
		public  void setSide(int side)
			{
				this.side = side;
			}
		
		//setLength_Breadth method to save length and breadth value from rectangle method.
		public  void setLength_Breadth(int length, int breadth)
			{
				this.length = length;
				this.breadth = breadth;			
			}

		JButton black;
		JButton white;
		JButton save;
		JButton load;
		JButton view;
		JButton colour;
		JButton exit;
			
		//actionPerformed method to call methods in resposne to the button clicked.
		public void actionPerformed(ActionEvent e) 
			{
				super.actionPerformed(e);
				if(e.getSource() == black)
					{
						clearInterface();
						setBackground_Col(Color.black);
						clear();
					}
				
				else if(e.getSource() == white)
					{
						clearInterface();
						setBackground_Col(Color.white);
						clear();
					}
				
				else if(e.getSource() == save)
					{
						saveImg();
						displayMessage("The image has been saved successfully.");
						save.setEnabled(false);
					}
				
				else if(e.getSource() == load)
					{
						load();
						displayMessage("The image has been loaded successfully.");
						load.setEnabled(false);
					}
				
				else if(e.getSource() == view)
					{
						ViewImage viewImg = new ViewImage();
						viewImg.loadImg();	
						view.setEnabled(false);
					}
				
				else if(e.getSource() == colour)
					{
						clearInterface();
						Color panel = JColorChooser.showDialog(null, "Pen Colour", Color.red);
			            setPenColour(panel);
					}
				
				else if(e.getSource() == exit)
					{
						JOptionPane.showMessageDialog(exit, "Make sure you have saved the image before exiting.");
						System.exit(0);									
					}
			}
			        	        		
		//square method to create a square.
		public void square(int side)
			{
				for(int i = 0; i < 4; i++)
					{
						forward(side);
						turnRight(90);
					}
				squareX = getxPos();
				squareY = getyPos();
			}	
		
		//rectangle method to create a rectangle.
		public void rectangle(int length, int breadth)
			{		
				if(length == breadth)
					{
						displayMessage("Length and breadth cannot be equal to form a rectangle.");
					}
					
				else
					{
						forward(length);
						turnRight(90);
						forward(breadth);
						turnRight(90);
						forward(length);
						turnRight(90);
						forward(breadth);
						turnRight(90);
					}
				rectangleX = getxPos();
				rectangleY = getyPos();
			}	
		
		//triangle method which creates an equilateral triangle.
		public void triangle(int side)
			{
				forward(side);				
				turnRight(120);
				triangleX[0] = getxPos();
				triangleY[0] = getyPos();
				forward(side);				
				turnRight(120);
				triangleX[1] = getxPos();
				triangleY[1] = getyPos();
				forward(side);
				turnRight(120);
				triangleX[2] = getxPos();
				triangleY[2] = getyPos();
			}	
			
		//Overloaded triangle method to create a triangle with three parameters.
		public void triangle(int length, int length_2, int length_3) 
			{
				penDown();
		        
				int angle = (int) Math.round(Math.toDegrees(Math.acos((Math.pow(length_2, 2) 
							+ Math.pow(length_3, 2) - Math.pow(length, 2)) / (2 * length_2 * length_3))));
				
			    int angle_2 = (int) Math.round(Math.toDegrees(Math.acos((Math.pow(length, 2) 
			    			  + Math.pow(length_3, 2) - Math.pow(length_2, 2)) / (2 * length * length_3))));
			    
			    int angle_3 = (int) Math.round(Math.toDegrees(Math.acos((Math.pow(length, 2) 
			    			  + Math.pow(length_2, 2) - Math.pow(length_3, 2)) / (2 * length * length_2))));
		        
		        angle = 180 - angle;
		        angle_2 = 180 - angle_2;
		        angle_3 = 180 - angle_3;
		        
		        if(length <= 100 || length_2 <= 100 || length_3 <= 100)
		        	{
		        		displayMessage("Please enter parameter greater than 100.");
		        	}
		        
		        else
		        	{
						forward(length_3);
						turnRight(angle_2);
						ScaleneTriangleX[0] = getxPos();
						ScaleneTriangleY[0] = getyPos();
						forward(length);
						turnRight(angle_3);
						ScaleneTriangleX[1] = getxPos();
						ScaleneTriangleY[1] = getyPos();
						forward(length_2);
						turnRight(angle);
						ScaleneTriangleX[2] = getxPos();
						ScaleneTriangleY[2] = getyPos();
						clearInterface();
		        	}
			}
		
		//PenColour method to set the pen colour with r, g, b value.
		public void PenColour(int colour1, int colour2, int colour3)
			{
				setPenColour(new Color(colour1, colour2, colour3));
			}
		
		//BackgroundColour method to set the background colour with r, g, b value.
		public void BackgroundColour(int colour1, int colour2, int colour3)
			{
				setBackground_Col(new Color(colour1, colour2, colour3));
			}
		
		//circle method to create a circle outline.
		public void circle(int radius)
			{
				Graphics g = getGraphicsContext();
				g.setColor(PenColour);
				circleX = getxPos();
				circleY = getyPos();
				g.drawOval(circleX, circleY, radius, radius);
			}				
		
		//fill method to fill colour in outline of shapes.
		public void fill()
			{
				Graphics2D g = this.getGraphics2DContext();
				g.setColor(PenColour);
				g.fillOval(circleX, circleY, radius, radius);
				g.fillRect((squareX - side), (squareY - side), side, side);
				g.fillRect((rectangleX - length), (rectangleY - breadth), length, breadth);
				g.fillPolygon(triangleX, triangleY, 3);
				g.fillPolygon(ScaleneTriangleX, ScaleneTriangleY, 3);
			}
		
		//polygon method to create a outline of polygon having sides between 3 and 10.
		public void polygon(int sides, int size)
			{
				if(!(sides >= 3 && sides <= 10))
					{
						displayMessage("Please enter number of sides between 3 and 10");
					}
				
				else
					{
						int angle = 360 / sides;
						for(int i=0; i < sides; i++)
							{
								forward(size);
								turnRight(angle);
							}	
						clearInterface();
					}
			}
	
		//about method to display a dancing turtle.
		public void about()
			{
				super.about();
				displayMessage("Prashant Muni Bajracharya");
			}
		
		//save method which stores the commands passed through the String command variable in a txt file.
		public void save(String command) 
			{	
				try 
					{		
						if(command.equals("save") || command.equals("load") || command.equals("exit") 
						   || command.equals("quit") || command.equals("view") || command.equals("saveimg")
						   || command.equals("view"))
							{
							  	File commandFile2 = new File("Useless.txt");
							  
							  	FileWriter writeCommandFile2 = new FileWriter(commandFile2, true);
							  	writeCommandFile2.write(command);
							  	writeCommandFile2.write("\r\n");
							  	writeCommandFile2.close();
							}
						
						else
							{
								File commandFile = new File("Commands.txt");
								commandFile.createNewFile();
										
								FileWriter writeCommandFile = new FileWriter(commandFile, true);
								writeCommandFile.write(command);
								writeCommandFile.write("\r\n");
								writeCommandFile.close();
							}						
					} 
					
				catch (IOException e) 
					{
						System.out.println("The file cannot be created and written.");
						e.printStackTrace();
					}
			}
		
		//extractInt method to extract integers from a given string.
		 static String extractInt(String integers)
		    {
		       
		        integers = integers.replaceAll("[^\\d]", " ");
		        integers = integers.trim();
		        integers = integers.replaceAll(" +", " ");
		  
		        if (integers.equals(""))
		           return "-1";
		  
		        return integers;
		    }
		 
		 //copy method which copies command from Commands.txt and pastes it to CommandsRun.txt
		 public void copy()
			 {
			 	try 
				 	{
						FileReader read_1 = new FileReader("Commands.txt");
						BufferedReader read_2 = new BufferedReader(read_1);
						FileWriter writeCommand = new FileWriter("CommandsRun.txt", true);
						String s;
			 
						while ((s = read_2.readLine()) != null) 
							{ 
								writeCommand.write(s);
								writeCommand.write("\n");
								writeCommand.flush();
							}
						read_2.close();
						writeCommand.close();
					} 
			 	
			 	catch (IOException e) 
				 	{
						e.printStackTrace();
					}
		     }	 
		 
		 //load method to load the previous graphics and display it.
		 public void load()
			 {	
			 	
			 	File commandFile = new File("Commands.txt");
			   	File readFile = new File("CommandsRun.txt");
			   	    							   			   	
			   	try 
				   	{
						readFile.createNewFile();
					} 
			   	
			   	catch (IOException e1) 
				   	{
						e1.printStackTrace();
					}		   
				copy();   
				
				try 
					 {
						  Scanner scan = new Scanner(readFile);
						  while(scan.hasNextLine())
							  {
								  String line = scan.nextLine();
								  processCommand(line);
							  }
					  }
				
				  catch (FileNotFoundException e)
					  {
					  	e.printStackTrace();
					  }
		     }
		 
		 //saveImg method which saves a png file of the graphics drawn by the turtle.
		 public void saveImg()
		 	{		
			 	BufferedImage image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
			 	Graphics2D graphic = image.createGraphics(); 
			 	printAll(graphic);
				File outputfile = new File("Turtle.png");
				 try 
					 {
						ImageIO.write(image, "png", outputfile);
					 } 
				 
				 catch (IOException e) 
					 {
						e.printStackTrace();
					 }
				displayMessage("The image has been saved successfully.");
		 	}
		 
		 //loadImg method to view the saved image.
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
		 
		 //star method to create a star shape.
		 public void star(int side)
			 {
				for(int i = 0; i < 5; i ++)
					{
						forward(side);
						turnLeft(144);
					}
			 }		
		
		 //processCommand method which takes commands in a input field and functions according to the methods called below.
		@Override
		public void processCommand(String command) 
			{
				command = command.toLowerCase();
				String parameter_string = extractInt(command);	
				String[] parameter_array = parameter_string.split(" ");
				
				int[] parameter_value = new int[parameter_array.length];
				String value = command.replaceAll("[^0-9]", "");
				
				if(!command.equals("load"))
						save(command);
				
				for(int i = 0; i < parameter_array.length; i++)
					{
						parameter_value[i] = Integer.parseInt(parameter_array[i]);
					}
								
				if(command.equals("forward ") || command.equals("backward ") || command.equals("turnleft ") ||
				   command.equals("turnright ") || command.equals("square ") || command.equals("triangle ") ||
				   command.equals("pentagon ") || command.equals("hexagon ") || command.equals("star "))
					{
						displayMessage("Please enter a parameter with the command.");
						return;
					}
		
				else if(command.equals("about"))
					{
						about();
					}
				
				
				else if(command.equals("exit") || command.equals("quit"))
					{
						System.exit(0);
					}
				
				else if(command.equals("penup"))
					{
						penUp();
						clearInterface();
					}
				
				else if(command.equals("pendown"))
					{
						penDown();
						clearInterface();
					}
				
				else if(command.equals("load"))
					{
						load();
						clearInterface();
					}
				
				else if(command.equals("save"))
					{
						displayMessage("The command has been saved successfully.");
					}
				
				else if(command.equals("save img") || command.equals("saveimg"))
					{
						saveImg();
					}
				
				else if(command.equals("fill"))
					{						
						fill();
						clearInterface();
					}
				
				else if(command.equals("stroke "+ parameter_value[0]))
					{ 
						setStroke(parameter_value[0],false);
						clearInterface();
					}
			
				else if(command.equals("turnleft "+ parameter_value[0]))
					{ 
						turnLeft(parameter_value[0]);
						clearInterface();
					}	
						
				else if(command.equals("turnright "+ parameter_value[0]))
					{
						turnRight(parameter_value[0]);
						clearInterface();
					}
						
				else if(command.equals("forward " + parameter_value[0]))
					{
						forward(parameter_value[0]);
						clearInterface();
					}
						
				else if(command.equals("backward " + parameter_value[0]))
					{
						int negativeValue = parameter_value[0] * -1;
						forward(negativeValue);
						clearInterface();
					}
				
				else if(command.equals("black"))
					{
						setPenColour(Color.black);
						clearInterface();
					}
				
				else if(command.equals("green"))
					{
						setPenColour(Color.green);
						clearInterface();
					}
				
				else if(command.equals("red"))
					{
						setPenColour(Color.red);
						clearInterface();
					}
				
				else if(command.equals("white"))
					{
						setPenColour(Color.white);
						clearInterface();
					}
				
				else if(command.equals("reset"))
					{
						reset();
						turnLeft(90);
						clearInterface();
					}
				
				else if(command.equals("clear"))
					{
						clear();
						clearInterface();
					}	
				
				else if(command.equals("new"))
					{
						clear();
						clearInterface();
					}	
				
				else if(command.equals("square " + parameter_value[0]))
					{
						square(parameter_value[0]);
						setSide(parameter_value[0]);
						clearInterface();
					}
				
				else if(command.equals("triangle " + parameter_value[0]))
					{
						triangle(parameter_value[0]);
						clearInterface();
					}
				
				else if(command.equals("circle " + parameter_value[0]))
					{
						circle(parameter_value[0]);
						setRadius(parameter_value[0]);
						clearInterface();
					}
				
				else if(command.equals("star " + parameter_value[0]))
					{
						star(parameter_value[0]);
						clearInterface();
					}
				
				else if(command.contains("triangle ") && parameter_value.length == 3)	
					{
						if(command.equals("triangle " + parameter_value[0] + " " + parameter_value[1] + " " + parameter_value[2]))
							{
								triangle(parameter_value[0], parameter_value[1], parameter_value[2]);
							}
						
						else if (command.equals("triangle -" + parameter_value[0] + " -" + parameter_value[1] + " -" + parameter_value[2]))				
							{
								displayMessage("Please enter non negative parameters.");
							}			
						
						else
							displayMessage("Please enter a valid command with three positive parameters.");
					} 
				
				else if(command.contains("pen ") && parameter_value.length == 3)
					{
						if(command.equals("pen " + parameter_value[0] + " " + parameter_value[1] + " " + parameter_value[2]))
							{
								PenColour(parameter_value[0], parameter_value[1], parameter_value[2]);
								clearInterface();
							}
						
						else if (command.equals("pen -" + parameter_value[0] + " -" + parameter_value[1] + " -" + parameter_value[2]))				
							{
								displayMessage("Please enter non-negative parameters.");
							}			
					
						else
							displayMessage("Please enter a valid command with three positive parameters.");
					}
				
				else if(command.contains("background ") && parameter_value.length == 3)
					{
						if(command.equals("background " + parameter_value[0] + " " + parameter_value[1] + " " + parameter_value[2]))
							{
								BackgroundColour(parameter_value[0], parameter_value[1], parameter_value[2]);
								clear();
								clearInterface();
							}
						
						else if (command.equals("background -" + parameter_value[0] + " -" + parameter_value[1] + " -" + parameter_value[2]))				
							{
								displayMessage("Please enter non negative parameters.");
							}			
					
						else
							displayMessage("Please enter the command with three positive parameters.");
					}
				
				else if(command.contains("polygon ") && parameter_value.length == 2)
					{
						 if(command.equals("polygon "+ parameter_value[0] + " " + parameter_value[1]))
							{ 
								polygon(parameter_value[0], parameter_value[1]);
							}	
						 
						 else if (command.equals("polygon -" + parameter_value[0] + " -" + parameter_value[1]))				
							{
								displayMessage("Please enter non negative parameters.");
							}	
						 
						 else
								displayMessage("Please enter a valid command with two positive parameters.");
					}
				
				else if(command.contains("rectangle ") && parameter_value.length == 2)
					{
						 if(command.equals("rectangle "+ parameter_value[0] + " " + parameter_value[1]))
							{ 
								rectangle(parameter_value[0], parameter_value[1]);
								setLength_Breadth(parameter_value[0], parameter_value[1]);
								clearInterface();
							}	
						 
						 else if (command.equals("rectangle -" + parameter_value[0] + " -" + parameter_value[1]))				
							{
								displayMessage("Please enter non-negative parameters.");
							}	
						 
						 else
								displayMessage("Please enter a valid command with two positive parameters.");
					}												
							
				else if(command.equals("forward -" + parameter_value[0]) || command.equals("backward -" + parameter_value[0]) || command.equals("turnleft -" + parameter_value[0]) || 
						command.equals("turnright -" + parameter_value[0]) || command.equals("square -" + parameter_value[0]) || command.equals("triangle -" + parameter_value[0]) ||
						command.equals("stroke -" + parameter_value[0]) || command.equals("star -" + parameter_value[0]))
					{
						displayMessage("Please enter a non-negative parameter.");
					}
				
				else if(command.equals("forward") || command.equals("backward") || command.equals("turnleft") || 
						command.equals("turnright") || command.equals("square") || command.equals("triangle") || 
						command.equals("pen") || command.equals("polygon") || command.equals("stroke") ||
						command.equals("star") || command.equals("background") || command.equals("rectangle"))
					{
						displayMessage("Please enter a whitespace character then parameter.");
					}
				
				else if(command.equals("forward ") || command.equals("backward ") || command.equals("turnleft ") || 
						command.equals("turnright ") || command.equals("square ") || command.equals("triangle ") ||
						 command.equals("stroke ") || command.equals("star "))
					{
						displayMessage("Please enter a parameter with the command.");
					}
				
				else if (command.contains("forward " + value) || command.contains("backward " + value) || command.contains("turnleft " + value) ||
						 command.contains("turnright " + value) || command.contains("square " + value) || command.contains("triangle " + value) ||					  
						 command.contains("pen " + value) || command.contains("polygon " + value) || command.contains("stroke " + value) ||
						  command.contains("star " + value) || command.contains("background " + value)  || command.contains("rectangle " + value))
					{
						displayMessage("Please enter a numeric value.");
					}					  
				
				else
					{
						displayMessage("Please enter a valid command.");
					}
							
};	

	public static void main(String args[])
		{						
			GraphicsSystem a = new GraphicsSystem();
		}

}