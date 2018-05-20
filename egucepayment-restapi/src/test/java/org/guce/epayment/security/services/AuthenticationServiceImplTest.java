package org.guce.epayment.security.services;

import org.guce.epayment.core.entities.User;
import org.guce.epayment.core.services.CoreService;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author tadzotsa
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:TestConfig.xml")
public class AuthenticationServiceImplTest {

    private static final String TEST_PASSWORD = "p@55w0rd";

    @Autowired
    private AuthenticationService authenticationService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private CoreService coreService;

    @Ignore
    @Test//(expected = NoSuchMethodException.class)
    public void testSaveUser() throws Exception {

        Assert.assertNotNull(authenticationService);
        Assert.assertNotNull(coreService);

        final User user = new User();

        user.setEmail("hycient@gmail.com");
        user.setFirstName("Hyacinthe");
        user.setLastName("TADZOTSA");
        user.setLogin("hycient2018");
        user.setActive(true);
        user.setTitle("M.");

        authenticationService.saveUser(user, TEST_PASSWORD);
        Assert.assertTrue(authenticationService.authenticate("hycient2018", TEST_PASSWORD).isPresent());
        Assert.assertFalse(authenticationService.authenticate("hycient2018", "something").isPresent());
        Assert.assertTrue(coreService.findByUniqueKey("login", "hycient2018", User.class).isPresent());
        Assert.assertFalse(coreService.findByUniqueKey("email", "hycient@gmail.com", User.class).isPresent());
    }

    @Ignore
    @Test
    public void testHash() {
        System.out.println(passwordEncoder.encode(TEST_PASSWORD));
        System.out.println(passwordEncoder.encode("admin"));
        Assert.assertTrue(authenticationService.authenticate("hycient2018", TEST_PASSWORD).isPresent());
    }

}
