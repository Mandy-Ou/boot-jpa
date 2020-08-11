package com.mandy.dao;

import com.mandy.pojo.Users;
import java.util.List;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

/**
 * @description:  Repository  @Query
 * @author: mandy
 * @create: 2020/8/3 23:47
 */
public interface UsersRepositoryQueryAnnotation extends Repository<Users, Integer> {


    @Query("from Users where name = ?1")
    List<Users> queryByNameUseHQL(String name);

    @Query(value = "select * from t_users where name = ?", nativeQuery = true)
    List<Users> queryByNameUserSQL(String name);

    @Query("update Users set name = ?1 where id = ?2")
    @Modifying  // 需要执行一个更新操作
    void updateUsersNameById(String name, Integer id);
}
