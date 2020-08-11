package com.mandy.dao;

import com.mandy.pojo.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @description:  JpaSpecificationExecutor
 * @author: mandy
 * @create: 2020/8/7 22:42
 */
public interface UsersRepositorySpecification extends JpaRepository<Users, Integer>, JpaSpecificationExecutor<Users> {
}
