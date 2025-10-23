package br.com.bookfinder.repository;
import br.com.bookfinder.model.Book;
import java.sql.*;
import java.util.List;

public class BookRepository {

    private final String url = "jdbc:oracle:thin:@//oracle.fiap.com.br:1521/orcl";
    private final String user = "rm";
    private final String password = "";

    public void save(Book book) {
        try (Connection conn = DriverManager.getConnection(url, user, password)) {

            String sql = "INSERT INTO books (title, authors, published_date) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, book.getTitle());
            stmt.setString(2, book.getAuthors());
            stmt.setString(3, book.getPublishedDate());
            stmt.executeUpdate();

        } catch (Exception e) {
            System.err.println("Erro ao salvar no banco:");
            e.printStackTrace();
        }
    }
}
