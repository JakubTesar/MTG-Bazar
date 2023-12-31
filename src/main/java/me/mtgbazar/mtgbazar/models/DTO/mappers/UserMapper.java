package me.mtgbazar.mtgbazar.models.DTO.mappers;

import me.mtgbazar.mtgbazar.data.entities.UserEntity;
import me.mtgbazar.mtgbazar.models.DTO.BasicUserDTO;
import me.mtgbazar.mtgbazar.models.DTO.UserDTO;
import org.mapstruct.Mapper;

//@Mapper(componentModel = "spring")
@Mapper(componentModel = "spring")
public interface UserMapper {
    UserEntity toEntity(UserDTO source);
    UserDTO toDTO(UserEntity source);
    //BasicUserDTO toDTO (UserEntity source2);


}
