package com.helpers.util;

import com.helpers.util.database.UserJDBCTemplate;
import com.users.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserFactory {

    @Autowired
    private UserJDBCTemplate userJDBCTemplate;

    private static final Logger log = LoggerFactory.getLogger(UserFactory.class);


    // User object dispenser - returns a Registered or Not Registered user
    public User getUser(String userRegistrationStatus){
        log.info("Received get user request for user status: " + userRegistrationStatus);
        User user = null;
        switch (userRegistrationStatus){
            case "registered": {
                log.info("Generating registered user.");
                user = userJDBCTemplate.getRegisteredUser();
                break;
            }
            case "not registered": {
                log.info("Generating not registered user.");
                user = userJDBCTemplate.getNotRegisteredUser();
                break;
            }
        }
        assert user != null;
        log.info("User object is ready: " + user.getRegistrationStatus());
        return user;
    }
}
