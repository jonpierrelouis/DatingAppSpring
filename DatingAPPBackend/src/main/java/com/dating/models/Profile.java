package com.dating.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name="profile")
public class Profile {
	
	@Id
	@GeneratedValue
	@Column(name = "profile_id")
	private int profileId;

	@OneToOne
	@JoinColumn(name = "login_user_id")
	private Login login;
	
	@Column(name = "profile_name")
	private String name;
	
	@Column(name = "birth_day")
	private int birthDay;
	
	@Column(name = "birth_month")
	private int birthMonth;
	
	@Column(name = "birth_year")
	private int birthYear;
	
	@Column(name = "about_yourself")
	private String aboutYourself;
	
	@Column(name = "city")
	private String city;
	
	@Column(name = "state")
	private String state;
	
	@Column(name = "image")
	private byte[] image;
}
