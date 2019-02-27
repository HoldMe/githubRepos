package boot.com.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Table;

@Table(name="user_tb")
public class User extends BaseEntity implements Serializable{

	private static final long serialVersionUID = 5632581925141758354L;
	
	@Column(name="username")
	private String userName;
	@Column(name="deptno")
	private Long deptNo;
	@Column(name="telphone")
	private String phoneNum;
	@Column(name="email")
	private String email;
	@Column(name="sex")
	private String sex;
	@Column(name="roleno")
	private Long roleNo;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Long getDeptNo() {
		return deptNo;
	}
	public void setDeptNo(Long deptNo) {
		this.deptNo = deptNo;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Long getRoleNo() {
		return roleNo;
	}
	public void setRoleNo(Long roleNo) {
		this.roleNo = roleNo;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}

}
