package fi.haagahelia.course.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import fi.haagahelia.course.domain.*;

@Controller
public class BookController {
	@Autowired
	private BookRepository repository;

	@Autowired
	private CategoryRepository crepository;

	@RequestMapping(value = "/login")
	public String login() {
		return "login";
	}

	// Show all students
	@RequestMapping("/booklist")
	public String bookList(Model model) {

		model.addAttribute("books", repository.findAll());

		return "list";
	}

	// RESTful service to get all books
	@RequestMapping(value = "/books", method = RequestMethod.GET)
	public @ResponseBody List<Book> bookListRest() {
		return (List<Book>) repository.findAll();
	}

	// RESTful service to get book by id
	@RequestMapping(value = "/book/{id}", method = RequestMethod.GET)
	public @ResponseBody Optional<Book> findBooktRest(@PathVariable("id") Long id) {
		return repository.findById(id);
	}

	// Delete student
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String deleteBook(@PathVariable("id") Long bookId, Model model) {
		repository.deleteById(bookId);
		return "redirect:../booklist";
	}

	// Add new student
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/add")
	public String addBook(Model model) {
		model.addAttribute("book", new Book());
		model.addAttribute("category", crepository.findAll());
		return "addbook";
	}

	// Save new student
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(Book book) {
		repository.save(book);
		return "redirect:booklist";
	}

	@RequestMapping(value = "/edit")
	public String editBook(Long id, Model model) {
		Book book = repository.findById(id).orElse(null);
		if (book != null) {
			model.addAttribute("book", book);
			model.addAttribute("category", crepository.findAll());

			return "editbook";
		} else {
			// Handle the case where the book with the given ID doesn't exist
			return "redirect:/booklist";
		}
	}

	@PostMapping("/edit/{id}")
	public String editBookSubmit(@PathVariable("id") Long bookId, @ModelAttribute Book editedBook) {

		editedBook.setId(bookId);
		repository.save(editedBook);
		return "redirect:/booklist";
	}

	/*
	 * 
	 * @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET) public
	 * String editBook(@PathVariable("id") Long id, Model model) {
	 * model.addAttribute("book", repository.findById(id));
	 * model.addAttribute("category", crepository.findAll()); return "editbook"; }
	 */

}
