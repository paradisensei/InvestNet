package org.singularnost.repository;

import org.singularnost.model.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Aidar Shaifutdinov.
 */
@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {

    Token findBySocialId(Long socialId);

}
