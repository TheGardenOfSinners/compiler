package agenda;

import java.util.*;
import java.text.*;

public class User {
	/**
	 * ”√ªß√˚
	 */
	private String userName;
	/**
	 * √‹¬Î
	 */
	private String userPassword;

	/**
	 * initial userName and password is null.
	 */
	public User() {
		userName = null;
		userPassword = null;
	}

	public User(String username, String password) {
		userName = username;
		userPassword = password;
	}

	public String getUserName(){return userName;}
	
	public String getUserPassword(){return userPassword;}

	public void setUserName(String name) {userName = name;}

	public void setUserPassword(String password){userPassword = password;}

	/**
	 * to judge that is two User has same name.<br>
	 * @param otherUser User type, mean another User to compare with. 
	 * @return true mean the name has been used, false mean userName hasn't been used.
	 */
	public boolean sameNameAs(User otherUser){
		return this.getUserName().equals(otherUser.getUserName());
	}

	/**
	 * to judge that input to log in has same name and the same password.<br>
	 * @param input User type, mean an User to log in. 
	 * @return true mean log in success, false mean userName or password wrong.
	 */
	public boolean passWordCorrect(User input){
		if(input.getUserName().equals(this.getUserName())) {
			if(input.getUserPassword().equals(this.getUserPassword())) {
				return true;
			}
		}
		return false;
	}
}