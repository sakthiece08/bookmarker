package com.teqmonic.bookmarker.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class BookmarkDTO {

    private List<Bookmark> data;
    private long totalElements;
    private int totalPages;
    private int currentPage;
    @JsonProperty(value = "isFirstPage")
    private boolean isFirstPage;
    @JsonProperty(value = "isLastPage")
    private boolean isLastPage;
    private boolean hasPrev;
    private boolean hasNext;

    public BookmarkDTO(Page<Bookmark> page) {
        this.setData(page.getContent());
        this.setTotalElements(page.getTotalElements());
        this.setTotalPages(page.getTotalPages());
        this.setCurrentPage(page.getNumber()+1);
        this.setFirstPage(page.isFirst());
        this.setLastPage(page.isLast());
        this.setHasPrev(page.hasPrevious());
        this.setHasNext(page.hasNext());
    }
}
