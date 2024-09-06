package com.teqmonic.bookmarker.domain;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class BookmarkService {

    private final BookmarkRepository bookmarkRepository;
    private final BookmarkMapper bookmarkMapper;

    @Transactional(readOnly = true)
    public BookmarkDTO getBookmarks(Integer page){
        int pageNo = page < 0 ? 0 : page-1;
        Pageable pageable = PageRequest.of(pageNo, 8, Sort.Direction.DESC, "createdAt");
       // return new BookmarkDTO(bookmarkRepository.findAll(pageable).map(bookmarkMapper::convertToBookmark));
        return new BookmarkDTO(bookmarkRepository.findBookmarks(pageable));
    }

    @Transactional(readOnly = true)
    public BookmarkDTO searchBookmarks(String query, Integer page) {
        int pageNo = page < 0 ? 0 : page-1;
        Pageable pageable = PageRequest.of(pageNo, 8, Sort.Direction.DESC, "createdAt");
        return new BookmarkDTO(bookmarkRepository.findByTitleContainsIgnoreCase(query, pageable));
    }

    public BookmarksDTO createBookmark(CreateBookmarkRequest request) {
        BookmarkEntity entity = bookmarkMapper.convertToEntity(request);
        BookmarkEntity responseEntity = bookmarkRepository.save(entity);
        return bookmarkMapper.convertToBookmark(responseEntity);
    }
}
