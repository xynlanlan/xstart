package com.example.start;

import com.example.start.module.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = XstartApplication.class)
public class XstartApplicationTests {

	@Autowired
	UserService userService;
	@Test
	public void test() {
    }

}