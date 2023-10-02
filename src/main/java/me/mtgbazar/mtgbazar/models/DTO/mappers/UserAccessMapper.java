package me.mtgbazar.mtgbazar.models.DTO.mappers;

import me.mtgbazar.mtgbazar.data.entities.CardEntity;
import me.mtgbazar.mtgbazar.data.entities.UserEntity;
import me.mtgbazar.mtgbazar.models.DTO.CardDTO;
import me.mtgbazar.mtgbazar.models.DTO.UserAccessDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")

public interface UserAccessMapper {
        UserEntity toEntity(UserAccessDTO source);
        UserAccessDTO toDTO(UserEntity source);


}
