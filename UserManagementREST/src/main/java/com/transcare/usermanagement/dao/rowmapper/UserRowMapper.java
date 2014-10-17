package com.transcare.usermanagement.dao.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.transcare.usermanagement.dto.User;

public class UserRowMapper implements RowMapper<User> {

	public User mapRow(ResultSet rs, int numRow) throws SQLException {
		System.out.println(numRow);
		if (numRow > 0 && rs.next()) {

			System.out.println("Users found!");

			User user = new User();
			user.setUserid(rs.getInt(1));
			user.setUsername(rs.getString(2));
			user.setAge(rs.getInt(4));
			user.setContactno(rs.getString(5));
			user.setEmailaddress(rs.getString(6));

			return user;
		}
		return null;
	}

}
