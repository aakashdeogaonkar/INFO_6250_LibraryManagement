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
@Table(name="class")
public class ClassModel {
	
	@Id
	@Column(name = "ClassID")
	@GenericGenerator(name="classIdGenerator" , strategy="increment")
	@GeneratedValue(generator="classIdGenerator")
	private int classId;
	
	@Column(name = "Class_Name")
	private String className;

	
	@OneToOne
	@JoinColumn(name = "BookID")
	private BookModel bookModelObj;
	

	@OneToOne
	@JoinColumn(name = "TeacherID")
	private TeacherModel teacherId;
	
	@Column(name = "Year")
	private int year;
	
	@Column(name = "semester")
	private String Semester;

	public int getClassId() {
		return classId;
	}

	public void setClassId(int classId) {
		this.classId = classId;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public BookModel getBookModelObj() {
		return bookModelObj;
	}

	public void setBookModelObj(BookModel bookModelObj) {
		this.bookModelObj = bookModelObj;
	}

	public TeacherModel getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(TeacherModel teacherId) {
		this.teacherId = teacherId;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getSemester() {
		return Semester;
	}

	public void setSemester(String semester) {
		Semester = semester;
	}
	
	

}
