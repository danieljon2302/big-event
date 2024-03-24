package com.itheima.bigevent.pojo;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class User {
	
	private Integer id;
	private String username;
	//此註解為：可將返回的table中的敏感資訊ex:password屏蔽
	@JsonIgnore
	private String password;
	private String nickname;
	private String email;
	private String userPic;
	private LocalDateTime createTime;
	private LocalDateTime updateTime;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUserPic() {
		return userPic;
	}
	public void setUserPic(String userPic) {
		this.userPic = userPic;
	}
	public LocalDateTime getCreateTime() {
		return createTime;
	}
	public void setCreateTime(LocalDateTime createTime) {
		this.createTime = createTime;
	}
	public LocalDateTime getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(LocalDateTime updateTime) {
		this.updateTime = updateTime;
	}

}
