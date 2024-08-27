package com.teqmonic.bookmarker.initializer;

import com.teqmonic.bookmarker.domain.Bookmark;
import com.teqmonic.bookmarker.domain.BookmarkRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataInitializer implements CommandLineRunner {

    private final BookmarkRepository bookmarkRepository;

    // Remove inserting sample data as it is handled by Flyway migration script

    @Override
    public void run(String... args) throws Exception {
//       List<Bookmark> list = List.of(
//        Bookmark.builder().title("SpringBlog").url("https://spring.io/blog").createdAt(Instant.now()).build(),
//                Bookmark.builder().title("Reddit").url("https://www.reddit.com/r/Blogging/").createdAt(Instant.now()).build(),
//                Bookmark.builder().title("GoogleBlog").url("https://blog.google/intl/en-ca/").createdAt(Instant.now()).build(),
//                Bookmark.builder().title("Hostinger").url("https://www.hostinger.com/tutorials/blog-examples").createdAt(Instant.now()).build(),
//                Bookmark.builder().title("K8s").url("https://kubernetes.io/blog/").createdAt(Instant.now()).build(),
//                Bookmark.builder().title("Oracle").url("https://blogs.oracle.com/java/").createdAt(Instant.now()).build(),
//                Bookmark.builder().title("Baeldung").url("https://www.baeldung.com/java-blogs").createdAt(Instant.now()).build(),
//                Bookmark.builder().title("Medium").url("https://medium.com/").createdAt(Instant.now()).build(),
//                Bookmark.builder().title("whizlabs").url("https://www.whizlabs.com/blog/best-java-blogs/").createdAt(Instant.now()).build(),
//                Bookmark.builder().title("Microsoft").url("https://tech.feedspot.com/microsoft_blogs/").createdAt(Instant.now()).build());
//        bookmarkRepository.saveAll(list);
//        log.info("Saved Bookmarks into the table");

    }
}
