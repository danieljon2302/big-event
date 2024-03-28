package com.itheima.bigevent.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.itheima.bigevent.pojo.Article;
import com.itheima.bigevent.pojo.PageBean;
import com.itheima.bigevent.pojo.Result;
import com.itheima.bigevent.service.ArticalService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/artical")
public class ArticleController {
	
	@Autowired
	ArticalService articalService;
	
	@PostMapping
	public Result add(@RequestBody @Validated Article artical) {

		articalService.add(artical);
		return Result.success();
		
	}
	
	@GetMapping
	public Result<PageBean<Article>> list(
			Integer pageNum,
			Integer pageSize,
			@RequestParam(required = false) Integer categoryId,
			@RequestParam(required = false) String state			
			){
		PageBean<Article> pb = articalService.list(pageNum, pageSize, categoryId, state);
		return Result.success(pb);
	}
	

}
