package org.guce.siat.common.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import junit.framework.TestCase;
import org.guce.siat.common.dao.config.H2DataSourceConfig;
import org.guce.siat.common.model.Administration;
import org.guce.siat.common.model.Authority;
import org.guce.siat.common.model.User;
import org.guce.siat.common.model.UserAuthority;
import org.guce.siat.common.utils.enums.AuthorityConstants;
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
//@Transactional
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = {H2DataSourceConfig.class})
public class UserDaoTest extends TestCase {

    private static final int NB_USERS = 10;

    @Autowired
    private TestDataBuilder builder;

    private Administration administration;
    private Authority authority;

    @Before
    @Override
    public void setUp() {
        Assert.assertTrue(NB_USERS % 2 == 0);
        administration = builder.getAdministration();

        authority = builder.getAuthority(AuthorityConstants.INSPECTEUR);

        for (int i = 0; i < NB_USERS; i++) {
            User user = builder.getUser();
            user.setAdministration(administration);
            builder.getUserDao().update(user);

            user.setUserAuthorityList(new ArrayList<UserAuthority>());
            user.getUserAuthorityList().add(new UserAuthority(user, authority));
            builder.getUserAuthorityDao().saveList(user.getUserAuthorityList());
        }
    }

    @Ignore
    @Test
    public void test_findUsersByAdministrationAndAuthorities() {
        List<User> users = builder.getUserDao().findUsersByAdministrationAndAuthorities(Arrays.asList(administration.getId()), new String[]{authority.getRole()});
        // all the users are enabled
        Assert.assertEquals(NB_USERS, users.size());
        users = builder.getUserDao().findActiveItems();
        int nb = users.size();
        Assert.assertTrue(nb == NB_USERS);
        for (int i = 0; i < NB_USERS; i++) {
            if (i % 2 == 0) {
                users.get(i).setEnabled(false);
                builder.getUserDao().update(users.get(i));
            }
        }
        // some users are disabled
        users = builder.getUserDao().findActiveItems();
        nb = users.size();
        Assert.assertTrue(nb == NB_USERS);
        users = builder.getUserDao().findUsersByAdministrationAndAuthorities(Arrays.asList(administration.getId()), new String[]{authority.getRole()});
        Assert.assertEquals(NB_USERS / 2, users.size());
    }

    @Ignore
    @Test
    public void test_findUsersByAuthorities() {
        List<User> users = builder.getUserDao().findAll();
        Assert.assertFalse(users.isEmpty());
        users = builder.getUserDao().findUsersByAuthorities(AuthorityConstants.INSPECTEUR.getCode());
        Assert.assertFalse(users.isEmpty());
        users = builder.getUserDao().findUsersByAuthorities(AuthorityConstants.CONTROLEUR.getCode());
        Assert.assertTrue(users.isEmpty());
        users = builder.getUserDao().findAll();
        for (int i = 0; i < NB_USERS; i++) {
            if (i % 2 == 0) {
                users.get(i).setEnabled(false);
                builder.getUserDao().update(users.get(i));
            }
        }
        users = builder.getUserDao().findUsersByAuthorities(AuthorityConstants.INSPECTEUR.getCode());
        Assert.assertFalse(users.isEmpty());
        Assert.assertEquals(NB_USERS / 2, users.size());
    }

}
