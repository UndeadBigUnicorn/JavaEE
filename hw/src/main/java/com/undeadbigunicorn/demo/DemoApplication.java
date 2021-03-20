package com.undeadbigunicorn.demo;

import com.undeadbigunicorn.demo.domain.entities.BookEntity;
import com.undeadbigunicorn.demo.service.BookService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		ApplicationContext applicationContext = SpringApplication.run(DemoApplication.class, args);

		BookService bookService = applicationContext.getBean(BookService.class);

		BookEntity book1 = BookEntity.builder()
				.title("Kobzar")
				.author("Taras Shevchenko")
				.isbn("1252136126")
				.build();
		bookService.saveBook(book1);

		BookEntity book2 = BookEntity.builder()
				.title("Zapovit")
				.author("Taras Shevchenko")
				.isbn("125515125")
				.build();
		bookService.saveBook(book2);

		BookEntity book3 = BookEntity.builder()
				.title("Hamlet")
				.author("William Shakespeare")
				.isbn("251618124")
				.build();
		bookService.saveBook(book3);

		BookEntity book4 = BookEntity.builder()
				.title("Sonnets")
				.author("William Shakespeare")
				.isbn("8989235982113")
				.build();
		bookService.saveBook(book4);

		BookEntity book5 = BookEntity.builder()
				.title("Romeo and Julie")
				.author("William Shakespeare")
				.isbn("88988912312512")
				.build();
		bookService.saveBook(book5);

		BookEntity book6 = BookEntity.builder()
				.title("Faust")
				.author("Johann Wolfgang von Goethe")
				.isbn("90129521512")
				.build();
		bookService.saveBook(book6);

		BookEntity book7 = BookEntity.builder()
				.title("Der Erlkönig")
				.author("Johann Wolfgang von Goethe")
				.isbn("1157182481287")
				.build();
		bookService.saveBook(book7);

		BookEntity book8 = BookEntity.builder()
				.title("Egmont")
				.author("Johann Wolfgang von Goethe")
				.isbn("8712758172512")
				.build();
		bookService.saveBook(book8);

		BookEntity book9 = BookEntity.builder()
				.title("Dune")
				.author("Frank Herbert")
				.isbn("90980192512532")
				.build();
		bookService.saveBook(book9);

		BookEntity book10 = BookEntity.builder()
				.title("Dune: The Butlerian Jihad")
				.author("Brian Herbert")
				.isbn("6782735832195321")
				.build();
		bookService.saveBook(book10);

		BookEntity book11 = BookEntity.builder()
				.title("God-Emperor of Dune")
				.author("Frank Herbert")
				.isbn("09819872657812")
				.build();
		bookService.saveBook(book11);
	}

}
