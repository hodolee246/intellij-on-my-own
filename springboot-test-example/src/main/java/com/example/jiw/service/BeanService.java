package com.example.jiw.service;

import com.example.jiw.repo.BeanRepository;
import com.example.jiw.vo.Bean;
import org.springframework.stereotype.Service;

@Service
public class BeanService {

    private final BeanRepository beanRepository;

    public BeanService(BeanRepository beanRepository) {
        this.beanRepository = beanRepository;
    }

    public Bean findByName(String name) {
        return beanRepository.findByName(name);
    }
}
