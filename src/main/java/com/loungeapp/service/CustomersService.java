package com.loungeapp.service;

import java.util.Optional;

import com.loungeapp.model.BaseWrapper;

public interface CustomersService {

	BaseWrapper doGetCustomersList(Optional<Integer> pageNo);

}
