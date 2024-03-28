package com.itheima.bigevent.pojo;

import java.time.LocalDateTime;

import org.hibernate.validator.constraints.URL;

import com.itheima.bigevent.anno.State;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class Article {
	
	private Integer id;
	@NotEmpty
	@Pattern(regexp = "^\\S{1,10}$")
	private String title;
	@NotEmpty
	private String content;
	@NotEmpty
	@URL
	private String coverImg;
	//自定義validated(草稿/ 已發布) 
	@State
	private String state;
	@NotNull
	private Integer categoryId;
	private Integer createUser;
	private LocalDateTime createTime;
	private LocalDateTime updateTime;

}
