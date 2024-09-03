package com.teqmonic.bookmarker.domain;

import org.springframework.stereotype.Component;

@Component
public class BookmarkMapper {

 protected BookmarksDTO convertToBookmark(BookmarkEntity entity) {
     return BookmarksDTO.builder().url(entity.getUrl()).title(entity.getTitle()).id(entity.getId()).build();
 }
}
