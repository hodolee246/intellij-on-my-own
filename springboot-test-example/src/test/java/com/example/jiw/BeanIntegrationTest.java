package com.example.jiw;

import static org.junit.Assert.*;

import com.example.jiw.service.BeanService;
import com.example.jiw.vo.Bean;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BeanIntegrationTest {

    @Autowired
    private BeanService beanService;

    @Test
    public void getBeanByName() {
        Bean bean = beanService.findByName("coffeeBean");
        assertEquals("coffeeBean", bean.getName());
    }
}
