package com.t4pj.mvp_practices.RecyclerView02;

/**
 * Created by Akechi on 6/22/2016.
 */
public class User {
    String name, description;

    public User(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
