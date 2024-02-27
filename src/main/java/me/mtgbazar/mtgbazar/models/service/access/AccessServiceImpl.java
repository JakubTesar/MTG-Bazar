package me.mtgbazar.mtgbazar.models.service.access;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import me.mtgbazar.mtgbazar.data.entities.UserEntity;
import me.mtgbazar.mtgbazar.data.repositories.UsersRepositories;
import me.mtgbazar.mtgbazar.models.DTO.UserAccessDTO;
import me.mtgbazar.mtgbazar.models.DTO.UserDTO;
import me.mtgbazar.mtgbazar.models.DTO.mappers.CardMapper;
import me.mtgbazar.mtgbazar.models.DTO.mappers.UserMapper;
import me.mtgbazar.mtgbazar.models.exeptions.DuplicateUsernameException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;

@Service
public class AccessServiceImpl implements AccessService, UserDetailsService {
    @Autowired
    private UsersRepositories usersRepository;
    @Autowired
    private UsersRepositories usersRepositories;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void registerUser(UserAccessDTO user) throws DuplicateEmailException {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(user.getUsername());
        userEntity.setEmail(user.getEmail());
        userEntity.setPassword(passwordEncoder.encode(user.getPassword()));

        try {
            usersRepository.save(userEntity);
        } catch (DataIntegrityViolationException e) {
            Optional<UserEntity> usernameInput = usersRepository.findByUsername(user.getUsername());
            Optional<UserEntity> emailInput = usersRepository.findByEmail(user.getEmail());
            if (emailInput.isPresent()) {
                throw new DuplicateEmailException();
            }
            if (usernameInput.isPresent()) {
                throw new DuplicateUsernameException();
            }
        }
        try {
            usersRepository.save(userEntity);
        } catch (DataIntegrityViolationException e) {
            throw new DuplicateEmailException();
        }
    }

    @Override
    public UserDTO getUserById(long userId) {
        return userMapper.toDTO(usersRepository.findById(userId).orElseThrow());
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        var tmp = usersRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Email, " + email + " not found"));
        return tmp;
    }

    @Override
    public boolean isThisUserLoggedNow(long userId) {
        return getLoggedUser().getId() == userId;
    }

    @Override
    public UserDTO getLoggedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String singedUserUsername = authentication.getName();
        UserEntity user = usersRepositories.findByUsername(singedUserUsername).orElseThrow();
        return userMapper.toDTO(user);
    }
}
