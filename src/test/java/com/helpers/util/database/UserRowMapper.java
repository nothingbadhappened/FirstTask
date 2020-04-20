package com.helpers.util.database;

import com.users.User;
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
    private static Logger log = LoggerFactory.getLogger(UserRowMapper.class);

    @Override
    public User mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        log.debug("Mapping DB entry (rownum:" +rowNum + " data to User object: \n" + resultSet.toString());
        user.setUserId(resultSet.getInt("user_id"));
        user.setUserEmail(resultSet.getString("user_email"));
        user.setUserPassword(resultSet.getString("user_password"));
        user.setRegistrationStatus(resultSet.getString("is_registered"));

        return user;
    }
}
