package com.poly.entity;

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
@Entity @Table(name="Categories")
public class Category {

	@Id
	String id;
	String name;
	
	@JsonIgnore
	@OneToMany(mappedBy = "category")
	List<Product> products;
}
