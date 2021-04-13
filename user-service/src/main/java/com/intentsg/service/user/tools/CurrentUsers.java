package com.intentsg.service.user.tools;

import com.intentsg.service.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class CurrentUsers {

    private final PasswordEncoder encoder;

    private CopyOnWriteArrayList<User> users = new CopyOnWriteArrayList<>();
    private Random random = new Random();

    @Autowired
    public CurrentUsers(PasswordEncoder encoder) {
        this.encoder = encoder;
    }

    public User addUser(User user) {
        user.setSecretKey(encoder.encode(user.getPassword()+random.nextLong()));
        users.add(user);
        return user;
    }

    public boolean removeUser(User user) {
        return users.remove(user);
    }

    public boolean checkExists(User user) {
        if (users.indexOf(user)== (-1)) return false;
        return users.get(users.indexOf(user)).getSecretKey().equals(user.getSecretKey());
    }


}
