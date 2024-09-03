package com.teqmonic.bookmarker.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BookmarkRepository extends JpaRepository<BookmarkEntity, Long> {

    @Query("select new com.teqmonic.bookmarker.domain.BookmarksDTO(b.id, b.title, b.url) from BookmarkEntity b")
    Page<BookmarksDTO> findBookmarks(Pageable pageable);
}
