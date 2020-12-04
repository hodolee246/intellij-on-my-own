package com.example.jiw.repo;

import com.example.jiw.vo.Bean;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class SimpleBeanRepository implements BeanRepository {

    private Map<String, Bean> beanMap = new HashMap<>();

    @Override
    public Bean findByName(String name) {
        return beanMap.get(name);
    }

    @Override
    public void add(Bean bean) {
        beanMap.put(bean.getName(), bean);
    }
}
