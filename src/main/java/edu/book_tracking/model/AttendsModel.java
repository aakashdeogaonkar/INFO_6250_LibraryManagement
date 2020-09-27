package edu.book_tracking.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;


@NamedQueries({
	@NamedQuery(
	name = "AttendsModel.getClassByStudentId",
	query = "select classId from AttendsModel where studentId = :studentIdPlaceholder"
	)
})
@Entity
@Table(name="attends")
public class AttendsModel implements Serializable{
	
	
	@Id
	@Column(name = "AttendId")
	@GenericGenerator(name="attendIdGen" , strategy="increment")
	@GeneratedValue(generator="attendIdGen")
	private int attendId;
	
	@Column(name = "StudentID")
	private int studentId;
	
	@Column(name = "ClassID")
	private int classId;

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public int getClassId() {
		return classId;
	}

	public void setClassId(int classId) {
		this.classId = classId;
	}
}
