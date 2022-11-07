package com.dev.bookmarker.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


//  public interface PojoRepository extends JpaRepository<Pojo, Data Type of Primary Key>
public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {


    @Query("SELECT new com.dev.bookmarker.domain.BookmarkDTO(b.id, b.title, b.url, b.createdAt) from Bookmark b")
    Page<BookmarkDTO> getBookmarks(Pageable pageable);
}