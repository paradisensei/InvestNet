package org.singularnost.service.impl;

import org.singularnost.model.Token;
import org.singularnost.model.User;
import org.singularnost.repository.TokenRepository;
import org.singularnost.service.TokenService;
import org.singularnost.util.gh_api.user.GitHubUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @author Aidar Shaifutdinov.
 */
@Service
@Transactional
public class TokenServiceImpl implements TokenService {

    private final TokenRepository tokenRepository;

    @Autowired
    public TokenServiceImpl(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Token get(Long socialId) {
        return tokenRepository.findBySocialId(socialId);
    }

    @Override
    public void add(Token token, GitHubUser githubUser, User user) {
        token.setSocialId(githubUser.getId());
        token.setDate(new Date());
        token.setUser(user);
        tokenRepository.save(token);
    }

    @Override
    public void update(Token oldToken, Token newToken) {
        oldToken.setAccessToken(newToken.getAccessToken());
        oldToken.setDate(new Date());
        tokenRepository.save(oldToken);
    }

}
