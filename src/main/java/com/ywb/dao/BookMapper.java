package com.ywb.dao;

import com.ywb.entity.Book;

public interface BookMapper {
    Book selectBookById();
    void del();
    void update();
    void add();
}
