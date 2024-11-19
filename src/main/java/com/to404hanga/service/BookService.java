package com.to404hanga.service;

import com.to404hanga.domain.Book;
import com.to404hanga.domain.User;
import com.to404hanga.entity.PageResult;

public interface BookService {
    public PageResult selectNewBooks(int pageNum, int PageSize);

    public Book findById(String id);

    public Integer borrowBook(Book book);

    public PageResult search(Book book, int pageNum, int pageSize);

    public Integer addBook(Book book);

    public Integer editBook(Book book);

    //分页条件查询当前借阅信息
    public PageResult searchBorrowed(Book book, User user, Integer pageNum, Integer pageSize);

    public boolean returnBook(String id, User user);

    //确认归还
    public boolean confirmBook(String id);
}
