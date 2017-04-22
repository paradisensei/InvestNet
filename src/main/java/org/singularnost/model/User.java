package org.singularnost.model;

import org.singularnost.util.gh_api.user.GitHubUser;

import javax.persistence.*;

/**
 * @author Aidar Shaifutdinov.
 */
@Entity
@Table(name = "users")
@SequenceGenerator(name = "users_gen", sequenceName = "users_seq", allocationSize = 1)
public class User {

    public User() {
    }

    public User(GitHubUser gitHubUser) {
        name = gitHubUser.getName();
        email = gitHubUser.getEmail();
        company = gitHubUser.getCompany();
        location = gitHubUser.getLocation();
        bio = gitHubUser.getBio();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_gen")
    private Long id;

    @Column(name = "client_token")
    private String clientToken;

    private String name;

    private String email;

    private String company;

    private String location;

    private String bio;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClientToken() {
        return clientToken;
    }

    public void setClientToken(String clientToken) {
        this.clientToken = clientToken;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

}
