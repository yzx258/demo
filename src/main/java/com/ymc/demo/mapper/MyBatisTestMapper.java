package com.ymc.demo.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author yucw
 * @date 2020/1/17 11:00
 */
@Mapper
public interface MyBatisTestMapper {

    /**
     * 使用MyBatis查询数据
     *
     * @return
     */
    @Select("SELECT remark FROM crm_agent_commission_log")
    List<String> findAll();


    /**
     * 测试连接
     */
    @Select("select 1 from dual")
    int testSqlConnent();
}
