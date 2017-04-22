package org.singularnost.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Aidar Shaifutdinov.
 */
@Entity
@Table(name = "token")
@SequenceGenerator(name = "token_gen", sequenceName = "token_seq", allocationSize = 1)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "token_gen")
    private Long id;

    @Column(name = "social_id")
    private Long socialId;

    @Column(name = "access_token")
    @JsonProperty("access_token")
    private String accessToken;

    @Column(name = "expires_in")
    @JsonProperty("expires_in")
    private int expiresIn;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "birth_date")
    private Date date;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSocialId() {
        return socialId;
    }

    public void setSocialId(Long socialId) {
        this.socialId = socialId;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public int getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(int expiresIn) {
        this.expiresIn = expiresIn;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
