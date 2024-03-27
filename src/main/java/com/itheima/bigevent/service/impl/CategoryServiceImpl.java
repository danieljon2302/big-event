package com.itheima.bigevent.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itheima.bigevent.mapper.CategoryMapper;
import com.itheima.bigevent.pojo.Category;
import com.itheima.bigevent.service.CategoryService;
import com.itheima.bigevent.util.ThreadLocalUtil;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryMapper categoryMapper;
	
	@Override
	public void add(Category category) {
		
		//前端只會傳categoryName;  categoryAlias; 所以其他的要自己加
		category.setCreateTime(LocalDateTime.now());
		category.setUpdateTime(LocalDateTime.now());
		//使用者id在登入時已存在ThreadLocal裡了
		Map<String, Object> map= ThreadLocalUtil.get();
		Integer userId = (Integer)map.get("id");
		category.setCreateUser(userId);
		
		categoryMapper.add(category);
	}

	@Override
	public List<Category> list() {
		
		Map<String, Object> map= ThreadLocalUtil.get();
		Integer userId = (Integer)map.get("id");
		
		return categoryMapper.list(userId);
	}

	@Override
	public Category findById(Integer id) {
		Category c = categoryMapper.findById(id);
		return c;
	}

	@Override
	public void update(Category category) {
		category.setUpdateTime(LocalDateTime.now());
		categoryMapper.update(category);
	}
	
}
