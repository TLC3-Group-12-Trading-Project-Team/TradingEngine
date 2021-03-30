package com.TradeProject.TradeEngine.Repository;

import com.TradeProject.TradeEngine.models.OrderTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderTransactionRepository extends JpaRepository<OrderTransaction, Long> {
//    Optional<OrderTransaction> findById(String email);
    @Query(value = "select * from order_transaction where status = ?1 ",
            nativeQuery = true
    )
    List<OrderTransaction> findAllByStatus(String status);

}
