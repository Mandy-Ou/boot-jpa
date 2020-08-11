package com.mandy.test;

import com.mandy.BootJpaApplication;
import com.mandy.dao.*;
import com.mandy.pojo.Users;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.annotation.Rollback;

/**
 * @description:  测试类
 * @author: mandy
 * @create: 2020/8/1 12:26
 */
//@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = BootJpaApplication.class)
public class UsersRepositoryTest {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private UsersRepositoryByName usersRepositoryByName;

    @Autowired
    private UsersRepositoryQueryAnnotation usersRepositoryQueryAnnotation;

    @Autowired
    private UsersRepositoryCrudRepository usersRepositoryCrudRepository;

    @Autowired
    private UsersRepositoryPagingAndSorting usersRepositoryPagingAndSorting;

    @Autowired
    private UsersRepositorySpecification usersRepositorySpecification;

    /**
     * Repository--保存数据
     */
    @Test
    public void hello() {
        System.out.println("hello world");
        Users users = new Users();
        users.setAddress("上海市");
        users.setAge(24);
        users.setName("王五");
        this.usersRepository.save(users);
    }

    /**
     * Repository--方法名称命名测试
     */
    @Test
    public void testFindByName() {
        List<Users> list = this.usersRepositoryByName.findByName("Mandy");
        for (Users users : list) {
            System.out.println(users);
        }
    }

    @Test
    public void testFindByNameAndAge() {
        List<Users> list = this.usersRepositoryByName.findByNameAndAge("Mandy", 20);
        for (Users users : list) {
            System.out.println(users);
        }
    }

    @Test
    public void testFindByNameLike() {
        List<Users> list = this.usersRepositoryByName.findByNameLike("Ma%");
        for (Users users : list) {
            System.out.println(users);
        }
    }

    @Test
    public void testQueryByNameUserHQL() {
        List<Users> list = this.usersRepositoryQueryAnnotation.queryByNameUseHQL("Mandy");
        for (Users users : list) {
            System.out.println(users);
        }
    }

    @Test
    public void testQueryByNameUserSQL() {
        List<Users> list = this.usersRepositoryQueryAnnotation.queryByNameUserSQL("Mandy");
        for (Users users : list) {
            System.out.println(users);
        }
    }

    @Test
    @Transactional  // Transactional与@Test一起使用时事务是自动回滚的，所以数据库没有改变
    @Rollback(false)  // 取消回滚
    public void testUpdateUsersNameById() {
        this.usersRepositoryQueryAnnotation.updateUsersNameById("Mandy2", 2);
    }

    @Test
    public void testCrudRepositorySave() {
        Users user = new Users();
        user.setAddress("天津");
        user.setName("张三丰");
        user.setAge(22);
        this.usersRepositoryCrudRepository.save(user);
    }

    @Test
    public void testCrudRepositoryUpdate() {
        Users user = new Users();
        user.setId(5);  // 需要有id
        user.setAddress("北京");
        user.setName("王五3");
        user.setAge(22);
        this.usersRepositoryCrudRepository.save(user);
    }

    @Test
    public void testCrudRepositoryFindOne() {
        Optional<Users> users = this.usersRepositoryCrudRepository.findById(4);
        System.out.println(users.get());
    }

    @Test
    public void testCrudRepositoryFindAll() {
        List<Users> list = (List<Users>)this.usersRepositoryCrudRepository.findAll();
        for (Users users : list) {
            System.out.println(users);
        }
    }

    @Test
    public void testCrudRepositoryDeleteById() {
        this.usersRepositoryCrudRepository.deleteById(4);
    }

    /**
     * 排序测试
     */
    @Test
    public void testPagingAndSortingRepositorySort() {
        // Sort对象封装了排序规则
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        List<Users> list = (List<Users>)this.usersRepositoryPagingAndSorting.findAll(sort);
        for (Users users : list) {
            System.out.println(users);
        }
    }

    /**
     * 分页测试
     */
    @Test
    public void testPagingAndSortingRepositoryPaging() {
        // Pageable：封装了分页的参数，当前页，每页显示的条数。注意：他的当前页是从0开始。
        // PageRequest(page,size) page:当前页，size:每页显示的条数
        Pageable pageable = PageRequest.of(1, 2);
        Page<Users> page = this.usersRepositoryPagingAndSorting.findAll(pageable);
        System.out.println("总条数：" + page.getTotalElements());
        System.out.println("总页数：" + page.getTotalPages());
        List<Users> list = page.getContent();
        for (Users users : list) {
            System.out.println(users);
        }
    }

