package com.teqmonic.bookmarker.domain;

import jakarta.persistence.Column;
import lombok.*;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class BookmarksDTO {
    private Long id;
    private String title;
    private String url;
}
