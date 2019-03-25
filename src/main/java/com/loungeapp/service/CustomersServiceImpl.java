package com.loungeapp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.loungeapp.domain.CustomerAddresses;
import com.loungeapp.domain.Customers;
import com.loungeapp.model.BaseWrapper;
import com.loungeapp.model.CustomerDetailsWrapper;
import com.loungeapp.persitence.CustomersAddressesRepository;
import com.loungeapp.persitence.CustomersRepository;
import com.loungeapp.utils.CommonUtility;

@Service
@Transactional(rollbackFor = Throwable.class)
public class CustomersServiceImpl implements CustomersService {

	@Autowired
	private CustomersRepository customersRepository;

	@Autowired
	private CustomersAddressesRepository customersAddressesRepository;

	@Autowired
	private CommonUtility commonUtility;

	@Override
	public BaseWrapper doGetCustomersList(Optional<Integer> pageNo) {

		List<Customers> customersList;

		if (pageNo.isPresent()) {
			Sort sort = new Sort(Direction.DESC, "cardCode");
			PageRequest pageRequest = commonUtility.getPageRequest(pageNo.get(), sort);
			customersList = customersRepository.findAll(pageRequest).getContent();
		} else {
			customersList = customersRepository.findAll();
		}

		if (customersList.isEmpty())
			customersList = new ArrayList<Customers>();

		// Create Response And Send
		List<CustomerDetailsWrapper> response = new ArrayList<CustomerDetailsWrapper>();

		for (Customers customers : customersList) {
			List<CustomerAddresses> customerAddressesList = customersAddressesRepository
					.findByCardCode(customers.getCardCode());

			CustomerDetailsWrapper customerDetailsWrapper = new CustomerDetailsWrapper(customers,
					customerAddressesList);

			response.add(customerDetailsWrapper);
		}

		return new BaseWrapper(response);
	}

}
