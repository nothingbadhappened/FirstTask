package com.endava.helpers.util.database;

import com.endava.users.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class UserDaoImpl implements UserDao {

    private static Logger log = LoggerFactory.getLogger(UserDaoImpl.class);
    private UserRowMapper userRowMapper = new UserRowMapper();
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        log.debug("Setting Data Source");
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

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
                "where  is_registered = 0 " +
                "limit 1 ";
        log.debug("Getting not registered user. SQL: " + SQL);
        return (User) jdbcTemplate.queryForObject(SQL, userRowMapper);
    }

}
