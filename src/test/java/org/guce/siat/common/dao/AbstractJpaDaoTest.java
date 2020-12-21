package org.guce.siat.common.dao;

import java.util.List;
import org.apache.commons.lang3.RandomStringUtils;
import org.guce.siat.common.dao.config.H2DataSourceConfig;
import org.guce.siat.common.model.User;
import org.junit.Assert;
import org.junit.Before;
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
@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {H2DataSourceConfig.class})
public class AbstractJpaDaoTest {

    private static final int NB_USERS = 10;

    @Autowired
    private UserDao userDao;

    @Before
    public void setUp() {
        for (int i = 0; i < NB_USERS; i++) {
            userDao.save(getUser());
        }
    }

    @Test
    public void test_findAll() {
        Assert.assertEquals(NB_USERS, userDao.findAll().size());
        userDao.deleteList(userDao.findAll());
        Assert.assertEquals(0, userDao.findAll().size());
    }

    @Test
    public void test_findActiveItems() {
        List<User> allUsers = userDao.findAll();
        for (int i = 0; i < NB_USERS; i++) {
            if (i % 2 == 0) {
                allUsers.get(i).setDeleted(true);
                userDao.update(allUsers.get(i));
            }
        }
        Assert.assertNotEquals(NB_USERS / 4, userDao.findActiveItems().size());
        Assert.assertEquals(NB_USERS / 2, userDao.findActiveItems().size());
    }

    private User getUser() {

        User user = new User();

        user.setLogin(RandomStringUtils.randomAlphanumeric(10));

        return user;
    }

}
