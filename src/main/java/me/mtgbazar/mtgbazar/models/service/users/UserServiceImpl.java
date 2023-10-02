package me.mtgbazar.mtgbazar.models.service.users;

import me.mtgbazar.mtgbazar.data.repositories.UsersRepositories;
import me.mtgbazar.mtgbazar.models.DTO.UserDTO;
import me.mtgbazar.mtgbazar.models.DTO.mappers.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.StreamSupport;

@Service
public class UserServiceImpl implements UserService  {

    @Autowired
    private UsersRepositories usersRepository;

    @Autowired
    private UserMapper userMapper;
    @Override
    public List<UserDTO> getAllUsers() {
        return StreamSupport.stream(usersRepository.findAll().spliterator(), false)
                .map(i -> userMapper.toDTO(i))
                .toList();
    }

    @Override
    public UserDTO getUserById(long userId) {
        return null;
    }
}
