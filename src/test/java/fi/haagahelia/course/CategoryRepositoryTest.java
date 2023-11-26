package fi.haagahelia.course;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import fi.haagahelia.course.domain.Category;
import fi.haagahelia.course.domain.CategoryRepository;

@SpringBootTest(classes = BookstoreApplication.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CategoryRepositoryTest {
	
	
	@Autowired
	private CategoryRepository crepository;
	
	@Test
	public void createNewCategory(){
		Category category = new Category("Mystery");
		crepository.save(category);
		
		Category savedCategory = crepository.findByName("Mystery").get(0);

        assertThat(savedCategory).isNotNull();
        assertThat(savedCategory.getName()).isEqualTo("Mystery");
   }
	
	@Test
	public void deleteNewCategory() {
		Category category = new Category("Biography");
		crepository.save(category);
		crepository.delete(category);
		

		List <Category> deletedCategory = crepository.findByName("Biography");
		
		assertThat(deletedCategory).isEmpty();
	}
	
	@Test
	public void findCategoryByName() {
		List <Category> category = crepository.findByName("Romance");
		
		assertThat(category).hasSize(1);
		assertThat(category.get(0).getName()).isEqualTo("Romance");
	}
	
}
