package com.teqmonic.bookmarker.api;

import com.teqmonic.bookmarker.domain.BookmarkDTO;
import com.teqmonic.bookmarker.domain.BookmarkService;
import com.teqmonic.bookmarker.domain.BookmarksDTO;
import com.teqmonic.bookmarker.domain.CreateBookmarkRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/bookmarks")
public class BookmarkController {

    private final BookmarkService bookmarkService;

    @GetMapping
    public BookmarkDTO getBookmarks(@RequestParam(name = "page", defaultValue = "1") Integer page,
                                    @RequestParam(name = "query", defaultValue = "") String query) {
        // Get bookmarks api
        if(null == query || query.trim().isEmpty()) {
            return bookmarkService.getBookmarks(page);
        }
         // Search api
         return bookmarkService.searchBookmarks(query, page);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BookmarksDTO createBookmark(@Valid @RequestBody CreateBookmarkRequest request) {
       return bookmarkService.createBookmark(request);
    }
}
