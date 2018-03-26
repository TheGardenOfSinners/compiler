package agenda;

import java.util.*;
import java.io.*;
import java.lang.*;

public class Agenda {

	/**
	 * datapath of user
	 */
	private static final String USERSTOREPATH = "./../data/User.txt";

	/**
	 * datapath of meeting
	 */
	private static final String MEETINGSTOREPATH = "./../data/Meeting.txt";

	/**
	 * save the users
	 */
	private ArrayList<User> userList;
	/**
	 * save the meetings
	 */
	private ArrayList<Meeting> meetingList;

	/**
	 * constructor, initial the userlist and the meetinglist from data.
	 */
	public Agenda() {
		userList = new ArrayList<User>();
		meetingList = new ArrayList<Meeting>();
		readData();
	}

	/**
	 * Destructor function, writeback data in data.
	 */
	protected void finalize() {
		writeData();
	}

	/**
	 * read the userlist and the meetinglist from text<br>
	 */
	public void readData() {
		try{
			FileInputStream fis = new FileInputStream(USERSTOREPATH);
			InputStreamReader isr = new InputStreamReader(fis);
			BufferedReader br = new BufferedReader(isr);
			String userName = null;
			String userPassword = null;
			String id = null;
			String date = null;
			String title = null;
			while((userName = br.readLine()) != null) {
				userPassword = br.readLine();
				User user1 = new User(userName, userPassword);
				userList.add(user1);
			}
			br.close();
			isr.close();
			fis.close();
			fis = new FileInputStream(MEETINGSTOREPATH);
			isr = new InputStreamReader(fis);
			br = new BufferedReader(isr);
			while((id = br.readLine()) != null) {
				userName = br.readLine();
				userPassword = br.readLine();
				User user1 = new User(userName, userPassword);
				userName = br.readLine();
				userPassword = br.readLine();
				User user2 = new User(userName, userPassword);
				date = br.readLine();
				MyDate date_1 = new MyDate();
				date_1.setMyDate(date);
				date = br.readLine();
				MyDate date_2 = new MyDate();
				date_2.setMyDate(date);
				title = br.readLine();
				Meeting meeting1 = new Meeting(id, user1, user2, date_1, date_2, title);
				meetingList.add(meeting1);
			}
			System.out.println("Data have already been read in!!");
		} catch (Exception ex) {
			System.out.println("Data Error!!");
		}
	}


	/**
	 * store the userlist and the meetinglist into txt.<br>
	 */
	public void writeData() {
		try {
			FileOutputStream fos = new FileOutputStream(USERSTOREPATH);
    		DataOutputStream dos = new DataOutputStream(fos);
    		for(int i = 0;i < userList.size(); i++) {
    			dos.writeBytes(userList.get(i).getUserName());
    			dos.writeBytes("\n");
    			dos.writeBytes(userList.get(i).getUserPassword());
    			dos.writeBytes("\n");
    		}
    		dos.close();
    		fos.close();
    		fos = new FileOutputStream(MEETINGSTOREPATH);
    		dos = new DataOutputStream(fos);
    		for(int i = 0;i < meetingList.size(); i++) {
    			Meeting aaa = meetingList.get(i);
    			dos.writeBytes(aaa.getMeetingId());
    			dos.writeBytes("\n");
    			dos.writeBytes(aaa.getSponsor().getUserName());
    			dos.writeBytes("\n");
    			dos.writeBytes(aaa.getSponsor().getUserPassword());
    			dos.writeBytes("\n");
    			dos.writeBytes(aaa.getParticipant().getUserName());
    			dos.writeBytes("\n");
    			dos.writeBytes(aaa.getParticipant().getUserPassword());
    			dos.writeBytes("\n");
    			dos.writeBytes(aaa.getStartDate().strMyDate());
    			dos.writeBytes("\n");
    			dos.writeBytes(aaa.getEndDate().strMyDate());
    			dos.writeBytes("\n");
    			dos.writeBytes(aaa.getMeetingTitle());
    			dos.writeBytes("\n");
    		}
    		dos.close();
    		fos.close();
		} catch (Exception e) {
			System.out.println("write in Error");
		}
	}

	/**
	 * resigter a new user<br>
	 * @param user1 User type, mean another a User wait for register. 
	 * @return true mean success, false mean not.
	 */
	public boolean registerNewUser(User user1) {
		for(int i = 0; i < userList.size(); i++) {
			if(userList.get(i).sameNameAs(user1)) {
				System.out.println("This name has been registered!!");
				return false;
			}
		}
		userList.add(user1);
		System.out.println("register succesfully!!");
		return true;
	}


