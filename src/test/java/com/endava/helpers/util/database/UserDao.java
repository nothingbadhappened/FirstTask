package com.endava.helpers.util.database;


import com.endava.users.User;

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

    boolean updateUser(User user);

    boolean createUser(User user);
}

/**
 * CURRENT DB HOST IS GOOGLE CLOUD
 * TO RESTORE THE MYSQL TEST DB PLEASE USE THE QUERIES BELOW
 * <p>
 * --------------------------------------------------------------
 * <p>
 * CREATE TABLE users (
 * user_id INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
 * user_email VARCHAR(30) NOT NULL,
 * user_password VARCHAR(30) NOT NULL,
 * is_registered INT (1)
 * )
 * <p>
 * <p>
 * INSERT INTO users (user_email, user_password, is_registered)
 * VALUES ('elchupakabra@mailinator.com', 'Test1234!', 1),
 * ('invalid@email.zyx', '125415f12', 0),
 * ('ifgqenhgi@ign.asf', '12fgadsg@!', 0)
 * <p>
 * select * from users
 * <p>
 * --------------------------------------------------------------
 */
