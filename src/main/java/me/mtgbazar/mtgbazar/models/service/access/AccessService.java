package me.mtgbazar.mtgbazar.models.service.access;

import me.mtgbazar.mtgbazar.models.DTO.UserAccessDTO;
import me.mtgbazar.mtgbazar.models.DTO.UserDTO;

public interface AccessService {
    void registerUser(UserAccessDTO userDTO) throws DuplicateEmailException;
    UserDTO getUserById(long userId);
    boolean isThisUserLoggedNow(long userId);
    UserDTO getLoggedUser();
    boolean verify(String key);
}
