package com.loungeapp.persitence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.loungeapp.domain.CustomerAddresses;

@Repository
public interface CustomersAddressesRepository extends JpaRepository<CustomerAddresses, String> {

	List<CustomerAddresses> findByCardCode(String cardCode);

}
