package agenda;

import java.util.*;
import java.text.*;

public class MyDate {

	/**
	 * formulate the String rule.
	 */
	private static final SimpleDateFormat MYDATEFORMAT = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss");

	/**
	 * the DAYS in different months.
	 */
	private static final int[] DAYS = { 0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 }; 
	private Date myDate;
	
	public MyDate() {
		myDate = new Date();
	}

	/**
	 * the string which is input must suit "yyyy-MM-dd HH:mm:ss"<br>
	 * and its number must match the rule of date.<br>
	 * @param strDate String type, the input string.
	 * @return true mean succesfully set, false mean not.
	 */
	public boolean setMyDate(String strDate) {
		if(!isValidDate(strDate)) {
			System.out.println("Your input \""+ strDate +"\"don't suit \"yyyy-MM-dd-HH:mm:ss\"");
			return false;
		}
		try {
			myDate = MYDATEFORMAT.parse(strDate);
		} catch(Exception ex) {
			System.out.println("Your input \""+ strDate +"\"don't suit \"yyyy-MM-dd-HH:mm:ss\"");
			return false;
		}
		return true;
	}


	/**
	 * the string which is input must suit "yyyy-MM-dd HH:mm:ss"<br>
	 * and its number must match the rule of date.<br>
	 * @param strDate String type, the input string. 
	 * @return true mean strDate is legal, false mean illegal.
	 */
	private static boolean isValidDate(String strDate) {  
    	try {  
    	    int year = Integer.parseInt(strDate.substring(0, 4));
    	    if (year <= 0)  
    	        return false;  
    	    int month = Integer.parseInt(strDate.substring(5, 7));
    	    if (month <= 0 || month > 12)  
     	       return false;  
      	    int day = Integer.parseInt(strDate.substring(8, 10));
    	    if (day <= 0 || day > DAYS[month])  
        	    return false;  
	        if (month == 2 && day == 29 && !isLeapYear(year)) {
    	        return false;
        	}
        	int hour = Integer.parseInt(strDate.substring(11, 13));
            if (hour < 0 || hour > 23)  
                return false;  
            int minute = Integer.parseInt(strDate.substring(14, 16));
            if (minute < 0 || minute > 59)  
                return false;  
            int second = Integer.parseInt(strDate.substring(17, 19));
            if (second < 0 || second > 59)  
                return false;  
  
        } catch (Exception e) {  
            e.printStackTrace();
            return false;
        }
        return true;  
	}  

	/**
	 * each 4 is leap ,each 100 is not,each 400 is leap.<br>
	 * @param inputYear int type, the input year. 
	 * @return true mean strDate is LeapYear, false mean LeapYear.
	 */
	private static boolean isLeapYear(int inputYear) {
		if(inputYear % 4 != 0)return false;
		else if(inputYear % 100 == 0 && inputYear % 400 != 0)return false;
		else return true;
	}

	public Date getMyDate(){return myDate;}

	/**
	 * translate to string
	 * @return the string of date.
	 */
	public String strMyDate(){return MYDATEFORMAT.format(myDate);}

	
	public boolean earlyThan(MyDate date_2) {
		return myDate.before(date_2.getMyDate());
	}
	public boolean equalTo(MyDate date_2) {
		return strMyDate().equals(date_2.strMyDate());
	}
	public boolean laterThan(MyDate date_2) {
		return myDate.after(date_2.getMyDate());
	}
}