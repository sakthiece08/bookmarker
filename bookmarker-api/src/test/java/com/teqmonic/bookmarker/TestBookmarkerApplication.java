package com.teqmonic.bookmarker;

import org.springframework.boot.SpringApplication;

public class TestBookmarkerApplication {

	public static void main(String[] args) {
		SpringApplication.from(BookmarkerApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
