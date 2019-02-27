package boot.com.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import boot.com.entity.User;

public class UserMapper implements RowMapper<User>{

	public User mapRow(ResultSet set, int index) throws SQLException {
		User user = new User();
		user.setId(set.getLong(1));
		user.setUserName(set.getString(2));
		user.setSex((set.getString(3)!=null && set.getString(3).equals("mail"))?"ÄÐ":"Å®");
		user.setDeptNo(set.getLong(4));
		user.setRoleNo(set.getLong(5));
		user.setEmail(set.getString(6));
		user.setPhoneNum(set.getString(7));
		return user;
	}

}
