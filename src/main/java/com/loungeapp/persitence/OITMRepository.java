package com.loungeapp.persitence;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.loungeapp.domain.OITM;

public interface OITMRepository extends JpaRepository<OITM, String> {

	List<OITM> findByItmsGrpCod(int itemGroupCode, Pageable pageable);

	List<OITM> findByItmsGrpCod(int itemGrpCd);

	List<OITM> findByItemCodeIn(Set<String> itemCodesSet);

}
