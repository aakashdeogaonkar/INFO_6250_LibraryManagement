package edu.book_tracking.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;
import org.hibernate.annotations.GenericGenerator;

@NamedNativeQueries({
	@NamedNativeQuery(
			name="ParentModel.getByUser",
			query="select ParentID from parent where UserID = :userID "
			)
})
@Entity
@Table(name="parent")
public class ParentModel implements Serializable {
	
	@Id
	@Column(name = "ParentID")
	@GenericGenerator(name="maxParentIdIncrementer1" , strategy="increment")
	@GeneratedValue(generator="maxParentIdIncrementer1")
	private int parentId;
	
	@OneToOne
	@JoinColumn(name="StudentID")
	private StudentModel studentModelObj;
	
	@OneToOne
	@JoinColumn(name="UserID")
	private UserModel userModelObj;
		
	@Column(name = "ParentName")
	private String parentName;
	
	@Column(name = "ContactNumber")
	private String contactNumber;
	
	@Column(name = "DueAmount")
	private String dueAmount;
	
	public StudentModel getStudentModelObj() {
		return studentModelObj;
	}

	public void setStudentModelObj(StudentModel studentModelObj) {
		this.studentModelObj = studentModelObj;
	}
		
	public UserModel getUserModelObj() {
		return userModelObj;
	}

	public void setUserModelObj(UserModel userModelObj) {
		this.userModelObj = userModelObj;
	}

	public int getParentId() {
		return parentId;
	}
	
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}


	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getDueAmount() {
		return dueAmount;
	}

	public void setDueAmount(String dueAmount) {
		this.dueAmount = dueAmount;
	}
}
