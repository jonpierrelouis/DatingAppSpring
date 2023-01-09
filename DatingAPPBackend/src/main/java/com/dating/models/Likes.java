package com.dating.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
@Table(name="likes")
public class Likes {
	
	@Id
	@Column(name = "like_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int likesId;
	
	@Column(name = "like_name", unique=true, nullable=false)
	String singleLike;

	@JsonIgnore
	@ManyToMany
	private List<Profile> profiles;

	public Likes(int likesId, String singleLike) {
		super();
		this.likesId = likesId;
		this.singleLike = singleLike;
	}

	public Likes(String singleLike) {
		super();
		this.singleLike = singleLike;
	}
	
	
	
	

}
