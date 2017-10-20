package com.lvguangzeng.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.alibaba.fastjson.JSONObject;

@Mapper
public interface TestMapper {
	
	@Select("select * from test where id=#{id}")
	public JSONObject query(@Param("id") int id);

}
