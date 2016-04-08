package org.oscar.airtraffic.modelDaos;

import javax.sql.DataSource;

import org.oscar.airtraffic.model.User;
import org.oscar.airtraffic.modelDaos.Login_DAO.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ApiKeyDao {


	private JdbcTemplate access;
	
	@Autowired
	public void setAccess(DataSource dataSource){
		this.access = new JdbcTemplate(dataSource);
	}
	
	public boolean searchApiKey(String key){
		
		String sql = "SELECT * FROM apiKey WHERE apikey = ?";		
		try {
			if(this.access.queryForList(sql, key).size()>0){
				return true;
			}
			return false;
		} catch (Exception e) {
			System.out.println("Exception Caught: "+e);
			return false;
		}
	}
}
