package org.guce.epayment.core.services;

import java.math.BigDecimal;
import java.util.List;
import org.guce.epayment.core.entities.User;
import org.guce.epayment.core.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Transactional
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> findByRoles(final String roleNames) {
        return userRepository.findByRoles(StringUtils.commaDelimitedListToSet(roleNames));
    }

    @Override
    public List<User> findByPartnerAndRoles(final BigDecimal partnerId, final String roleNames) {

        return userRepository.findByPartnerAndRoles(partnerId, StringUtils.commaDelimitedListToSet(roleNames));
    }

    @Override
    public List<User> findDesactivedUsers() {
        return userRepository.findDesactivedUsers();
    }

    @Override
    public List<User> findLockedUsers() {
        return userRepository.findLockedUsers();
    }

}
