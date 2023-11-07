package me.mtgbazar.mtgbazar.models.service.users;

import me.mtgbazar.mtgbazar.data.entities.UserEntity;
import me.mtgbazar.mtgbazar.data.repositories.UsersRepositories;
import me.mtgbazar.mtgbazar.models.DTO.UserDTO;
import me.mtgbazar.mtgbazar.models.DTO.mappers.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.StreamSupport;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UsersRepositories usersRepositories;

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<UserDTO> getAllUsers() {
        return StreamSupport.stream(usersRepositories.findAll().spliterator(), false)
                .map(i -> userMapper.toDTO(i))
                .toList();
    }

    @Override
    public UserDTO getUserById(long userId) {
        UserEntity user = usersRepositories.findById(userId).orElseThrow();
        UserDTO userDTO = userMapper.toDTO(user);
        return userDTO;
    }

    public UserDTO getLoggedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String singedUserEmail = authentication.getName();
        UserEntity user = usersRepositories.findByEmail(singedUserEmail).orElseThrow();
        return userMapper.toDTO(user);
    }
}
