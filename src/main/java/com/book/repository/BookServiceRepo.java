package com.book.repository;
import com.book.entity.Book;

import java.util.List;

public interface BookServiceRepo {
	Book save(Book book);
	List<Book> findAll();
	Book fineById(Long id);
}
