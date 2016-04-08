package org.oscar.airtraffic.modelDaos;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.*;
import javax.sql.DataSource;

import org.oscar.airtraffic.model.Log;
import org.oscar.airtraffic.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class LogsDao {
	
	private JdbcTemplate access;
	
	@Autowired
	public void setAccess(DataSource dataSource){
		this.access = new JdbcTemplate(dataSource);
	}
	
	public boolean add(Log log){
		String sql = "INSERT INTO log (time, apikey, route) VALUES (?,?,?)";
		try {
			this.access.update(sql, log.getDate(), log.getApiKey(), log.getRoute());
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public List<Log> getLogs(){
		String sql = "SELECT * FROM log";
		try {
			return this.access.query(sql, new LogMapper());
		} catch (Exception e) {
			return new ArrayList<>();
		}
		
	}
	
	protected static final class LogMapper implements RowMapper<Log>{
		@Override
		public Log mapRow(ResultSet rs, int rowNum) throws SQLException {
			Log log = new Log();
			log.setApiKey(rs.getString("apikey"));
			log.setDate(rs.getDate("time"));
			log.setRoute(rs.getString("route"));
			return log;
		}
	}
}
