package me.mtgbazar.mtgbazar.models.DTO.mappers;

import me.mtgbazar.mtgbazar.data.entities.CardForSaleEntity;
import me.mtgbazar.mtgbazar.data.entities.UserEntity;
import me.mtgbazar.mtgbazar.models.DTO.BasicCardForSaleDTO;
import me.mtgbazar.mtgbazar.models.DTO.BasicUserDTO;
import me.mtgbazar.mtgbazar.models.DTO.UserDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BasicUserDTOMapper {
    BasicUserDTO toDTO(UserDTO source);
}
