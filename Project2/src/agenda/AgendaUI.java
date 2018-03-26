package agenda;

import java.util.*;
import java.io.*;
import java.lang.*;

public class AgendaUI {
	private Agenda myAgenda;

	/**
	 * initial myAgenda
	 */
	public AgendaUI() {
		myAgenda = new Agenda();
	}

	/**
	 * the input interface of the UI<br>
	 * call the private function processCommand(String) to process the command.
	 */
	public void inputLine() {
		boolean flag = true;
		String cmdCommand;
		Scanner sc = new Scanner(System.in);
		while(flag) {
			System.out.print("$ ");
			cmdCommand = sc.nextLine();
			flag = processCommand(cmdCommand);
		}
	}

	/**
	 * to process the command, split it and judge what command it is.
	 * @param cmdCommand mean the whole line of command string.
	 * @return ture mean continue input the command, flase mean quit the Agenda.
	 */
    private boolean processCommand(String cmdCommand) {
    	String[] cmdArray = cmdCommand.split(" ");
    	switch(cmdArray[0]) {
    		case "register":
    		    registerCommand(cmdArray);
    		    break;
    		case "add":
    			addCommand(cmdArray);
    			break;
    		case "query":
    			queryCommand(cmdArray);
    			break;
    		case "delete":
    		    deleteCommand(cmdArray);
    		    break;
    		case "clear":
    		    clearCommand(cmdArray);
    		    break;
    		case "batch":
    		    return bacthCommand(cmdArray);
    		case "quit":
    		    return quitCommand(cmdArray);
    		default:
    			System.out.println("There is no " + cmdArray[0] + " command!!");
    		    return true;
    	}
    	return true;
    }

    /**
	 * to process the register command<br>
	 * judge whether it's legal and call the register function of myAgenda if legal.<br>
	 * @param cmdArray mean the array of string command.
	 */
    private void registerCommand(String[] cmdArray) {
    	if(cmdArray.length != 3) {
    		System.out.println("your input must suit \"$ register [username] [password]\"");
    		return;
    	}
    	if(cmdArray[1].length() > 20) {
    		System.out.println("Your name is too long!!");
    		return;
    	}
    	if(cmdArray[2].length() > 20) {
    		System.out.println("Your password is too long!!");
    		return;
    	}
    	User user1 = new User(cmdArray[1], cmdArray[2]);
    	myAgenda.registerNewUser(user1);
    	return;
    }

    /**
	 * to process the add command<br>
	 * judge whether it's legal.<br>
	 * translate the string message into Date User and so on.<br>
	 * @param cmdArray mean the array of string command.
	 */
    private void addCommand(String[] cmdArray) {
    	if(cmdArray.length != 7) {
    		System.out.println("your input must suit \"$ add [userName] [password] [other] [start] [end] [title]\"");
    		return;
    	}
    	boolean flag = true;
    	Meeting newMeeting = new Meeting();
    	User user1 = new User(cmdArray[1], cmdArray[2]);
    	newMeeting.setSponsor(user1);
    	User user2 = new User();
    	user2.setUserName(cmdArray[3]);
    	newMeeting.setParticipant(user2);
    	MyDate date_1 = new MyDate();
    	flag = date_1.setMyDate(cmdArray[4]);
    	if(!flag)return;
    	newMeeting.setStartDate(date_1);
    	MyDate date_2 = new MyDate();
    	flag = date_2.setMyDate(cmdArray[5]);
    	if(!flag)return;
    	if(date_2.earlyThan(date_1)) {
    		System.out.println("Your input endDate is early than your startDate!!");
    		return;
    	}
    	newMeeting.setEndDate(date_2);
    	if(cmdArray[6].length() > 20) {
    		System.out.println("Your title is too long!!");
    	}
    	newMeeting.setMeetingTitle(cmdArray[6]);
    	myAgenda.createNewMeeting(newMeeting);
    	return;
    }

