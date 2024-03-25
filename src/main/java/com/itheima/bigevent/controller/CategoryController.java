package com.itheima.bigevent.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itheima.bigevent.pojo.Category;
import com.itheima.bigevent.pojo.Result;
import com.itheima.bigevent.service.CategoryService;



@RestController
@RequestMapping("/category")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;

	@PostMapping
	public Result add(@RequestBody @Validated Category category) {
		categoryService.add(category);
		return Result.success();
	}
	
	@GetMapping
	public Result<List<Category>> list(){
		List<Category> cs = categoryService.list();
		return Result.success(cs);
	}
	
	@GetMapping("/detail")
	public Result<Category> detail(Integer id){
		//id為文章分類id, 非userId
		Category c = categoryService.findById(id);
		return Result.success(c);
	}
}
