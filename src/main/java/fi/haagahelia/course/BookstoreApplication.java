package fi.haagahelia.course;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import fi.haagahelia.course.domain.Book;
import fi.haagahelia.course.domain.BookRepository;



@SpringBootApplication
public class BookstoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	@Bean
	public CommandLineRunner bookDemo(BookRepository repository) {
		return (args) -> {
			Book b1 = (new Book("Stephenie Meyer", "12345", "Midnight Sun", "2013"));
			Book b2 = (new Book("Gary D.", "236547", "Orbiting Jupiter", "2015"));
			Book b3 = (new Book("John Green", "536526", "Paper Towns", "2017"));

			repository.save(b1);
			repository.save(b2);
			repository.save(b3);

		};

		/*
		 * private static final Logger log =
		 * LoggerFactory.getLogger(BookstoreApplication.class);
		 * 
		 * public static void main(String[] args) {
		 * SpringApplication.run(BookstoreApplication.class, args); }
		 * 
		 * @Bean public CommandLineRunner bookstoreDemo(BookRepository repository) {
		 * return (args) -> { log.info("save a couple of books"); repository.save(new
		 * Book("John", "Johnson", "hdgbfjha", "bdkb")); repository.save(new
		 * Book("Katy", "Kateson", "kate", "svdhg"));
		 * 
		 * log.info("fetch all books"); for (Book book : repository.findAll()) {
		 * log.info(book.toString()); }
		 * 
		 * };
		 */
	}
}

