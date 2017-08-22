package com.shop.user.entity;



/**
 * 用户实体类
 * @author Administrator
 */
public class User {
	private Integer uid; // 用户id
	private String username; // 用户账号
	private String password; // 用户密码
	private String name; // 用户姓名
	private String email; // 用户邮箱
	private String phone; // 用户电话
	private String address; // 用户地址
	private Integer state; // 用户状态
	private String code; // 激活码

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return "User [uid=" + uid + ", username=" + username + ", password="
				+ password + ", name=" + name + ", email=" + email + ", phone="
				+ phone + ", address=" + address + ", state=" + state
				+ ", code=" + code + "]";
	}

	
}
