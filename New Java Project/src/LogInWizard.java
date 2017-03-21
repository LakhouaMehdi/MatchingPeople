
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
//import javax.swing.event.*;
//import java.util.*;
//import java.io.*;


//Lakhoua Mehdi <Lakhoua_Mehdi@yahoo.com>

public class LogInWizard extends JFrame implements ActionListener  //handles login and registration
{  
	private Container c;
	
LogInPanel log;           //maybe  //these are the same login and register panels being shown every time
RegisterPanel reg;        //maybe //so modifications stay. to think about.

	public LogInWizard() { 
		//create a container
		c=this.getContentPane();
		//create login and register panels as member variables
		log= new LogInPanel();
		reg=new RegisterPanel();
		//add action listeners to the buttons in those panels
		log.registerBtn.addActionListener(this); 
		log.loginBtn.addActionListener(this);
		reg.cancelBtn.addActionListener(this);
		reg.confirmBtn.addActionListener(this);
		//we start with the container having the loginPanel
		c.add(log);
	}
	
	
	public void actionPerformed(ActionEvent e)
	{   
	if ( e.getSource()== log.loginBtn){
		//check if password and user name match an existing user
	    //log in and open the program
		
		  }
    else if (e.getSource()== log.registerBtn){
    	//user doesn't have an account. must show registration screen
    	//remove login screen, add registration screen, revalidate/repaint
    	    //note: can do this by using card layout for container c maybe better but it's working for now
    	c.remove(log);
    	c.add(reg);
    	this.setTitle("Register"); 
    	// probably can change size of the window somewhere here if needed
    	this.revalidate();
    	this.repaint();
    	this.setContentPane(c);
    	
    	}
    else if (e.getSource()== reg.cancelBtn) {
       //user canceled registration. must go back to login screen.
       //same thing
    	c.remove(reg);
    	c.add(log); 
    	this.setTitle("Log In");
    	this.revalidate();
        this.repaint();
    	this.setContentPane(c);
    }
    else if (e.getSource()== reg.confirmBtn) {
    	//user clicked confirm 
    	//must check password is valid , username is valid, email is valid, rules are accepted
    	//and user doesn't already exist.
    	//if everything is good create the account. tell what is wrong
    	
      }
		
	}	//end action performed  
	
	public static void main(String [] args )
	{   
		
		LogInWizard win = new LogInWizard();
	//	win.setResizable(false);
		win.setSize(600,500);
		win.setVisible(true);
		win.setTitle("Welcome, log in here please.");
		win.setLocationRelativeTo(null);  //centers The window
		win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
	 
	}
	}
		  //END TOOLS



//END OF FILE
