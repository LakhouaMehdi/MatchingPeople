import java.awt.*;
import javax.swing.*;
import javax.swing.text.Document;
import java.awt.event.*;


//Lakhoua Mehdi <Lakhoua_Mehdi@yahoo.com>

//This class provides similar features to MyCustomTextField but uses key listener that allows to hide/show password when the mouse enters the box.
//see MyCustomTextField.java for details
//it's good use this class for PASSWORD DataType if you want that feature. see DataType.java

public final class MyCustomPasswordField extends JPasswordField implements MouseListener, KeyListener //note: this is not a subclass of MyCustomTextField.
{  
  private int maxCharAllowed;    //it is 30 by default. see init() method. it can be changed via setter method.
                                //copy pasting is disabled in these password fields.
  
  private boolean enableColoring=false; //enables coloring of textField depending on whether data is valid or not.
                                        //false by default unless provided true to a constructor or you used setter.
  
  DataValidityChecker checker;  //has a DataValidityChecker that will determine whether the password is valid or not.
                                //the DataType of the DataValidityChecker will be password and must not be allowed to change.
                                //for other DataTypes use MyCustomTextField class.
  
    MyCustomPasswordField() {  //i kept some of the super constructors but probably won't need them.
	super(); 
	this.init();
	}	
	MyCustomPasswordField(int columns) {
	super(columns);
	this.init();
	}
	MyCustomPasswordField(Document doc, String txt, int columns) {
	super(doc, txt, columns);
	this.init();
	}
	MyCustomPasswordField(String text) {
	super(text);
	this.init();
	}
	MyCustomPasswordField(String text, int columns) {
	super(text, columns);
	this.init();
	}
	MyCustomPasswordField(boolean coloring) { //added constructors
		super();
		enableColoring=coloring;
		this.init();
		}
	
	MyCustomPasswordField(int columns,boolean coloring) { //added constructors
		super(columns);
		enableColoring=coloring;
		this.init();
		}	

public void init() {
	this.checker=new DataValidityChecker(DataType.PASSWORD);//Type of the validity checker is PASSWORD. do not change it to something else. use mycustomtextfield instead
	maxCharAllowed=30;        //make this  maxCharAllowed= this.checker.getMaxRestriciton(); //checker must be created before this statement
	this.addMouseListener(this);
	this.addKeyListener(this);
	this.setTransferHandler(null);  //disables copy pasting
	// this.setPreferredSize(new Dimension(550,30));           will handle format elsewhere
	
}

//setters and getters
public void setEnableColoring(boolean b){
	this.enableColoring=b;
}	
public boolean getEnableColoring(){
	return this.enableColoring;
}
	
 public void setMaxChar(int maxChar) {
	 this.maxCharAllowed=maxChar;
	 }
   
 public int getMaxChar() {
	 return maxCharAllowed;
	 }	
 
//coloring
 void highlightRed() {
	// this.setBorder(BorderFactory.createMatteBorder(1,1,1,1,Color.RED));
	 this.setBackground(Color.RED);
 }

 void highlightGreen() {
	// this.setBorder(BorderFactory.createMatteBorder(1,1,1,1,Color.GREEN));
	 this.setBackground(Color.GREEN);
 }
 
 void removeHighlight() {
	// this.setBorder(BorderFactory.createMatteBorder(1,1,1,1,Color.BLACK));
	 this.setBackground(Color.WHITE);
}
 public void updateColor(){
	 checker.setData(String.valueOf(this.getPassword()));
		if(enableColoring==true){  
			   if (String.valueOf(this.getPassword()).equals("") && this.containsValidPassword() == false) {this.removeHighlight();}  // if passwordfield is empty want no color. whether data is valid or not
			   else if (this.containsValidPassword() == true) {this.highlightGreen();}   //if valid green if no valid red
		       else {this.highlightRed();}                            
		       }
 }
//data validity check
 public boolean containsValidPassword() {
	 checker.setData(String.valueOf(this.getPassword()));
	 return (checker.hasValidData());
	 
 }
 
 public void keyTyped(KeyEvent e) { 
	 
     if (String.valueOf(this.getPassword()).length() >= maxCharAllowed ) // limits the password field to maxChar characters
         e.consume();   //can play a sound here       //can disable some symbols here but better check it in containsValidPassword
    //note: getPassword() returns char[] . so conversion with String.valueOf() to get the text as a string.
     }
 
 
 public void keyReleased(KeyEvent e) { 
			 this.updateColor();
			 
	        }
 
 public void keyPressed(KeyEvent e) { //holding key //if user holds key the color will wait until it's released to change. even if display has already changed.
     // can disable holding key event except for backspace. p.zero. minimal impact.
	 this.updateColor();
}
public void mouseEntered(MouseEvent e) {
	this.setEchoChar((char) 0);  //makes characters show
} 
public void mouseExited(MouseEvent e) {
	this.setEchoChar('\u2022');      //makes characters hide
}  
public void mouseClicked(MouseEvent e) {	
} 
public void mouseReleased(MouseEvent e) {	
}
public void mousePressed(MouseEvent e) {
} 
}