

import java.awt.*;
import javax.swing.*;
import javax.swing.text.Document;
import java.awt.event.*;


//Lakhoua Mehdi <Lakhoua_Mehdi@yahoo.com>


public final class DataValidityChecker
{  
 
  private String data; //data string
  private DataType myDataType; //see DataType.java
  private int minRestriction; //min number of characters to be valid
  private int maxRestriction; //max number of characters to be valid
  private boolean enforceMinMaxRestrictions; //check for min, max restrictions or not.
  private int minNumberOfCaps; //min number of capital letters to be valid
  private int minNumberOfDigits; //min number of digits 0-9 to be valid
  private int minNumberOfSpecialCharacters; //min number of special characters to be valid.
  private String specialCharacters;
  private String forbiddenCharacters;
  private String requiredCharacters; 
  private String latestReason="none"; //reason for which validity check was failed.
 
  //to do: add variable to record which validity checks were failed to be able to display a message for the user.  done
  //to do: agree on validity rules for each DataType.
  
  //Constructors
  DataValidityChecker(String data,DataType type) { //DataType is required for all constructors
		this.myDataType=type;
		this.data=data;
		this.init();
   }
  DataValidityChecker(DataType type) { //data can be set later using setter.
		this.myDataType=type;
		this.init();
 }
  private void init() {     //private. sets the variables for each data type.
	  switch (myDataType) {
	      case USERNAME: minRestriction=6; maxRestriction=30; enforceMinMaxRestrictions = true;
	                     minNumberOfCaps=0; minNumberOfDigits=0; minNumberOfSpecialCharacters=0;
	                     specialCharacters="";requiredCharacters="_";forbiddenCharacters="!@#$%^&*()*/";
	    	  break;
	      case PASSWORD: minRestriction=6; maxRestriction=30; enforceMinMaxRestrictions = true;
	      				 minNumberOfCaps=1; minNumberOfDigits=1; minNumberOfSpecialCharacters=1;
	      				specialCharacters= "!@#$%^&*()*/"; requiredCharacters="";forbiddenCharacters="";
	    	  break;
	      case EMAIL:  minRestriction=6; maxRestriction=256; enforceMinMaxRestrictions = true;
	      			   minNumberOfCaps=0; minNumberOfDigits=0; minNumberOfSpecialCharacters=0;
	      		       specialCharacters=""; forbiddenCharacters=""; requiredCharacters="@.";
	    	  break;
	      case TEXT:  minRestriction=0; maxRestriction=1000; enforceMinMaxRestrictions = false;//means that TEXT will be considered valid even if over 1000 char.
	      			  minNumberOfCaps=0; minNumberOfDigits=0; minNumberOfSpecialCharacters=0;
	      			  specialCharacters= forbiddenCharacters= requiredCharacters="";
	    	  break;
	  }
	 
  } 

//setters and getters
    public void setData(String data){
		this.data=data;
	}
	public String getData() {
		return this.data;
	}
	public void setDataType(DataType typ) {  //note: after changing the data type via setter it is important to call the init() method to change the settings as well.
		this.myDataType=typ;                
		this.init();
	}
	public DataType getDataType() {
		return this.myDataType;
	}
    public String getLatestReason(){
    	return this.latestReason;
    }
//data validity checks. I keep those private. Warning. don't call these method with null data.
 private boolean okMinRestriction() {   //ok tested
	 
	 if(this.enforceMinMaxRestrictions==true) { 
		 if (data.length() < this.minRestriction) {
			 latestReason="For " + String.valueOf(this.myDataType) + " you need at least " + String.valueOf(this.minRestriction) + " number of characters" ; 
			 //note:please always use explicit conversions as shown above.
			 return false; //min number of char not reached
			 }
	     else { return true;  } 
		   }
	 else return true;
 }
 
 private boolean okMaxRestriction(){   //ok
	 if(this.enforceMinMaxRestrictions==true) { 
	     
		 if (data.length() > this.maxRestriction) {
			 latestReason="For " + String.valueOf(this.myDataType) + " you can have at most " + String.valueOf(this.maxRestriction) + " number of characters" ;
			 return false; 
			 }//max number of char exceeded 
	      else {return true;   }

		 }
	 else return true;	 
 }
 
