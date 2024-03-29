package com.dating.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.dating.keys.UserLikesKey;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Entity
@IdClass(UserLikesKey.class)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name="user_dislikes")
public class UserDislikes {

	@Id
	@Column(name = "profile_id_fk")
	@JsonIgnore
	int userId;
	
	@Id
	@Column(name = "like_id_fk")
	int likeId;
	
}
