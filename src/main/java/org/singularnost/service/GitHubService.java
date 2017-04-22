package org.singularnost.service;


import org.singularnost.model.Token;
import org.singularnost.util.gh_api.user.GitHubUser;

/**
 * @author Aidar Shaifutdinov.
 */
public interface GitHubService {

    Token getToken(String code);

    GitHubUser getUser(Token token);

}
