package com.tinnt.user_service.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Document(collection = "users")
public class UserEntity {
	@Id
	private String id;
	
	private String name;

	@Indexed(unique = true)
	private String username;
	
	private int age;
	
}