package com.dating.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Model used for sign in and sign up for the account
 * @author Jonathan
 *
 */
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "login")
@Getter
@Setter
public class Login {
	
	@Id
	@Column(name = "user_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;
	
	@Column(name = "user_name")
	private String userName;
	
	@Column(name = "user_email")
	private String userEmail;
	
	@Column(name = "user_password")
	private String userPassword;
	
	@OneToOne(mappedBy = "login", cascade = CascadeType.ALL)
	private Profile profile;

	public Login(String userName, String userEmail, String userPassword) {
		this.userName = userName;
		this.userEmail = userEmail;
		this.userPassword = userPassword;
	}
	
	
	
}
