package com.mandy.dao;

import com.mandy.pojo.Users;
import java.util.List;
import org.springframework.data.repository.Repository;

/**
 * @description: Repository接口的方法名称命名查询
 * @author: mandy
 * @create: 2020/8/3 0:01
 */
public interface UsersRepositoryByName extends Repository<Users,Integer> {

    /**
     * 方法的名称必须要尊孙驼峰式命名规则。findBy(关键字)+属性名称（首字母要大写）+查询条件（首字母大学）
     * @param name
     * @return
     */
    List<Users> findByName(String name);

    List<Users> findByNameAndAge(String name, Integer age);

    List<Users> findByNameLike(String name);
}
