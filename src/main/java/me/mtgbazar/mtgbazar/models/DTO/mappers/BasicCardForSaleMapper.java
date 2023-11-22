package me.mtgbazar.mtgbazar.models.DTO.mappers;

import me.mtgbazar.mtgbazar.data.entities.CardForSaleEntity;
import me.mtgbazar.mtgbazar.data.entities.UserEntity;
import me.mtgbazar.mtgbazar.models.DTO.*;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BasicCardForSaleMapper {
    BasicCardForSaleDTO toBasicDTO(CardForSaleDTO source);

    BasicUserDTO toBasicDTO(UserDTO source);
}
