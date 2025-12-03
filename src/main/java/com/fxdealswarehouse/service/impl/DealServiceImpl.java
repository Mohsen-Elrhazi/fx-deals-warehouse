package com.fxdealswarehouse.service.impl;

import com.fxdealswarehouse.dto.request.DealRequestDTO;
import com.fxdealswarehouse.dto.response.DealResponseDTO;
import com.fxdealswarehouse.exception.DealAlreadyExistsException;
import com.fxdealswarehouse.exception.DealNotFoundException;
import com.fxdealswarehouse.mapper.DealMapper;
import com.fxdealswarehouse.model.Deal;
import com.fxdealswarehouse.repository.DealRepository;
import com.fxdealswarehouse.service.DealService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.rmi.AlreadyBoundException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class DealServiceImpl implements DealService {
    private final DealRepository dealRepository;
    private final DealMapper dealMapper;

    @Override
    public DealResponseDTO createDeal(DealRequestDTO dealRequestDTO) {
        if(dealRepository.existsByDealId(dealRequestDTO.getDealId())){
            throw new DealAlreadyExistsException("Deal with ID '" + dealRequestDTO.getDealId() + "' already exists.");
        }

        Deal deal = dealMapper.toEntity(dealRequestDTO);
        Deal savedDeal = dealRepository.save(deal);
        log.info("Deal with ID '{}' created successfully", savedDeal.getDealId());

        return dealMapper.toResponseDTO(savedDeal);
    }

    @Override
    public DealResponseDTO getDealByDealId(String dealId) {
        Deal deal = dealRepository.findByDealId(dealId)
                .orElseThrow(() -> {
                    return new DealNotFoundException("Deal with ID '" + dealId + "' not found.");
                });

        log.info("Deal with ID '{}' retrieved successfully", dealId);
        return dealMapper.toResponseDTO(deal);
    }


    @Override
    public List<DealResponseDTO> getAllDeals() {
        List<DealResponseDTO> deals = dealRepository.findAll()
                .stream()
                .map(dealMapper::toResponseDTO)
                .toList();
        log.info("{} deals retrieved", deals.size());
        return deals;
    }
}
