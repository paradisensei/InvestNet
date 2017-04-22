package org.singularnost.service;


import org.singularnost.model.User;
import org.singularnost.util.gh_api.user.GitHubUser;

/**
 * @author Aidar Shaifutdinov.
 */
public interface UserService {

    User get(String clientToken);

    User add(GitHubUser githubUser, String clientToken);

    void update(User user, String clientToken);

}
