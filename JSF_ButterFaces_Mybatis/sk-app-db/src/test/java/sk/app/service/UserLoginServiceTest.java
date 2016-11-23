package sk.app.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import sk.app.dao.UserDetailsDAO;
import sk.app.domain.UserDetailDomain;

import static org.junit.Assert.assertEquals;

/**
 * Created by mlobb on 8/24/2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/spring-config.xml")
public class UserLoginServiceTest {

    private static final String USERNAME = "test";
    private UserLoginService userLoginService;
    private UserDetailDomain userDetailDomain;

    @Autowired
    private UserDetailsDAO userDetailsDAO;

    @Before
    public void init(){
        userLoginService = new UserLoginService();
        userDetailDomain = new UserDetailDomain();

        userLoginService.setDetailDAO(userDetailsDAO);
        userDetailDomain.setPassword(USERNAME);
        userDetailDomain.setUsername(USERNAME);
        userDetailDomain.setId(1);
    }

    @Test
    public void testLoadUserByUsername_negative(){
        UserDetails expectedUserDetails = userLoginService.loadUserByUsername(USERNAME);
        assertEquals("test", expectedUserDetails.getUsername().toString());
        assertEquals("test", expectedUserDetails.getPassword().toString());
        assertEquals(2 , expectedUserDetails.getAuthorities().size());
    }
}
