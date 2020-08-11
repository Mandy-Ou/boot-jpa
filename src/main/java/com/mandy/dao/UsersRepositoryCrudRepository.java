package com.mandy.dao;

import com.mandy.pojo.Users;
import org.springframework.data.repository.CrudRepository;

/**
 * @description:  CrudRepository接口
 * @author: mandy
 * @create: 2020/8/4 0:26
 */
public interface UsersRepositoryCrudRepository extends CrudRepository<Users, Integer> {
}
