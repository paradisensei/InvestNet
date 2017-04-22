package org.singularnost.service.impl;

import org.singularnost.model.Token;
import org.singularnost.service.GitHubService;
import org.singularnost.util.gh_api.props.GitHubOAuth;
import org.singularnost.util.gh_api.user.GitHubUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 * @author Aidar Shaifutdinov.
 */
@Service
public class GitHubServiceImpl implements GitHubService {

    private final GitHubOAuth gitHubOAuth;
    private final RestTemplate restTemplate;

    @Autowired
    public GitHubServiceImpl(RestTemplate restTemplate, GitHubOAuth gitHubOAuth) {
        this.restTemplate = restTemplate;
        this.gitHubOAuth = gitHubOAuth;
    }

    @Override
    public Token getToken(String code) {
        MultiValueMap<String, Object> params = new LinkedMultiValueMap<>();
        params.add("client_id", gitHubOAuth.clientId());
        params.add("client_secret", gitHubOAuth.secret());
        params.add("code", code);
        String tokenUrl = gitHubOAuth.tokenUrl();
        return restTemplate.postForObject(tokenUrl, params, Token.class);
    }

    @Override
    public GitHubUser getUser(Token token) {
        String userUrl = gitHubOAuth.userUrl() + token.getAccessToken();
        return restTemplate.getForObject(userUrl, GitHubUser.class);
    }

}
