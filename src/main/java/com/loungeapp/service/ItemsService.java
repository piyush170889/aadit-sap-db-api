package com.loungeapp.service;

import java.util.Optional;

import com.loungeapp.exception.ServicesException;
import com.loungeapp.model.BaseWrapper;

public interface ItemsService {

	BaseWrapper doGetItems(int pageNo, Optional<Integer> limit) throws ServicesException;

	BaseWrapper doGetAllItems();
	
}
