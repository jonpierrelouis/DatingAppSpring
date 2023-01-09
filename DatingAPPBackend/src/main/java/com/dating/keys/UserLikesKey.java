package com.dating.keys;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserLikesKey implements Serializable{
	
	private int userId;
	
	private int likeId;

}
