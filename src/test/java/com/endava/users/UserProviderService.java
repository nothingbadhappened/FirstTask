package com.endava.users;

import com.endava.helpers.util.database.UserDaoImpl;
import com.endava.steps.context.ContextKeys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserProviderService {

    private final UserDaoImpl userDaoImpl;
    private static final Logger log = LoggerFactory.getLogger(UserProviderService.class);

    @Autowired
    public UserProviderService(UserDaoImpl  userDaoImpl){
        this.userDaoImpl = userDaoImpl;
    }

    // User object dispenser - returns a Registered or Not Registered user
    public User getUser(String userRegistrationStatus) {
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
            log.error("Invalid user parameter, cannot create user with status [{}]", userRegistrationStatus);
            throw new IllegalArgumentException("Invalid user parameter, cannot create user with status " + userRegistrationStatus);
        }

        log.info("User object is ready: " + user.getRegistrationStatus());
        return user;
    }

    public User getUser(ContextKeys key) {
        if (key == ContextKeys.USER_REGISTERED) {
            return userDaoImpl.getRegisteredUser();
        } else if (key == ContextKeys.USER_NOT_REGISTERED) {
            return userDaoImpl.getNotRegisteredUser();
        } else {
            log.error("Invalid user parameter, cannot create user with status [{}]", key);
            throw new IllegalArgumentException("Invalid user parameter, cannot create user with status " + key);
        }
    }
}