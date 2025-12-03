package com.fxdealswarehouse.service;

import com.fxdealswarehouse.dto.request.DealRequestDTO;
import com.fxdealswarehouse.dto.response.DealResponseDTO;

import java.util.List;

public interface DealService {
    DealResponseDTO createDeal(DealRequestDTO dealRequestDTO);
    DealResponseDTO getDealByDealId(String dealId);
    List<DealResponseDTO> getAllDeals();

}
