package edu.book_tracking.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;
import org.hibernate.annotations.GenericGenerator;


@NamedQueries({
	@NamedQuery(
	name = "StudentModel.getByUserId",
	query = "from StudentModel where studentId = :userModelObjPlaceHolder"
	)	
})
@NamedNativeQueries({
	@NamedNativeQuery(
			name="StudentModel.getByUser",
			query="select StudentID from student where UserID = :userID "
			)
})
@Entity
@Table(name="student")
public class StudentModel implements Serializable{
		
	@Id
	@Column(name = "StudentID")
	@GenericGenerator(name="studentIdGenerator" , strategy="increment")
	@GeneratedValue(generator="studentIdGenerator")
	private int studentId;
	
	@Column(name = "StudentName")
	private String studentName;
	
	@Column(name = "EmailID")
	private String emailId;
	
	@Column(name = "AdvisorID")
	private int advisorId;
	
	@OneToOne
	@JoinColumn(name="UserID")
	private UserModel userModelObj;

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public int getAdvisorId() {
		return advisorId;
	}

	public void setAdvisorId(int advisorId) {
		this.advisorId = advisorId;
	}

	public UserModel getUserModelObj() {
		return userModelObj;
	}

	public void setUserModelObj(UserModel userModelObj) {
		this.userModelObj = userModelObj;
	}	
}
