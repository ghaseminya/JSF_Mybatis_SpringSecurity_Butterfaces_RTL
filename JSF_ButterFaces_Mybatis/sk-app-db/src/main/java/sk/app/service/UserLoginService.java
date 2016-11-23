package sk.app.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import sk.app.dao.UserDetailsDAO;
import sk.app.domain.UserDetailDomain;

/**
 * Created by mlobb on 8/25/2015.
 */
public class UserLoginService implements UserDetailsService {

    @Autowired
    private UserDetailsDAO detailDAO;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetailDomain userDetail = detailDAO.getUserDetail(username);

        if (userDetail == null) {
            throw new UsernameNotFoundException("User " + username + " not found");
        }

        boolean enabled = true;
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;
        Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        List<String> roles = detailDAO.getRoles(userDetail.getId());
        for (String role : roles) {
            authorities.add(new SimpleGrantedAuthority(role));
        }

        return new User(username, userDetail.getPassword(), enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    }

    public void setDetailDAO(UserDetailsDAO detailDAO) {
        this.detailDAO = detailDAO;
    }
}
