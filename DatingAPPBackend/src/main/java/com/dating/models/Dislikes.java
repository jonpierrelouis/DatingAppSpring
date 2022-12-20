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
@Table(name="dislikes")
public class Dislikes {

	@Id
	@Column(name = "dislike_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int dislikesId;
	
	@Column(name = "dislike_name")
	String singleDislike;

	@JsonIgnore
	@ManyToMany
	private List<Profile> profiles;
}
