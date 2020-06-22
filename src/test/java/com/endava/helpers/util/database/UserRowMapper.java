package com.endava.helpers.util.database;

import com.endava.users.User;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class UserRowMapper implements RowMapper {

    @Autowired
    User user;

    private ResultSet resultSet;

    private static Logger log = LoggerFactory.getLogger(UserRowMapper.class);

    @Override
    public User mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        this.resultSet = resultSet;
        log.debug("Mapping DB entry (rownum:" + rowNum + ")" + " data to User object: \n" + toString(resultSet));
        user.setUserId(resultSet.getInt("user_id"));
        user.setUserEmail(resultSet.getString("user_email"));
        user.setUserPassword(resultSet.getString("user_password"));
        user.setRegistrationStatus(resultSet.getString("is_registered"));

        return user;
    }

    @NotNull
    private String toString(ResultSet resultSet) throws SQLException {
        return "user_id: " + resultSet.getString(1)
                + " | user_email: " + resultSet.getString(2)
                + " | user_password: " + resultSet.getString(3)
                + " | is_registered: " + resultSet.getString(4);
    }
}
