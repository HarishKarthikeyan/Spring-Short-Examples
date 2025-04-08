package com.jpa.repository;

import com.jpa.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Integer> {
    @Query("""
            SELECT a from Author a WHERE a.id = :id
            """)
    Author findAuthorById(Integer id);

    @Query("select a from Author a")
    List<Author> findAll();
}
