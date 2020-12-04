package com.example.jiw;

import static org.junit.Assert.*;

import com.example.jiw.repo.SimpleBeanRepository;
import com.example.jiw.service.BeanService;
import com.example.jiw.vo.Bean;
import org.junit.Before;
import org.junit.Test;

public class BeanServiceUnitTest2 {

    private SimpleBeanRepository beanRepository;
    private BeanService beanService;

    // given
    @Before
    public void addBean() {
        beanRepository = new SimpleBeanRepository();
        beanRepository.add(new Bean("coffee"));
        beanService = new BeanService(beanRepository);
    }

    @Test
    public void getBeanByName() {
        // when
        Bean bean = beanService.findByName("coffee");
        // then
        assertEquals(bean.getName(), "coffee");
    }

    @Test
    public void getBeanByName2() {
        // when
        Bean bean = beanService.findByName("coffee?");
        // then
        assertEquals(bean.getName(), "coffee");
    }
}
