package me.mtgbazar.mtgbazar.models.service.users;

import me.mtgbazar.mtgbazar.models.DTO.CardDTO;
import me.mtgbazar.mtgbazar.models.DTO.UserDTO;

import java.util.List;

public interface UserService {
    List<UserDTO> getAllUsers();
    UserDTO getUserById(long userId);
    UserDTO getLoggedUser();
}
