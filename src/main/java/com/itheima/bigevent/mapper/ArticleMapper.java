package com.itheima.bigevent.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import com.itheima.bigevent.pojo.Article;

@Mapper
public interface ArticleMapper {

	@Insert("insert into artical(title,content,state,category_id,create_user,create_time,update_time,cover_img) "+
			"values(#{title},#{content},#{state},#{categoryId},#{createUser},#{createTime},#{updateTime},#{ coverImg,})")
	void add(Article artical);

	List<Article> list(Integer userId, Integer categoryId, String state);

}
