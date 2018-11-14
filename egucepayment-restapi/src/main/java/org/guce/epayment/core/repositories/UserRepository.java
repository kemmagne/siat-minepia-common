package org.guce.epayment.core.repositories;

import java.math.BigDecimal;
import java.util.Collection;
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

    @Query("SELECT u FROM User u WHERE u.deleted = false ORDER BY u.id DESC")
    @Override
    List<User> findAll();

    @Query("SELECT u FROM User u WHERE u.login = ?1 AND u.deleted = false")
    Optional<User> findByLogin(String login);

    @Query("SELECT u FROM User u WHERE u.active = false")
    List<User> findDesactivedUsers();

    @Query("SELECT u FROM User u WHERE u.locked = true")
    List<User> findLockedUsers();

    @Query("SELECT u FROM User u JOIN u.roles r WHERE r.name IN ?1 AND u.active = true AND u.locked = false AND u.deleted = false")
    List<User> findByRoles(Collection<String> roleNames);

    @Query("SELECT u FROM User u JOIN u.roles r WHERE u.partner.id = ?1 AND r.name IN ?2 AND u.active = true AND u.locked = false AND u.deleted = false")
    List<User> findByPartnerAndRoles(BigDecimal partnerId, Collection<String> roleNames);

}

