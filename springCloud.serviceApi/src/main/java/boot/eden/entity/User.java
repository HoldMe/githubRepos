package boot.eden.entity;

import java.io.Serializable;

/**
* @author Eden
* @version 创建时间：2019年1月14日 下午6:07:50
* 类说明
*/
public class User implements Serializable{

	private static final long serialVersionUID = 6855195456394663874L;
	
	private String name;
	private String sex;
	private Integer age;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}

}
