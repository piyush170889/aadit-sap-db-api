package com.loungeapp.persitence;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.loungeapp.domain.Orders;
import com.loungeapp.model.DispatchOrderMgmtTo;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Integer> {

	Orders findByDocEntry(int orderDtlsId);

	@Query(value = "select dor.DISPATCH_DATE, DISPATCH_TIME, dor.ORDER_ID, DISPATCH_QTY, dor.ITEM_CODE, oitm.ItemName from dispatch_order_schedule dos "
			+ "inner join dispatch_orders dor on dos.DISPATCH_ORDERS_ID=dor.DISPATCH_ORDERS_ID and dor.DISPATCH_DATE>=?1 "
			+ "inner join OITM oitm on dor.ITEM_CODE=oitm.ItemCode order by dor.DISPATCH_DATE ASC", nativeQuery = true)
	List<DispatchOrderMgmtTo> findDispatchOrders(String date);

	List<Orders> findByDocNumIn(Set<Integer> orderIdsSet);

}
