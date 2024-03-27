package com.itheima.bigevent.pojo;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.groups.Default;
import lombok.Data;

@Data
public class Category {
//	notnull與notempty差異為: 一個是你不行不傳, 另一個是你傳了但裡面不能為空值
	
	@NotNull(groups = Update.class)
	private Integer id;
	
//	@NotEmpty(groups = {Update.class, Add.class})
	@NotEmpty
	private String categoryName;
//	@NotEmpty(groups = {Update.class, Add.class})
	@NotEmpty
	private String categoryAlias;
	
	private Integer createUser;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createTime;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime updateTime;
	
	//驗證分組
	//若某個驗證屬性沒有指定分組, 默認屬於default分組
	//分組之間可以“繼承”, a extends b, 代表a中含有b
	public interface Add extends Default{};
	
	public interface Update extends Default{}{};
	
}
