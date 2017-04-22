package org.singularnost.service.impl;

import org.singularnost.model.User;
import org.singularnost.repository.UserRepository;
import org.singularnost.service.UserService;
import org.singularnost.util.gh_api.user.GitHubUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Aidar Shaifutdinov.
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public User get(String clientToken) {
        return userRepository.findByClientToken(clientToken);
    }

    @Override
    public User add(GitHubUser githubUser, String clientToken) {
        User user = new User(githubUser);
        user.setClientToken(clientToken);
        return userRepository.save(user);
    }

    @Override
    public void update(User user, String clientToken) {
        user.setClientToken(clientToken);
        userRepository.save(user);
    }

}
