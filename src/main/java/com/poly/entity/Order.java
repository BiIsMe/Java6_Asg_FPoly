package com.poly.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Data
@AllArgsConstructor @NoArgsConstructor
@Entity @Table(name="Orders")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	Date createdate = new Date();
	String status = "accepted";
	String phone = "0000000000";
	String address;
	Double price;
	
	@ManyToOne
	@JoinColumn(name="username")
	Account account;
	
	@JsonIgnore
	@OneToMany(mappedBy = "order")
	List<Orderdetail>orderdetails;
	
}
