import java.awt.*;
import javax.swing.*;
import javax.swing.text.Document;
import java.awt.event.*;

//Lakhoua Mehdi <Lakhoua_Mehdi@yahoo.com>

//This class is a custom TextField that uses a DataValidityChecker to check the validity of the data at every key stroke.
//see DataType.java and DataValidityChecker.java.
//it colors red if data is invalid, green if valid and blank if data is "".
//Another feature is that it limits the maximum number of characters that can be entered in the textbox. extra key strokes will be discarded.

public final class MyCustomTextField extends JTextField implements KeyListener //to think about: maybe extend JFormattedTextField instead.
{  
  private int maxCharAllowed;  //this value limits the # of max char in the box. extra input is discarded when user hits the threshold
                               //Each data type will have a default value that can be changed via setter method. see init() method.
                               //this feature is added to prevent users from exceeding maxRestrictions for the data type in a DataValidityChecker
                               //copy pasting is disabled in these text fields
                               //note:setText() method can be used to have the text field contain more than the maxCharAllowed.
                               //the DataValidityChecker will still catch this as invalid data and no further characters can be entered.
                               
  private boolean enableColoring=false; //enables coloring of textField depending on whether data is valid or not.
                                        //false by default for all data types unless provided true to a constructor or you used setter.
  
  private DataType myType;    //type of intended content of textField to check for validity.
                              //type MUST be provided to constructor because the purpose of having this textfield is that we can check whether the data is valid
                              //by calling a single method which simplifies code.
                              //If you just need a textfield for getting input that you don't need to validate use a normal JtextField instead.
                              //best to use this class with EMAIL,TEXT or USERNAME.
  							  //if intended type is PASSWORD, i recommend you use MyCustomPasswordField instead. 
                              //it works the same but has Hide/show characters features.
  
  DataValidityChecker checker;  //has a DataValidityChecker that will determine whether the data is valid or not. my type is passed to the DataValidityChecker. see init() method
                        //it is important that the type of the Data type of JtextField and the type of the checker match.
                        //i.e when myType of text field is changed by a setter method myDataType in the data checker is changed too.
  
	MyCustomTextField(DataType type) { //custom constructor //DataType is required for all constructors. i left some of the superclass constructors out because
		super();                       //we don't need them.
		myType=type;
		this.init();           
		}
	MyCustomTextField(DataType type,boolean coloring) { //custom constructor
		super(); 
		myType=type;
		enableColoring=coloring;
		this.init();
		}
	
	MyCustomTextField(int columns,DataType type) { //custom constructor
		super(columns); 
		myType=type;
		this.init();
		}	
	
	MyCustomTextField(int columns,DataType type, boolean coloring) { //custom constructor
		super(columns); 
		myType=type;
		enableColoring=coloring;
		this.init();
		}	
	
	private void init() {  //private. will setup action listeners for the text Field, setup the DataValidityChecker type and maxCharAllowed. + a few extra settings.
		
		this.addKeyListener(this);
	//	this.setEditable(false); //                       //Format of the controls will be handled elsewhere.
	//	this.setPreferredSize(new Dimension(550,30));
		this.setTransferHandler(null);  //disables copy pasting in the text field
		this.checker=new DataValidityChecker(this.myType); //important:Set the type of the checker to match the type of the text field.
		switch (myType) {        
			case TEXT:   maxCharAllowed=1000;   //better to match these values with the maxRestriction of the validity checker. ex. 
							break;              //maxCharAllowed = this.checker.getMaxRestriction(); i havn't provided the getter method yet.
							                    //note: to do this checker must be instantiated BEFORE the switch block.
			case USERNAME:  maxCharAllowed=30;
			   //           this.setText("e.g Henry_Smith"); //can set default text in here if needed
							break;
			case PASSWORD: maxCharAllowed=30;
							break;
			case EMAIL:  maxCharAllowed=254;
	             	    	break;
			default: maxCharAllowed = 1000;
			         this.setText("MyCustomTextField class doesn't like this DataType. Better use something else to house this DataType.");
			       //if you use this textfield to house a different data type that we don't want it to house it'll give this warning intended for the programmer
			         this.setEnableColoring(false);
			         this.setEditable(false);
			         break;
		}
		
		}
//setters and getters
	public void setContentType(DataType typ) {
		this.myType=typ;                        
		checker.setDataType(typ);
	}
	public DataType getContentType() {
		return this.myType;
	}
	
	public void setMaxCharAllowed(int maxChar) {    //set max number of characters that can be entered in the TextField
		this.maxCharAllowed=maxChar; 
	}
	public int getMaxCharAllowed() {
		return maxCharAllowed;
	 }
	public void setEnableColoring(boolean b){  //if true allows coloring of the textField depending on whether the data is valid or not
		this.enableColoring=b;                 //for the current type
	}	
	public boolean getEnableColoring(){
		return this.enableColoring;
	}

//coloring  //to do: pick better display
 void highlightRed() {
	 this.setBackground(Color.RED);
 }
 void highlightGreen() {
	 this.setBackground(Color.GREEN);     //can do borders instead
 }
 void removeHighlight() {
	 this.setBackground(Color.WHITE);
 }
 
 public void updateColor(){
	 checker.setData(this.getText()); //gives the current text to the checker.
		if(enableColoring==true){ //checks if data is valid and decides of the color 
			   if (this.getText().equals("") && this.containsValidData() == false) {this.removeHighlight();}// if textbox is empty want no color. whether data is valid or not.
			   else if (this.containsValidData() == true) {this.highlightGreen();}   //if valid green if no valid red
		       else {this.highlightRed();}                            
		       }
 }
//data validity check
 public boolean containsValidData() { 
	// DataValidityChecker x=new DataValidityChecker(this.getText(), this.myType);  
	 //important note about memory here!! this method is called repeatedly at each key stroke so the color updates. Every time you new a dataValidityChecker
	 //space is taken in RAM (in heap precisely) to house it. when the method ends, the pointer to that location is set to null, but the space is not freed 
	 //until Java Virtual Machine starts garbage collection. C++ would allow us to free the space immediately with a delete statement.
	 //this is why it's better to put the checker as a member instead so it's not recreated every time. I am not sure i got this right. 
     //it is something to think about.
	 checker.setData(this.getText());
	 return (checker.hasValidData());    //calls hasvalidData method of the checker which is why it's important that data type are always kept the same
 }
 
 //keyEvent handlers
 public void keyTyped(KeyEvent e) { 
 		if (this.getText().length() >= maxCharAllowed ) // limits the textfield to maxChar characters
 			e.consume();                                //get's rid of the extra key pushes. can play a sound here 
 														////problem is user was still able to copy paste to exceed the limit.
 		                                                //therefore i disabled copy paste in init() method.
 		                                                //can still use setText() to exceed the maxCharAllowed. but checker will still catch it as invalid data.
 		                                                //priority zero.
 //	this.updateColor(); we update the color when key is released actually because at this step the textfield still contains the older text because
 // the KeyEvent handler is processed before the new letter or text is put in the textfield	
 }
 
 public void keyReleased(KeyEvent e) {  //to do: don't want to check for validity for keys that don't modify input i.e arrow keys, shift key or caps key.
 		                                //fix for extra speed. medium priority.
 		this.updateColor();
 		
    }
 public void keyPressed(KeyEvent e) { //holding key //if user holds key the color will wait until it's released to change color. even if display has already changed.
	                                  //can disable holding key event except for backspace. zero priority.
	    this.updateColor();           //i'm updating color here too to counter that
 	}

}