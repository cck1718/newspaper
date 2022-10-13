package com.cck;

import com.cck.dao.MenuDao;
import com.cck.dao.UserDao;
import com.cck.domain.entity.User;
import com.cck.service.MenuService;
import com.cck.utils.JwtUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/*@SpringBootTest
@RunWith(SpringRunner.class)*/
public class test {
    @Test
    public void test1(){
        String token = JwtUtils.createToken("12");
        System.out.println(JwtUtils.verifyToken(token));
        System.out.println(JwtUtils.getValue(token, "id"));
    }

/*    @Autowired
    private MenuDao menuDao;
    
    @Autowired
    private UserDao userDao;

    @Test
    public void t1(){
        List<String> byUserId = menuDao.findByUserId(1);
        System.out.println(byUserId);
    }*/

    @Test
    public void t2(){
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        System.out.println(bCryptPasswordEncoder.matches("zhangsan", "$2a$10$oo32XasOKNq5zqzVN8EbDux0WY8kyFWWbGjUA9U2IxkkMJSdjHpW2"));
    }
}
