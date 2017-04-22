package org.singularnost.util.gh_api.props;

import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * @author Aidar Shaifutdinov.
 */
@Component
@PropertySource("classpath:properties/oauth.properties")
public class GitHubOAuth implements EnvironmentAware {

    private Environment env;

    @Override
    public void setEnvironment(Environment env) {
        this.env = env;
    }

    public String clientId() {
        return env.getProperty("gitHub.clientId");
    }

    public String secret() {
        return env.getProperty("gitHub.secret");
    }

    public String tokenUrl() {
        return env.getProperty("gitHub.tokenUrl");
    }

    public String userUrl() {
        return env.getProperty("gitHub.userUrl");
    }

}
