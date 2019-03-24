package com.loungeapp.restcontrollers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.loungeapp.service.CustomersService;

@RestController
@RequestMapping(value="ext/customers")
public class CustomersController {

	
	@Autowired
	private CustomersService customersService;
	
	
	@RequestMapping(value="", method=RequestMethod.GET)
	public Object getCustomersList(@RequestParam(value="pageNo", required=false) Optional<Integer> pageNo) {
		
		return customersService.doGetCustomersList(pageNo);
	}
	
}
