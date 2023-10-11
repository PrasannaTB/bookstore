package fi.haagahelia.course;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import fi.haagahelia.course.domain.Book;
import fi.haagahelia.course.domain.BookRepository;
import fi.haagahelia.course.domain.Category;
import fi.haagahelia.course.domain.CategoryRepository;

@SpringBootTest(classes = BookstoreApplication.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class BookRepositoryTest {
	
	@Autowired
	private BookRepository repository;
	
	@Autowired
	private CategoryRepository crepository;
	
	@Test
	public void createNewBook() {
		Category category = new Category("Self-help");
		crepository.save(category);
		Book book = new Book ("Robin Sharma", "7687464", "A monk who sold his ferrari", "2017", category);
		repository.save(book);
		assertThat(book.getId()).isNotNull();
	}
	
	@Test
	public void deleteNewBook() {
		List<Book> books = repository.findByTitle("Paper Towns");
		Book book = books.get(0);
		repository.delete(book);
		List<Book> newBook = repository.findByTitle("Paper Towns");
		assertThat(newBook).hasSize(0);
		
	}
	
	@Test
	public void findByTitleShouldReturnBook() {
		
		List<Book> books = repository.findByTitle("Orbiting Jupiter");
		
		assertThat(books).hasSize(1);
		assertThat(books.get(0).getAuthor()).isEqualTo("Gary D.");
	}
	
	@Test
	public void findByTitle() {
		
		List<Book> books = repository.findByTitle("Paper Towns");
		
		assertThat(books).hasSize(1);
		assertThat(books.get(0).getAuthor()).isEqualTo("John Green");
	}

	

}
