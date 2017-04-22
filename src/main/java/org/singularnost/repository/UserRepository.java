package org.singularnost.repository;

import org.singularnost.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Aidar Shaifutdinov.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByClientToken(String clientToken);

}
