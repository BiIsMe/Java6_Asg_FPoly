package com.poly.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Data
@AllArgsConstructor @NoArgsConstructor
@Entity @Table(name="Accounts")
public class Account implements Serializable{

	@Id
	String username;
	String password;
	String name;
	String email;
	String phone = "0000000000";
	String photo;
	
	@JsonIgnore
	@OneToMany(mappedBy = "account")
	List<Authority> authorities;
	
	@JsonIgnore
	@OneToMany(mappedBy = "account")
	List<Order> orders;
	
}
