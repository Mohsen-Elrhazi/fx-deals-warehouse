package com.fxdealswarehouse.repository;

import com.fxdealswarehouse.model.Deal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DealRepository extends JpaRepository<Deal, Long> {
    boolean existsByDealId(String dealId);
    Optional<Deal> findByDealId(String dealId);
}