 private boolean okMinNumberOfCaps() {   //ok
	 int count=0;
	 for(int i=0;i<data.length();i++) {
		 if((int)(data.charAt(i))>64 && (int)(data.charAt(i))<91) {count++;}
	 }
	 
	 if (count< this.minNumberOfCaps) {
		 latestReason="For " + String.valueOf(this.myDataType) + " you need at least " + String.valueOf(this.minNumberOfCaps) + " capital letters. I only see " + String.valueOf(count) ;
		 return false; }
	 else { return true; } 
 }
 
 private boolean okMinNumberOfDigits(){   //ok
	 int count=0;
	 for(int i=0;i<data.length();i++) {
		 if((int)(data.charAt(i))>47 && (int)(data.charAt(i))<58) {count++;}
	 }
	 if (count< this.minNumberOfDigits) {
		 latestReason="For " + String.valueOf(this.myDataType) + " you need at least " + String.valueOf(this.minNumberOfDigits) + " number of digits. I detected " + String.valueOf(count) ;
		 return false; }
	 else { return true; } 
	 
 }
 private boolean okSpecialCharacters(){    //ok //warning: will cause exceptions if specialCharacters is null. fixed
	 if (specialCharacters==null || specialCharacters=="") return true;
	 int count=0;
	 for(int i=0;i<data.length();i++) {    
		 if(this.specialCharacters.contains(String.valueOf(data.charAt(i))) == true) count++;
	 }
	 
	 if (count< this.minNumberOfSpecialCharacters) {
		 latestReason="For " + String.valueOf(this.myDataType) + " you must have at least " 
	            + String.valueOf(this.minNumberOfSpecialCharacters) + " special character(s):"+ this.specialCharacters ;
		 return false; }
	 else { return true; } 
 }
 private boolean okForbiddenCharacters(){    //ok. fixed
	 if(forbiddenCharacters==null || forbiddenCharacters=="") {return true; }
	 for(int i=0;i<data.length();i++) {
		 if(this.forbiddenCharacters.contains(String.valueOf(data.charAt(i))) == true) {
			 latestReason="For " + String.valueOf(this.myDataType) + " you cannot have these characters " + this.forbiddenCharacters 
					 +   ". I detected " + String.valueOf(data.charAt(i)) ;
			 return false;   }//forbidden character detected
	 }
	 return true;
 }
 private boolean okRequiredCharacters(){    //ok. fixed
	 if (requiredCharacters == null || requiredCharacters=="") {return true; }
	 for(int j=0;j<this.requiredCharacters.length();j++) {  
		if(this.data.contains(String.valueOf(requiredCharacters.charAt(j))) == false) {
			latestReason="For " + String.valueOf(this.myDataType) + " you are required to use the characters:" 
		                                     + this.requiredCharacters + ". I did not detect " + String.valueOf(requiredCharacters.charAt(j));
			return false; } //required character is missing
	 }
	 
	 return true;
	 
 }
//data validity check called from outside. the other validity checks are private
 public boolean hasValidData() {
	boolean result; 
	 
	 if (this.data==null || this.data=="") { //checking for null data here to avoid exceptions. The ok... methods do not check for null.  
		 this.latestReason= "The field is empty";      //do not call those when data may be null.
	    result= false;
	    }
	 
	 else { result = okForbiddenCharacters()   //latest reason will hold the reason for the first failed check
			    && okRequiredCharacters()      //when one of the methods returns false the whole expression is false. the rest of the expression is not evaluated.
			    && okMinNumberOfCaps()
				&& okMinNumberOfDigits()
				&& okSpecialCharacters()
				&& okMaxRestriction() 
				&& okMinRestriction();
	 
	 }
	 System.out.println("checking if current Data is valid Data is:" + this.data + "\nType is:"+ this.myDataType + "\nresut is:" + String.valueOf(result));
	 if (result==false){ System.out.println("Because of:" + latestReason); //debug
	                              }
	 return result;
	 
 }
	
 
}