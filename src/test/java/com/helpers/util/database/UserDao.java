package com.helpers.util.database;

import com.users.User;

import javax.sql.DataSource;
import java.util.List;

public interface UserDao {
    // == methods ==
    public void setDataSource(DataSource dataSource);
    public void createUser(String userEmail, String userPassword, int userRegistrationStatus);

    public User getRegisteredUser();
    public User getNotRegisteredUser();

    User getUserById(Long id);
    List<User> getAllUsers();

    boolean deleteUser(User user);
    boolean updateUser(User person);
    boolean createUser(User person);
}
