package com.poly.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.poly.dao.OrderDao;
import com.poly.dao.OrderDetailDao;
import com.poly.entity.Order;
import com.poly.service.OrderService;

@Controller
public class OrderController {

	@Autowired
	OrderService osv;
	
	@Autowired
	OrderDetailDao oddao;
	
	@Autowired
	OrderDao dao;
	
	@RequestMapping("order/checkout")
	public String checkout() {
		
		return "order/checkout";
	}
	
	@RequestMapping("order/list")
	public String olist(Model model, HttpServletRequest request) {
		String username = request.getRemoteUser();
		model.addAttribute("orders",osv.findByUser(username));
		return "order/list";
	}
	
	@RequestMapping("order/detail/{id}")
	public String odetail(Model model,@PathVariable("id")Long id) {
		Order order = osv.findById(id);
		model.addAttribute("order",order);
		return "order/detail";
	}
	
}
