package edu.book_tracking.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="accesslevel")
public class AccessLevel implements Serializable{

	@Id
	@Column(name = "AccessID")
	@GenericGenerator(name="maxIdIncrementer" , strategy="increment")
	@GeneratedValue(generator="maxIdIncrementer")
	private int accessId;
	
	@Column(name = "Access_Level")
	private String accessLevel;

	public int getAccessId() {
		return accessId;
	}

	public void setAccessId(int accessId) {
		this.accessId = accessId;
	}

	public String getAccessLevel() {
		return accessLevel;
	}

	public void setAccessLevel(String accessLevel) {
		this.accessLevel = accessLevel;
	}
	
}
