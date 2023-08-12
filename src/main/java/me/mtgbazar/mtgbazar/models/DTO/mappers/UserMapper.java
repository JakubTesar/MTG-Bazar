package me.mtgbazar.mtgbazar.models.DTO.mappers;

import me.mtgbazar.mtgbazar.data.entities.CardEntity;
import me.mtgbazar.mtgbazar.data.entities.UserEntity;
import me.mtgbazar.mtgbazar.models.DTO.CardDTO;
import me.mtgbazar.mtgbazar.models.DTO.UserDTO;
import org.mapstruct.Mapper;

//@Mapper(componentModel = "spring")
@Mapper(componentModel = "spring")
public interface UserMapper {
    UserEntity toEntity(UserDTO source);

    UserDTO toDTO(UserEntity source);

}
