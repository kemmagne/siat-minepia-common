package org.guce.epayment.core.repositories;

import java.util.Optional;
import org.guce.epayment.core.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author tadzotsa
 */
public interface RoleRepository extends JpaRepository<Role, Integer> {

    Optional<Role> findByName(String roleName);

}
