package sk.app.db;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import sk.app.dao.UserDetailsDAO;
import sk.app.domain.UserDetailDomain;

/**
 * Created by mlobb on 8/25/2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/spring-config.xml")
public class DaoTest {

    private Logger logger = LoggerFactory.getLogger(DaoTest.class);

    @Autowired
    private UserDetailsDAO userDetailsDAO;

    @Test
    public void testGetRoles() {
        UserDetailDomain userDetail = userDetailsDAO.getUserDetail("test");
        List<String> roles = userDetailsDAO.getRoles(userDetail.getId());
        assertEquals("[ADMIN, MODERATOR]", roles.toString());
        logger.info("get Roles is satisfied : " + roles.toString());
    }
}
