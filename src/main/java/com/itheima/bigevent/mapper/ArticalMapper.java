package com.itheima.bigevent.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import com.itheima.bigevent.pojo.Artical;

@Mapper
public interface ArticalMapper {

	@Insert("insert into artical(title,content,state,category_id,create_user,create_time,update_time,cover_img) "+
			"values(#{title},#{content},#{state},#{categoryId},#{createUser},#{createTime},#{updateTime},#{ coverImg,})")
	void add(Artical artical);

}
