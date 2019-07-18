package com.book.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.book.entity.Book;
import com.book.repository.BookRepo;
import com.book.repository.BookServiceRepo;
import java.util.List;

@Service
public class BookServiceImpl implements BookServiceRepo {

	@Autowired
	private BookRepo repo;
	@Override
	public Book save(Book book) {
		return repo.save(book);
	}
	@Override
	public List<Book> findAll() {
		return repo.findAll();
	}

	@Override
	public Book fineById(Long id) {
		return repo.getOne(id);
	}

}