    /**
	 * to process the query command<br>
	 * judge whether it's legal.<br>
	 * translate the string message into Date User for myAgenda.<br>
	 * show the result of query<br>
	 * @param cmdArray mean the array of string command.
	 */
    private void queryCommand(String[] cmdArray) {
    	if(cmdArray.length != 5) {
    		System.out.println("your input must suit \"$ query [userName] [password] [start] [end]\"");
    		return;
    	}
    	boolean flag = true;
    	User user1 = new User(cmdArray[1], cmdArray[2]);
    	MyDate date_1 = new MyDate();
    	flag = date_1.setMyDate(cmdArray[3]);
    	if(!flag)return;
    	MyDate date_2 = new MyDate();
    	flag = date_2.setMyDate(cmdArray[4]);
    	if(!flag)return;
    	if(date_2.earlyThan(date_1)) {
    		System.out.println("Your input endDate is early than your startDate!!");
    		return;
    	}
    	ArrayList<Meeting> query = myAgenda.queryMeetingByDate(user1, date_1, date_2);
    	if(query != null) {
    		for(int i = 0; i < query.size(); i++) {
    			Meeting a = query.get(i);
    			System.out.printf("|     Id      |%20s|    Title    |%20s|\n|   Sponsor   |%20s| Participant |%20s|\n|  startDate  |%20s|   endDate   |%20s|\n\n", a.getMeetingId(), a.getMeetingTitle(),
    			                 a.getSponsor().getUserName(), a.getParticipant().getUserName(), a.getStartDate().strMyDate(),
    			                 a.getEndDate().strMyDate());

    		}
    	}
    	return;
    }

    /**
	 * to process the delete command<br>
	 * judge whether it's legal.<br>
	 * translate the string message into User for myAgenda.<br>
	 * show the if it delete successfully<br>
	 * @param cmdArray mean the array of string command.
	 */
    private void deleteCommand(String[] cmdArray) {
    	if(cmdArray.length != 4) {
    		System.out.println("your input must suit \"delete [userName] [password] [meetingId]\"");
    		return;
    	}
    	User user1 = new User(cmdArray[1], cmdArray[2]);
    	myAgenda.deleteByMeetingId(user1, cmdArray[3]);
    	return;
    }

    /**
	 * to process the clear command<br>
	 * judge whether it's legal.<br>
	 * translate the string message into User for myAgenda.<br>
	 * @param cmdArray mean the array of string command.
	 */
    private void clearCommand(String[] cmdArray) {
    	if(cmdArray.length != 3) {
    		System.out.println("your input must suit \"$ clear [userName] [password]\"");
    		return;
    	}
    	User user1 = new User(cmdArray[1], cmdArray[2]);
    	myAgenda.clearByUser(user1);
    	return;
    }
    
    /**
	 * to process the quit command<br>
	 * judge whether it's legal.<br>
	 * write back the data<br>
	 * @param cmdArray mean the array of string command.
	 * @return false mean quit success, true mean not
	 */
    private boolean quitCommand(String[] cmdArray) {
    	if(cmdArray.length != 1) {
    		System.out.println("there is not any parameter behind $ quit!!");
    		return true;
    	}
    	myAgenda.writeData();
    	return false;
    }

    /**
	 * to process the bacth command<br>
	 * judge whether it's legal.<br>
	 * read command from data<br>
	 * run command one by one.<br>
	 * @param cmdArray mean the array of string command.
	 * @return false mean there is quit command in it, true mean not.
	 */
    private boolean bacthCommand(String[] cmdArray) {
    	if(cmdArray.length != 2) {
    		System.out.println("your input must suit \"$ batch [fileName]\"");
    		return true;
    	}
    	try {
    		File fileName = new File(cmdArray[1]);
    		Scanner sc = new Scanner(fileName);
    		String cmdCommand;
    		boolean flag = false;
    		while(sc.hasNextLine()) {
    			cmdCommand = sc.nextLine();
    			System.out.println("$ " + cmdCommand);
    			flag = processCommand(cmdCommand);
    			if(!flag) {
    				return false;
    			}
    		}
    	} catch (Exception e) {
    		System.out.println("read data wrong!!");
    		return true;
    	}
    	return true;
    }

}