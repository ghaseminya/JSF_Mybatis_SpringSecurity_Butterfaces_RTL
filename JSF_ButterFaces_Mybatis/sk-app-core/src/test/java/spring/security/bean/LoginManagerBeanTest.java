package spring.security.bean;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import sk.fus.app.bean.LoginManagedBean;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LoginManagerBeanTest {

    private LoginManagedBean loginManagedBean;
    private UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken;

    @Mock
    private AuthenticationManager authenticationManager;
    @Mock
    private Authentication authentication;

    @Before
    public void init() {
        loginManagedBean = new LoginManagedBean();
        authentication = new TestingAuthenticationToken(new Object(), new Object());
        usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken("tester", "tester");
        loginManagedBean.setUsername("tester");
        loginManagedBean.setPassword("tester");
        loginManagedBean.setAuthManager(authenticationManager);
        authentication.setAuthenticated(true);
    }

    @After
    public void destroy() {
    }

    @Test
    public void test_Login() throws Exception{
        when(authenticationManager.authenticate(usernamePasswordAuthenticationToken)).thenReturn(authentication);
        String response = loginManagedBean.login();
        assertEquals("correct" , response.toString());
    }

    @Test
    public void test_Logout() {
        String respone = loginManagedBean.logout();
        assertEquals("logout", respone.toString());
    }
}
