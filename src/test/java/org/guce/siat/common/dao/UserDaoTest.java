package org.guce.siat.common.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.lang3.RandomStringUtils;
import org.guce.siat.common.dao.config.H2DataSourceConfig;
import org.guce.siat.common.model.Administration;
import org.guce.siat.common.model.Authority;
import org.guce.siat.common.model.User;
import org.guce.siat.common.model.UserAuthority;
import org.guce.siat.common.utils.enums.AuthorityConstants;
import org.guce.siat.common.utils.enums.AuthorityType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ht
 */
@Transactional(readOnly = true)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {H2DataSourceConfig.class})
public class UserDaoTest {

    private static final int NB_USERS = 10;

    @Autowired
    private AdministrationDao administrationDao;
    @Autowired
    private AuthorityDao authorityDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private UserAuthorityDao userAuthorityDao;

    private Administration administration;
    private Authority authority;

    @Before
    public void setUp() {
        Assert.assertTrue(NB_USERS % 2 == 0);
        administration = new Administration();
        administration.setLabelEn(RandomStringUtils.randomAlphanumeric(10));
        administration.setLabelFr(RandomStringUtils.randomAlphanumeric(10));
        administrationDao.save(administration);

        authority = new Authority();
        authority.setId(1l);
        authority.setAuthorityType(AuthorityType.DECISION);
        authority.setLabelEn(AuthorityConstants.INSPECTEUR.getLabel());
        authority.setLabelFr(AuthorityConstants.INSPECTEUR.getLabel());
        authority.setRole(AuthorityConstants.INSPECTEUR.name());
        authorityDao.save(authority);

        for (int i = 0; i < NB_USERS; i++) {
            User user = userDao.save(getUser());
            user.setUserAuthorityList(new ArrayList<UserAuthority>());
            user.getUserAuthorityList().add(new UserAuthority(user, authority));
            userAuthorityDao.saveList(user.getUserAuthorityList());
        }
    }

    @Ignore
    @Test
    public void test_findUsersByAdministrationAndAuthorities() {
        List<User> users = userDao.findUsersByAdministrationAndAuthorities(Arrays.asList(administration.getId()), new String[]{authority.getRole()});
        // all the users are disabled
        Assert.assertEquals(0, users.size());
        users = userDao.findActiveItems();
        int nb = users.size();
        Assert.assertTrue(nb == NB_USERS);
        for (int i = 0; i < NB_USERS; i++) {
            if (i % 2 == 0) {
                users.get(i).setEnabled(true);
                userDao.update(users.get(i));
            }
        }
        // some users are enabled
        users = userDao.findActiveItems();
        nb = users.size();
        Assert.assertTrue(nb == NB_USERS);
        users = userDao.findUsersByAdministrationAndAuthorities(Arrays.asList(administration.getId()), new String[]{authority.getRole()});
        Assert.assertEquals(NB_USERS / 2, users.size());
    }

    private User getUser() {

        User user = new User();

        user.setLogin(RandomStringUtils.randomAlphanumeric(10));
        user.setAdministration(administration);

        return user;
    }
}
