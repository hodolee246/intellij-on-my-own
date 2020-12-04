package com.example.jiw;

import static org.junit.Assert.*;

import com.example.jiw.repo.SimpleBeanRepository;
import com.example.jiw.service.BeanService;
import com.example.jiw.vo.Bean;
import org.junit.Before;
import org.junit.Test;

public class BeanServiceUnitTest {

    private SimpleBeanRepository simpleBeanRepository;

    private BeanService beanService;

    @Before
    public void setUp() {
        simpleBeanRepository = new SimpleBeanRepository();
        simpleBeanRepository.add(new Bean("coffeeBean"));
        beanService = new BeanService(simpleBeanRepository);
    }

    @Test
    public void getBeanByName() {
        Bean bean = beanService.findByName("coffeeBean");
        assertEquals("coffeeBean", bean.getName());
    }
}
