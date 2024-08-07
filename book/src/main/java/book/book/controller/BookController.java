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
    model.addAttribute("newBook", new Book());
    return "books/index";
  }

  @PostMapping
  public String saveBook(@ModelAttribute Book book) {
    bookService.saveBook(book);
    return "redirect:/books";
  }

  @GetMapping("/{id}")
  public String getBookById(@PathVariable Long id, Model model) {
    Book book = bookService.getBookById(id);
    model.addAttribute("book", book);
    return "books/show";
  }

  @GetMapping("/{id}/edit")
  public String editBook(@PathVariable Long id, Model model) {
    Book book = bookService.getBookById(id);
    model.addAttribute("book", book);
    return "books/edit";
  }

  @PostMapping("/{id}")
  public String updateBook(@PathVariable Long id, @ModelAttribute Book book) {
    book.setId(id);
    bookService.saveBook(book);
    return "redirect:/books";
  }

  @PostMapping("/{id}/delete")
  public String deleteBook(@PathVariable Long id) {
    bookService.deleteBook(id);
    return "redirect:/books";
  }

}