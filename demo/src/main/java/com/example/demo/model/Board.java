package com.example.demo.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Board {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long boardId;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(nullable = false, length = 500)
    private String content;

    @Column(nullable = false)
    private String writer;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private LocalDateTime write_time;

    public Board() {
    }

    public Long getBoardId() {
        return boardId;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getWriter() {
        return writer;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getWrite_time() {
        return write_time;
    }

    @PrePersist
    public void insert_time() {
        this.write_time = LocalDateTime.now();
    }

    @PreUpdate
    public void update_time() {
        this.write_time = LocalDateTime.now();
    }
}
