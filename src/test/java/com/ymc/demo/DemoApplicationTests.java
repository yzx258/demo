package com.ymc.demo;

import com.ymc.demo.entity.DemoUser;
import com.ymc.demo.jpa.UserRepository;
import com.ymc.demo.mapper.MyBatisTestMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @Autowired
    private MyBatisTestMapper myBatisTestMapper;

    @Autowired
    private UserRepository userRepository;


    /**
     * MyBatis测试是否能够链接MySql
     */
    @Test
    public void testMyBatis()
    {
        int i = myBatisTestMapper.testSqlConnent();
        System.out.println("测试数据库是否链接上：" + i);
    }

    /**
     * MyBatis查询数据
     */
    @Test
    public void findAll()
    {
        try {
            List<String> list = myBatisTestMapper.findAll();
            System.out.println(list);
        }catch (Exception e)
        {
            System.out.println(e);
        }

    }

    /**
     * JPA手动写SQL测试
     */
    @Test
    public void testJpa()
    {
        DemoUser user = userRepository.findByUserName("yiauto");
        System.out.println("测试链接："+user);

        List<DemoUser> demoUsers = userRepository.selectAll();
        System.out.println("数据大小：" + demoUsers.size());
    }



}
