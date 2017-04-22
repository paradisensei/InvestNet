package org.singularnost.controller.api;

import org.singularnost.model.Token;
import org.singularnost.model.User;
import org.singularnost.service.GitHubService;
import org.singularnost.service.TokenService;
import org.singularnost.service.UserService;
import org.singularnost.util.gh_api.user.GitHubUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Aidar Shaifutdinov.
 */
@RestController
@RequestMapping("/api/oauth")
public class OAuthController {

    private final GitHubService gitHubService;
    private final UserService userService;
    private final TokenService tokenService;

    @Autowired
    public OAuthController(GitHubService gitHubService, UserService userService,
                           TokenService tokenService) {
        this.gitHubService = gitHubService;
        this.userService = userService;
        this.tokenService = tokenService;
    }

    @RequestMapping("/gitHub")
    public void gitHubCallback(@RequestParam("code") String code,
                               @RequestParam("state") String clientToken) {
        Token token = gitHubService.getToken(code);
        GitHubUser gitHubUser = gitHubService.getUser(token);
        Token oldToken = tokenService.get(gitHubUser.getId());
        if (oldToken == null) {
            User user = userService.add(gitHubUser, clientToken);
            tokenService.add(token, gitHubUser, user);
        } else {
            userService.update(oldToken.getUser(), clientToken);
            tokenService.update(oldToken, token);
        }
    }

}
