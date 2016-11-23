package sk.app.dao;

import java.util.List;

import sk.app.domain.UserDetailDomain;

/**
 * Created by mlobb on 8/25/2015.
 */
public interface UserDetailsDAO {

    public List<String> getRoles(final int id);

    public UserDetailDomain getUserDetail(final String username);

}
