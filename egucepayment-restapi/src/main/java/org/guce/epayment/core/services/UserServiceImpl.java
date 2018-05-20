package org.guce.epayment.core.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
import org.guce.epayment.core.entities.User;
import org.guce.epayment.core.repositories.RoleRepository;
import org.guce.epayment.core.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ApplicationService appService;

    @Override
    public List<User> findByRoles(final String roleNames) {

        final List<User> users = new ArrayList<>();

        Arrays.asList(StringUtils.split(roleNames, appService.getColSep())).forEach(roleName -> {

            users.addAll(roleRepository.findByName(roleName).get().getUsers());
        });

        return users.stream().distinct().collect(Collectors.toList());
    }

    @Override
    public List<User> findByPartnerAndRoles(final BigDecimal partnerId, final String roleNames) {

        return findByRoles(roleNames).stream().filter(user -> partnerId.equals(user.getPartner().getId())).collect(Collectors.toList());
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
