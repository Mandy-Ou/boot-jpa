package com.mandy.pojo;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

/**
 * @description:
 * @author: mandy
 * @create: 2020/8/9 23:11
 */
@Entity
@Table(name = "t_menus")
public class Menus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "menus_id")
    private Integer menusId;

    @Column(name = "menus_name")
    private String menusName;

    @Column(name = "menus_url")
    private String menusUrl;

    @Column(name = "parent_id")
    private Integer parentId;


    @ManyToMany(mappedBy = "menus")
    private Set<Roles> roles = new HashSet<>();

    public Integer getMenusId() {
        return menusId;
    }

    public void setMenusId(Integer menusId) {
        this.menusId = menusId;
    }

    public String getMenusName() {
        return menusName;
    }

    public void setMenusName(String menusName) {
        this.menusName = menusName;
    }

    public String getMenusUrl() {
        return menusUrl;
    }

    public void setMenusUrl(String menusUrl) {
        this.menusUrl = menusUrl;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Set<Roles> getRoles() {
        return roles;
    }

    public void setRoles(Set<Roles> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "Menus{" +
                "menusId=" + menusId +
                ", menusName='" + menusName + '\'' +
                ", menusUrl='" + menusUrl + '\'' +
                ", parentId=" + parentId +
                '}';
    }
}
