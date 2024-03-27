package com.itheima.bigevent.service.impl;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itheima.bigevent.mapper.ArticalMapper;
import com.itheima.bigevent.pojo.Artical;
import com.itheima.bigevent.service.ArticalService;
import com.itheima.bigevent.util.ThreadLocalUtil;

@Service
public class ArticalServiceImpl implements ArticalService {

	@Autowired
	private ArticalMapper articalMapper;
	
	@Override
	public void add(Artical artical) {

		artical.setCreateTime(LocalDateTime.now());
		artical.setUpdateTime(LocalDateTime.now());
		
		
		Map<String, Object> map= ThreadLocalUtil.get();
		Integer createUser = (Integer)map.get("id");
		artical.setCreateUser(createUser);
		
		articalMapper.add(artical);
	}

}
