package com.teqmonic.bookmarker.api;

import com.teqmonic.bookmarker.domain.BookmarkDTO;
import com.teqmonic.bookmarker.domain.BookmarkService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class BookmarkController {

    private final BookmarkService bookmarkService;

    @GetMapping(value = "/bookmarks")
    public BookmarkDTO getBookmarks(@RequestParam(name = "page", defaultValue = "1") Integer page,
                                    @RequestParam(name = "query", defaultValue = "") String query) {
        // Get bookmarks api
        if(null == query || query.trim().isEmpty()) {
            return bookmarkService.getBookmarks(page);
        }
         // Search api
         return bookmarkService.searchBookmarks(query, page);
    }
}
