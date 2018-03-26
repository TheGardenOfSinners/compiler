package agenda;

import java.util.*;
import java.text.*;




public class Meeting {
	private String meetingId;
	private User sponsor;
	private User participant;
	private MyDate startDate;
	private MyDate endDate;
	private String meetingTitle;

	public Meeting() {
		sponsor = null;
	    startDate = null;
	    endDate = null;
	    participant = null;
	    meetingTitle = null;
	    meetingId = null;
	}

	public Meeting(String inputId,
		    User user1,
		    User user2,
		    MyDate date_1,
		    MyDate date_2,
		    String inputTitle) {
		sponsor = user1;
		participant = user2;
		meetingId = inputId;
		startDate = date_1;
		endDate = date_2;
		meetingTitle = inputTitle;
	}

	public User getSponsor(){return sponsor;}

	public User getParticipant(){return participant;}

	public MyDate getStartDate(){return startDate;}

	public MyDate getEndDate(){return endDate;}

	public String getMeetingTitle(){return meetingTitle;}

	public String getMeetingId(){return meetingId;}

	public void setSponsor(User user1) {sponsor = user1;}

	public void setParticipant(User user2){participant = user2;}

	public void setMeetingId(String inputId){meetingId = inputId;}

	public void setMeetingTitle(String inputTitle){meetingTitle = inputTitle;}

	public void setStartDate(MyDate date_1){startDate = date_1;}

	public void setEndDate(MyDate date_2){endDate = date_2;}

	/**
	 * @param otherUser User type, mean a User. 
	 * @return true mean this User is the sponsor, false mean userName isn't the sponsor.
	 */
	public boolean sponsorIs(User otherUser){
		return this.sponsor.getUserName().equals(otherUser.getUserName());
	}

	/**
	 * @param otherUser User type,mean a User. 
	 * @return true mean this User is the Participant, false mean userName isn't the Participant.
	 */
	public boolean participantIs(User otherUser){
		return this.participant.getUserName().equals(otherUser.getUserName());
	}

	/**
	 * @param otherMeeting Meeting type,mean another Meeting. 
	 * @return true mean this time is the Overlap, false mean not.
	 * 
	 */
	public boolean isTimeOverlap(Meeting otherMeeting) {
		if(endDate.earlyThan(otherMeeting.getStartDate()) || endDate.equalTo(otherMeeting.getStartDate()))return false;
		else if(startDate.laterThan(otherMeeting.getEndDate()) || startDate.equalTo(otherMeeting.getEndDate()))return false;
		else return true;
	}

	/**
	 * @param date_1 MyDate type, mean startDate of a period of time. 
	 * @param date_2 MyDate type, mean endDate of a period of time.
	 * @return true mean this time is the Overlap, false mean not.
	 * 
	 */
	public boolean isTimeOverlap(MyDate date_1, MyDate date_2) {
		if(endDate.earlyThan(date_1) || endDate.equalTo(date_1))return false;
		else if(startDate.laterThan(date_2) || startDate.equalTo(date_2))return false;
		else return true;
	}

	/**
	 * @param otherMeeting Meeting type,mean another Meeting.
	 * @return true mean these two meeting cannot be exist together, false mean can exist together.
	 * 
	 */
	public boolean isInConflict(Meeting otherMeeting) {
		if(isTimeOverlap(otherMeeting)) {
			if(otherMeeting.sponsorIs(this.sponsor) || otherMeeting.participantIs(this.sponsor))
				return true;
			else if(otherMeeting.sponsorIs(this.participant) || otherMeeting.participantIs(this.participant))
				return true;
			else
				return false;
		}
		else return false;
	}
}