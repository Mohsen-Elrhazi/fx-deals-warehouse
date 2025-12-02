package com.fxdealswarehouse.mapper;


import com.fxdealswarehouse.dto.request.DealRequestDTO;
import com.fxdealswarehouse.dto.response.DealResponseDTO;
import com.fxdealswarehouse.model.Deal;
import org.mapstruct.Mapper;

@Mapper(componentModel = "Spring")
public interface DealMapper {

    Deal toEntity(DealRequestDTO dealDTO);

    DealResponseDTO toResponseDTO(Deal deal);
}
