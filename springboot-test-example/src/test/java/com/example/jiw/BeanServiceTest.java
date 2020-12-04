package com.example.jiw;

import static org.junit.Assert.*;

import com.example.jiw.repo.SimpleBeanRepository;
import com.example.jiw.service.BeanService;
import com.example.jiw.vo.Bean;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {
                BeanService.class,
                SimpleBeanRepository.class
        })
public class BeanServiceTest {

    @Autowired
    private BeanService beanService;

    @MockBean
    private SimpleBeanRepository simpleBeanRepository;

    @Before
    public void addBean() {
        BDDMockito.given(this.simpleBeanRepository.findByName("coffeeBean"))
                .willReturn(new Bean("anyBean"));
    }

    @Test
    public void getBeanByName() {
        Bean bean = beanService.findByName("coffeeBean");
        assertEquals("anyBean", bean.getName());
    }
}
