package com.helpers.util;

import com.helpers.util.database.UserDaoImpl;
import com.users.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserFactory {

    @Autowired
    private UserDaoImpl userDaoImpl;
    private static final Logger log = LoggerFactory.getLogger(UserFactory.class);

    // User object dispenser - returns a Registered or Not Registered user
    public User getUser(String userRegistrationStatus){
        log.info("Received get user request for user status: " + userRegistrationStatus);

        User user = null;

        if (userRegistrationStatus.equals("1") ||
            userRegistrationStatus.equals("registered") ||
            userRegistrationStatus.equals("is registered")){
                log.info("Generating registered user...");
                user = userDaoImpl.getRegisteredUser();
        } else if (userRegistrationStatus.equals("0") ||
                userRegistrationStatus.equals("not registered") ||
                userRegistrationStatus.equals("is not registered")) {
            log.info("Generating not registered user...");
            user = userDaoImpl.getNotRegisteredUser();
        } else {
            log.error("Invalid user parameter, cannot create user");
            return null;
        }

        log.info("User object is ready: " + user.getRegistrationStatus());
        return user;
    }
}
