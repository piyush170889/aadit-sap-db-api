package com.loungeapp.restcontrollers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.loungeapp.exception.ServicesException;
import com.loungeapp.model.BaseWrapper;
import com.loungeapp.service.ItemsService;

@RestController
public class ItemsController {

	@Autowired
	private ItemsService itemsService;
	
	
	@RequestMapping(value = "ext/items", method = RequestMethod.GET)
	public BaseWrapper getItems(@RequestParam(value="pageNo", required=true) int pageNo,
			@RequestParam(value="limit", required=false) Optional<Integer> limit) throws ServicesException {
		
		return itemsService.doGetItems(pageNo, limit);
	}
	
	@RequestMapping(value = "ext/all-items", method = RequestMethod.GET)
	public BaseWrapper getAllItems() {
		
		return itemsService.doGetAllItems();
	}
}
