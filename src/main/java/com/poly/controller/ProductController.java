package com.poly.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.poly.dao.ProductDao;
import com.poly.entity.Product;
import com.poly.service.ProductService;


@Controller
public class ProductController {

	@Autowired
	ProductDao dao;
	
	@RequestMapping("product/list")
	public String plist(Model model, @RequestParam("cid") Optional<String>cid, @RequestParam("p") Optional<Integer> pg) {
		Pageable pageable = (Pageable) PageRequest.of(pg.orElse(0), 9);
		if(cid.isPresent()) {
			Page<Product> list = dao.findAllByCate(cid.get(), pageable);
			model.addAttribute("page",list);
		}
		else {
			Page<Product> page = dao.findAll(pageable);		
			model.addAttribute("page",page);
		}
		return "product/list";
	}
	
	@RequestMapping("product/detail/{id}")
	public String pdetail(Model model,@PathVariable("id") Integer id) {
		Product prod = dao.findById(id).get();
		model.addAttribute("item",prod);
		return"product/detail";
	}
}
