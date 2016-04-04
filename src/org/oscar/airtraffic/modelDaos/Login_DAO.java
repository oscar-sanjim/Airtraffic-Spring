package org.oscar.airtraffic.modelDaos;

import org.springframework.stereotype.*;
import org.springframework.jdbc.core.*;
import org.springframework.jdbc.core.namedparam.*;
import org.oscar.airtraffic.model.User;
import org.springframework.beans.factory.annotation.*;
import java.sql.*;
import javax.sql.*;
import java.util.*;

@Repository
public class Login_DAO extends Abstact_AirTrafficDaos_MiddleWare{
	
	private JdbcTemplate access;
	
	@Autowired
	public void setAccess(DataSource dataSource){
		this.access = new JdbcTemplate(dataSource);
	}
	
	public Object searchUser(User user){
		System.out.println("Name: "+user.getName()+" Password: "+ user.getPassword());
		String sql = "SELECT * FROM user WHERE name = ? AND password = ?";		
		try {
			return this.access.queryForObject(sql, new UserMapper(),user.getName(), user.getPassword());
		} catch (Exception e) {
			System.out.println("Exception Caught: "+e);
			return null;
		}		
	}
}
