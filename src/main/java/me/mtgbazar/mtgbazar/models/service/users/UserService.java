package me.mtgbazar.mtgbazar.models.service.users;

import me.mtgbazar.mtgbazar.data.entities.filter.UserFilter;
import me.mtgbazar.mtgbazar.models.DTO.CardDTO;
import me.mtgbazar.mtgbazar.models.DTO.UserDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {
    Page<UserDTO> getAllUsers(UserFilter f, Pageable pageable);
    UserDTO getUserById(long userId);
    UserDTO getLoggedUser();
}
