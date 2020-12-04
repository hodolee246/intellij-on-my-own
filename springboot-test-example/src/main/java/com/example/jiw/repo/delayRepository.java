package com.example.jiw.repo;

import org.springframework.stereotype.Repository;

@Repository
public class delayRepository {
    public delayRepository() {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
