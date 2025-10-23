package br.com.bookfinder.view;
import br.com.bookfinder.model.Book;
import java.util.List;

public class BookView {

    public void showBooks(List<Book> books) {
        if (books.isEmpty()) {
            System.out.println("Nenhum livro encontrado!");
            return;
        }

        System.out.println(" Resultados encontrados:");
        for (int i = 0; i < books.size(); i++) {
            System.out.println((i + 1) + "." + books.get(i));
        }
    }
}

