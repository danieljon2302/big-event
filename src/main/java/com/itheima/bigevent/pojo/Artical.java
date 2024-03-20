package com.itheima.bigevent.pojo;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class Artical {
	
	private Integer id;
	private String title;
	private String content;
	private String coverImg;
	private String state;
	private Integer categoryId;
	private Integer createUser;
	private LocalDateTime createTime;
	private LocalDateTime updateTime;

}
