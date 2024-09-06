package com.teqmonic.bookmarker.api;

import com.teqmonic.bookmarker.domain.BookmarkEntity;
import com.teqmonic.bookmarker.domain.BookmarkRepository;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import java.time.Instant;
import java.util.List;


import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestPropertySource(properties = {
        "spring.datasource.url=jdbc:tc:postgresql:15.8:///demoTestDB"
})
public class BookmarkControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    BookmarkRepository bookmarkRepository;

    @BeforeEach
    void setUp() {
        // delete records which got inserted from db migration scripts, to get consistent results
        bookmarkRepository.deleteAllInBatch();
        // to assert more test cases
        List<BookmarkEntity> list = List.of(
                BookmarkEntity.builder().title("SpringBlog").url("https://spring.io/blog").createdAt(Instant.now()).build(),
                BookmarkEntity.builder().title("Reddit").url("https://www.reddit.com/r/Blogging/").createdAt(Instant.now()).build(),
                BookmarkEntity.builder().title("GoogleBlog").url("https://blog.google/intl/en-ca/").createdAt(Instant.now()).build(),
                BookmarkEntity.builder().title("Hostinger").url("https://www.hostinger.com/tutorials/blog-examples").createdAt(Instant.now()).build(),
                BookmarkEntity.builder().title("K8s").url("https://kubernetes.io/blog/").createdAt(Instant.now()).build(),
                BookmarkEntity.builder().title("Oracle").url("https://blogs.oracle.com/java/").createdAt(Instant.now()).build(),
                BookmarkEntity.builder().title("Baeldung").url("https://www.baeldung.com/java-blogs").createdAt(Instant.now()).build(),
                BookmarkEntity.builder().title("Medium").url("https://medium.com/").createdAt(Instant.now()).build(),
                BookmarkEntity.builder().title("whizlabs").url("https://www.whizlabs.com/blog/best-java-blogs/").createdAt(Instant.now()).build(),
                BookmarkEntity.builder().title("Microsoft").url("https://tech.feedspot.com/microsoft_blogs/").createdAt(Instant.now()).build());
        bookmarkRepository.saveAll(list);

    }

    @ParameterizedTest
    @CsvSource({
                "1, 10, 2, 1, true, false",
                "2, 10, 2, 2, false, true"
            })
    void shouldGetBookmarks(int pageNo, int totalElements, int totalPages, int currentPage, boolean isFirstPage, boolean isLastPage) throws Exception {
        mvc.perform(get("/api/bookmarks?page="+pageNo))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalElements", CoreMatchers.equalTo(totalElements)))
                .andExpect(jsonPath("$.totalPages", CoreMatchers.equalTo(totalPages)))
                .andExpect(jsonPath("$.currentPage", CoreMatchers.equalTo(currentPage)))
                .andExpect(jsonPath("$.isFirstPage", CoreMatchers.equalTo(isFirstPage)))
                .andExpect(jsonPath("$.isLastPage", CoreMatchers.equalTo(isLastPage)));
    }

    @Test
    void shouldCreateBookmarkSuccessfully() throws Exception {
        mvc.perform(
                post("/api/bookmarks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(
                                """
                                 {
                                    "title": "Test-blog",
                                    "url": "http://test-blog.com"
                                 }
                                """
                        )
                   )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", notNullValue()))
                .andExpect(jsonPath("$.title", is("Test-blog")))
                .andExpect(jsonPath("$.url", is("http://test-blog.com")));
    }

    @Test
    void shouldFailToCreateBookmarkWhenUrlIsNotPresent() throws Exception {
        this.mvc.perform(
                        post("/api/bookmarks")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("""
                {
                    "title": "SivaLabs Blog"
                }
                """)
                )
                .andExpect(status().isBadRequest())
                .andExpect(header().string("Content-Type", is("application/problem+json")))
                .andExpect(jsonPath("$.type", is("https://zalando.github.io/problem/constraint-violation")))
                .andExpect(jsonPath("$.title", is("Constraint Violation")))
                .andExpect(jsonPath("$.status", is(400)))
                .andExpect(jsonPath("$.violations", hasSize(1)))
                .andExpect(jsonPath("$.violations[0].field", is("url")))
                .andExpect(jsonPath("$.violations[0].message", is("Url should not be empty")))
                .andReturn();
    }
}
