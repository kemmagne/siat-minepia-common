package org.guce.epayment.security.services;

import java.util.Optional;
import org.guce.epayment.core.entities.User;

/**
 *
 * @author tadzotsa
 */
public interface AuthenticationService {

    void saveUser(User user, String password);

    Optional<User> authenticate(final String userLogin, final String password);

}
