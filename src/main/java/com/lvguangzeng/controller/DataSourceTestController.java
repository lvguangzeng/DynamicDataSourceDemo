package com.lvguangzeng.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.lvguangzeng.annotation.DB;
import com.lvguangzeng.mapper.TestMapper;

@RestController
public class DataSourceTestController {
	@Autowired
	private TestMapper testMapper;

	@DB("db1DataSource")
	@RequestMapping(value = "/query1/{id}", method = RequestMethod.GET)
	public JSONObject query1(@PathVariable("id") int id) {
		return testMapper.query(id);
	}

	@DB("db2DataSource")
	@RequestMapping(value = "/query2/{id}", method = RequestMethod.GET)
	public JSONObject query2(@PathVariable("id") int id) {
		return testMapper.query(id);
	}
}
