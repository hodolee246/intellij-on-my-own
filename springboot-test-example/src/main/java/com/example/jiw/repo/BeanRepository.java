package com.example.jiw.repo;

import com.example.jiw.vo.Bean;

public interface BeanRepository {
    Bean findByName(String name);
    void add(Bean bean);
}
