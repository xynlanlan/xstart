package com.example.start;

import com.example.start.module.user.model.User;
import com.example.start.module.user.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = XstartApplication.class)
public class XstartApplicationTests {

	@Autowired
    UserService userService;
	@Test
	public void test() {
        List<User> list = userService.findByPager(null, 1, 10);
        System.out.println("======"+list.toString());
    }

}
