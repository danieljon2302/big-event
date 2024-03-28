package com.itheima.bigevent.service.impl;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.bigevent.mapper.ArticleMapper;
import com.itheima.bigevent.pojo.Article;
import com.itheima.bigevent.pojo.PageBean;
import com.itheima.bigevent.service.ArticalService;
import com.itheima.bigevent.util.ThreadLocalUtil;

import java.util.List;

@Service
public class ArticalServiceImpl implements ArticalService {

	@Autowired
	private ArticleMapper articalMapper;
	
	@Override
	public void add(Article artical) {

		artical.setCreateTime(LocalDateTime.now());
		artical.setUpdateTime(LocalDateTime.now());
		
		
		Map<String, Object> map= ThreadLocalUtil.get();
		Integer createUser = (Integer)map.get("id");
		artical.setCreateUser(createUser);
		
		articalMapper.add(artical);
	}

	@Override
	public PageBean<Article> list(Integer pageNum, Integer pageSize, Integer categoryId, String state) {
		//1. 創建pagehelper物件
		PageBean<Article> pb = new PageBean<>(); 
		//2. 開始分頁查詢PageHelper
		PageHelper.startPage(pageNum, pageSize);
		//3. 調用Mapper
		Map<String, Object> map= ThreadLocalUtil.get();
		Integer userId = (Integer)map.get("id");
		List<Article> as = articalMapper.list(userId, categoryId, state);
		//page中提供了方法, 可以獲取pagehelper分頁查詢後, 得到的總記錄條數及當前業數據
		Page<Article> p = (Page<Article>)as;
		//將資料儲存到pagebean中
		pb.setTotal(p.getTotal());
		pb.setItems(p.getResult());
		
		return pb;
	}

}
