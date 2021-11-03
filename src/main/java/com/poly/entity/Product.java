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
@Entity @Table(name="Products")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	
	String name;
	String image;
	Double price;
	int quantity = 10;
	Date createdate = new Date();
	boolean available = true;
	
	@ManyToOne
	@JoinColumn(name="categoryid")
	Category category;
	
	@JsonIgnore
	@OneToMany(mappedBy = "product")
	List<Orderdetail> orderDetails;
	
}
