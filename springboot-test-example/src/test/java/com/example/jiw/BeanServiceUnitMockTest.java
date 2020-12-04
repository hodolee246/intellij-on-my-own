package com.example.jiw;

import static org.junit.Assert.*;

import com.example.jiw.repo.BeanRepository;
import com.example.jiw.service.BeanService;
import com.example.jiw.vo.Bean;
import org.junit.Test;
import org.mockito.Mockito;

public class BeanServiceUnitMockTest {

    @Test
    public void getBeanByName() {
        // setup
        BeanRepository beanRepository = Mockito.mock(BeanRepository.class);
        // beanRepo 의 findByName 이 호출되면 새로운 bean 을 리턴
        Mockito.when(beanRepository.findByName("coffeeBean"))
                .thenReturn(new Bean("coffeeBean"));
        BeanService beanService = new BeanService(beanRepository);
        // when
        Bean actualBean = beanService.findByName("coffeeBean");
        // then
        assertEquals("coffeeBean", actualBean.getName());
    }
}
