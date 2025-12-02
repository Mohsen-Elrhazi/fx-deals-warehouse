package com.fxdealswarehouse.repository;

import com.fxdealswarehouse.model.Deal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DealRepository extends JpaRepository<Deal, String> {
}
