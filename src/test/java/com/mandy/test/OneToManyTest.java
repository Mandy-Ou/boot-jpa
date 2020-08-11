package com.mandy.test;

import com.mandy.BootJpaApplication;
import com.mandy.dao.UsersRepository;
import com.mandy.pojo.Roles;
import com.mandy.pojo.Users;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @description:  一对多关联测试
 * @author: mandy
 * @create: 2020/8/9 22:26
 */
@SpringBootTest(classes = BootJpaApplication.class)
public class OneToManyTest {

    @Autowired
    private UsersRepository usersRepository;

    /**
     * 一对多关联关系的添加
     */
    @Test
    public void testSave() {
        // 创建一个用户
        Users users = new Users();
        users.setAddress("天津");
        users.setAge(32);
        users.setName("小刚");
        // 创建一个角色
        Roles roles = new Roles();
        roles.setRoleName("管理员");
        // 关联
        roles.getUsers().add(users);
        users.setRoles(roles);
        // 保存
        this.usersRepository.save(users);
    }

    /**
     * 一对多关联关系的查询
     */
    @Test
    public void testFind() {
        Optional<Users> findOne = this.usersRepository.findById(6);
        System.out.println(findOne.get());
        Roles roles = findOne.get().getRoles();
        System.out.println(roles.getRoleName());
    }
}
