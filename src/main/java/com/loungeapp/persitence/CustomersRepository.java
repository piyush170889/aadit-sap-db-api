package com.loungeapp.persitence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.loungeapp.domain.Customers;

@Repository
public interface CustomersRepository extends JpaRepository<Customers, String> {

}
