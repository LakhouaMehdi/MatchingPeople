
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
//import javax.swing.event.*;
//import java.util.*;
//import java.io.*;


//Lakhoua Mehdi <Lakhoua_Mehdi@yahoo.com>

public class RegisterPanel extends JPanel  //register window
{	
	
	protected JButton confirmBtn;
	protected JButton cancelBtn;
	private JLabel usernameLbl,passwordLbl,confirmPasswordLbl,emailLbl;
	private MyCustomTextField usernameTxt,emailTxt; 
	//myTextField extends JTextfield. added max # of characters.
	//myPasswordField extends JPasswordfield. added max # of characters and shows password when mouse enters box.
	private MyCustomPasswordField passwordFld,confirmPasswordFld;  
	private JRadioButton acceptRulesCheckBox;

	public RegisterPanel() { 
		
		initialize(this);            //JPanel is subclass of Container
		formatComponents(this);      //JPanel is subclass of Container
	}
	public void initialize (Container container) { //layout is handled here  (temporary layout)
		
		 //This panel contains 2 panels. The top panel contains 4 labels and 4 textFields to enter user name,password,
		 //confirm password and email and 1 radio button.
	     //The bottom panel contains 2 buttons. one to register and one to cancel
		container.setLayout(new GridLayout(2,1));
		container.setBackground(Color.WHITE);
			
	//Top Panel
		//create top panel (will contain 4 labels and 4textFields and 1 radio button)
		JPanel topPanel = new JPanel();
	    topPanel.setLayout(new GridBagLayout());    
		GridBagConstraints gbc = new GridBagConstraints();
		//background of top panel
		topPanel.setBackground(new Color(0, 206, 209));
		//create labels and text fields for panel
		usernameLbl= new JLabel ("Username:");
		passwordLbl= new JLabel ("Password:");
		confirmPasswordLbl= new JLabel ("Confirm password:");
		emailLbl = new JLabel ("Email:");
		usernameTxt= new MyCustomTextField(20, DataType.USERNAME, true);
		passwordFld= new MyCustomPasswordField(20, true);
		confirmPasswordFld= new MyCustomPasswordField(20, true);
		emailTxt = new MyCustomTextField(20, DataType.EMAIL, true);
		acceptRulesCheckBox = new JRadioButton("I accept the rules");
		//add Labels and text fields to top panel
		gbc.gridx = 0;
		gbc.gridy = 0;
		topPanel.add(usernameLbl,gbc);
		gbc.gridy++;
		topPanel.add(passwordLbl,gbc);
		gbc.gridy++;
		topPanel.add(confirmPasswordLbl,gbc);
		gbc.gridy++;
		topPanel.add(emailLbl,gbc);
		gbc.gridy++;
    	topPanel.add(acceptRulesCheckBox,gbc);
		gbc.anchor=GridBagConstraints.EAST;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridy=0;
		gbc.gridx++;
		topPanel.add(usernameTxt,gbc);
		gbc.gridy++;
		topPanel.add(passwordFld,gbc);
		gbc.gridy++;
		topPanel.add(confirmPasswordFld,gbc);
		gbc.gridy++;
		topPanel.add(emailTxt,gbc);
		gbc.anchor = GridBagConstraints.LAST_LINE_START;
		gbc.fill = GridBagConstraints.CENTER;
		
	//Bottom Panel
		//create bottom panel
		JPanel bottomPanel=new JPanel(new FlowLayout(FlowLayout.CENTER,30,20));
		//background of bottom panel
		bottomPanel.setBackground(SystemColor.desktop); 
		//create buttons for panel
		confirmBtn= new JButton("Confirm");
		cancelBtn= new JButton("Cancel");
		//note: action listeners for the buttons are added in LogInWizard class for now. because i want to keep
				//the GUI and the program logic separated for now
				//have to make the buttons protected not private to be allowed to do that
		//add buttons to bottom panel
		bottomPanel.add(confirmBtn);
		bottomPanel.add(cancelBtn);
	    //add panels to container	
		container.add(topPanel);
		container.add(bottomPanel);
	//	topPanel.setOpaque(false);
	//	bottomPanel.setOpaque(false);
		   
	   }
	 
	
	//METHODS NEEDED
	   //boolean isGoodPassword(String passwordEntered); verifies has at least 6 char
	   //boolean isGoodUsername(String usernameEntered); verifies user name has at least 6 char
	   //boolean isGoodEmail(String emailEntered);       verifies email is valid
	   //check both passwords match
	   //check user doesn't already exist
	
	//TOOLS
	public void formatComponents(Container container) {  //This method handles format of the components in the container
		
		Font lblFont = new Font("Times new roman",Font.PLAIN, 20);
		Font btnFont = new Font ("Times new roman",Font.PLAIN,20);
		Font txtFont = new Font ("Times new roman",Font.PLAIN,20);
		
	    for (Component component : container.getComponents()) {
	        if (component instanceof JTextField) {  //text field settings. JPasswordField is subclass of JTextField
	         //((JTextField)component).setText("");
	         //((JTextField) component).setOpaque(false); 
	         //((JTextField) component).setBorder(null);
	           ((JTextField) component).setForeground(Color.BLACK);
	           ((JTextField) component).setFont(txtFont);
	         //((JTextField) component).setAlignmentX(SwingConstants.CENTER);
	            /*   if (component instanceof JPasswordField) {  //additional settings for passwordfield
				      //((JPasswordField)component).setText("");
					  // ((JPasswordField)component).setOpaque(false); 
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
