package com.mandy.dao;

import com.mandy.pojo.Users;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @description:
 *     参数1：当前需要映射的实体
 *     参数2：当前映射的实体中的OID的类型
 *
 * @author: mandy
 * @create: 2020/8/1 12:20
 */
public interface UsersRepository extends JpaRepository<Users, Integer> {

}
