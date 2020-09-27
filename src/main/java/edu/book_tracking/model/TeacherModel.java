package edu.book_tracking.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="teacher")
public class TeacherModel {

	@Id
	@Column(name = "TeacherID")
	@GenericGenerator(name="teacherIdGenerator" , strategy="increment")
	@GeneratedValue(generator="teacherIdGenerator")
	private int TeacherID;
	
	@Column(name = "Teacher_Name")
	private String teacherName;
	
	@Column(name = "Contact_Number")
	private String contactNumber;
	
	@Column(name = "EmailID")
	private String emailId;
	
	@OneToOne
	@JoinColumn(name="UserID")
	private UserModel UserModelObj;

	public int getTeacherID() {
		return TeacherID;
	}

	public void setTeacherID(int teacherID) {
		TeacherID = teacherID;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public UserModel getUserModelObj() {
		return UserModelObj;
	}

	public void setUserModelObj(UserModel userModelObj) {
		UserModelObj = userModelObj;
	}
}
