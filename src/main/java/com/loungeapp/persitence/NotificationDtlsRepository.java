package com.loungeapp.persitence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.loungeapp.domain.NotificationDtls;

@Repository
public interface NotificationDtlsRepository extends JpaRepository<NotificationDtls, Integer> {

}
