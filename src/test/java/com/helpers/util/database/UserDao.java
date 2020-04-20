package com.helpers.util.database;

import com.users.User;
import java.util.List;

public interface UserDao {
    // == init ==
    public void setDataSource();

    // == methods ==
    public void createUser(String userEmail, String userPassword, int userRegistrationStatus);
    public List<User> getAllUsers();
    public User getRegisteredUser();
    public User getNotRegisteredUser();
}
