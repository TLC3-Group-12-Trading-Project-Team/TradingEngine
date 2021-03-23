package com.TradeProject.TradeEngine.Repository;

import com.TradeProject.TradeEngine.models.Portfolio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PortfolioRepository extends JpaRepository<Portfolio, Long> {
    Optional<Portfolio> findByName(String name);
    List<Portfolio> findByClientId(Long client_id);
}
