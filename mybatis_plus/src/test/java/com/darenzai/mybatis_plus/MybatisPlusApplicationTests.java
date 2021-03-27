package com.darenzai.mybatis_plus;

import com.darenzai.mybatis_plus.entity.User;
import com.darenzai.mybatis_plus.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class MybatisPlusApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @org.junit.Test
    public void listAll(){
        List<User>  list=userMapper.selectList(null);
        System.out.println(list);
    }

    @org.junit.Test
    public void updateUser() {

        User user = new User();
        user.setId(1231103936770154497L);
        user.setAge(120);

        int row = userMapper.updateById(user);
        System.out.println(row);
    }

    @Test
    void contextLoads() {






    }

}
