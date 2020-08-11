package com.mandy.test;

import com.mandy.BootJpaApplication;
import com.mandy.dao.RolesRepository;
import com.mandy.pojo.Menus;
import com.mandy.pojo.Roles;
import java.util.Optional;
import java.util.Set;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @description:
 * @author: mandy
 * @create: 2020/8/9 23:28
 */
@SpringBootTest(classes = BootJpaApplication.class)
public class ManyToManyTest {

    @Autowired
    private RolesRepository rolesRepository;

    /**
     * 添加测试
     */
    @Test
    public void testSave() {
        // 创建角色对象
        Roles r = new Roles();
        r.setRoleName("项目经理");
        // 创建菜单对象
        Menus menus = new Menus();
        menus.setMenusName("xxx管理系统");
        menus.setParentId(0);

        Menus menus2 = new Menus();
        menus2.setParentId(1);
        menus.setMenusName("项目管理");
        // 关联
        r.getMenus().add(menus);
        r.getMenus().add(menus2);
        menus.getRoles().add(r);
        menus2.getRoles().add(r);
        // 保存
        this.rolesRepository.save(r);
    }

    /**
     * 查询操作
     */
    @Test
    public void testFind() {
        Optional<Roles> rolesOptional = this.rolesRepository.findById(4);
        System.out.println(rolesOptional.get().getRoleName());
        Set<Menus> menus = rolesOptional.get().getMenus();
        for (Menus menus2 : menus) {
            System.out.println(menus2);
        }
    }
}
