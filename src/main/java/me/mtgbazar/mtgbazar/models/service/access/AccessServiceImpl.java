package me.mtgbazar.mtgbazar.models.service.access;

import me.mtgbazar.mtgbazar.data.entities.UserEntity;
import me.mtgbazar.mtgbazar.data.repositories.UsersRepositories;
import me.mtgbazar.mtgbazar.models.DTO.UserDTO;
import me.mtgbazar.mtgbazar.models.DTO.mappers.CardMapper;
import me.mtgbazar.mtgbazar.models.DTO.mappers.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccessServiceImpl implements AccessService{
    @Autowired
    private UsersRepositories usersRepositories;
    @Autowired
    private UserMapper userMapper;
    @Override
    public void registerUser(UserDTO userDTO) {
        UserEntity user = userMapper.toEntity(userDTO);
        usersRepositories.save(user);
    }

    @Override
    public UserDTO getUserById(long userId) {
        return userMapper.toDTO(usersRepositories.findById(userId).orElseThrow());
    }
}
