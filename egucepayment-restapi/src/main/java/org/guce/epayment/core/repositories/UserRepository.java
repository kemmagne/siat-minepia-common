package org.guce.epayment.core.repositories;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import org.guce.epayment.core.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author tadzotsa
 */
public interface UserRepository extends JpaRepository<User, BigDecimal> {

    @Query("SELECT u FROM User u ORDER BY u.id DESC")
    @Override
    List<User> findAll();

    Optional<User> findByLogin(String login);

    @Query("SELECT u FROM User u WHERE u.active = false")
    List<User> findDesactivedUsers();

    @Query("SELECT u FROM User u WHERE u.locked = true")
    List<User> findLockedUsers();

}
