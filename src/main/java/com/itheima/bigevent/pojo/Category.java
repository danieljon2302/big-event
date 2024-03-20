package com.itheima.bigevent.pojo;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class Category {

	private Integer id;
	private String categoryName;
	private String categoryAlias;
	private Integer createUser;
	private LocalDateTime createTime;
	private LocalDateTime updateTime;
	
}
