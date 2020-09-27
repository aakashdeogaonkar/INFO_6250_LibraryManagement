package edu.book_tracking.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;


@NamedQueries({
	@NamedQuery(
	name = "UserModel.getById",
	query = "from UserModel where userId = :userID"
	),
	@NamedQuery(
	name = "UserModel.getAllUsers",
	query = "from UserModel"
	)
})
@Entity
@Table(name="user")
public class UserModel implements Serializable{
	
	
	@Id
	@Column(name = "UserID")
	private String userId;
	
	@Column(name="Password")
	private String password;
	
	@OneToOne
	@JoinColumn(name="AccessID")
	private AccessLevel accessLevelObj;
	
	
	public AccessLevel getAccessLevelObj() {
		return accessLevelObj;
	}

	public void setAccessLevelObj(AccessLevel accessLevelObj) {
		this.accessLevelObj = accessLevelObj;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
