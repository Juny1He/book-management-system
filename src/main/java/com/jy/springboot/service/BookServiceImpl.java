package com.jy.springboot.service;

import com.jy.springboot.domain.Book;
import com.jy.springboot.domain.BookRepository;
import com.jy.springboot.exception.BookNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import com.jy.springboot.exception.BookNotFoundExcepiton;

/**
 * Created by limi on 2017/8/23.
 */

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    /**
     * 获取一个书单信息
     * @param id
     * @return
     */
    @Override
    public Book getBookById(Long id) {
        Book book = bookRepository.findById(id).orElse(null);
        if (book == null) {
            throw new BookNotFoundException("Book list information not found.");
        }
        return book;
    }
}