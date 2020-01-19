package com.ymc.demo.jpa;

import com.ymc.demo.entity.DemoUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author yiautos
 */
@Repository
public interface UserRepository extends JpaRepository<DemoUser,Long> {

    /**
     * JPA根据用户名称，查找对应数据
     * @param userName 用户名称
     * @return
     */
    DemoUser findByUserName(String userName);

    /**
     * JPA手动编写SQL，查询所有数据1
     * @return
     */
    @Query(value = "select * from demo_user",nativeQuery = true)
    List<DemoUser> selectAll();

}