    /**
     * 分页+排序测试
     */
    @Test
    public void testPagingAndSortingRepositoryPagingAndPaging() {
        // Pageable：封装了分页的参数，当前页，每页显示的条数。注意：他的当前页是从0开始。
        // PageRequest(page,size) page:当前页，size:每页显示的条数
        Pageable pageable = PageRequest.of(0, 2, Sort.by(Sort.Direction.DESC, "id"));
        Page<Users> page = this.usersRepositoryPagingAndSorting.findAll(pageable);
        System.out.println("总条数：" + page.getTotalElements());
        System.out.println("总页数：" + page.getTotalPages());
        List<Users> list = page.getContent();
        for (Users users : list) {
            System.out.println(users);
        }
    }

    /**
     * JpaRepository  排序测试
     */
    @Test
    public void testJpaRepositorySort() {
        // Sort对象封装了排序规则
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        List<Users> list = this.usersRepository.findAll(sort);
        for (Users users : list) {
            System.out.println(users);
        }
    }

    /**
     * JpaSpecificationExecutor 单条件测试
     */
    @Test
    public void testJpaSpecificationExecutor1() {
        // Specification<Users>：用于封装查询条件
        Specification<Users> spec = new Specification<Users>() {
            // Predicate:封装了单个的查询条件
            /**
             * @param root  查询对象的属性的封装
             * @param criteriaQuery  封装了我们要执行的查询中的各个部分的信息 select  from order by
             * @param criteriaBuilder   查询条件的构造器。定义不同的查询条件
             * @return
             */
            @Override
            public Predicate toPredicate(Root<Users> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                // where name = 'Mandy'
                /**
                 * 参数一：查询的条件属性
                 * 参数二：条件的值
                 */
                Predicate pre = criteriaBuilder.equal(root.get("name"), "Mandy");
                return pre;
            }
        };
        List<Users> list = this.usersRepositorySpecification.findAll(spec);
        for (Users users : list) {
            System.out.println(users);
        }
    }


    /**
     * JpaSpecificationExecutor 多条件测试
     */
    @Test
    public void testJpaSpecificationExecutor2() {
        // Specification<Users>：用于封装查询条件
        Specification<Users> spec = new Specification<Users>() {
            // Predicate:封装了单个的查询条件
            /**
             * @param root  查询对象的属性的封装
             * @param criteriaQuery  封装了我们要执行的查询中的各个部分的信息 select  from order by
             * @param criteriaBuilder   查询条件的构造器。定义不同的查询条件
             * @return
             */
            @Override
            public Predicate toPredicate(Root<Users> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                // where name = 'Mandy' and age = 20
                List<Predicate> list = new ArrayList<>();
                list.add(criteriaBuilder.equal(root.get("name"), "Mandy"));
                list.add(criteriaBuilder.equal(root.get("age"), 20));
                Predicate[] arr = new Predicate[list.size()];
                return criteriaBuilder.and(list.toArray(arr));
            }
        };
        List<Users> list = this.usersRepositorySpecification.findAll(spec);
        for (Users users : list) {
            System.out.println(users);
        }
    }


    /**
     * JpaSpecificationExecutor 多条件查询第二种写法测试
     */
    @Test
    public void testJpaSpecificationExecutor3() {
        // Specification<Users>：用于封装查询条件
        Specification<Users> spec = new Specification<Users>() {
            // Predicate:封装了单个的查询条件
            /**
             * @param root  查询对象的属性的封装
             * @param criteriaQuery  封装了我们要执行的查询中的各个部分的信息 select  from order by
             * @param criteriaBuilder   查询条件的构造器。定义不同的查询条件
             * @return
             */
            @Override
            public Predicate toPredicate(Root<Users> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                // where name = 'Mandy' and age = 20
//                return criteriaBuilder.or(criteriaBuilder.equal(root.get("name"), "Mandy"), criteriaBuilder.equal(root.get("age"), 20));
                // 如果既有and 又有or查询
                return criteriaBuilder.or(criteriaBuilder.and(criteriaBuilder.equal(root.get("name"), "Mandy"), criteriaBuilder.equal(root.get("age"), 20)),
                        criteriaBuilder.equal(root.get("id"), 1));
            }
        };
        // 排序 分页
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        List<Users> list = this.usersRepositorySpecification.findAll(spec, sort);
        for (Users users : list) {
            System.out.println(users);
        }
    }

}
