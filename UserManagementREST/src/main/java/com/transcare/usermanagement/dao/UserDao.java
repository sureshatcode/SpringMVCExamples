package com.transcare.usermanagement.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.mysql.jdbc.Statement;
import com.transcare.usermanagement.dto.User;

@Repository
public class UserDao implements IUserDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Value("${UserDao.performLogin}")
	private String performLogin;

	@Value("${UserDao.addNewUser}")
	private String addNewUser;

	@Value("${UserDao.getAllUsers}")
	private String getAllUsers;

	@Value("${UserDao.updateUser}")
	private String updateUser;

	public String getAddNewUser() {
		return addNewUser;
	}

	public void setAddNewUser(String addNewUser) {
		this.addNewUser = addNewUser;
	}

	public String getGetAllUsers() {
		return getAllUsers;
	}

	public void setGetAllUsers(String getAllUsers) {
		this.getAllUsers = getAllUsers;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public String getPerformLogin() {
		return performLogin;
	}

	public void setPerformLogin(String performLogin) {
		this.performLogin = performLogin;
	}

	public User performLogin(String userName, String password) {
		System.out.println("Login Query: " + performLogin + " with params: "
				+ userName + "," + password);
		try {
			User user = jdbcTemplate.query(performLogin,
					new ResultSetExtractor<User>() {

						public User extractData(ResultSet rs)
								throws SQLException, DataAccessException {
							if (rs.next()) {
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
					}, new Object[] { userName, password });
			if (user != null) {
				return user;
			}
		} catch (Exception e) {
			System.err.println(e.getLocalizedMessage());
		}
		return null;
	}

	public boolean update(User user) {

		int res = jdbcTemplate.update(
				updateUser,
				new Object[] { user.getEmailaddress(), user.getAge(),
						user.getContactno(), user.getUserid() });

		if (res > 0) {
			System.out.println("Value is updated!");
			return true;
		}
		return false;
	}

	public List<User> getAllUsers() {

		List<User> users = jdbcTemplate.query(getAllUsers,
				new ResultSetExtractor<List<User>>() {
					public List<User> extractData(ResultSet rs)
							throws SQLException, DataAccessException {

						if (rs.next()) {

							List<User> users = new ArrayList<User>();

							do {
								User user = new User();
								user.setUserid(rs.getInt(1));
								user.setUsername(rs.getString(2));
								user.setAge(rs.getInt(4));
								user.setContactno(rs.getString(5));
								user.setEmailaddress(rs.getString(6));

								users.add(user);
							} while (rs.next());
							return users;

						}

						return null;
					}
				});

		if (users != null) {
			return users;
		}

		return null;
	}

	public int deleteUser(int userId) {

		return 0;
	}

	public User addNewUser(final User user) {

		if (user != null) {
			int affectedRows = 0;
			KeyHolder keyHoler = new GeneratedKeyHolder();
			affectedRows = jdbcTemplate.update(new PreparedStatementCreator() {

				public PreparedStatement createPreparedStatement(Connection con)
						throws SQLException {
					PreparedStatement stmt = con.prepareStatement(addNewUser,
							Statement.RETURN_GENERATED_KEYS);
					stmt.setString(1, user.getUsername());
					stmt.setString(2, user.getPassword());
					stmt.setInt(3, user.getAge());
					stmt.setString(4, user.getContactno());
					stmt.setString(5, user.getEmailaddress());
					return stmt;
				}
			}, keyHoler);

			if (affectedRows > 0) {
				user.setUserid((Long) keyHoler.getKey());
				System.out.println("User Account is created: " + user);
				return user;
			}

		}
		return null;
	}
}
