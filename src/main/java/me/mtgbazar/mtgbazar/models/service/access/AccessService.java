package me.mtgbazar.mtgbazar.models.service.access;

import me.mtgbazar.mtgbazar.models.DTO.UserDTO;

public interface AccessService {
    void registerUser(UserDTO userDTO);

    UserDTO getUserById(long userId);
}
