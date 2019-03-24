package com.loungeapp.persitence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.loungeapp.domain.DeliveryHeader;

@Repository
public interface DeliveryHeaderRepository extends JpaRepository<DeliveryHeader, Integer> {

	List<DeliveryHeader> findByDocEntryIn(List<Integer> deliveryHeaderLookUpIdsList);

}
