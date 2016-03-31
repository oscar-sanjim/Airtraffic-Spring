package org.oscar.airtraffic.modelDaos;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.oscar.airtraffic.model.User;
import org.springframework.jdbc.core.RowMapper;

public abstract class Abstact_AirTrafficDaos_MiddleWare {
	
	
	protected static final class UserMapper implements RowMapper<User>{
		@Override
		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			User user = new User();
			user.setId(rs.getInt("id"));
			user.setName(rs.getString("name"));
			user.setPassword(rs.getString("password"));
			return user;
		}
	}
}
