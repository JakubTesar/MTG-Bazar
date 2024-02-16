package me.mtgbazar.mtgbazar.data.repositories;

import me.mtgbazar.mtgbazar.data.entities.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UsersRepositories extends CrudRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmail(String email);
    Optional<UserEntity> findByUsername(String username);

}
