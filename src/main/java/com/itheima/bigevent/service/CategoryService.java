package com.itheima.bigevent.service;

import java.util.List;

import com.itheima.bigevent.pojo.Category;

public interface CategoryService {

//	新增分類
	void add(Category category);
//	列表查詢
	List<Category> list();
//	根據id查詢分類訊息
	Category findById(Integer id);

}
