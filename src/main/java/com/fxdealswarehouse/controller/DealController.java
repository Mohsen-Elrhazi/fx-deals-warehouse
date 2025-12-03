package com.fxdealswarehouse.controller;

import com.fxdealswarehouse.dto.request.DealRequestDTO;
import com.fxdealswarehouse.dto.response.ApiResponse;
import com.fxdealswarehouse.dto.response.DealResponseDTO;
import com.fxdealswarehouse.service.DealService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name="deals",description="APIs for managing deals")
@RestController
@RequestMapping("/api/deals")
@AllArgsConstructor
public class DealController {
    private final DealService dealService;

    @PostMapping
    public ResponseEntity<ApiResponse<DealResponseDTO>> createDeal(@Valid @RequestBody DealRequestDTO dealRequestDTO) {
        DealResponseDTO dealResponseDTO = dealService.createDeal(dealRequestDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(
                ApiResponse.<DealResponseDTO>builder()
                        .status("success")
                        .message("Deal created successfully")
                        .data(dealResponseDTO)
                        .build()
        );
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<DealResponseDTO>>> getAllDeals() {
        List<DealResponseDTO> deals = dealService.getAllDeals();

        return ResponseEntity.ok(
                ApiResponse.<List<DealResponseDTO>>builder()
                        .status("success")
                        .message("Deals retrieved successfully")
                        .data(deals)
                        .build()
        );
    }

    @GetMapping("/{dealId}")
    public ResponseEntity<ApiResponse<DealResponseDTO>> getDealByDealId(@PathVariable String dealId) {
        DealResponseDTO dealResponseDTO = dealService.getDealByDealId(dealId);
        return ResponseEntity.ok(
                ApiResponse.<DealResponseDTO>builder()
                        .status("success")
                        .message("Deal retrieved successfully")
                        .data(dealResponseDTO)
                        .build()
        );
    }

}
