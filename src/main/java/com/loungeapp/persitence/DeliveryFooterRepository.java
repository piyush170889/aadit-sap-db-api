package com.loungeapp.persitence;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.loungeapp.domain.DeliveryFooter;

@Repository
public interface DeliveryFooterRepository extends JpaRepository<DeliveryFooter, Integer> {

	List<DeliveryFooter> findByBaseEntryAndItemCode(int orderDlsId, String itemCode);

	@Query(nativeQuery = true, value = "select dln.DocEntry,dln.LineNum,dln.BaseEntry,dln.LineStatus,dln.ItemCode,dln.Dscription,dln.Quantity,"
			+ "dln.ShipDate,dln.OpenQty,dln.Price,dln.Currency,dln.Rate,dln.LineTotal,dln.DocDate,dln.ShipToCode,dln.ShipToDesc,dln.QtyToShip,dln.DelivrdQty,dln.OrderedQty "
			+ "from ODLN as odln inner join DLN1 as dln on odln.DocEntry=dln.DocEntry and dln.BaseEntry=?1 and dln.ItemCode=?2 and odln.DocDate = ?3")
	List<DeliveryFooter> findDeliveryFooterDetailsByBaseEntryAndItemCodeAndDate(int orderDlsId, String itemCode,
			String dateToSend);

}
