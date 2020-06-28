package com.endava.users;

import com.endava.helpers.util.database.UserDaoImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserFactory {

    private static UserDaoImpl userDaoImpl;
    private static final Logger log = LoggerFactory.getLogger(UserFactory.class);

    @Autowired
    public UserFactory(UserDaoImpl  userDaoImpl){
        UserFactory.userDaoImpl = userDaoImpl;
    }

    // User object dispenser - returns a Registered or Not Registered user
    public static User getUser(String userRegistrationStatus) {
        log.info("Received get user request for user status: " + userRegistrationStatus);

        User user = null;

        if (userRegistrationStatus.equals("1") ||
                userRegistrationStatus.toLowerCase().equals("registered") ||
                userRegistrationStatus.toLowerCase().equals("is registered")) {
            log.info("Generating user with status \"registered\"");
            user = userDaoImpl.getRegisteredUser();
        } else if (userRegistrationStatus.equals("0") ||
                userRegistrationStatus.toLowerCase().equals("not registered") ||
                userRegistrationStatus.toLowerCase().equals("is not registered")) {
            log.info("Generating user with status \"not registered\"");
            user = userDaoImpl.getNotRegisteredUser();
        } else {
            log.error("Invalid user parameter, cannot create user");
            return null;
        }

        log.info("User object is ready: " + user.getRegistrationStatus());
        return user;
    }
}
