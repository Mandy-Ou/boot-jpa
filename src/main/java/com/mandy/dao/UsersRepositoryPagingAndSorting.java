package com.mandy.dao;

import com.mandy.pojo.Users;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @description:    PagingAndSortingRepository接口
 * @author: mandy
 * @create: 2020/8/6 0:09
 */
public interface UsersRepositoryPagingAndSorting extends PagingAndSortingRepository<Users, Integer> {
}
