package com.intentsg.service.user.tools;

import com.intentsg.service.user.entity.User;

public class CurrentUser {
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
