package org.guce.siat.common.dao;

import java.util.List;
import org.guce.siat.common.dao.config.DataSourceConfig;
import org.guce.siat.common.model.User;
import org.guce.siat.common.utils.enums.PositionType;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author ht
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DataSourceConfig.class})
public class UserDaoTest {

    @Autowired
    private UserDao userDao;

    /**
     * Test of loadUserByUsername method, of class UserDao.
     */
    @Ignore
    @Test
    public void testLoadUserByUsername() {
    }

    /**
     * Test of createUser method, of class UserDao.
     */
    @Ignore
    @Test
    public void testCreateUser() {
    }

    /**
     * Test of updateUser method, of class UserDao.
     */
    @Ignore
    @Test
    public void testUpdateUser() {
    }

    /**
     * Test of getUserByLogin method, of class UserDao.
     */
    @Ignore
    @Test
    public void testGetUserByLogin() {
    }

    /**
     * Test of findByMail method, of class UserDao.
     */
    @Ignore
    @Test
    public void testFindByMail() {
    }

    /**
     * Test of updateFailAttempts method, of class UserDao.
     */
    @Ignore
    @Test
    public void testUpdateFailAttempts() {
    }

    /**
     * Test of findUsersByAdministrationsAndPositions method, of class UserDao.
     */
    @Ignore
    @Test
    public void testFindUsersByAdministrationsAndPositions_List_PositionTypeArr() {
    }

    /**
     * Test of findUsersByAuthorities method, of class UserDao.
     */
    @Ignore
    @Test
    public void testFindUsersByAuthorities() {
    }

    /**
     * Test of findUsersByAdministrationsIds method, of class UserDao.
     */
    @Ignore
    @Test
    public void testFindUsersByAdministrationsIds() {
    }

    /**
     * Test of findUsersByAdministrationsIdsAndPosition method, of class
     * UserDao.
     */
    @Ignore
    @Test
    public void testFindUsersByAdministrationsIdsAndPosition() {
        System.out.println("org.guce.siat.common.dao.UserDaoTest.testFindUsersByAdministrationsIdsAndPosition()");
        List<User> users = userDao.findUsersByAdministrationsIdsAndPosition(PositionType.CHEF_BUREAU, 106l);
        Assert.assertTrue(users.size() == 1);
    }

    /**
     * Test of findUsersByAdministrationsAndPositions method, of class UserDao.
     */
    @Ignore
    @Test
    public void testFindUsersByAdministrationsAndPositions_Administration_PositionTypeArr() {
    }

    /**
     * Test of removeAllGrantedAuthorities method, of class UserDao.
     */
    @Ignore
    @Test
    public void testRemoveAllGrantedAuthorities() {
    }

    /**
     * Test of findUsersByAdministrationAndAuthorities method, of class UserDao.
     */
    @Ignore
    @Test
    public void testFindUsersByAdministrationAndAuthorities() {
    }

    /**
     * Test of findByStepAndFileTypeAndAdministration method, of class UserDao.
     */
    @Ignore
    @Test
    public void testFindByStepAndFileTypeAndAdministration() {
    }

    /**
     * Test of findSuperUserByFileType method, of class UserDao.
     */
    @Ignore
    @Test
    public void testFindSuperUserByFileType() {
    }

}
