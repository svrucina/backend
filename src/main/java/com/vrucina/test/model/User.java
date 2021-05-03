package com.vrucina.test.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.vrucina.test.model.Authority;
import com.vrucina.test.model.Post;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.*;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    @Size(max = 100)
    private String username;

    @NotBlank
    @Size(max = 250)
    private String password;

    @NotBlank
    @Size(max = 250)
    private String first_name;

    @NotBlank
    @Size(max = 250)
    private String last_name;


    public User(@NotBlank @Size(max = 100) String username, @NotBlank @Size(max = 250) String password, @NotBlank @Size(max = 250) String first_name, @NotBlank @Size(max = 250) String last_name, Set<Authority> authorities) {
        this.username = username;
        this.password = password;
        this.first_name = first_name;
        this.last_name = last_name;
        this.authorities = authorities;
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_authority",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "authority_id"))
    private Set<Authority> authorities = new HashSet<>();

    @OneToMany(
            mappedBy = "user",
            fetch = FetchType.EAGER
    )
    @JsonManagedReference
    private List<Post> posts = new ArrayList<>();

    public User() {
    }

    public Set<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<Authority> authorities) {
        this.authorities = authorities;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                '}';
    }
}
