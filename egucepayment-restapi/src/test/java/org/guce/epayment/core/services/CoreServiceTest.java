package org.guce.epayment.core.services;

import org.guce.epayment.core.entities.Role;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author tadzotsa
 */
@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = {DataBaseTestConfig.class, MailConfig.class})
@ContextConfiguration("classpath:TestConfig.xml")
public class CoreServiceTest {

    @Autowired
    private CoreService coreService;

    private Role role;
    private Integer roleId = 0;

    @Before
    public void init() throws Exception {
        role = new Role("ADMIN", "Administrateur");
        coreService.save(role, Role.class);
        roleId = role.getId();
    }

    @Ignore
    @Test
    public void testFindById() throws Exception {
        Assert.assertNotNull(roleId);
        Assert.assertTrue(coreService.findById(roleId, Role.class).isPresent());
    }

    @Ignore
    @Test
    public void testFindByUniqueKey() throws Exception {
        Assert.assertTrue(coreService.findByUniqueKey("roleName", "ADMIN", Role.class).isPresent());
        Assert.assertFalse(coreService.findByUniqueKey("roleName", "ADMIN2", Role.class).isPresent());
    }

    @Ignore
    @Test
    public void testFindAll() throws Exception {
        Assert.assertFalse(coreService.findAll(Role.class).isEmpty());
    }

}
