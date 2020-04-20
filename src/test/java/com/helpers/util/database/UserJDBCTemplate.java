package com.helpers.util.database;

import com.helpers.configuration.MySQLDataSource;
import com.users.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.List;

@Component
public class UserJDBCTemplate implements UserDao{

    // == Fields ==
    @Autowired
    private MySQLDataSource dataSource;
    @Autowired
    User user;
    @Autowired
    private UserRowMapper userRowMapper;
    private JdbcTemplate jdbcTemplateObject;
    private static Logger log = LoggerFactory.getLogger(UserJDBCTemplate.class);

    // == Methods ==

    // == Init ==
    @Override
    public void setDataSource() {
        log.debug("Establishing DB connection: " + dataSource.toString());
        this.jdbcTemplateObject = new JdbcTemplate((DataSource) dataSource);
        log.debug("Database connected.");
    }

    // == Create new user entry in Users table
    @Override
    public void createUser(String userEmail, String userPassword, int userRegistrationStatus) {
        String SQL = "insert into users (userEmail, userPassword, userRegistrationStatus) values (?, ?, ?)";
        log.debug("Inserting new user data. SQL:\n" + SQL +
                  "\nValues: " + userEmail + userPassword + userRegistrationStatus);
        jdbcTemplateObject.update(SQL, userEmail, userPassword, userRegistrationStatus);
    }

    // == Get the list of results
    @Override
    public List<User> getAllUsers() {
        String SQL = "select * from users";
        log.debug("Fetching all users data. SQL:\n" + SQL);
        return jdbcTemplateObject.query(SQL, userRowMapper);
    }

    // == Get specific user ==
    @Override
    public User getRegisteredUser() {
        String SQL = "select * " +
                     "from users " +
                     "where is_registered = 1 " +
                     "limit 1 ";
        log.debug("Getting registered user. SQL: " + SQL);

        return (User) jdbcTemplateObject.queryForObject(SQL, userRowMapper);
    }

    @Override
    public User getNotRegisteredUser() {
        String SQL = "select * " +
                "from users " +
                "where  isRegistered = 0 " +
                "limit 1 ";
        log.debug("Getting not registered user. SQL: " + SQL);
        return (User)jdbcTemplateObject.queryForObject(SQL, userRowMapper);
    }

}
