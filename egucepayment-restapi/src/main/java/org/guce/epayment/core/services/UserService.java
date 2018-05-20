package org.guce.epayment.core.services;

import java.math.BigDecimal;
import java.util.List;
import org.guce.epayment.core.entities.User;

/**
 *
 * @author tadzotsa
 */
public interface UserService {

    List<User> findByRoles(String roleNames);

    List<User> findByPartnerAndRoles(BigDecimal partnerId, String roleNames);

    List<User> findDesactivedUsers();

    List<User> findLockedUsers();

}
