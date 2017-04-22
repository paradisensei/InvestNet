package org.singularnost.service;


import org.singularnost.model.Token;
import org.singularnost.model.User;
import org.singularnost.util.gh_api.user.GitHubUser;

/**
 * @author Aidar Shaifutdinov.
 */
public interface TokenService {

    Token get(Long socialId);

    void add(Token token, GitHubUser githubUser, User user);

    void update(Token oldToken, Token newToken);

}
