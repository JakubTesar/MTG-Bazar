package me.mtgbazar.mtgbazar.models.service.users;

import me.mtgbazar.mtgbazar.data.entities.CardEntity;
import me.mtgbazar.mtgbazar.data.entities.UserEntity;
import me.mtgbazar.mtgbazar.data.entities.filter.UserFilter;
import me.mtgbazar.mtgbazar.data.repositories.UserRepository;
import me.mtgbazar.mtgbazar.data.repositories.UsersRepositories;
import me.mtgbazar.mtgbazar.models.DTO.CardDTO;
import me.mtgbazar.mtgbazar.models.DTO.UserDTO;
import me.mtgbazar.mtgbazar.models.DTO.mappers.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.StreamSupport;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UsersRepositories usersRepositories;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Override
    public Page<UserDTO> getAllUsers(UserFilter filter, Pageable pageable) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<UserEntity> userEntities = userRepository.findAllUsers(filter, pageable);
        List<UserDTO> userDTOS;
        int toIndex = Math.min(startItem + pageSize, userEntities.size());
        if (userEntities.size() < startItem) userDTOS = Collections.emptyList();
        else userDTOS = userEntities.subList(startItem, toIndex).stream().map(c -> userMapper.toDTO(c)).toList();

        return new PageImpl<UserDTO>(userDTOS, PageRequest.of(currentPage, pageSize), userEntities.size());
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
