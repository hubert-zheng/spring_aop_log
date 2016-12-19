package com.hubert.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hubert.bean.User;
import com.hubert.bean.UserExample;
import com.hubert.mapper.UserMapper;
import com.hubert.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserMapper userMapper;
	
	@Override
	public List<User> queryUser() {
		UserExample example = new UserExample();
		//Criteria c = example.createCriteria();
		return userMapper.selectByExample(example);
	}
	

}
