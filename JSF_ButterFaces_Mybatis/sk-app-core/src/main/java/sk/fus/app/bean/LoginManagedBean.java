package sk.fus.app.bean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name = "loginBean")
@SessionScoped
public class LoginManagedBean {
    private static final Logger LOG = LoggerFactory.getLogger(LoginManagedBean.class);

    private String username;
    private String password;

    @ManagedProperty(value = "#{authenticationManager}")
    private AuthenticationManager authManager;

    public String login() {
        LOG.info("Starting login from LoginManagedBean");
        try {
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username, password);
            Authentication authenticate = authManager.authenticate(usernamePasswordAuthenticationToken);
            SecurityContextHolder.getContext().setAuthentication(authenticate);
            return "correct";
        } catch (final Exception e) {
            LOG.error("Error log in " + e);
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_FATAL, 
                "Invalid login", "Bad Credential. Try again"));
        }

        return null;
    }

    public String logout() {
        SecurityContextHolder.clearContext();
        return "logout";
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public AuthenticationManager getAuthManager() {
        return authManager;
    }

    public void setAuthManager(AuthenticationManager authManager) {
        this.authManager = authManager;
    }
}
