package com.teqmonic.bookmarker.domain;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CreateBookmarkRequest {
    @NotEmpty(message = "Title should not be empty")
    @Size(max = 30)
    private String title;

    @NotEmpty(message = "Url should not be empty")
    @Size(max = 50)
    private String url;
}
