package me.mtgbazar.mtgbazar.models.DTO.mappers;

import me.mtgbazar.mtgbazar.data.entities.CardEntity;
import me.mtgbazar.mtgbazar.data.entities.CardForSaleEntity;
import me.mtgbazar.mtgbazar.models.DTO.CardDTO;
import me.mtgbazar.mtgbazar.models.DTO.CardForSaleDTO;
import org.mapstruct.Mapper;
@Mapper(componentModel = "spring")
public interface CardForSaleMapper {
    CardForSaleEntity toEntity(CardForSaleDTO source);
    CardForSaleDTO toDTO(CardForSaleEntity source);
}