	/**
	 * create a new meeting<br>
	 * @param meeting1 Meeting type, mean new meeting information waitng for create wait for register. 
	 * @return true mean success, false mean not.
	 */
	public boolean createNewMeeting(Meeting meeting1) {
		boolean flag = false;

		if(meeting1.getSponsor().sameNameAs(meeting1.getParticipant())) {
			System.out.println("Sponsor and the participant is the same person!");
			return false;
		}
		for(int i = 0;i < userList.size(); i++) {
			if(userList.get(i).sameNameAs(meeting1.getSponsor())) {
				if(userList.get(i).passWordCorrect(meeting1.getSponsor())) {
					flag = true;
				} else {
					System.out.println("Your password is wrong!!");
					return false;
				}
			}
		}
		if(!flag) {
			System.out.println("Your username is wrong!!");
			return false;
		}
		flag = false;
		for(int i = 0;i < userList.size(); i++) {
			if(userList.get(i).sameNameAs(meeting1.getParticipant())) {
				flag = true;
				meeting1.setParticipant(userList.get(i));
				break;
			}
		}
		if(!flag) {
			System.out.println("The participant has not been registered!!");
			return false;
		}
		for(int i = 0;i < meetingList.size(); i++) {
			if(meetingList.get(i).isInConflict(meeting1)) {
				System.out.println("This meeting is in conflict with another meeting in meeting list!!");
				return false;
			}
		}
		meeting1.setMeetingId(this.getNextId());
		meetingList.add(meeting1);

		System.out.println("create succesfully!!");
		return true;
	}

	/**
	 * to get the next id can be used for a new meeting of meeting list<br>
	 * @return mean the next id can be use.
	 */
	public String getNextId() {
		if(meetingList.size() == 0) {
			return "1";
		} else {
			String strId1 = meetingList.get(meetingList.size() - 1).getMeetingId();
			int i=Integer.parseInt(strId1);
			i++;
			String strId2 = Integer.toString(i);
			return strId2;
		}
	}

	/**
	 * query Meeting by user name and password and start end date<br>
	 * @param user1 User type, mean the user who want to query his meeting. 
	 * @param date_1 MyDate type, mean the start date of the time he want to query.
	 * @param date_2 MyDate type, mean the end date of the time he want to query.
	 * @return a ArrayList of Meeting which are meet the requirements.
	 */
	public ArrayList<Meeting> queryMeetingByDate(User user1, MyDate date_1, MyDate date_2) {
		ArrayList<Meeting> output = new ArrayList<Meeting>();
		boolean flag = false;
		for(int i = 0; i < userList.size(); i++) {
			if(userList.get(i).sameNameAs(user1)) {
				if(userList.get(i).passWordCorrect(user1)) {
					flag = true;
				}
				break;
			}
		}
		if(!flag) {
			System.out.println("Your username or password is wrong!!");
			return null;
		}
		for(int i = 0; i < meetingList.size(); i++) {
			if(meetingList.get(i).participantIs(user1) || meetingList.get(i).sponsorIs(user1)) {
				if(meetingList.get(i).isTimeOverlap(date_1, date_2))
					output.add(meetingList.get(i));
			}
		}
		return output;
	}


	/**
	 * delete Meeting by user name and password and start end date<br>
	 * @param user1 User type, mean the user who want to delete his meeting. 
	 * @param id String type, mean the id of the meeting that he want to delete.
	 * @return true mean delete successfully,false mean not.
	 */
	public boolean deleteByMeetingId(User user1, String id) {
		boolean flag = false;
		for(int i = 0; i < userList.size(); i++) {
			if(userList.get(i).sameNameAs(user1)) {
				if(userList.get(i).passWordCorrect(user1)) {
					flag = true;
				}
				break;
			}
		}
		if(!flag) {
			System.out.println("Your username or password is wrong!!");
			return false;
		}
		for(int i = 0; i < meetingList.size(); i++) {
			if(meetingList.get(i).getMeetingId().equals(id)) {
				if(meetingList.get(i).participantIs(user1) || meetingList.get(i).sponsorIs(user1)) {
					Meeting removeOne = meetingList.remove(i);
					System.out.println("remove success!!");
					return true;
				} else {
					System.out.println("This meeting is not your meeting,you cannot delete it!!");
					return false;
				}
			}
		}
		System.out.println("ID not found!!");
		return false;
	}

	/**
	 * delete all Meeting of the user<br>
	 * @param user1 User type, mean the user who want to delete his meeting.
	 * @return true mean delete successfully,false mean not.
	 */
	public boolean clearByUser(User user1) {
		boolean flag = false;
		for(int i = 0; i < userList.size(); i++) {
			if(userList.get(i).sameNameAs(user1)) {
				if(userList.get(i).passWordCorrect(user1)) {
					flag = true;
				}
				break;
			}
		}
		if(!flag) {
			System.out.println("Your username or password is wrong!!");
			return false;
		}
		for(int i = 0; i < meetingList.size(); i++) {
			if(meetingList.get(i).participantIs(user1) || meetingList.get(i).sponsorIs(user1)) {
				Meeting removeOne = meetingList.remove(i);
				i--;
			}
		}
		System.out.println("Clear success!!");
		return true;
	}

}