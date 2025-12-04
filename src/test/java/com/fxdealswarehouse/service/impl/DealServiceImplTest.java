package com.fxdealswarehouse.service.impl;

import com.fxdealswarehouse.dto.request.DealRequestDTO;
import com.fxdealswarehouse.dto.response.DealResponseDTO;
import com.fxdealswarehouse.exception.DealAlreadyExistsException;
import com.fxdealswarehouse.exception.DealNotFoundException;
import com.fxdealswarehouse.mapper.DealMapper;
import com.fxdealswarehouse.model.Deal;
import com.fxdealswarehouse.repository.DealRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DealServiceImplTest {

    @Mock
    private DealRepository dealRepository;

    @Mock
    private DealMapper dealMapper;

    @InjectMocks
    private DealServiceImpl dealService;

    private DealRequestDTO dealRequestDTO;
    private Deal deal;
    private DealResponseDTO dealResponseDTO;

    @BeforeEach
    void setUp() {
        dealRequestDTO = DealRequestDTO.builder()
                .dealId("D001")
                .fromCurrencyIsoCode("USD")
                .toCurrencyIsoCode("EUR")
                .dealTimestamp(LocalDateTime.now())
                .dealAmount(1000.0)
                .build();

        deal = Deal.builder()
                .dealId("D001")
                .fromCurrencyIsoCode("USD")
                .toCurrencyIsoCode("EUR")
                .dealTimestamp(LocalDateTime.now())
                .dealAmount(1000.0)
                .build();

        dealResponseDTO = DealResponseDTO.builder()
                .dealId("D001")
                .fromCurrencyIsoCode("USD")
                .toCurrencyIsoCode("EUR")
                .dealTimestamp(LocalDateTime.now())
                .dealAmount(1000.0)
                .build();
    }

    @Test
    void createDeal_success() {
        when(dealRepository.existsByDealId(dealRequestDTO.getDealId())).thenReturn(false);
        when(dealMapper.toEntity(dealRequestDTO)).thenReturn(deal);
        when(dealRepository.save(deal)).thenReturn(deal);
        when(dealMapper.toResponseDTO(deal)).thenReturn(dealResponseDTO);

        DealResponseDTO response = dealService.createDeal(dealRequestDTO);

        assertNotNull(response);
        assertEquals("D001", response.getDealId());
        verify(dealRepository, times(1)).save(deal);
    }

    @Test
    void createDeal_alreadyExists_throwsException() {
        when(dealRepository.existsByDealId(dealRequestDTO.getDealId())).thenReturn(true);

        assertThrows(DealAlreadyExistsException.class, () -> dealService.createDeal(dealRequestDTO));
        verify(dealRepository, never()).save(any());
    }

    @Test
    void getDealByDealId_success() {
        when(dealRepository.findByDealId("D001")).thenReturn(Optional.of(deal));
        when(dealMapper.toResponseDTO(deal)).thenReturn(dealResponseDTO);

        DealResponseDTO response = dealService.getDealByDealId("D001");

        assertNotNull(response);
        assertEquals("D001", response.getDealId());
    }

    @Test
    void getDealByDealId_notFound_throwsException() {
        when(dealRepository.findByDealId("D001")).thenReturn(Optional.empty());

        assertThrows(DealNotFoundException.class, () -> dealService.getDealByDealId("D001"));
    }

    @Test
    void getAllDeals_success() {
        when(dealRepository.findAll()).thenReturn(List.of(deal));
        when(dealMapper.toResponseDTO(deal)).thenReturn(dealResponseDTO);

        List<DealResponseDTO> allDeals = dealService.getAllDeals();

        assertEquals(1, allDeals.size());
        assertEquals("D001", allDeals.get(0).getDealId());
    }
}
