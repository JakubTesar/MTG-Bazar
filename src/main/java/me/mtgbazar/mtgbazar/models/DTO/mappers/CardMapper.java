package me.mtgbazar.mtgbazar.models.DTO.mappers;

import me.mtgbazar.mtgbazar.data.entities.CardEntity;
import me.mtgbazar.mtgbazar.models.DTO.CardDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CardMapper {
    CardEntity toEntity(CardDTO source);
    CardDTO toDTO(CardEntity source);
}
