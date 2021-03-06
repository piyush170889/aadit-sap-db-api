package com.loungeapp.restcontrollers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.loungeapp.exception.ServicesException;
import com.loungeapp.service.OrdersService;

@RestController
@RequestMapping(value = "/ext/orders")
public class OrdersController {

	@Autowired
	private OrdersService ordersService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public Object getOrders(@RequestParam(value = "pageNo", required = false) Optional<Integer> pageNo,
			@RequestParam(value = "limit", required = false) Optional<Integer> limit) throws ServicesException {

		return ordersService.doGetOrders(pageNo, limit);
	}

	@RequestMapping(value = "{order-dtls-id}", method = RequestMethod.GET)
	public Object getOrdersDetails(@PathVariable(value = "order-dtls-id") int orderDlsId) throws ServicesException {

		return ordersService.doGetOrdersDetails(orderDlsId);
	}

	@RequestMapping(value = "/{order-dtls-id}/item-details/{item-code}", method = RequestMethod.GET)
	public Object getOrdersItemDetails(@PathVariable(value = "order-dtls-id") int orderDlsId,
			@PathVariable(value = "item-code") String itemCode) throws ServicesException {

		return ordersService.doGetOrderItemsDetails(orderDlsId, itemCode);
	}
	
	@RequestMapping(value = "/{order-dtls-id}/item-details/{item-code}/by-date/{date}", method = RequestMethod.GET)
	public Object getOrdersItemDetails(@PathVariable(value = "order-dtls-id") int orderDlsId,
			@PathVariable(value = "item-code") String itemCode, @PathVariable(value = "date") String date) throws ServicesException {

		return ordersService.doGetOrderItemsDetailsAfterDate(orderDlsId, itemCode, date);
	}
		
}
