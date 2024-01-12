package me.mtgbazar.mtgbazar.data.repositories;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import me.mtgbazar.mtgbazar.data.entities.CardEntity;
import me.mtgbazar.mtgbazar.data.entities.QCardEntity;
import me.mtgbazar.mtgbazar.data.entities.QUserEntity;
import me.mtgbazar.mtgbazar.data.entities.UserEntity;
import me.mtgbazar.mtgbazar.data.entities.filter.CardFilter;
import me.mtgbazar.mtgbazar.data.entities.filter.UserFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository {
    @Autowired
    private EntityManager entityManager;

    public List<UserEntity> findAllUsers(UserFilter f, Pageable pageable) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
        QUserEntity u = QUserEntity.userEntity;
        var predicate = u.email.like("%%");
        if (f.getEmail() != null)
            predicate = predicate.and(u.email.like("%" + f.getEmail() + "%"));
        if (f.getName() != null)
            predicate = predicate.and(u.username.like("%" + f.getName() + "%"));

        return queryFactory.selectFrom(u)
                .where(predicate)
                .fetch();
    }
}
