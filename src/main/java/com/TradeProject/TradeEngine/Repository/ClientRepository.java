package com.TradeProject.TradeEngine.Repository;

import com.TradeProject.TradeEngine.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {
    Optional<Client> findClientByEmail(String email);
}