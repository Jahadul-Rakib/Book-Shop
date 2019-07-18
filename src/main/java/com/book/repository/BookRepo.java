package com.book.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.book.entity.Book;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepo extends JpaRepository<Book, Long> {
	
}
