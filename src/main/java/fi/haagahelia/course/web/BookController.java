package fi.haagahelia.course.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fi.haagahelia.course.domain.*;

@Controller
public class BookController {
	@Autowired
	private BookRepository repository;

	@RequestMapping("/booklist")
	public String bookList(Model model) {

		model.addAttribute("books", repository.findAll());

		return "list";
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String deleteBook(@PathVariable("id") Long bookId, Model model) {
		repository.deleteById(bookId);
		return "redirect:../booklist";
	}

	@RequestMapping(value = "/add")
	public String addBook(Model model) {
		model.addAttribute("book", new Book());
		return "addbook";
	}

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

	

}
