package book.book.controller;

import book.book.model.Book;
import book.book.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {

  @Autowired
  private BookService bookService;

  @GetMapping
  public String getAllBooks(Model model) {
    List<Book> books = bookService.getAllBooks();
    model.addAttribute("books", books);
    return "books/index";
  }

  @GetMapping("/new")
  public String createBookForm(Model model) {
    model.addAttribute("book", new Book());
    return "books/create";
  }

  @PostMapping
  public String saveBook(@ModelAttribute Book book) {
    bookService.saveBook(book);
    return "redirect:/books";
  }
}