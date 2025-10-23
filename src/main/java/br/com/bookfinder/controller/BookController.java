package br.com.bookfinder.controller;

import br.com.bookfinder.service.BookService;
import br.com.bookfinder.repository.BookRepository;
import br.com.bookfinder.view.BookView;
import br.com.bookfinder.model.Book;
import java.util.List;
import java.util.Scanner;

public class BookController {

    private final BookService service = new BookService();
    private final BookRepository repository = new BookRepository();
    private final BookView view = new BookView();
    private final Scanner sc = new Scanner(System.in);

    public void search(String query) {
        List<Book> books = service.searchBooks(query);
        view.showBooks(books);

        if (books.isEmpty()) return;

        System.out.print("Digite o número do livro que deseja salvar (0 para nenhum): ");
        int escolha = sc.nextInt();
        sc.nextLine();

        if (escolha > 0 && escolha <= books.size()) {
            Book selecionado = books.get(escolha - 1);
            repository.save(selecionado);
            System.out.println("Livro salvo no banco: " + selecionado.getTitle());
        } else {
            System.out.println("Nenhum livro foi salvo.");
        }
    }
}

