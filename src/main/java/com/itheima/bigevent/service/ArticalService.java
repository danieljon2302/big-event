package com.itheima.bigevent.service;

import com.itheima.bigevent.pojo.Article;
import com.itheima.bigevent.pojo.PageBean;

public interface ArticalService {

	void add(Article artical);

	PageBean<Article> list(Integer pageNum, Integer pageSize, Integer categoryId, String state);


}
