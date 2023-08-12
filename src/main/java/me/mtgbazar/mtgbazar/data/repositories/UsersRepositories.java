package me.mtgbazar.mtgbazar.data.repositories;

import me.mtgbazar.mtgbazar.data.entities.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UsersRepositories extends CrudRepository<UserEntity, Long> {
}
