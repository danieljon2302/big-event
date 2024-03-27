package com.itheima.bigevent.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itheima.bigevent.pojo.Artical;
import com.itheima.bigevent.pojo.Result;
import com.itheima.bigevent.service.ArticalService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/artical")
public class ArticalController {
	
	@Autowired
	ArticalService articalService;
	
	@PostMapping
	public Result add(@RequestBody @Validated Artical artical) {

		articalService.add(artical);
		return Result.success();
		
	}
	

}
