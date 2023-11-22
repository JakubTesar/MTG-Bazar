package me.mtgbazar.mtgbazar.models.DTO.mappers;

import me.mtgbazar.mtgbazar.data.entities.CardEntity;
import me.mtgbazar.mtgbazar.data.entities.CardForSaleEntity;
import me.mtgbazar.mtgbazar.models.DTO.BasicCardForSaleDTO;
import me.mtgbazar.mtgbazar.models.DTO.CardDTO;
import me.mtgbazar.mtgbazar.models.DTO.CardForSaleDTO;
import org.hibernate.annotations.Target;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CardForSaleMapper {
    CardForSaleEntity toEntity(CardForSaleDTO source);

    CardForSaleDTO toDTO(CardForSaleEntity source);

    //BasicCardForSaleDTO toBasicDTO (CardForSaleDTO source);
}

