package com.waterhub.waterhub.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Role implements Serializable {

    private Long id;
    private String name;
    private List<User> users = new ArrayList<>();

    public Role() {

    }

    public Role(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public User getUser(int index) {
        return users.get(index);
    }

    public void setUser(User user) {
        users.add(user);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Role) {
            Role r = (Role) obj;

            return id.equals(r.getId());
        } else
            return false;
    }

    @Override
    public String toString() {
        return name;
    }
}
