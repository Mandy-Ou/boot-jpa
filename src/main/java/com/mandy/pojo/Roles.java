package com.mandy.pojo;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

/**
 * @description:
 * @author: mandy
 * @create: 2020/8/9 22:19
 */
@Entity
@Table(name = "t_roles")
public class Roles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Integer roleId;

    @Column(name = "role_name")
    private String roleName;

    @OneToMany(mappedBy = "roles")
    private Set<Users> users = new HashSet<>();

    @ManyToMany(cascade = CascadeType.PERSIST,fetch = FetchType.EAGER)
    // @JoinTable:映射中间表
    // joinColumns: 当前表中的主键所关联的中间表中的外键字段
    @JoinTable(name = "t_roles_menus", joinColumns = @JoinColumn(name = "role_id"), inverseJoinColumns = @JoinColumn(name = "menus_id"))
    private Set<Menus> menus = new HashSet<>();

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Set<Users> getUsers() {
        return users;
    }

    public void setUsers(Set<Users> users) {
        this.users = users;
    }

    public Set<Menus> getMenus() {
        return menus;
    }

    public void setMenus(Set<Menus> menus) {
        this.menus = menus;
    }
}
