package fi.haagahelia.course;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import fi.haagahelia.course.domain.Book;
import fi.haagahelia.course.domain.BookRepository;
import fi.haagahelia.course.domain.Category;
import fi.haagahelia.course.domain.CategoryRepository;

@SpringBootApplication
public class BookstoreApplication {
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	@Bean
	public CommandLineRunner bookDemo(BookRepository repository, CategoryRepository crepository) {
		return (args) -> {
			log.info("save a couple of books");

			crepository.save(new Category("Romance"));
			crepository.save(new Category("Fantasy"));
			crepository.save(new Category("Young-Adult"));
			crepository.save(new Category("Science Fi"));

			repository.save(new Book("Stephenie Meyer", "12345", "Midnight Sun", "2013",
					crepository.findByName("Fantasy").get(0)));
			repository.save(new Book("Gary D.", "236547", "Orbiting Jupiter", "2015",
					crepository.findByName("Young-Adult").get(0)));
			repository.save(
					new Book("John Green", "536526", "Paper Towns", "2017", crepository.findByName("Romance").get(0)));

			log.info("fetch all students");
			for (Book book : repository.findAll()) {
				log.info(book.toString());
			}
		};
	}
}
