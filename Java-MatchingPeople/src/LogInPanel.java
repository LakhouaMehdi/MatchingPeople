
import java.awt.*;

import javax.swing.*;


//Lakhoua Mehdi <Lakhoua_Mehdi@yahoo.com>

public class LogInPanel extends JPanel //login panel
{                                      
	
	protected JButton loginBtn;
	protected JButton registerBtn;
	private JLabel usernameLbl,passwordLbl;
	private MyCustomTextField usernameTxt;
	//myTextField extends JTextfield. added max # of characters.
	//myPasswordField extends JPasswordfield. added max # of characters and shows password when mouse enters box.
	private MyCustomPasswordField passwordFld;
	
	
	public LogInPanel()
	{ 	
		initialize(this);        //JPanel is subclass of Container.
		formatComponents(this);  //JPanel is subclass of Container.
	}

	public void initialize (Container container) {  //This method handles Layout (temporary layout)
		
		//This panel contains 2 panels. The top panel contains 2 labels and 2 textFields 
		//to enter user name and password
	    //The bottom panel contains 2 buttons. one for login and one for registering
		
		container.setLayout(new GridLayout(2,1));
		container.setBackground(Color.WHITE);

		
	//Top Panel
		//create top panel (will contain labels and textFields)
		JPanel topPanel = new JPanel();
	    topPanel.setLayout(new GridBagLayout());     
		GridBagConstraints gbc = new GridBagConstraints();
		//background color of top panel
		topPanel.setBackground(new Color(0, 206, 209));
		//create labels and text fields for panel
		usernameLbl= new JLabel ("Username:  ");
		passwordLbl= new JLabel ("Password:  ");
		usernameTxt= new MyCustomTextField(20, DataType.PASSWORD);
		passwordFld= new MyCustomPasswordField(20);
		//add Labels and text fields to top panel (2 labels 2 text fields)
		gbc.gridx = 0;
		gbc.gridy = 0;
		topPanel.add(usernameLbl,gbc);
		gbc.gridy++;
		topPanel.add(passwordLbl,gbc);
		gbc.anchor=GridBagConstraints.EAST;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridy=0;
		gbc.gridx++;
		topPanel.add(usernameTxt,gbc);
		gbc.gridy++;
		topPanel.add(passwordFld,gbc);
		gbc.anchor = GridBagConstraints.WEST;
		gbc.fill = GridBagConstraints.NONE;
	//Bottom Panel
		//create bottom panel
		JPanel bottomPanel=new JPanel(new FlowLayout(FlowLayout.CENTER,30,20));
		//background of bottom panel
		bottomPanel.setBackground(SystemColor.desktop);
		//create buttons for panel
		loginBtn= new JButton("Login");
		registerBtn= new JButton("Register");
		//note: action listeners for the buttons are added in LogInWizard class for now. because i want to keep
		      //the GUI and the program logic separated for now
	          //have to make the buttons protected not private to be allowed to do that
		//add buttons to bottom panel
		bottomPanel.add(loginBtn);
		bottomPanel.add(registerBtn);
	//add panels to container	
		container.add(topPanel);
		container.add(bottomPanel);
	    //topPanel.setOpaque(false);
		//bottomPanel.setOpaque(false);
		
	}
	
	   
	//TOOLS

	   
	public void formatComponents(Container container) {  //This method handles format of the components in the container.  
		                     // to do: maybe create a class panelFormatter. or a method in common for all panels. p.low
			                 // to do: agree on layout of panels, agree on format of panels and components. p.medium
			Font lblFont = new Font("Times new roman",Font.PLAIN, 20);
			Font btnFont = new Font ("Times new roman",Font.PLAIN,20);
			Font txtFont = new Font ("Times new roman",Font.PLAIN,20);
			
		    for (Component component : container.getComponents()) {
		        if (component instanceof JTextField) {  //text field settings. passwordfield is subclass of textfield
		         //((JTextField)component).setText("");
		         //((JTextField) component).setOpaque(false); 
		         //((JTextField) component).setBorder(null);
		           ((JTextField) component).setForeground(Color.BLACK);
		           ((JTextField) component).setFont(txtFont);
		         //((JTextField) component).setAlignmentX(SwingConstants.CENTER);
		             /*  if (component instanceof JPasswordField) {  //additional settings for passwordfield
					      //((JPasswordField)component).setText("");
						  //((JPasswordField)component).setOpaque(false); 
				          //((JPasswordField)component).setBorder(null);
				          //((JPasswordField)component).setForeground(Color.BLACK);
						    ((JTextField) component).setFont(txtFont);
						           ((JTextField) component).setAlignmentX(SwingConstants.CENTER);
						        } */
		        } 
		        else if (component instanceof JLabel) {  //label settings
		           ((JLabel) component).setFont(lblFont);
		         //((JLabel) component).setAlignmentX(SwingConstants.CENTER);
		         //((JLabel) component).setForeground(Color.BLACK);
		        }
		        else if (component instanceof JButton) {   //button settings        
		    	 //((JButton) component).setForeground(Color.BLACK);   
		    	 //((JButton) component).setBackground(Color.WHITE);   
		    	 //((JButton) component).setBorder(null);              
		    	   ((JButton) component).setFont(btnFont);
		    	 //((JButton) component).setAlignmentX(SwingConstants.CENTER);
		    	 //	((JButton) component).setContentAreaFilled(false);
		    	   ((JButton) component).  setSize(new Dimension(126, 75));
		    	   ((JButton) component).  setFocusable(false);
		    	 //((JButton) component).  setFocusPainted(false);
		        }
		        else if (component instanceof JRadioButton){  //radio button settings
		        	//((JRadioButton) component).setBackground(Color.WHITE);
		        	//((JRadioButton) component).setAlignmentX(SwingConstants.CENTER);
		        	((JRadioButton) component).setFont(lblFont);
		        	((JRadioButton) component).setOpaque(false);
		        	//((JRadioButton) component).setForeground(Color.BLACK);;
		        	((JRadioButton) component).setFocusable(false);
		        }
		        else if (component instanceof Container) { //if component is a container call method on it
		           formatComponents((Container)component);
		        }
		    } 
		    // end for loop
		}
	//end formatComponents
}
//END OF FILE
