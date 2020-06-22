package com.endava.helpers.util.database;

import com.endava.users.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    // == Fields ==
    private static Logger log = LoggerFactory.getLogger(UserDaoImpl.class);

    @Autowired
    User user;

    @Autowired
    private UserRowMapper userRowMapper;

    private JdbcTemplate jdbcTemplate;

    // == Methods ==
    // == Init ==
    @Autowired
    public void setDataSource(DataSource dataSource) {
        log.debug("Setting Data Source");
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    // == Create new user entry in Users table
    @Override
    public void createUser(String userEmail, String userPassword, int userRegistrationStatus) {
        String SQL = "insert into users (userEmail, userPassword, userRegistrationStatus) values (?, ?, ?)";
        log.debug("Inserting new user data. SQL:\n" + SQL +
                "\nValues: " + userEmail + userPassword + userRegistrationStatus);
        jdbcTemplate.update(SQL, userEmail, userPassword, userRegistrationStatus);
    }

    // == Get the list of results
    @Override
    public List<User> getAllUsers() {
        String SQL = "select * from users";
        log.debug("Fetching all users data. SQL:\n" + SQL);
        return jdbcTemplate.query(SQL, userRowMapper);
    }

    @Override
    public boolean deleteUser(User user) {
        return false;
    }

    @Override
    public boolean updateUser(User person) {
        return false;
    }

    @Override
    public boolean createUser(User person) {
        return false;
    }

    // == Get specific user ==
    @Override
    public User getRegisteredUser() {
        String SQL = "select * " +
                "from users " +
                "where is_registered = 1 " +
                "limit 1 ";
        log.debug("Getting registered user. SQL: " + SQL);

        return (User) jdbcTemplate.queryForObject(SQL, userRowMapper);
    }

    @Override
    public User getNotRegisteredUser() {
        String SQL = "select * " +
                "from users " +
                "where  isRegistered = 0 " +
                "limit 1 ";
        log.debug("Getting not registered user. SQL: " + SQL);
        return (User) jdbcTemplate.queryForObject(SQL, userRowMapper);
    }

    @Override
    public User getUserById(Long id) {
        return null;
    }

}
